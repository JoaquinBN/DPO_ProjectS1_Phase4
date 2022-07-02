package PresentationLayer.Views;

import java.util.Scanner;

/**
 * ConductorView is a class that displays messages to the user interface.
 */
public class ConductorView {
    private final Scanner sc;

    /**
     * Constructor for the ConductorView class.
     */
    public ConductorView() {
        sc = new Scanner(System.in);
    }

    /**
     * Show error message.
     * @param error The error message.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Show message
     * @param message The message.
     */
    public void showMessage(String message) {
        System.out.print(message);
    }

    /**
     * Ask for player name.
     * @param index The index of the player.
     * @param totalPlayers The total number of players.
     * @return The name of the player.
     */
    public String askForPlayerName(int index, int totalPlayers) {
        System.out.print("Enter the player's name (" + index + "/" + totalPlayers + "): ");
        return sc.nextLine();
    }

    /**
     * Show continue message
     * @return The answer of the user.
     */
    public boolean showContinueMessage(){
        String answer;
        while(true){
            System.out.print("Continue the execution? [yes/no]: ");
            answer = sc.nextLine();
            if(answer.equals("yes")){
                return true;
            }else if(answer.equals("no")){
                return false;
            }else{
                System.out.println("\nInvalid answer. Please try again:");
            }
        }
    }

    /**
     * Display the investigation points of a player.
     * @param investigationPoints The investigation points of the player.
     */
    public void displayIPCount(int investigationPoints){
        System.out.print(" PI count: " + investigationPoints);
        if(investigationPoints == 0){
            System.out.print(" - Disqualified!");
        }
    }

    /**
     * Show the new player's form if he evolved after passing a trial
     * @param playerName The name of the player.
     * @param playerForm The new form of the player.
     */
    public void displayEvolution(String playerName, String playerForm) {
        System.out.print(playerName + " is now a " + playerForm + " (with 5 PI). ");
    }
}
