/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeper.ui;

/**
 * An option to place within a menu
 * @author DragonSheep
 */
public class MenuOption {
    
    /**
     * The label in a resource bundle to access the text to associate
     * with the option
     */
    private String optionTextLabel;
    
    /**
     * The function to call when the button is pressed
     */
    private NextStepHandler methodCall;

    /**
     * @return the optionTextLabel
     */
    public String getOptionTextLabel() {
        return optionTextLabel;
    }

    /**
     * @param optionTextLabel the optionTextLabel to set
     */
    public void setOptionTextLabel(String optionTextLabel) {
        this.optionTextLabel = optionTextLabel;
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
     * @param optionTextLabel The label in a resource bundle 
     * to access the text to text to associate with the option
     * @param methodCall The function to call when the option is selected
     */
    public MenuOption(String optionTextLabel, NextStepHandler methodCall) {
        this.optionTextLabel = optionTextLabel;
        this.methodCall = methodCall;
    }
    
    
}
