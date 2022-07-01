package PersistenceLayer;

import BusinessLayer.Entities.Edition;
import java.util.ArrayList;
import java.util.List;

public interface EditionsFileManager {

    void writeEditions(ArrayList<Edition> Editions);
    List<String[]> readEditions();
}
