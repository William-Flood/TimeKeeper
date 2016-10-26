/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeper.ui;

/**
 *
 * @author DragonSheep
 */
public interface ProjectActivityRegistrar {
    public boolean RecordActivity(String username, 
            String projectID, 
            String typeOfActivity);
}
