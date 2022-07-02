package PersistenceLayer.TrialsFileDAO;

import BusinessLayer.Entities.Trials.Trials;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * TrialsJSONManager is a class that manages the trials file to be able to read and write the trials in a json file
 * so the data can be stored and used again.
 */
public class TrialsJSONManager implements TrialsFileManager {
    private final String filename = "files/Trials.json";
    private final Gson gson;

    /**
     * Constructor for TrialsJSONManager
     */
    public TrialsJSONManager(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void writeTrials(ArrayList<Trials> t) throws IOException {
            FileWriter writer = new FileWriter(filename);
            writer.write(gson.toJson(t));
            writer.close();

    }

    @Override
    public List<String[]> readTrials() throws IOException {
            FileReader reader = new FileReader(filename);
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            List<String[]> trials = new ArrayList<>();
            if(jsonArray != null) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject object = jsonArray.get(i).getAsJsonObject();
                    if (object.get("typeOfTrial").getAsString().equals("Paper publication")) {
                        String[] aux = new String[7];
                        aux[0] = object.get("trialName").getAsString();
                        aux[1] = object.get("typeOfTrial").getAsString();
                        aux[2] = object.get("publicationName").getAsString();
                        aux[3] = object.get("quartile").getAsString();
                        aux[4] = object.get("acceptProbability").getAsString();
                        aux[5] = object.get("revisionProbability").getAsString();
                        aux[6] = object.get("rejectProbability").getAsString();
                        trials.add(aux);
                    } else if (object.get("typeOfTrial").getAsString().equals("Master studies")) {
                        String[] aux = new String[5];
                        aux[0] = object.get("trialName").getAsString();
                        aux[1] = object.get("typeOfTrial").getAsString();
                        aux[2] = object.get("masterName").getAsString();
                        aux[3] = object.get("numberOfCredits").getAsString();
                        aux[4] = object.get("probabilityOfPassing").getAsString();
                        trials.add(aux);
                    } else if (object.get("typeOfTrial").getAsString().equals("Doctoral thesis defense")) {
                        String[] aux = new String[4];
                        aux[0] = object.get("trialName").getAsString();
                        aux[1] = object.get("typeOfTrial").getAsString();
                        aux[2] = object.get("fieldOfStudy").getAsString();
                        aux[3] = object.get("defenseDifficulty").getAsString();
                        trials.add(aux);
                    } else if (object.get("typeOfTrial").getAsString().equals("Budget request")) {
                        String[] aux = new String[4];
                        aux[0] = object.get("trialName").getAsString();
                        aux[1] = object.get("typeOfTrial").getAsString();
                        aux[2] = object.get("entityName").getAsString();
                        aux[3] = object.get("budgetAmount").getAsString();
                        trials.add(aux);
                    }
                }
            }
            return trials;
    }
}