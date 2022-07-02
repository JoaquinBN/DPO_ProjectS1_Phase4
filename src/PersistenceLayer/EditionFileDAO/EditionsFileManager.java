package PersistenceLayer.EditionFileDAO;

import BusinessLayer.Entities.Edition;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface EditionsFileManager {

    void writeEditions(ArrayList<Edition> Editions) throws IOException;
    List<String[]> readEditions() throws IOException, CsvException;
}
