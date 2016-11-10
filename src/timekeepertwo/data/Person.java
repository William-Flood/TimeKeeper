/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeepertwo.data;
import flood.util.StringUtil;

/**
 * A human, or other sufficiently intelligent being
 * @author DragonSheep
 */

public class Person {
  String personID;
  String firstName;
  String lastName;
  String password;
  /**
   * @var Some description of the person's role
   */
  String personCategory;
  static String[] personCategoryList = {"EMPLOYEE", "MANAGER"};
    /**
     * 
     * @return The ID associated with the person
     */
    public String getPersonID() {
        return personID;
    }
    /**
     * 
     * @return Person's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * 
     * @return Person's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**'
     * 
     * @return Person's password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * 
     * @return The type of person
     */
    public String getPersonCategory() {
        return personCategory;
    }
  
    /**
     * Creates a person object
     * @param personID The ID associated with the person
     * @param firstName Person's first name
     * @param lastName Person's last name
     * @param password Person's password
     * @param personCategory  The type of person.
     */
  public Person(String personID, 
          String firstName, 
          String lastName, 
          String password, 
          String personCategory) {
      if(StringUtil.isInteger(personID)) {
          this.personID = personID;
      }
      else {
          throw new IllegalArgumentException();
      }
      this.firstName = firstName;
      this.lastName = lastName;
      this.password = password;
      this.personCategory = personCategory;
  }
}
