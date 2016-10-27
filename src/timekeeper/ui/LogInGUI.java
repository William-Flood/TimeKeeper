/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeper.ui;

import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author k0513525
 */
public class LogInGUI extends JFrame implements TimeKeeperUI {
    //public boolean running;
    
    public LogInGUI(ResourceBundle bundle, 
            LogInCheckHandler logInCheckHandler,
            NextStepHandler cancelHandler,
            NextStepHandler toMenuHandler) {
        super();
        final int FRAME_HEIGHT = 150;
        final int FRAME_WIDTH = 350;
        //JFrame logInFrame = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout());
        JLabel lblUserName = new JLabel(bundle.getString("userNameLabel"));
        mainPanel.add(lblUserName);
        JTextField tfLogIn = new JTextField(20);
        mainPanel.add(tfLogIn, "span 2, wrap");
        //New row
        JLabel lblpassword = new JLabel(bundle.getString("passwordLabel"));
        mainPanel.add(lblpassword);
        JPasswordField pfPassword = new JPasswordField(20);
        mainPanel.add(pfPassword, "span 2, wrap");
        //new row
        JLabel lblError = new JLabel();
        mainPanel.add(lblError);
        JButton btnCancel = new JButton(bundle.getString("cancelLabel"));
        btnCancel.addActionListener((ActionEvent e)-> {
            //running = false;
            cancelHandler.nextStep();
            this.dispatchEvent(
                    new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        mainPanel.add(btnCancel);
        JButton btnLogIn = new JButton(bundle.getString("logInLabel"));
        btnLogIn.addActionListener((ActionEvent e)-> {
            if(logInCheckHandler.checkLogIn(tfLogIn.getText(),
                    new String(pfPassword.getPassword()))) {
                //running = false;
                toMenuHandler.nextStep();
                this.setDefaultCloseOperation(
                        WindowConstants.DISPOSE_ON_CLOSE);
                this.dispatchEvent(
                        new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            } else {
                lblError.setText(bundle.getString("logInFailLabel"));
            }
        });
        mainPanel.add(btnLogIn);
        this.add(mainPanel);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
    }
    
    public void display() {
        this.setVisible(true);
    }
}
