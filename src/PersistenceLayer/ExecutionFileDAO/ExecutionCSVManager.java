package PersistenceLayer.ExecutionFileDAO;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExecutionCSVManager implements ExecutionFileManager {
    private static final String filename = "files/Execution.csv";
    
    public ExecutionCSVManager() {}

    /**
     * Write players data to the csv file.
     * @param playersData List of String arrays with players data.
     */
    @Override
    public void writePlayersData(List<String[]> playersData){
        try {
            CSVWriter writer= new CSVWriter(new FileWriter(filename, true),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "\n");
            for (String[] player : playersData) {
                writer.writeNext(player);
            }
            writer.close();
        } catch (IOException e) {
            // handle exception
        }
    }

    /**
     * Reads the players data from the csv file.
     * @param allTrials the trials to write.
     */
    @Override
    public void writeTrials(String[] allTrials) throws IOException {
        CSVWriter writer= new CSVWriter(new FileWriter(filename, false),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "\n");
        writer.writeNext(allTrials);
        writer.close();
    }

    /**
     * Reads the players data from the csv file.
     * @return playersData the data read.
     */
    @Override
    public List<String[]> readPlayersData() throws IOException, CsvException {
            CSVReader reader = new CSVReader(new FileReader(filename));
            List<String[]> playersData = reader.readAll();
            playersData.remove(0);
            reader.close();
            return playersData;
    }

    /**
     * Reads the trials from the csv file.
     * @return the trials read.
     */
    @Override
    public String[] readTrials() throws IOException, CsvValidationException {
            CSVReader reader = new CSVReader(new FileReader(filename));
            String[] allTrials = reader.readNext();
            reader.close();
            return allTrials;
    }

    /**
     * Checks if the file is empty.
     * @return if the file is empty or not.
     */
    @Override
    public boolean fileIsEmpty() throws CsvValidationException, IOException {
            CSVReader reader = new CSVReader(new FileReader(filename));
            String[] allTrials = reader.readNext();
            return allTrials == null;
    }

    /**
     * Clears the file.
     */
    @Override
    public void deleteFile() throws IOException {
            CSVWriter writer = new CSVWriter(new FileWriter(filename, false),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "\n");
            writer.close();
    }
}
