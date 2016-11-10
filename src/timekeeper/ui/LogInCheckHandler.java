/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeper.ui;

/**
 * Used to allow a user to log into the application
 * @author DragonSheep
 */
public interface LogInCheckHandler {
    
    /**
     * Used to allow a user to log into the application
     * @param name The user ID
     * @param password The user's password
     * @return 
     */
    boolean checkLogIn(String name, String password);
}
