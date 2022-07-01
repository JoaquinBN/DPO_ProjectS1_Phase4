package PersistenceLayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExecutionJSONManager implements ExecutionManager {
    private static final String filename = "files/Execution.json";
    private final Gson gson;
    private final List<String[]> executions;

    public ExecutionJSONManager(Gson gson, List<String[]> executions) throws FileNotFoundException {
        this.executions = gson.fromJson(gson.newJsonReader(new FileReader(filename)), List.class);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
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
        return executions;
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
