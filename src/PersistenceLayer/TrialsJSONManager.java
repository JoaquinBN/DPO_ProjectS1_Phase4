package PersistenceLayer;

import BusinessLayer.Entities.Trials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrialsJSONManager implements TrialsFileManager {
    private static final String filename = "files/Trials.json";
    private final Gson gson;

    public TrialsJSONManager() throws FileNotFoundException {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void writeTrials(ArrayList<Trials> t) {

        try {
            System.out.println(t.get(0).getDataToWrite()[0]);
            FileWriter writer = new FileWriter(filename, true);
            for (Trials trial : t) {
                String[] line = trial.getDataToWrite();
                System.out.println(line[0]);
                gson.toJson(trial.getDataToWrite(), writer);
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