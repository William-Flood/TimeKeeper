/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeeperone.dataaccess;
import timekeepertwo.data.TimeRecord;
import timekeeperone.data.Project;
import timekeepertwo.data.Person;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author k0513525
 */
public class RecordSaver {
    static final String outPath = "..//..//sample_data/time_record_data.txt";
    
    /**
     * Writes a record to file
     * @param recordToSave A Time Record to save to file
     * @throws IOException 
     */
    public static void saveTimeRecord(TimeRecord recordToSave) throws IOException {
        File outFile = new File(outPath);
        FileWriter appender = new FileWriter(outFile, true);
        String projectID = recordToSave.getProject().getProjectID();
        String personID = recordToSave.getPerson().getPersonID();
        String startOrStop = recordToSave.getStartOrStop();
        String time = recordToSave.getDateAndTime().toString();
        String line = System.lineSeparator() + projectID + "|" + personID + "|" + startOrStop + 
                "|" + time;
        appender.write(line);
        appender.close();
    }
}
