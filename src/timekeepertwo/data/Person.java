/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeepertwo.data;
import flood.util.StringUtil;

/**
 *
 * @author k0513525
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
  
    public String getPersonID() {
        return personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getPersonCategory() {
        return personCategory;
    }
  
  public Person(String personID, String firstName, String lastName, String password, String personCategory) {
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
