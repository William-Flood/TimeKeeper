/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeepertwo.dataaccess;
import timekeepertwo.data.TimeRecord;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

/**
 * Saves a time record to a file
 * @author DragonSheep
 */
public class RecordSaver {
    static final String OUTPATH = "..//..//sample_data/time_record_data.txt";
    
    /**
     * Writes a record to file
     * @param recordToSave A Time Record to save to file
     * @throws IOException 
     */
    public static void saveTimeRecord(TimeRecord recordToSave) throws IOException {
        File outFile = new File(OUTPATH);
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
