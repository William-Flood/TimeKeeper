/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeper.ui;

import java.util.ResourceBundle;

/**
 *
 * @author k0513525
 */
public class UIFactory {
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
            throw new IllegalArgumentException();
        }
    }
            
}
