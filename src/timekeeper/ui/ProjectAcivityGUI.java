/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeper.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import static timekeeper.ui.MainMenuGUI.WIDTH;

/**
 *
 * @author DragonSheep
 */
public class ProjectAcivityGUI extends JFrame implements TimeKeeperUI{
    
    static final int WIDTH = 250;
    static final int HEIGHT = 150;
    
    public ProjectAcivityGUI(
            ResourceBundle bundle,
            String username,
            ProjectActivityRegistrar activityRecorder,
            NextStepHandler canceler) {
        super("Title"/*bundle.getString("title")*/); //TODO: create resource bundle to handle gui; remove hardcoding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout());
        JLabel lblProgramPrompt = new JLabel("Program:"/*bundle.getString("programPrompt")*/);
        mainPanel.add(lblProgramPrompt, "span 2");
        JTextField tfProject = new JTextField(10);
        mainPanel.add(tfProject, "wrap");
        JCheckBox ckbSigningIn = new JCheckBox();
        mainPanel.add(ckbSigningIn);
        JLabel lblActivityPrompt = 
                new JLabel("Checking in"/*bundle.getString("activityPrompt")*/);
        mainPanel.add(lblActivityPrompt, "span 2, wrap");
        JButton btnCancel = new JButton(/*bundle.getString(
            "cancelPrompt")*/);
        btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canceler.nextStep();
                }
            
            });
        mainPanel.add(btnCancel);
        JButton btnSend = new JButton(/*bundle.getString(
            "cancelPrompt")*/);
        btnSend.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //TODO: Perform save
                }
            
            });
        mainPanel.add(btnSend);
        
        
        
        
        
        this.add(mainPanel);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void display() {
        this.setVisible(true);
    }
    
    
}
