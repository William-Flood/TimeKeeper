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
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Optional;
import timekeeper.ui.TimeKeeperUI;
import timekeeper.ui.UIFactory;
import timekeeper.ui.UIType;
import java.util.ResourceBundle;
import java.util.stream.Stream;
import timekeeper.ui.MenuOption;
import timekeepertwo.dataaccess.RecordRetriever;

/**
 * Records activity on various projects
 * Known issues:
 * @author DragonSheep
 */
public class TimeKeeper {

    
  static boolean latch;
  static Person user;
  /**
   *  Main menu options.
   */
  static final MenuOption[] MAIN_MENU_OPTIONS = {
      new MenuOption("recordProjectActivity",()->toProjectActivity()),
      new MenuOption("logOut",()->goToLogin())
  };
  /**
   * Code execution begins here. 
   * @param args Ignored
   */
  public static void main(String[] args) {
      goToLogin();
  }
  /**
   *  Shows the login menu.
   */
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
   * Attempts to log a user into the application.
   * @param username The ID number of the user
   * @param password The password of the user
   * @return Whether or not the login was successful
   */
  public static boolean attemptLogin (String username, String password)  {
      boolean wasPersonFound = false;
      Person foundPerson;
      try {
        foundPerson = PersonProjectAccess.getPerson(username, password);
        if(null != foundPerson) {
            user = foundPerson;
            wasPersonFound = true;
        }
      } catch (FileNotFoundException e) {
          throw new UncheckedIOException(e);
      }
      return wasPersonFound;
  }
  /**
   * Displays menu with a list of options
   * for navigating through the application.
   */
  public static void toMainMenu() {
      ResourceBundle mainMenuBundle = ResourceBundle.getBundle(
              "timekeepertwo.MainMenuText",
              Locale.ENGLISH);
      TimeKeeperUI mainMenu = UIFactory.makeMainMenuUI(UIType.GUI, 
              mainMenuBundle, 
              user.getFirstName() + " " + user.getLastName(), 
              MAIN_MENU_OPTIONS);
      
      //Make the menu visible
      mainMenu.display();
  }
  /**
   * Opens the project activity menu
   * allowing the user to sign into and
   * out of projects, and records a log
   * of this activity.
   */
  public static void toProjectActivity() {
      ResourceBundle projectActivityBundle = ResourceBundle.getBundle(
              "timekeepertwo.ProjectActivityText",
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
              projectActivityBundle,  
              filteredList, 
              
              //Code to call in order to record project activity
              (project,activityType)->recordActivity(project, 
                activityType),
              
              //Code to call if the user cancels activity
              ()->toMainMenu());
      
      //Makes the window visible
      projectActivity.display();
      
  }
  
  /**
   * Records project activity to file.
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
