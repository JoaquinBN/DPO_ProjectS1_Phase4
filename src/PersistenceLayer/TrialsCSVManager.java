package PersistenceLayer;

import BusinessLayer.Entities.Trials;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrialsCSVManager implements TrialsManager {


    public TrialsCSVManager() {
    }

    /**
     * Writes the trials to the csv file.
     *
     * @param trials the trials to write.
     */
    @Override
    public void writeTrials(ArrayList<Trials> trials){
        try {
            CSVWriter writer = new CSVWriter(new FileWriter("files/Trials.csv", false),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "\n");

            for (Trials trial : trials){
                writer.writeNext(trial.getDataToWrite());
            }
            writer.close();
        } catch (IOException e) {
            // handle exception
        }
    }

    /**
     * Reads the trials from the csv file.
     *
     * @return the trials read.
     */
    @Override
    public List<String[]> readTrials(){
        try {
            CSVReader reader = new CSVReader(new FileReader("files/Trials.csv"));
            List<String[]> trials = reader.readAll();
            reader.close();
            return trials;
        } catch (IOException | CsvException e) {
            // handle exception
        }
        return null;
    }
}
