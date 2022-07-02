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
    private String[] executionData;

    public ExecutionJSONManager() throws FileNotFoundException {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void writePlayersData(List<String[]> playersData) throws IOException {
        FileWriter writer = new FileWriter(filename, false);

        JsonArray data = new JsonArray();
        for (String executionDatum : executionData) {
            JsonObject object = new JsonObject();
            object.addProperty("trial", executionDatum);
            data.add(object);
        }

        for (String[] playerData : playersData) {
            JsonObject object = new JsonObject();
            object.addProperty("name", playerData[0]);
            object.addProperty("investigationPoints", playerData[1]);
            object.addProperty("playerStatus", playerData[2]);
            data.add(object);
        }

        writer.write(gson.toJson(data));
        writer.close();
    }

    @Override
    public void writeTrials(String[] allTrials) throws IOException {
            this.executionData = allTrials;
    }

    @Override
    public List<String[]> readPlayersData() throws FileNotFoundException {
            JsonArray jsonArray = gson.fromJson(new FileReader(filename), JsonArray.class);
            List<String[]> playersData = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject object = jsonArray.get(i).getAsJsonObject();
                if (!object.has("trial")) {
                    String[] aux = new String[3];
                    aux[0] = object.get("name").getAsString();
                    aux[1] = object.get("investigationPoints").getAsString();
                    aux[2] = object.get("playerStatus").getAsString();
                    playersData.add(aux);
                }

            }

            return playersData;
    }

    @Override
    public String[] readTrials() throws FileNotFoundException {
        FileReader reader = new FileReader(filename);
        JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
        List<String[]> trials = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject object = jsonArray.get(i).getAsJsonObject();
            if (object.has("trial")) {
                String[] aux = new String[1];
                aux[0] = object.get("trial").getAsString();
                trials.add(aux);
            }

        }
        String[] aux = new String[trials.size()];
        for (int i = 0; i < trials.size(); i++) {
            aux[i] = trials.get(i)[0];
        }

        return aux;
    }

    @Override
    public boolean fileIsEmpty() throws IOException {
            FileReader reader = new FileReader(filename);
            JsonArray jsonArray = gson.fromJson(reader, JsonArray.class);
            return jsonArray == null || jsonArray.size() == 0;
    }

    @Override
    public void deleteFile() throws IOException {
            FileWriter writer = new FileWriter(filename);
            writer.write("");
            writer.close();
    }
}
