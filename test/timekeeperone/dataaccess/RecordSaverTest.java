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
import timekeepertwo.data.TimeRecord;
import timekeepertwo.data.Person;
import timekeepertwo.data.Project;
import java.time.LocalDateTime;

/**
 *
 * @author wifoo
 */
public class RecordSaverTest {
    
    public RecordSaverTest() {
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
     * Test of saveTimeRecord method, of class RecordSaver.
     */
    @Test
    public void testSaveTimeRecord() throws Exception {
        System.out.println("saveTimeRecord");
        Person testPerson = PersonProjectAccess.getPerson("1100047");
        Project testProject = PersonProjectAccess.getProject("120");
        LocalDateTime testDate = LocalDateTime.now();
        TimeRecord recordToSave = new TimeRecord(testProject,testPerson,"S",
                testDate);
        RecordSaver.saveTimeRecord(recordToSave);
    }
    
}
