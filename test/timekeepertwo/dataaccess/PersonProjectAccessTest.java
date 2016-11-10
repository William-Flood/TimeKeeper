/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeepertwo.dataaccess;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import timekeepertwo.data.Person;
import timekeepertwo.data.Project;

/**
 *
 * @author DragonSheep
 */
public class PersonProjectAccessTest {
    
    public PersonProjectAccessTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPerson method, of class PersonProjectAccess.
     */
    @Test
    public void testGetPerson() throws Exception {
        System.out.println("getPerson");
        String personID = "1100034";
        String expResult = "Joaquin";
        try {
            Person result = PersonProjectAccess.getPerson(personID);
            assertEquals(expResult, result.getFirstName());
        } catch (IllegalArgumentException ex) {
            fail("ID not found");
        }
    }

    /**
     * Test of getProject method, of class PersonProjectAccess.
     */
    @Test
    public void testGetProject() throws Exception {
        System.out.println("getProject");
        String projectID = "100";
        String expResult = "Clown Simulator";
        try {
            Project result = PersonProjectAccess.getProject(projectID);
            assertEquals(expResult, result.getName());
        } catch (IllegalArgumentException ex) {
            fail("ID not found");
        }
        
    }
    
}
