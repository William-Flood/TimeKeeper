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
    public boolean RecordActivity(Project project,  
            String typeOfActivity);
}
