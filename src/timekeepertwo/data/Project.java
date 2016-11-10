/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeepertwo.data;
import flood.util.StringUtil;
/**
 * A project tracked by the application
 * @author DragonSheep
 */
public class Project {
  String projectID;
  String activeFlag;
  String name;
  String description;

  /**
   * 
   * @return The ID associated with the project
   */
    public String getProjectID() {
        return projectID;
    }

    /**
     * 
     * @return An indication of if the project is active
     */
    public String getActiveFlag() {
        return activeFlag;
    }

    /**
     * 
     * @return The name of the project
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return A description of the project
     */
    public String getDescription() {
        return description;
    }

    /**
     * Creates a project object
     * @param projectID The ID associated with the project
     * @param activeFlag An indication of if the project is active
     * @param name The name of the project
     * @param description  A description of the project
     */
    public Project(String projectID, String activeFlag, String name, String description) {
        if(StringUtil.isInteger(projectID)) {
            this.projectID = projectID;
        }
        else {
            throw new IllegalArgumentException();
        }
        this.activeFlag = activeFlag;
        this.name = name;
        this.description = description;
    }
    
    @Override
    public String toString() {
        return getProjectID()+", "+getName()+", "+getActiveFlag()+
                ", "+getDescription();
    }

  /*public Project (String projectID, String activeFlag, String name, String description) {
  }*/
}
