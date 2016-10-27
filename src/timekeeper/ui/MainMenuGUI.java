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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author wifoo
 */
public class MainMenuGUI extends JFrame implements TimeKeeperUI{
    
    static final int WIDTH = 250;
    static final int START_HEIGHT = 40;
    static final int HEIGHT_SCALE = 60;
    
    /**
     * 
     * @param bundle The resource bundle to take text from
     * @param username The name of the current user
     * @param menuOptions A list of options to make avaliable through the menu
     */
    public MainMenuGUI(
            ResourceBundle bundle,
            String username,
            MenuOption[] menuOptions) {
        super(bundle.getString("title"));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new MigLayout());
        JLabel welcomePanel = new JLabel(bundle.getString("welcome")+username);
        mainPanel.add(welcomePanel, "wrap");
        
        //Adds all relevant buttons to the menu
        for(MenuOption menuOption:menuOptions) {
            JButton optionButton = new JButton(bundle.getString(
            menuOption.getOptionTextLabel()));
            optionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuOption.getMethodCall().nextStep();
                    
                    //Currently, there is no reason to keep the main menu
                    //open after any option is selected.
                    MainMenuGUI.this.dispatchEvent(
                        new WindowEvent(MainMenuGUI.this, 
                                WindowEvent.WINDOW_CLOSING));
                }
            
            });
            mainPanel.add(optionButton, "wrap");
        }
        
        this.add(mainPanel);
        //The window height should scale with the number of buttons added
        this.setSize(WIDTH, START_HEIGHT + HEIGHT_SCALE*menuOptions.length);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }
    
    /**
     * Allows other objects to display this window.
     */
    public void display() {
        this.setVisible(true);
    }
}
