package PersistenceLayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileManager {
    public FileManager() {
    }

    public boolean checkIfDirectoryExists() {
        File file = new File("files");
        return file.isDirectory();
    }

    public void createExecutionCSVFileIfNecessary() throws IOException {
        File file = new File("files/Execution.csv");
        if(!file.exists())
            Files.createFile(file.toPath());
    }

    public void createTrialsCSVFileIfNecessary() throws IOException {
        File file = new File("files/Trials.csv");
        if(!file.exists())
            Files.createFile(file.toPath());
    }

    public void createEditionsCSVFileIfNecessary() throws IOException {
        File file = new File("files/Editions.csv");
        if(!file.exists())
            Files.createFile(file.toPath());
    }

    public void createExecutionJSONFileIfNecessary() throws IOException {
        File file = new File("files/Execution.json");
        if(!file.exists())
            Files.createFile(file.toPath());
    }

    public void createTrialsJSONFileIfNecessary() throws IOException {
        File file = new File("files/Trials.json");
        if(!file.exists())
            Files.createFile(file.toPath());

    }

    public void createEditionsJSONFileIfNecessary() throws IOException {
        File file = new File("files/Editions.json");
        if(!file.exists())
            Files.createFile(file.toPath());
    }
}
