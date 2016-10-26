/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeepertwo;
import timekeepertwo.data.Person;
import timekeepertwo.data.Project;
import timekeepertwo.data.TimeRecord;
import timekeepertwo.dataaccess.PersonProjectAccess;
import timekeepertwo.dataaccess.RecordSaver;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.io.IOException;
import java.util.Locale;
import timekeeper.ui.TimeKeeperUI;

import timekeeper.ui.UIFactory;
import timekeeper.ui.UIType;
import java.util.ResourceBundle;
import timekeeper.ui.MenuOption;
import timekeeper.ui.NextStepHandler;

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
  //TODO: add project signin option to main menu
  static final MenuOption[] menuOptions = {
      new MenuOption("logOut",()->goToLogin())
  };
    
  public static void main(String[] args) {
      //goToLogin();
      //System.out.println("Hello, World!");
      user = new Person("52", "dummy", "dummy", "dummy", "dummy");
      toProjectActivity();
  }
  
  static void goToLogin() {
    user = null;
      ResourceBundle logInBundle = ResourceBundle.getBundle(
              "timekeepertwo.LogInText",
              Locale.ENGLISH);
      TimeKeeperUI logInUI = UIFactory.makeLogInUI(UIType.GUI, 
              logInBundle, 
              (username,password)->attemptLogin (username, password), 
              ()->{}, 
              ()->toMainMenu());
      logInUI.display();
    }
  
  /**
   * Log into a user into the application
   * @param username The ID number of the user
   * @param password The password of the user
   * @return Whether or not the login was successful
   */
  static boolean attemptLogin (String username, String password) {
      boolean wasPersonFound = false;
      Person foundPerson;
      try {
        foundPerson = PersonProjectAccess.getPerson(username, password);
        if(null != foundPerson) {
            user = foundPerson;
            wasPersonFound = true;
        }
      } catch (FileNotFoundException e) {
          //TODO: create view to display file not found exception
      }
      return wasPersonFound;
  }
  
  static void toMainMenu() {
      ResourceBundle mainMenuBundle = ResourceBundle.getBundle(
              "timekeepertwo.MainMenuText",
              Locale.ENGLISH);
      TimeKeeperUI mainMenu = UIFactory.makeMainMenuUI(UIType.GUI, 
              mainMenuBundle, 
              user.getFirstName() + " " + user.getLastName(), 
              menuOptions);
      mainMenu.display();
  }
  
  static void toProjectActivity() {
      ResourceBundle mainMenuBundle = ResourceBundle.getBundle(
              "timekeepertwo.MainMenuText",
              Locale.ENGLISH);
      TimeKeeperUI projectActivity = UIFactory.makeProjectActivityUI(UIType.GUI, 
              mainMenuBundle, 
              user.getFirstName() + " " + user.getLastName(), 
              (userName,projectID,checkIn)->true, //TODO: Fill methods
              ()->{});
      projectActivity.display();
  }
  
  
}
