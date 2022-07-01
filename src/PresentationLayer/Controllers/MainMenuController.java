package PresentationLayer.Controllers;

import BusinessLayer.ConductorManager;
import BusinessLayer.EditionManager;
import BusinessLayer.Entities.Player;
import BusinessLayer.PlayerManager;
import BusinessLayer.TrialManager;
import PersistenceLayer.*;
import PresentationLayer.Views.MainMenuView;

import java.io.FileNotFoundException;

public class MainMenuController {
    private final MainMenuView mainMenuView;
    private final ComposerController composerController;
    private final ConductorController conductorController;
    private final PlayerManager playerManager;
    private final EditionManager editionManager;
    private final TrialManager trialManager;
    private final ConductorManager conductorManager;
    private EditionsFileManager editionsFileManager;
    private TrialsFileManager trialsFileManager;
    private ExecutionFileManager executionFileManager;

    /**
     * Constructor for the MainMenuController.
     * @param mainMenuView
     * @param composerController
     * @param conductorController
     */
    public MainMenuController(MainMenuView mainMenuView, ComposerController composerController, ConductorController conductorController, PlayerManager playerManager, EditionManager editionManager, TrialManager trialManager, ConductorManager conductorManager) {
        this.mainMenuView = mainMenuView;
        this.composerController = composerController;
        this.conductorController = conductorController;
        this.playerManager = playerManager;
        this.editionManager = editionManager;
        this.trialManager = trialManager;
        this.conductorManager = conductorManager;
    }

    public void selectFormatDisplay() {
        String format = mainMenuView.selectFormatDisplay();
        switch (format) {
            case "I" -> {
                mainMenuView.showMessage("\nLoading data from CSV files...\n");
                executionFileManager = new ExecutionCSVManager();
                editionsFileManager = new EditionCSVManager();
                trialsFileManager = new TrialsCSVManager();
            }
            case "II" -> {
                mainMenuView.showMessage("\nLoading data from JSON files...\n");
                try {
                    executionFileManager = new ExecutionJSONManager();
                    editionsFileManager = new EditionJSONManager();
                    trialsFileManager = new TrialsJSONManager();
                } catch (FileNotFoundException e) {
                    System.out.println("Error");
                }
            }
            default -> {
                mainMenuView.showError("\nInvalid selection. Please choose again.\n");
                selectFormatDisplay();
            }
        }
        editionManager.setFileManagers(editionsFileManager, executionFileManager);
        trialManager.setTrialsFileManager(trialsFileManager);
        conductorManager.setFileManagers(editionsFileManager, trialsFileManager, executionFileManager);
        playerManager.setExecutionFileManager(executionFileManager);
        mainMenuDisplay();
    }
    /**
     * Starts the main menu.
     */
    public void mainMenuDisplay() {
        switch (mainMenuView.mainMenuDisplay()) {
            case 'A' -> composerController.start();
            case 'B' -> conductorController.start();
            default -> {
                mainMenuView.showError("\nInvalid role. Choose A or B.\n");
                mainMenuDisplay();
            }
        }


    }


}
