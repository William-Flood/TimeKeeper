/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeper.ui;

import timekeepertwo.data.Person;
import timekeepertwo.data.Project;

/**
 *
 * @author DragonSheep
 */
public interface ProjectActivityRegistrar {
    
    /**
     * Calls a controller to write a record of project activity
     * @param project A project to record activity on
     * @param typeOfActivity The type of activity to record(Starting or stopping)
     * @return 
     */
    public void RecordActivity(Project project,  
            String typeOfActivity);
}
