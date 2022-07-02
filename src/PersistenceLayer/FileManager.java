package PersistenceLayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * FileManager is a class user to manage the possibility of files or directory not existing
 */
public class FileManager {

    /**
     * Constructor for FileManager
     */
    public FileManager() {
    }

    /**
     * Check if the directory exists and creates if it doesn't.
     * @return true if the directory exists, false if it doesn't
     */
    public boolean checkIfDirectoryExists() {
        File file = new File("files");
        return file.isDirectory();
    }

    /**
     * Check if the execution csv file exists and creates if it doesn't.
     * @throws IOException if the file could not be created.
     */
    public void createExecutionCSVFileIfNecessary() throws IOException {
        File file = new File("files/Execution.csv");
        if(!file.exists())
            Files.createFile(file.toPath());
    }

    /**
     * Check if the trial csv file exists and creates if it doesn't.
     * @throws IOException if the file could not be created.
     */
    public void createTrialsCSVFileIfNecessary() throws IOException {
        File file = new File("files/Trials.csv");
        if(!file.exists())
            Files.createFile(file.toPath());
    }

    /**
     * Check if the edition csv file exists and creates if it doesn't.
     * @throws IOException if the file could not be created.
     */
    public void createEditionsCSVFileIfNecessary() throws IOException {
        File file = new File("files/Editions.csv");
        if(!file.exists())
            Files.createFile(file.toPath());
    }

    /**
     * Check if the execution json file exists and creates if it doesn't.
     * @throws IOException if the file could not be created.
     */
    public void createExecutionJSONFileIfNecessary() throws IOException {
        File file = new File("files/Execution.json");
        if(!file.exists())
            Files.createFile(file.toPath());
    }

    /**
     * Check if the trial json file exists and creates if it doesn't.
     * @throws IOException if the file could not be created.
     */
    public void createTrialsJSONFileIfNecessary() throws IOException {
        File file = new File("files/Trials.json");
        if(!file.exists())
            Files.createFile(file.toPath());

    }

    /**
     * Check if the edition json file exists and creates if it doesn't.
     * @throws IOException if the file could not be created.
     */
    public void createEditionsJSONFileIfNecessary() throws IOException {
        File file = new File("files/Editions.json");
        if(!file.exists())
            Files.createFile(file.toPath());
    }
}
