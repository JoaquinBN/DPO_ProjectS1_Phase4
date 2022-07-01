import BusinessLayer.ConductorManager;
import BusinessLayer.EditionManager;
import BusinessLayer.PlayerManager;
import BusinessLayer.TrialManager;
import PersistenceLayer.ExecutionFileManager;
import PersistenceLayer.TrialsCSVManager;
import PresentationLayer.Controllers.ComposerController;
import PresentationLayer.Controllers.ConductorController;
import PresentationLayer.Controllers.MainMenuController;
import PresentationLayer.Views.ComposerView;
import PresentationLayer.Views.ConductorView;
import PresentationLayer.Views.MainMenuView;

import java.io.IOException;

public class Main {
    /**
     * Main function of the program
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException {
        PlayerManager playerManager = new PlayerManager();
        TrialManager trialManager = new TrialManager();
        EditionManager editionManager = new EditionManager();
        ConductorView conductorView = new ConductorView();
        ConductorManager conductorManager = new ConductorManager(trialManager);
        ConductorController conductorController = new ConductorController(conductorManager, conductorView, playerManager);
        ComposerView composerView = new ComposerView();
        ComposerController composerController = new ComposerController(editionManager, trialManager, composerView);
        MainMenuView mainMenuView = new MainMenuView();
        MainMenuController mainMenuController = new MainMenuController(mainMenuView, composerController, conductorController, playerManager, editionManager, trialManager, conductorManager);
        mainMenuController.selectFormatDisplay();
    }
}
