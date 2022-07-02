package PersistenceLayer.EditionFileDAO;

import BusinessLayer.Entities.Edition;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface EditionsFileManager {

    /**
     * Write the editions to the file
     * @param Editions the editions to write
     * @throws IOException if the file could not be written
     */
    void writeEditions(ArrayList<Edition> Editions) throws IOException;

    /**
     * Read the editions from the file
     * @return the editions read
     * @throws IOException if the file could not be read
     * @throws CsvException if the file is not in the correct format
     */
    List<String[]> readEditions() throws IOException, CsvException;
}
