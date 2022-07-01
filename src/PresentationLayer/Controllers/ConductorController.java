package PresentationLayer.Controllers;

import BusinessLayer.ConductorManager;
import BusinessLayer.Entities.Player;
import BusinessLayer.PlayerManager;
import PresentationLayer.Views.ConductorView;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.ArrayList;

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
        try{
            conductorView.showMessage("\nEntering execution mode...\n");
            conductorManager.loadDataForTrials();
            if(conductorManager.fileIsEmpty()){
                if(conductorManager.loadDataForCurrentEdition()){
                    conductorView.showMessage("\n---The Trials 2022---\n\n");
                    for(int i = 0; i < conductorManager.getTotalPlayer(); i++){
                        playerManager.addPlayer(conductorView.askForPlayerName(i+1, conductorManager.getNumberOfPlayers()));
                    }
                    startIndex = 0;
                    executeEdition();
                }else
                    conductorView.showError("\nNo edition is defined for the current year (2022).\n");
            }else{
                conductorView.showMessage("\n---The Trials 2022---\n");
                startIndex = conductorManager.loadDataForExecution();
                playerManager.loadPlayersData();
                conductorManager.initializeEditionData(playerManager.getTotalPlayers());
                executeEdition();
            }
        } catch (IOException | CsvException e) {
            conductorView.showError("\nError loading data.\n");
        }

        conductorView.showMessage("Shutting down...\n");
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

            if(conductorManager.isBudgetRequested(i)){
                conductorView.showMessage(conductorManager.isBudgetAcquired(i, playerManager.getSumIPs()));
            }

            for (Player player: playerManager.getPlayers()) {
                if (!player.isDead()) {
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
            conductorView.showMessage("\nTHE TRIALS 2022 HAVE ENDED - PLAYERS LOST \n\n");
            try{
                conductorManager.eraseInformationExecutionFile();
            } catch (IOException e) {
                conductorView.showError("\nError erasing data.\n");
            }
        } else if (i == conductorManager.getNumTrials()){
            conductorView.showMessage("\nTHE TRIALS 2022 HAVE ENDED - PLAYERS WON \n\n");
            try{
                conductorManager.eraseInformationExecutionFile();
            } catch (IOException e) {
                conductorView.showError("\nError erasing data.\n");
            }
        }else {
            conductorView.showMessage("\nSaving & ");
            try{
                conductorManager.saveData(i, startIndex);
                playerManager.saveData();
            } catch (IOException | CsvException e) {
                conductorView.showError("\nError saving files.\n");
            }
        }

    }


}
