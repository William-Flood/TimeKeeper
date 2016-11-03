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
import java.io.UncheckedIOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import timekeeper.ui.TimeKeeperUI;

import timekeeper.ui.UIFactory;
import timekeeper.ui.UIType;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import timekeeper.ui.MenuOption;
import timekeeper.ui.NextStepHandler;
import timekeepertwo.dataaccess.RecordRetriever;

/**
 * Records activity on various projects
 * Known issues:
 *   -Program will not handle errors reading the person file
 *   -Program will display inactive projects
 *   -Program will not display time spent on project if ending work
 *   -ProjectActivityGUI text hard-coded - should use resource bundle 
 * @author DragonSheep
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
      new MenuOption("recordProjectActivity",()->toProjectActivity()),
      new MenuOption("logOut",()->goToLogin())
  };
    
  public static void main(String[] args) {
      goToLogin();
      //user = new Person("52", "dummy", "dummy", "dummy", "dummy");
      //toProjectActivity();
  }
  
  public static void goToLogin() {
    user = null;
      ResourceBundle logInBundle = ResourceBundle.getBundle(
              "timekeepertwo.LogInText",
              Locale.ENGLISH);
      TimeKeeperUI logInUI = UIFactory.makeLogInUI(UIType.GUI, 
              logInBundle, 
                //Code to call in order to log user in
              (username,password)->attemptLogin (username, password), 
              ()->{}, //Code to call after user cancels activity
              ()->toMainMenu()); //Code to call after successful login
      logInUI.display();
    }
  
  /**
   * Log into a user into the application
   * @param username The ID number of the user
   * @param password The password of the user
   * @return Whether or not the login was successful
   */
  public static boolean attemptLogin (String username, String password) {
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
  
  public static void toMainMenu() {
      ResourceBundle mainMenuBundle = ResourceBundle.getBundle(
              "timekeepertwo.MainMenuText",
              Locale.ENGLISH);
      TimeKeeperUI mainMenu = UIFactory.makeMainMenuUI(UIType.GUI, 
              mainMenuBundle, 
              user.getFirstName() + " " + user.getLastName(), 
              menuOptions);
      
      //Make the menu visible
      mainMenu.display();
  }
  
  public static void toProjectActivity() {
      ResourceBundle mainMenuBundle = ResourceBundle.getBundle(
              "timekeepertwo.MainMenuText",
              Locale.ENGLISH);
      Project[] projectList;
      try{
          projectList = PersonProjectAccess.getProjectList();
      }catch(FileNotFoundException ex) {
          //In the event of a file read error, simply display nothing
          projectList = new Project[]{};
      }
      Project[] filteredList = Stream.of(projectList).
              filter((project)->project.getActiveFlag().equals("A")).
              toArray(Project[]::new);
      
      //Creates the UI
      TimeKeeperUI projectActivity = UIFactory.makeProjectActivityUI(UIType.GUI, 
              mainMenuBundle,  
              filteredList, //TODO: filter projectList to remove inactive projects
              
              //Code to call in order to record project activity
              (project,activityType)->recordActivity(project, 
                activityType),
              
              //Code to call if the user cancels activity
              ()->toMainMenu());
      
      //Makes the window visible
      projectActivity.display();
      
  }
  
  /**
   * Records project activity to file
   * @param project The project with activity to record
   * @param activityType Whether work began or ended on the project
   * @return A value to indicate whether the save succeeded
   */
  public static long recordActivity(Project project, 
          String activityType) {
      
    
        TimeRecord recordToAdd = new TimeRecord(project,
                user,
                activityType,
                LocalDateTime.now());
    
        try {
            Optional<TimeRecord> latestRecord = 
                    RecordRetriever.returnStartActivity(project, user);
            /*System.out.println(latestRecord.isPresent()?
                    latestRecord.get().getDateAndTime():"nada"
            );*/
            RecordSaver.saveTimeRecord(recordToAdd);
            if(latestRecord.isPresent()){
                return ChronoUnit.HOURS.between(
                        latestRecord.get().getDateAndTime(), 
                        recordToAdd.getDateAndTime());
            } else {
                return -1;
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        } catch(UncheckedIOException ex) {
            throw ex;
        }
  }
  
  
}
