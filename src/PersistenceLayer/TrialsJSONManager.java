package PersistenceLayer;

import BusinessLayer.Entities.Trials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrialsJSONManager implements TrialsManager {

    private static final String filename = "files/Execution.json";
    private final Gson gson;

    public TrialsJSONManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void writeTrials(ArrayList<Trials> trials) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (Trials trial : trials) {
                gson.toJson(trial, writer);
            }
            writer.close();
        } catch (IOException e) {
            // handle exception
        }
    }

    @Override
    public List<String[]> readTrials() {
        try {
            FileReader reader = new FileReader(filename);
            List<String[]> trials = gson.fromJson(reader, List.class);
            reader.close();
            return trials;
        } catch (IOException e) {
            // handle exception
        }
        return null;
    }
}