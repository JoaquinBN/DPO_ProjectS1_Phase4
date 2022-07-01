package PresentationLayer.Controllers;

import PresentationLayer.Views.MainMenuView;

public class MainMenuController {
    private final MainMenuView mainMenuView;
    private final ComposerController composerController;
    private final ConductorController conductorController;

    /**
     * Constructor for the MainMenuController.
     * @param mainMenuView
     * @param composerController
     * @param conductorController
     */
    public MainMenuController(MainMenuView mainMenuView, ComposerController composerController, ConductorController conductorController) {
        this.mainMenuView = mainMenuView;
        this.composerController = composerController;
        this.conductorController = conductorController;
    }

    public void selectFormatDisplay() {
        String format = mainMenuView.selectFormatDisplay();
        switch (format) {
            case "I" -> {
                mainMenuView.showMessage("\nLoading data from CSV files...\n");
                mainMenuDisplay();
            }
            case "II" -> {
                mainMenuView.showMessage("\nLoading data from JSON files...\n");
                mainMenuDisplay();
            }
            default -> {
                mainMenuView.showError("\nInvalid selection. Please choose again.\n");
                selectFormatDisplay();
            }
        }
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
