package PersistenceLayer;

import BusinessLayer.Entities.Trials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrialsJSONManager implements TrialsFileManager {
    private final String filename = "files/Trials.json";
    private final Gson gson;

    public TrialsJSONManager() throws FileNotFoundException {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void writeTrials(ArrayList<Trials> t) {
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write(gson.toJson(t));
            writer.close();
        } catch (IOException e) {
            // handle exception
        }

    }

    @Override
    public List<String[]> readTrials() {
        try {
            FileReader reader = new FileReader(filename);
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            List<String[]> trials = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject object = jsonArray.get(i).getAsJsonObject();
                if (object.get("typeOfTrial").getAsString().equals("Paper publication")) {
                    String[] aux = new String[6];
                    aux[0] = object.get("trialName").getAsString();
                    aux[1] = object.get("typeOfTrial").getAsString();
                    aux[2] = object.get("trialDate").getAsString();
                    aux[3] = object.get("trialLocation").getAsString();
                    aux[4] = object.get("trialDescription").getAsString();
                    aux[5] = object.get("trialId").getAsString();
                    trials.add(aux);

                }
                if
            }
            reader.close();

            return data;
        } catch (FileNotFoundException e) {
            // handle exception
        } catch (IOException e) {
            // handle exception
        }
        return null;
    }
}