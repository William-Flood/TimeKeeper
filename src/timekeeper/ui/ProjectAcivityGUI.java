/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeper.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import static timekeeper.ui.MainMenuGUI.WIDTH;
import timekeepertwo.data.Project;

/**
 *
 * @author DragonSheep
 */
public class ProjectAcivityGUI extends JFrame implements TimeKeeperUI{
    
    static final int WIDTH = 300;
    static final int BASE_HEIGHT = 170;
    static final int HEIGHT_SCALE = 20;
    
    /**
     *
     * @param bundle
     * @param username
     * @param availableProjects
     */
    public ProjectAcivityGUI(
            ResourceBundle bundle,
            Project[] availableProjects,
            ProjectActivityRegistrar activityRecorder,
            NextStepHandler canceler) {
        super("Title"/*bundle.getString("title")*/); //TODO: create resource bundle to handle gui; remove hardcoding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout());
        JLabel lblProgramPrompt = new JLabel("Program:"/*bundle.getString("projectPrompt")*/);
        mainPanel.add(lblProgramPrompt, "wrap");
        String[] projectTableHeaders = {
            "Project ID"/*bundle.getString("projectID")*/,
        "Name"/*bundle.getString("projectName")*/};
        JTable tblProject = new JTable(tablefyProjects(availableProjects), 
                projectTableHeaders);
        
        //Causes the second column on tblProject to have a preferred width
        //of 250 units.
        tblProject.getColumnModel().getColumn(1).setPreferredWidth(250);
        
        //Adds tblProject and its header to mainPanel, causing it to take up two
        //columns and cause the next added control to move to the next row.
        mainPanel.add(tblProject.getTableHeader(), "span 2, wrap");
        mainPanel.add(tblProject, "span 2, wrap");
        //A checkbox to allow the user to indicate if one is starting or
        //ending work on a project
        JCheckBox ckbSigningIn = new JCheckBox();
        mainPanel.add(ckbSigningIn);
        
        //A text prompt requesting information as to whether the user is
        //beginning or ending project work
        JLabel lblActivityPrompt = 
                new JLabel("Checking in"/*bundle.getString("activityPrompt")*/);
        
        //Adds lblActivityPrompt to mainPanel, causing it to take up two
        //columns and cause the next added control to move to the next row.
        mainPanel.add(lblActivityPrompt, "span 2, wrap");
        JButton btnCancel = new JButton("Cancel"/*bundle.getString(
            "cancelPrompt")*/);
        btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canceler.nextStep();
                    
                    ProjectAcivityGUI.this.dispatchEvent(
                        new WindowEvent(ProjectAcivityGUI.this, 
                                WindowEvent.WINDOW_CLOSING));
                }
            
            });
        mainPanel.add(btnCancel);
        
        //lblResponse has to be declared before the btnSend action listener
        //is added, so the action listener can use it.
        JLabel lblResponse = 
                new JLabel();
        JButton btnSend = new JButton("Record"/*bundle.getString(
            "cancelPrompt")*/);
        btnSend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedProject = tblProject.getSelectedRow();
                    //selectedProject will be -1 if no project was selected
                    //in the table
                    if(selectedProject >= 0){
                        Project project = 
                            availableProjects[selectedProject];
                        //Sets the value of typeOfActivity to "S" when the
                        //checkbox is marked, and to "E" otherwise.
                        String typeOfActivity = ckbSigningIn.isSelected()?
                             "S":"E";   
                        if(activityRecorder.RecordActivity(project, 
                            typeOfActivity)){
                            lblResponse.setText("Record saved!");
                        } else {
                            lblResponse.setText("Sad face :,(");
                        }
                    } else {
                        //No project was selected
                        lblResponse.setText("Please actually select a project");
                    }
                }
            
            });
        //Place btnSend at the end of its row on the panel.
        mainPanel.add(btnSend, "wrap");
        mainPanel.add(lblResponse);
        
        
        
        
        this.add(mainPanel);
        
        //Height of window should scale with the size of the table;
        this.setSize(WIDTH, BASE_HEIGHT + 
                HEIGHT_SCALE*availableProjects.length);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Allows an object to make this UI visible
     */
    @Override
    public void display() {
        this.setVisible(true);
    }
    
    /**
     * Creates a data source for a JTable based on an array of Project objects
     * @param availableProjects An array of Project objects to populate a
     * JTable with
     * @return A valid data source for a JTable
     */
    private Object[][] tablefyProjects(Project[] availableProjects) {
        Object[][] returnTable = new Object[availableProjects.length][3];
        int i = 0;
        for(Project availableProject:availableProjects) {
            returnTable[i][0] = availableProject.getProjectID();
            returnTable[i][1] = availableProject.getName();
            i++;
        }
        return returnTable;
    }
    
    
}
