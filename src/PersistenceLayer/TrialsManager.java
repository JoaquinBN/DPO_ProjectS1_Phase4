package PersistenceLayer;

import BusinessLayer.Entities.Trials;

import java.util.ArrayList;
import java.util.List;

public interface TrialsManager {
    void writeTrials(ArrayList<Trials> trials);

    List<String[]> readTrials();
}
