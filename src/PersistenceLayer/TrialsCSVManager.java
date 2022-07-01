package PersistenceLayer;

import BusinessLayer.Entities.Trials;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrialsCSVManager implements TrialsManager {
    private static final String filename = "files/Trials.csv";
    private final CSVWriter writer;
    private final CSVReader reader;

    public TrialsCSVManager() throws IOException {
        this.reader = new CSVReader(new FileReader(filename));
        this.writer = new CSVWriter(new FileWriter(filename, false),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "\n");;
    }

    /**
     * Writes the trials to the csv file.
     *
     * @param trials the trials to write.
     */
    @Override
    public void writeTrials(ArrayList<Trials> trials){
        try {
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
            List<String[]> trials = reader.readAll();
            reader.close();
            return trials;
        } catch (IOException | CsvException e) {
            // handle exception
        }
        return null;
    }
}
