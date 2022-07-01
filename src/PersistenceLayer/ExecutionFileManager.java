package PersistenceLayer;

import java.util.List;

public interface ExecutionFileManager {
    void writePlayersData(List<String[]> playersData);
    void writeTrials(String[] allTrials);
    List<String[]> readPlayersData();
    String[] readTrials();
    boolean fileIsEmpty();
    void deleteFile();
}
