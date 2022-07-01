package PersistenceLayer;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExecutionCSVManager implements ExecutionManager {
    private static final String filename = "files/Execution.csv";
    private final CSVWriter writer;
    private final CSVReader reader;
    
    public ExecutionCSVManager() throws IOException {
        this.reader = new CSVReader(new FileReader(filename));
        this.writer = new CSVWriter(new FileWriter(filename, false),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "\n");
    }

    /**
     * Write players data to the csv file.
     * @param playersData List of String arrays with players data.
     */
    @Override
    public void writePlayersData(List<String[]> playersData){
        try {
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
    public void writeTrials(String[] allTrials){
        try {
            writer.writeNext(allTrials);
            writer.close();
        } catch (IOException e) {
        //handle exception
        }
    }

    /**
     * Reads the players data from the csv file.
     * @return playersData the data read.
     */
    @Override
    public List<String[]> readPlayersData(){
        try {
            List<String[]> playersData = reader.readAll();
            reader.close();
            return playersData;
        } catch (IOException | CsvException e) {
            //handle exception
        }
        return null;
    }

    /**
     * Reads the trials from the csv file.
     * @return the trials read.
     */
    @Override
    public String[] readTrials(){
        try {
            String[] allTrials = reader.readNext();
            reader.close();
            return allTrials;
        } catch (IOException | CsvException e) {
            //handle exception
        }
        return null;
    }

    /**
     * Checks if the file is empty.
     * @return if the file is empty or not.
     */
    @Override
    public boolean fileIsEmpty(){
        try {
            String[] allTrials = reader.readNext();
            return allTrials == null;
        } catch (IOException | CsvException e) {
            //handle exception
        }
        return false;
    }

    /**
     * Clears the file.
     */
    @Override
    public void deleteFile(){
        try {
            writer.close();
        } catch (IOException e) {
            //handle exception
        }
    }
}
