package PresentationLayer.Controllers;

import BusinessLayer.ConductorManager;
import BusinessLayer.Entities.Players.Player;
import BusinessLayer.PlayerManager;
import PresentationLayer.Views.ConductorView;

import java.util.ArrayList;

/**
 * ConductorController is a class that manages the connection between the business layer and the user interface.
 */
public class ConductorController {
    private final ConductorManager  conductorManager;
    private final ConductorView conductorView;
    private final PlayerManager playerManager;
    private int startIndex;

    /**
     * Constructor for the ConductorController.
     * @param conductorManager the conductor manager
     * @param conductorView the conductor view
     * @param playerManager the player manager
     */
    public ConductorController(ConductorManager conductorManager, ConductorView conductorView, PlayerManager playerManager) {
        this.conductorManager = conductorManager;
        this.conductorView = conductorView;
        this.playerManager = playerManager;
    }


    /**
     * Starts the conductor.
     */
    public void start() {
        boolean shutDown = false;
        conductorView.showMessage("\nEntering execution mode...\n");
        if(printExceptionMessage(conductorManager.loadDataForTrials(), true))
            shutDown = true;
        if(conductorManager.fileIsEmpty() == 1){
            if(conductorManager.loadDataForCurrentEdition()){
                conductorView.showMessage("\n---The Trials 2022---\n\n");
                for(int i = 0; i < conductorManager.getTotalPlayer(); i++){
                    playerManager.addPlayer(conductorView.askForPlayerName(i+1, conductorManager.getTotalPlayer()));
                }
                startIndex = 0;
                executeEdition();
            }else
                conductorView.showError("\nNo edition is defined for the current year (2022).\n");
        }else if (conductorManager.fileIsEmpty() == 0){
            conductorView.showMessage("\n---The Trials 2022---\n");
            if(printExceptionMessage(conductorManager.loadDataForExecution(), true))
                shutDown = true;
            startIndex = conductorManager.getStartIndex();
            if(printExceptionMessage(playerManager.loadPlayersData(), false))
                shutDown = true;
            conductorManager.initializeEditionData(playerManager.getTotalPlayers());
            if(!shutDown)
                executeEdition();
        }else
            printExceptionMessage(true, true);

        conductorView.showMessage("Shutting down...\n");
    }

    /**
     * Prints the error message given by the conductor or the player managers
     * @param isException true if an exception has been caught by the manager, false otherwise
     * @param isConductorManager true if the error is from the conductor, false otherwise
     * @return true if the program should shut down, false otherwise
     */
    private boolean printExceptionMessage(boolean isException, boolean isConductorManager){
        if(!isException){
            conductorView.showError("\n" + (isConductorManager?conductorManager.getErrorMessage():playerManager.getErrorMessage()) + "\n");
            return true;
        }
        return false;
    }

    /**
     * Executes the current edition.
     */
    private void executeEdition() {
        int i;
        ArrayList<Player> playersEvolved;
        for (i = 0; i < conductorManager.getNumTrials(); i++) {
            playersEvolved = new ArrayList<>();
            conductorView.showMessage("\nTrial #" + (startIndex + 1) + " - " + conductorManager.getTypeOfTrial(i) + "\n");

            //get a specific print in case it is a budget request. Budget requests need different attributes than the others
            //to know if they were passed or failed (this depends on the players as a group, not individually)
            if(conductorManager.isBudgetRequested(i)){
                conductorView.showMessage(conductorManager.isBudgetAcquired(i, playerManager.getSumIPs()));
            }

            for (Player player: playerManager.getPlayers()) {
                if (!player.isDead()) {
                    //set the dataNeeded attribute for the current trial, used only by the doctoral thesis defense and the budget request
                    //as they need to know the player's IPs.
                    conductorManager.setTrialExtraData(i, player.getInvestigationPoints());
                    conductorView.showMessage(conductorManager.getTrialPrintOutput(i, player.getTypeDisplay()));
                    player.addInvestigationPoints(conductorManager.incrementInvestigationPoints(i));
                    conductorView.displayIPCount(player.getInvestigationPoints());
                    playersEvolved.add(playerManager.evolvePlayer(player, conductorManager.isPassed(i), conductorManager.getTypeOfTrial(i)));
                }
            }
            conductorView.showMessage("\n\n");
            for(Player player : playersEvolved){
                if(player != null)
                    conductorView.displayEvolution(player.getName(), player.getType());
            }
            startIndex++;
            if (playerManager.allPlayersAreDead())
                break;
            else if (i != conductorManager.getNumTrials() - 1 && !conductorView.showContinueMessage()) {
                i++;
                break;
            }
        }
        if (playerManager.allPlayersAreDead()) {
            conductorView.showMessage("\n\nTHE TRIALS 2022 HAVE ENDED - PLAYERS LOST \n\n");
            printExceptionMessage(conductorManager.eraseInformationExecutionFile(),true);
        } else if (i == conductorManager.getNumTrials()){
            conductorView.showMessage("\n\nTHE TRIALS 2022 HAVE ENDED - PLAYERS WON \n\n");
            printExceptionMessage(conductorManager.eraseInformationExecutionFile(),true);
        }else {
            conductorView.showMessage("\nSaving & ");
            printExceptionMessage(conductorManager.saveData(i, startIndex),true);
            printExceptionMessage(playerManager.saveData(),false);
        }

    }


}
