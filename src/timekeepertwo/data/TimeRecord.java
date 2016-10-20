/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeepertwo.data;
import timekeepertwo.data.Person;
import java.time.LocalDateTime;
import flood.util.StringUtil;
import timekeepertwo.data.Project;
/**
 *
 * @author k0513525
 */
public class TimeRecord {
    
  Person person;
  Project project;
  /**
   * Indicates if the person is starting or stopping a work session.
   */
  String startOrStop;
  LocalDateTime dateAndTime;

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public Person getPerson() {
        return person;
    }

    public Project getProject() {
        return project;
    }

    public String getStartOrStop() {
        return startOrStop;
    }
    
    
  
  public TimeRecord (Project project, Person person, String startOrStop, LocalDateTime dateAndTime) {
      this.project = project;
      this.person = person;
      if(startOrStop.equals("S") || startOrStop.equals("E")) {
          this.startOrStop = startOrStop;
      }
      else {
          throw new IllegalArgumentException();
      }
      this.dateAndTime = dateAndTime;
  }
}
