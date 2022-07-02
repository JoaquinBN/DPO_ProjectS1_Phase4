package PersistenceLayer;

import BusinessLayer.Entities.Trials;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface TrialsFileManager {

    void writeTrials(ArrayList<Trials> trials) throws IOException;

    List<String[]> readTrials() throws IOException, CsvException;
}
