/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeepertwo.data;
import java.time.LocalDateTime;
/**
 * A record of a person's activity on a project
 * @author DragonSheep
 */
public class TimeRecord {
    
  Person person;
  Project project;
  /**
   * Indicates if the person is starting or stopping a work session.
   */
  String startOrStop;
  LocalDateTime dateAndTime;

  /**
   * 
   * @return The time of the activity
   */
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    /**
     * 
     * @return The person doing the work
     */
    public Person getPerson() {
        return person;
    }

    /**
     * 
     * @return The project worked on
     */
    public Project getProject() {
        return project;
    }

    /**
     * 
     * @return An indication of whether activity began or ended
     */
    public String getStartOrStop() {
        return startOrStop;
    }
    
    
  /**
   * Creates a time record
   * @param project The time of the activity
   * @param person The person doing the work
   * @param startOrStop The project worked on
   * @param dateAndTime  An indication of whether activity began or ended
   */
  public TimeRecord (Project project, 
          Person person, 
          String startOrStop, 
          LocalDateTime dateAndTime) {
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
