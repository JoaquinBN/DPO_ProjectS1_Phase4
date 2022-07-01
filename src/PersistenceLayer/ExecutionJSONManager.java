package PersistenceLayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExecutionJSONManager implements ExecutionManager {
    private static final String filename = "files/Execution.json";
    private final Gson gson;
    private final List<String[]> executions;

    public ExecutionJSONManager() throws FileNotFoundException {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.executions = gson.fromJson(gson.newJsonReader(new FileReader(filename)), List.class);
    }

    @Override
    public void writePlayersData(List<String[]> playersData) {
        try {
            gson.toJson(playersData, new FileWriter(filename));
        } catch (IOException e) {
            // handle exception
        }
    }

    @Override
    public void writeTrials(String[] allTrials) {
        // Not needed
    }

    @Override
    public List<String[]> readPlayersData() {
        List<String[]> playersData = new ArrayList<>();
        for (int i = 1; i < executions.size(); i++) {
            playersData.add(executions.get(i));
        }
        return playersData;
    }

    @Override
    public String[] readTrials() {
        return executions.get(0);
    }

    @Override
    public boolean fileIsEmpty() {
        return executions == null;
    }

    @Override
    public void deleteFile() {
        try {
            new FileWriter(filename).close();
        } catch (IOException e) {
            // handle exception
        }
    }
}
