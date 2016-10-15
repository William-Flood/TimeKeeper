/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeepertwo;
import timekeepertwo.data.Person;
import timekeeperone.data.Project;
import timekeepertwo.data.TimeRecord;
import timekeeperone.dataaccess.PersonProjectAccess;
import timekeeperone.dataaccess.RecordSaver;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.io.IOException;
import java.util.Locale;
import timekeeper.ui.TimeKeeperUI;

import timekeeper.ui.UIFactory;
import timekeeper.ui.UIType;
import java.util.ResourceBundle;

/**
 *
 * @author k0513525
 */
public class TimeKeeper {

    /**
     * @param args the command line arguments
     
  public static void main(String[] args) {
    if(false == checkArgsLength(args)) {
        System.err.println("Three arguments only");
        System.exit(-1);
    }
    if(false == checkStartStopArg(args[2])){
        System.err.println("Third argument S or E Only");
        System.exit(-1);
    }
    Person personFromID = null;
    try{
        personFromID = PersonProjectAccess.getPerson(args[1]);
    } catch(FileNotFoundException ex) {
        System.err.println("The person save file is missing!");
        System.exit(-1);
    } catch(IllegalArgumentException ex) {
        System.err.println("The person ID provided wasn't found!");
        System.exit(-1);
    }
    Project projectFromID = null;
    try{
        projectFromID = PersonProjectAccess.getProject(args[0]);
    } catch(FileNotFoundException ex) {
        System.err.println("The project save file is missing!");
        System.exit(-1);
    } catch(IllegalArgumentException ex) {
        System.err.println("The project ID provided wasn't found!");
        System.exit(-1);
    }
    
    TimeRecord recordToAdd = new TimeRecord(projectFromID,personFromID,args[2],
    LocalDateTime.now());
    
    try {
    RecordSaver.saveTimeRecord(recordToAdd);
    } catch (IOException ex) {
        System.err.println("File write failed");
        System.exit(-1);
    }
    
    System.out.println("Record saved for " + personFromID.getFirstName() +
            personFromID.getLastName() + " on " + projectFromID.getName() +
            " at "+ recordToAdd.getDateAndTime().toString());
    
      
  }
  
  public static boolean checkArgsLength(String[] args) {
      return args.length == 3;
  }
  
  public static boolean checkStartStopArg(String arg) {
      return (arg.equals("S") ||  arg.equals("E"));
  }
  */
  static boolean latch;
  static Person user;
    
  public static void main(String[] args) {
      ResourceBundle logInBundle = ResourceBundle.getBundle(
              "timekeepertwo.LogInText",
              Locale.ENGLISH);
      latch = true;
      TimeKeeperUI logInUI = UIFactory.makeLogInUI(UIType.GUI, 
              logInBundle, 
              (username,password)->checkLogin (username, password), 
              ()->{}, 
              ()->{latch = false;});
      //while(logInUI.isRunning()) {
          //System.out.print("");
      //}
      System.out.println("ran");
  }
  
  public static boolean checkLogin (String username, String password) {
      boolean wasPersonFound = false;
      Person foundPerson;
      try {
        //TODO: PersonProjectAccess.getPerson(username, password) currently
        //retrieves user information by ID, not username!
        foundPerson = PersonProjectAccess.getPerson(username, password);
        if(null != foundPerson) {
            user = foundPerson;
        }
      } catch (FileNotFoundException e) {
          //TODO: create view to display file not found exception
      }
      return wasPersonFound;
  }
}
