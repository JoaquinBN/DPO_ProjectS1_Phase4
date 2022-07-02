package PersistenceLayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExecutionJSONManager implements ExecutionFileManager {
    private static final String filename = "files/Execution.json";
    private final Gson gson;

    public ExecutionJSONManager() throws FileNotFoundException {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void writePlayersData(List<String[]> playersData) throws IOException {
            FileWriter writer = new FileWriter(filename, true);

            for (String[] playerData : playersData) {
                JsonObject object = new JsonObject();
                object.addProperty("name", playerData[0]);
                object.addProperty("investigationPoints", playerData[1]);
                object.addProperty("playerStatus", playerData[2]);
                writer.write(gson.toJson(object));
            }
            writer.close();
    }

    @Override
    public void writeTrials(String[] allTrials) throws IOException {
            FileWriter writer = new FileWriter(filename, false);
            JsonArray jsonArray = new JsonArray();
            for (String trial : allTrials) {
                JsonObject object = new JsonObject();
                object.addProperty("trial", trial);
                jsonArray.add(object);
            }
            writer.write(gson.toJson(jsonArray));
            writer.close();
    }

    @Override
    public List<String[]> readPlayersData() throws FileNotFoundException {
            JsonArray jsonArray = gson.fromJson(new FileReader(filename), JsonArray.class);
            List<String[]> playersData = new ArrayList<>();
            for (int i = 1; i < jsonArray.size(); i++) {
                JsonObject object = jsonArray.get(i).getAsJsonObject();
                String[] aux = new String[3];
                aux[0] = object.get("name").getAsString();
                aux[1] = object.get("investigationPoints").getAsString();
                aux[2] = object.get("playerStatus").getAsString();
                playersData.add(aux);
            }
            return playersData;
    }

    @Override
    public String[] readTrials() throws FileNotFoundException {
           FileReader reader = new FileReader(filename);
           JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
           if (jsonArray != null) {
               JsonArray jsonArray2 = jsonArray.get(0).getAsJsonArray();
               List<String> trials = new ArrayList<>();
               for (int i = 0; i < jsonArray2.size(); i++) {
                   trials.add(jsonArray2.get(i).getAsJsonObject().get("trial").getAsString());
               }
               String[] allTrials = new String[trials.size()];
               for (int i = 0; i < allTrials.length; i++) {
                   allTrials[i] = trials.get(i);
               }
               return allTrials;
           }
           return null;
    }

    @Override
    public boolean fileIsEmpty() throws FileNotFoundException {
            FileReader reader = new FileReader(filename);
            return true;

    }

    @Override
    public void deleteFile() throws IOException {
            FileWriter writer = new FileWriter(filename);
            writer.write("");
            writer.close();
    }
}
