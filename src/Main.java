import BusinessLayer.ConductorManager;
import BusinessLayer.EditionManager;
import BusinessLayer.PlayerManager;
import BusinessLayer.TrialManager;
import PersistenceLayer.*;
import PresentationLayer.Controllers.ComposerController;
import PresentationLayer.Controllers.ConductorController;
import PresentationLayer.Controllers.MainMenuController;
import PresentationLayer.Views.ComposerView;
import PresentationLayer.Views.ConductorView;
import PresentationLayer.Views.MainMenuView;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    /**
     * Main function of the program
     * @param args command line arguments
     */
    public static void main(String[] args){
        ExecutionFileManager executionFileManager;
        EditionsFileManager editionsFileManager;
        TrialsFileManager trialsFileManager;
        PlayerManager playerManager = new PlayerManager();
        TrialManager trialManager = new TrialManager();
        EditionManager editionManager = new EditionManager();
        ConductorView conductorView = new ConductorView();
        ConductorManager conductorManager = new ConductorManager(trialManager);
        ConductorController conductorController = new ConductorController(conductorManager, conductorView, playerManager);
        ComposerView composerView = new ComposerView();
        ComposerController composerController = new ComposerController(editionManager, trialManager, composerView);
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuController mainMenuController = new MainMenuController(mainMenuView, composerController, conductorController);
        FileManager fileManager = new FileManager();
        String format;

        if(!fileManager.checkIfDirectoryExists())
            mainMenuView.showError("\nError: The directory 'files' does not exist. Please create it within the project's directories.");
        else {
            try {
                fileManager.createTrialsCSVFileIfNecessary();
                fileManager.createEditionsCSVFileIfNecessary();
                fileManager.createExecutionCSVFileIfNecessary();
                fileManager.createTrialsJSONFileIfNecessary();
                fileManager.createEditionsJSONFileIfNecessary();
                fileManager.createExecutionJSONFileIfNecessary();
            } catch (IOException e) {
                mainMenuView.showError("\nError: The files could not be created. Please check the permissions of the project's directories.");
            }
            do {
                format = mainMenuView.selectFormatDisplay();
                switch (format) {
                    case "I" -> {
                        mainMenuView.showMessage("\nLoading data from CSV files...\n");
                        executionFileManager = new ExecutionCSVManager();
                        editionsFileManager = new EditionCSVManager();
                        trialsFileManager = new TrialsCSVManager();
                        editionManager.setFileManagers(editionsFileManager, executionFileManager);
                        trialManager.setTrialsFileManager(trialsFileManager);
                        conductorManager.setFileManagers(editionsFileManager, trialsFileManager, executionFileManager);
                        playerManager.setExecutionFileManager(executionFileManager);
                    }
                    case "II" -> {
                        mainMenuView.showMessage("\nLoading data from JSON files...\n");
                        try {
                            executionFileManager = new ExecutionJSONManager();
                            editionsFileManager = new EditionJSONManager();
                            trialsFileManager = new TrialsJSONManager();
                            editionManager.setFileManagers(editionsFileManager, executionFileManager);
                            trialManager.setTrialsFileManager(trialsFileManager);
                            conductorManager.setFileManagers(editionsFileManager, trialsFileManager, executionFileManager);
                            playerManager.setExecutionFileManager(executionFileManager);
                        } catch (FileNotFoundException e) {
                            System.out.println("Error");
                        }
                    }
                    default -> mainMenuView.showError("\nInvalid selection. Please choose again.\n");

                }
            }while(!(format.equals("I") || format.equals("II")));
            mainMenuController.mainMenuDisplay();
        }

    }
}
