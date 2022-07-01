package PersistenceLayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExecutionJSONManager implements ExecutionManager {
    @Override
    public void writePlayersData(List<String[]> playersData) {

    }

    @Override
    public void writeTrials(String[] allTrials) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter writer = new FileWriter("files/Execution.csv");
            gson.toJson(allTrials, writer);
            writer.close();
        } catch (IOException e) {
            // handle exception
        }
    }

    @Override
    public List<String[]> readPlayersData() {
        return null;
    }

    @Override
    public String[] readTrials() {
        return new String[0];
    }

    @Override
    public boolean fileIsEmpty() {
        return false;
    }

    @Override
    public void deleteFile() {

    }
}
