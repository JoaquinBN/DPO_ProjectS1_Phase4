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
            int i = 0;
            for (Trials trial : trials) {
                System.arraycopy(trial.getDataToWrite(),0,convertedTrials.get(i),0,trial.getDataToWrite().length);
                i++;
            }
            return convertedTrials;
    }
}