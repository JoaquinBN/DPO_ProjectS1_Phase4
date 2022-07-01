package PersistenceLayer;

import BusinessLayer.Entities.Trials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrialsJSONManager implements TrialsManager {

    private static final String filename = "files/Trials.json";
    private final Gson gson;
    private final List<Trials> trials;

    public TrialsJSONManager() throws FileNotFoundException {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.trials = gson.fromJson(gson.newJsonReader(new FileReader(filename)), List.class);
    }

    @Override
    public void writeTrials(ArrayList<Trials> t) {
        try {
            gson.toJson(t, new FileWriter(filename));
        } catch (IOException e) {
            // handle exception
        }
    }

    @Override
    public List<String[]> readTrials() {
            List<String[]> convertedTrials = new ArrayList<>();
            for (Trials trial : trials) {
                convertedTrials.add(trial.getDataToWrite());
            }
            return convertedTrials;
    }
}