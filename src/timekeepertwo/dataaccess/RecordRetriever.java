/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timekeepertwo.dataaccess;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import timekeepertwo.data.Person;
import timekeepertwo.data.Project;
import timekeepertwo.data.TimeRecord;

/**
 *
 * @author NH228U02
 */
public class RecordRetriever {

    static final String OUTPATH = "..//..//sample_data/time_record_data.txt";

    /**
     * Returns the latest activity of a project if the latest activity on
     * that project was starting work on it
     * @param workingProject The project to search for
     * @param currentUser The user to search for
     * @return
     * @throws IOException 
     */
    public static Optional<TimeRecord> returnStartActivity(Project workingProject,
            Person currentUser)
            throws IOException {
        Optional<TimeRecord> result = Optional.empty();
        try {
            Stream<String[]> splitTimeRecordLines
                    = Files.lines(Paths.get(OUTPATH)).
                    map((line) -> {
                        return line.split("[|]");
                    }).filter(
                        (splitLine)->(currentUser.getPersonID()
                                .equals(splitLine[1])&&
                                        workingProject.getProjectID().
                                                equals(splitLine[0]))
                    );
            Stream<TimeRecord> timeRecords
                = splitTimeRecordLines.map((splitLine) -> {
                    try{
                        return generateTimeKeeperFromPersonProjectAccess(splitLine);
                    }catch (FileNotFoundException ex) {
                        //The compiler has trouble recognizing that this
                        //exception is being handled.  Throwing an
                        //UncheckedIOException allows us to compile while
                        //successfully throwing the error.
                        throw new UncheckedIOException(ex);
                    }
                });
            result = timeRecords.max((foo,bar)->
                    foo.getDateAndTime().compareTo(bar.getDateAndTime()));
            if(!result.isPresent() || result.get().
                    getStartOrStop()
                    .equals("E")) {
                result = Optional.empty();
            }
            
        } catch (UncheckedIOException | IOException ex) {
            throw ex;
        }
        return result;
    }

    /**
     * 
     * @param generateFrom A string array used to build a time record
     * @return A time record
     * @throws FileNotFoundException 
     */
    private static TimeRecord generateTimeKeeperFromPersonProjectAccess
        (
            String[] generateFrom
    ) throws FileNotFoundException{
        return new TimeRecord(
                PersonProjectAccess.getProject(generateFrom[0]),
                PersonProjectAccess.getPerson(generateFrom[1]),
                generateFrom[2],
                LocalDateTime.parse(generateFrom[3])
        );
    }
}
