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
        tblProject.getColumnModel().getColumn(1).setPreferredWidth(250);
        mainPanel.add(tblProject.getTableHeader(), "span 2, wrap");
        mainPanel.add(tblProject, "span 2, wrap");
        JCheckBox ckbSigningIn = new JCheckBox();
        mainPanel.add(ckbSigningIn);
        JLabel lblActivityPrompt = 
                new JLabel("Checking in"/*bundle.getString("activityPrompt")*/);
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
        JLabel lblResponse = 
                new JLabel();
        JButton btnSend = new JButton("Record"/*bundle.getString(
            "cancelPrompt")*/);
        btnSend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedProject = tblProject.getSelectedRow();
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
                            lblResponse.setText("Sad face");
                        }
                    } else {
                        lblResponse.setText("Please actually select a project");
                    }
                }
            
            });
        mainPanel.add(btnSend, "wrap");
        mainPanel.add(lblResponse);
        
        
        
        
        this.add(mainPanel);
        this.setSize(WIDTH, BASE_HEIGHT + 
                HEIGHT_SCALE*availableProjects.length);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void display() {
        this.setVisible(true);
    }
    
    private Object[][] tablefyProjects(Project[] availableProjects) {
        Object[][] returnTable = new Object[availableProjects.length][3];
        int i = 0;
        for(Project availableProject:availableProjects) {
            returnTable[i][0] = availableProject.getProjectID();
            returnTable[i][1] = availableProject.getName();
            //returnTable[i][2] = availableProject.getActiveFlag();
            //returnTable[i][2] = availableProject.getDescription();
            i++;
        }
        return returnTable;
    }
    
    
}
