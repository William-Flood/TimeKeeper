/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeperone.data;
import flood.util.StringUtil;

/**
 *
 * @author k0513525
 */
public class Project {
  String projectID;
  String activeFlag;
  String name;
  String description;

    public String getProjectID() {
        return projectID;
    }

    public String getActiveFlag() {
        return activeFlag;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

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

  /*public Project (String projectID, String activeFlag, String name, String description) {
  }*/
}
