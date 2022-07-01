package PersistenceLayer;

import BusinessLayer.Entities.Edition;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class EditionCSVManager implements EditionsFileManager {
    private static final String filename = "files/Editions.csv";

    public EditionCSVManager(){}

    /**
     * Writes the editions to the csv file.
     *
     * @param Editions the editions to write.
     */
    @Override
    public void writeEditions(ArrayList<Edition> Editions){
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(filename, false),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER, "\n");
            for (Edition edition : Editions) {
                String[] line = new String[edition.getNumberOfTrials()];
                int i = 0;
                for(String trialName : edition.getTrials()) {
                    line[i] = trialName;
                    i++;
                }
                String[] data = {String.valueOf(edition.getYear()), String.valueOf(edition.getNumberOfPlayers())};
                String[] all = new String[data.length + line.length];
                System.arraycopy(data, 0, all, 0, data.length);
                System.arraycopy(line, 0, all, data.length, line.length);
                writer.writeNext(all);
            }
            writer.close();
        } catch (IOException e) {
            // handle exception
        }
    }

    /**
     * Reads the editions from the csv file.
     *
     * @return the editions read.
     */
    @Override
    public List<String[]> readEditions(){
        try {
            CSVReader reader = new CSVReader(new FileReader(filename));
            List<String[]> editions = reader.readAll();
            reader.close();
            return editions;
        } catch (IOException | CsvException e) {
            // handle exception
        }
        return null;
    }
}
