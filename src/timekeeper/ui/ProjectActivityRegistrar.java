/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeper.ui;

import timekeepertwo.data.Project;

/**
 * Sends a project and activity to a record keeper.
 * @author DragonSheep
 */
public interface ProjectActivityRegistrar {
    
    /**
     * Calls a controller to write a record of project activity
     * @param project A project to record activity on
     * @param typeOfActivity The type of activity to record(Starting or stopping)
     * @return 
     */
    public long RecordActivity(Project project,  
            String typeOfActivity);
}
