/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeper.ui;

/**
 *
 * @author wifoo
 */
public class MenuOption {
    
    /**
     * The label in a resource bundle to access the text to put on a button
     */
    private String buttonTextOption;
    
    /**
     * The function to call when the button is pressed
     */
    private NextStepHandler methodCall;

    /**
     * @return the buttonTextOption
     */
    public String getButtonTextOption() {
        return buttonTextOption;
    }

    /**
     * @param buttonTextOption the buttonTextOption to set
     */
    public void setButtonTextOption(String buttonTextOption) {
        this.buttonTextOption = buttonTextOption;
    }

    /**
     * @return the methodCall
     */
    public NextStepHandler getMethodCall() {
        return methodCall;
    }

    /**
     * @param methodCall the methodCall to set
     */
    public void setMethodCall(NextStepHandler methodCall) {
        this.methodCall = methodCall;
    }

    /**
     * 
     * @param buttonTextOption The label in a resource bundle 
     * to access the text to put on a button
     * @param methodCall The function to call when the button is pressed
     */
    public MenuOption(String buttonTextOption, NextStepHandler methodCall) {
        this.buttonTextOption = buttonTextOption;
        this.methodCall = methodCall;
    }
    
    
}
