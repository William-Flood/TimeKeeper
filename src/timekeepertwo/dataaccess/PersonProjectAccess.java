/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeepertwo.dataaccess;
import timekeepertwo.data.Person;
import timekeepertwo.data.Project;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author k0513525
 */
public class PersonProjectAccess {
  static final String SEPARATOR = "[|]|"+System.lineSeparator();
  static final String DIRECTORY = "..\\..\\sample_data\\";
  static final String PERSON_FILE_NAME = "\\person_data.txt";
  static final String PROJECT_FILE_NAME = "project_data.txt";
  static final String TIME_RECORD_FILE_NAME = "time_record_data.txt";
  
  /**
   * Retrieves a person record from file
   * @param personID
   * @return The person with the matching personID
   * @throws FileNotFoundException 
   */
  public static Person getPerson(String personID) throws FileNotFoundException {
      Person foundPerson = new Person("1","","","","");
      File personFile = new File(DIRECTORY + PERSON_FILE_NAME);
      Scanner inFile;
      try {
        inFile = new Scanner(personFile); 
      }
      catch (FileNotFoundException ex) {
        throw ex;
      }
      inFile.useDelimiter(SEPARATOR);
      boolean latch = true;
      while(latch) {
          if(false == inFile.hasNextLine()) {
              throw new IllegalArgumentException();
          }
          String foundID = inFile.next();
          //System.out.println(foundID);
          if(personID.equals(foundID)) {
              String fName = inFile.next();
              String lName = inFile.next();
              String password = inFile.next();
              String category = inFile.next();
              foundPerson = new Person(foundID,fName,lName,password,category);
              latch = false;
          }
          else {
              inFile.nextLine();
          }
          
      }
      inFile.close();
      return foundPerson;
  }
  
  /**
   * Retrieves a person record from file
   * @param personID
   * @param password
   * @return The person with the matching personID
   * @throws FileNotFoundException 
   */
  public static Person getPerson(String personID, String password)
          throws FileNotFoundException {
      Person foundPerson = null;
      File personFile = new File(DIRECTORY + PERSON_FILE_NAME);
      Scanner inFile;
      try {
        inFile = new Scanner(personFile); 
      }
      catch (FileNotFoundException ex) {
        throw ex;
      }
      inFile.useDelimiter(SEPARATOR);
      boolean latch = true;
      while(latch) {
          String foundID = inFile.next();
          
          if(personID.equals(foundID)) {
              String fName = inFile.next();
              String lName = inFile.next();
              String storedPassword = inFile.next();
              String category = inFile.next();
              if(password.equals(storedPassword)){
                foundPerson = new Person(foundID,
                      fName,
                      lName,
                      storedPassword,
                      category);
                latch = false;
              }
          }
          else if (latch) {
              inFile.nextLine();
          }
          if(false == inFile.hasNextLine()) {
              latch = false;
          }
          
      }
      inFile.close();
      return foundPerson;
  }
  
  /**
   * Retrieves a project record from file
   * @param projectID
   * @return
   * @throws FileNotFoundException 
   */
  public static Project getProject(String projectID) throws FileNotFoundException {
      Project foundProject = new Project("1","","","");
      File projectFile = new File(DIRECTORY + PROJECT_FILE_NAME);
      Scanner inFile;
      try {
        inFile = new Scanner(projectFile); 
      }
      catch (FileNotFoundException ex) {
        throw ex;
      }
      //Pattern pipePattern = Pattern.compile("^([^\\\\|]*\\\\|)+$");
              //Regex found at http://stackoverflow.com/questions/7959929/regex-to-match-a-pipe-delineated-file
      inFile.useDelimiter(SEPARATOR);
      boolean latch = true;
      while(latch) {
          String foundID = inFile.next();
          if(projectID.equals(foundID)) {
              String activity = inFile.next();
              String name = inFile.next();
              String description = inFile.next();
              foundProject = new Project(foundID,activity,name,description);
              latch = false;
          }
          else {
              inFile.nextLine();
          }
          if(false == inFile.hasNextLine()) {
              throw new IllegalArgumentException();
          }
          
      }
      inFile.close();
      return foundProject;
  }
  
  /**
   * Retrieves a project record from file
   * @param projectID
   * @return
   * @throws FileNotFoundException 
   */
  public static Project[] getProjectList() throws FileNotFoundException {
      ArrayList<Project> projectArrayList = new ArrayList<Project>();
      File projectFile = new File(DIRECTORY + PROJECT_FILE_NAME);
      Scanner inFile;
      try {
        inFile = new Scanner(projectFile); 
      }
      catch (FileNotFoundException ex) {
        throw ex;
      }
      //Pattern pipePattern = Pattern.compile("^([^\\\\|]*\\\\|)+$");
              //Regex found at http://stackoverflow.com/questions/7959929/regex-to-match-a-pipe-delineated-file
      inFile.useDelimiter(SEPARATOR);
      boolean latch = true;
      while(latch) {
          String foundID = inFile.next();
          String activity = inFile.next();
          String name = inFile.next();
          String description = inFile.next();
          projectArrayList.add( new Project(foundID,activity,name,description));
          inFile.nextLine();
          if(false == inFile.hasNextLine()) {
              latch = false;
          }
          
      }
      inFile.close();
      Project[] projectList = new Project[projectArrayList.size()];
      return projectArrayList.toArray(projectList);
  }
}
