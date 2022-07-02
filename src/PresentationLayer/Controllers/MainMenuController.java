package PresentationLayer.Controllers;

import PresentationLayer.Views.MainMenuView;

/**
 * MainMenuController is a class that manages the connection between the business layer and the user interface.
 */
public class MainMenuController {
    private final MainMenuView mainMenuView;
    private final ComposerController composerController;
    private final ConductorController conductorController;

    /**
     * Constructor for the MainMenuController.
     * @param mainMenuView the main menu view
     * @param composerController the composer controller
     * @param conductorController the conductor controller
     */
    public MainMenuController(MainMenuView mainMenuView, ComposerController composerController, ConductorController conductorController) {
        this.mainMenuView = mainMenuView;
        this.composerController = composerController;
        this.conductorController = conductorController;
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
