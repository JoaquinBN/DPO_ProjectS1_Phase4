package PersistenceLayer;

import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.List;

public interface ExecutionFileManager {
    void writePlayersData(List<String[]> playersData) throws IOException;
    void writeTrials(String[] allTrials) throws IOException;
    List<String[]> readPlayersData() throws IOException, CsvException;
    String[] readTrials() throws IOException, CsvValidationException;
    boolean fileIsEmpty() throws IOException, CsvValidationException;
    void deleteFile() throws IOException;
}
