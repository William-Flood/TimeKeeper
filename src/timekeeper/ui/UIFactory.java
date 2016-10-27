/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeper.ui;

import java.util.ResourceBundle;
import timekeepertwo.data.Project;

/**
 * A factory class to generate user interface elements
 * @author k0513525
 */
public class UIFactory {
    /**
     * Generates a user interface to log into the app
     * @param typeToMake Indicates which type of UI to generate
     * @param bundle A resource bundle used to pass in text for the UI
     * @param logInCheckHandler A LogInCheckHandler instance used to
     * log into attempt logging into the system
     * @param cancelHandler A Next Step Handler instance to be used if
     * the user cancels log in
     * @param toMenuHandler A Next Step Handler instance to be used after
     * a successful log in
     * @return The user interface object
     */
    public static TimeKeeperUI makeLogInUI(UIType typeToMake,
            ResourceBundle bundle, 
            LogInCheckHandler logInCheckHandler,
            NextStepHandler cancelHandler,
            NextStepHandler toMenuHandler) {
        if(UIType.GUI == typeToMake) {
            return new LogInGUI(bundle,
                    logInCheckHandler,
                    cancelHandler,
                    toMenuHandler
            );
        }
        else {
            throw new IllegalArgumentException("Invalid UI Type");
        }
    }
    
    /**
     * Generates a user interface element used to allow a user to select
     * from a list of functionality
     * @param typeToMake Indicates which type of UI should be created
     * @param bundle A resource bundle used to pass in text for the UI
     * @param username The name of the current user
     * @param menuOptions A list of options
     * @return The created user interface
     */
    public static TimeKeeperUI makeMainMenuUI(UIType typeToMake,
            ResourceBundle bundle,
            String username,
            MenuOption[] menuOptions) {
        if(UIType.GUI == typeToMake) {
            return new MainMenuGUI(bundle, username, menuOptions);
        }
        else {
            throw new IllegalArgumentException("Invalid UI Type");
        }
    }
    
    /**
     * Generates a user interface meant to allow recording of
     * project activity
     * @param typeToMake Indicates which type of UI should be created
     * @param bundle  A resource bundle used to pass in text for the UI
     * @param availableProjects A list of available projects
     * @param registrar A function used to pass a project to record
     * activity on and the type of activity to record
     * @param canceler A function controlling what happens when 'cancel'
     * is selected
     * @return 
     */
    public static TimeKeeperUI makeProjectActivityUI(UIType typeToMake,
            ResourceBundle bundle,
            Project[] availableProjects,
            ProjectActivityRegistrar registrar,
            NextStepHandler canceler) {
        if(UIType.GUI == typeToMake) {
            return new ProjectAcivityGUI(bundle, 
                    availableProjects,
                    registrar,
                    canceler);
        }
        else {
            throw new IllegalArgumentException("Invalid UI Type");
        }
    }
            
}
