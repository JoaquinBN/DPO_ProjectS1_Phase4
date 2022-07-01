package PersistenceLayer;

import BusinessLayer.Entities.Trials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrialsJSONManager implements TrialsFileManager {
    private static final String filename = "files/Trials.json";
    private final Gson gson;

    public TrialsJSONManager() throws FileNotFoundException {
        this.gson = new GsonBuilder().create();
    }

    @Override
    public void writeTrials(ArrayList<Trials> t) {
        try {
            String hey = "hey hey";
            FileWriter writer = new FileWriter(filename);
            gson.toJson(hey, writer);
            List<String[]> convertedTrials = new ArrayList<>();
                for (Trials trial : t) {
                    convertedTrials.add(trial.getDataToWrite());
                }
        } catch (IOException e) {
            // handle exception
        }
    }

    @Override
    public List<String[]> readTrials() {
        //try {
            List<String[]> trials = Collections.singletonList(new String[]{"as", "asd"});
                    //gson.fromJson(gson.newJsonReader(new FileReader(filename)), List.class);
            return trials;
        //} catch (IOException e) {
            // handle exception
        //}
        //return null;
    }
}