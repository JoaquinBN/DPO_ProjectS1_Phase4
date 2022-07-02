package PersistenceLayer.TrialsFileDAO;

import BusinessLayer.Entities.Trials.Trials;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrialsCSVManager implements TrialsFileManager {
    private static final String filename = "files/Trials.csv";

    public TrialsCSVManager(){
    }

    /**
     * Writes the trials to the csv file.
     *
     * @param trials the trials to write.
     */
    @Override
    public void writeTrials(ArrayList<Trials> trials) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filename, false),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "\n");
            for (Trials trial : trials){
                writer.writeNext(trial.getDataToWrite());
            }
            writer.close();
    }

    /**
     * Reads the trials from the csv file.
     *
     * @return the trials read.
     */
    @Override
    public List<String[]> readTrials() throws IOException, CsvException {
        CSVReader reader = new CSVReader(new FileReader(filename));
            List<String[]> trials = reader.readAll();
            reader.close();
            return trials;
    }
}
