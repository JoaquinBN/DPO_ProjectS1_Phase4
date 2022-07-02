package PersistenceLayer.TrialsFileDAO;

import BusinessLayer.Entities.Trials.Trials;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TrialsFileManager is a public interface to facilitate the managing of the editions file, in both CSV and JSON format
 */
public interface TrialsFileManager {

    /**
     * Writes the trials to the csv file.
     * @param trials the trials to write.
     * @throws IOException if the file could not be written.
     */
    void writeTrials(ArrayList<Trials> trials) throws IOException;

    /**
     * Reads the trials from the csv file.
     * @return the trials read.
     * @throws IOException if the file could not be read.
     * @throws CsvException if the file is not in the correct format.
     */
    List<String[]> readTrials() throws IOException, CsvException;
}
