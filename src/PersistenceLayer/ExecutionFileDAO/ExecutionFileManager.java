package PersistenceLayer.ExecutionFileDAO;

import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;

public interface ExecutionFileManager {
    /**
     * Write players data into the file.
     * @param playersData List of String arrays with players data.
     * @throws IOException if the file could not be written.
     */
    void writePlayersData(List<String[]> playersData) throws IOException;

    /**
     * Writes the trials into the file.
     * @param allTrials the trials to write.
     * @throws IOException if the file could not be written.
     */
    void writeTrials(String[] allTrials) throws IOException;

    /**
     * Reads the players data from the file.
     * @return playersData the data read.
     * @throws IOException if the file could not be read.
     * @throws CsvException if the file is not in the correct format.
     */
    List<String[]> readPlayersData() throws IOException, CsvException;

    /**
     * Reads the trials from the file.
     * @return the trials read.
     * @throws IOException if the file could not be read.
     * @throws CsvValidationException if the file is not in the correct format.
     */
    String[] readTrials() throws IOException, CsvValidationException;

    /**
     * Checks if the file is empty.
     * @return true if the file is empty, false otherwise.
     * @throws IOException if the file could not be read.
     * @throws CsvValidationException if the file is not in the correct format.
     */
    boolean fileIsEmpty() throws IOException, CsvValidationException;

    /**
     * Deletes the file.
     * @throws IOException if the file could not be deleted.
     */
    void deleteFile() throws IOException;
}
