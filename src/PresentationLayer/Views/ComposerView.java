package PresentationLayer.Views;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * ComposerView is a class that displays messages to the user interface.
 */
public class ComposerView {
    private final Scanner sc;

    /**
     * Constructor for the ComposerView class.
     */
    public ComposerView() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays the menu for the composer.
     * @return The menu choice.
     */
    public String managementMenu() {
        System.out.println("\n\t1) Manage Trial");
        System.out.println("\t2) Manage Editions");
        System.out.println("\n\t3) Exit");
        System.out.print("\nEnter an option: ");
        return sc.nextLine();
    }

    /**
     * Displays the menu for the composer to manage trials.
     * @return The menu choice.
     */
    public String manageTrialsMenu() {
        System.out.println("\n\ta) Create Trial");
        System.out.println("\tb) List Trials");
        System.out.println("\tc) Delete Trial");
        System.out.println("\n\td) Back");
        System.out.print("\nEnter an option: ");
        return sc.nextLine();
    }

    /**
     * Reads a string input by the user.
     * @param message The message to be displayed.
     * @return The string input by the user
     */
    public String readName(String message) {
        System.out.print("Enter the " + message + ": ");
        return sc.nextLine();
    }

    /**
     * reads a long variable from the user. Returns -1 if an exception was caught
     * @param message The message to be displayed to the user.
     * @return The long variable.
     */
    public Long readLong(String message) {
        System.out.print("Enter the " + message + ": ");
        try {
            return sc.nextLong();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            return (long) -1;
        }

    }
    /**
     * Reads the probability desired for the trial.
     * @param probability The probability of the trial.
     * @return The probability of the trial.
     */
    public String readProbability(String probability) {
        return String.valueOf(checkForExceptions("Enter the "+ probability + " probability: ", "\nThe "+ probability + " probability must be an integer between 0 and 100. Please try again:\n"));
    }

    /**
     * Lists all the trials in the system.
     * @param i index of the trial.
     * @param trialName name of the trial.
     */
    public void listTrials(int i, String trialName) {
        System.out.print("\n\t" + i + ") " + trialName);
    }

    /**
     * Displays the option to go to previous menu.
     * @param index The index of the back option.
     */
    public void showBack(int index) {
        System.out.print("\n\t" + index + ") Back\n");
    }

    /**
     * Asks the user to choose an option.
     * @return The option chosen.
     */
    public int getIndexInput(){ return checkForExceptions("\nEnter an option: ", "\nThe index must be an integer. \n") - 1;}

    /**
     * Displays the menu for the composer to manage editions.
     * @return The menu choice.
     */
    public String manageEditionsMenu() {
        System.out.println("\n\ta) Create Edition");
        System.out.println("\tb) List Editions");
        System.out.println("\tc) Duplicate Edition");
        System.out.println("\td) Delete Edition");
        System.out.println("\n\te) Back");
        System.out.print("\nEnter an option: ");
        return sc.nextLine();
    }

    /**
     * Reads an integer depending on the user's input.
     * @param dataType The data type to ask the user.
     * @return The integer input by the user, -1 if an exception was caught
     */
    public int readIntegerValue(String dataType) {
        return checkForExceptions("Enter the " + dataType + ": ", "\nThe " + dataType + " must be an integer.\n");
    }

    /**
     * Displays and lists all the trials in the system.
     * @param availableTrials The number of trials in the edition.
     * @param index The index of the trial.
     * @param totalTrials The total number of trials desired in the edition.
     * @return The trial index .
     */
    public int pickTrial(int availableTrials, int index, int totalTrials){
        int trialIndex;
        trialIndex = checkForExceptions("Pick a trial (" + index + "/" + totalTrials + "): ","\nThe number of the trial must be an integer.\n");
        if(trialIndex > 0 && trialIndex <= availableTrials){
            return trialIndex;
        }
        else{
            System.out.println("\nThe number of the trial must be between 1 and " + availableTrials + ".\n");
            return pickTrial(availableTrials, index, totalTrials);
        }
    }

    /**
     * Checks if the input is correct.
     * @param message The message to be displayed.
     * @param errorMessage The error message needed.
     * @return Whether it is true or false.
     */
    public int checkForExceptions(String message, String errorMessage) {
        try{
            System.out.print(message);
            return sc.nextInt();
        } catch (NumberFormatException | InputMismatchException  exception) {
            System.out.print(errorMessage);
        }finally {
            sc.nextLine();
        }
        return -1;
    }


    /**
     * Displays edition info.
     * @param year The year of the edition.
     * @param numberOfPlayers The number of players in the edition.
     */
    public void showEdition(int year, int numberOfPlayers) {
        System.out.println("\nYear: " + year);
        System.out.println("Players: " + numberOfPlayers);
        System.out.println("Trials:");
    }

    /**
     * Displays trial info.
     * @param k The index of the trial.
     * @param trialName The name of the trial.
     * @param typeOfTrial The type of the trial.
     */
    public void listEditionTrials(int k, String trialName, String typeOfTrial) {
        System.out.print("\t" + k + "- " + trialName + " (" + typeOfTrial + ")\n");
    }

    /**
     * Displays message if edition or trial is created successfully.
     * @param type edition or trial.
     */
    public void createSuccess(String type) {
        System.out.println("\nThe " + type + " was created successfully!");
    }

    /**
     * Display message if edition is cloned successfully.
     */
    public void duplicateEditionSuccess() {
        System.out.println("\nThe edition was cloned successfully!");
    }

    /**
     * Displays message if edition or trial is deleted successfully.
     * @param type edition or trial.
     */
    public void deleteSuccess(String type) {
        System.out.println("\nThe " + type + " was successfully deleted.");
    }

    /**
     * Displays trials' years.
     * @param i The index of the trial.
     * @param year The year of the trial.
     */
    public void listEditions(int i, int year) {
        System.out.println("\t" + i + ") The Trials " + year);
    }

    /**
     * Displays message when finishing program.
     */
    public void exitProgram() {
        System.out.println("\nShutting down...");
    }

    /**
     * Displays error message desired.
     * @param error The error message.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Displays  message desired.
     * @param message The error message.
     */
    public void showMessage(String message) {
        System.out.print(message);
    }

    /**
     * Get trial type.
     * @return The type of the trial.
     */
    public int getTrialTypeInput() {
        return checkForExceptions("\nEnter the trial's type: ", "\nThe trial's type must be an integer.\n");
    }

    /**
     * Display all trial types.
     */
    public void showTrialTypes() {
        System.out.println("\n\t--- Trial types ---\n");
        System.out.print("\t1) Paper publication\n");
        System.out.print("\t2) Master studies\n");
        System.out.print("\t3) Doctoral thesis defense\n");
        System.out.print("\t4) Budget request\n");
    }

    /**
     * Display message for deletion confirmation.
     * @param s edition or trial.
     * @return the confirmation input.
     */
    public String showDeletionConfirmation(String s) {
        System.out.print("\nEnter the " + s + " for confirmation (type 'cancel' to avoid deletion): ");
        return sc.nextLine();
    }

    /**
     * Display current message for current selected type.
     * @param type The type.
     */
    public void showList(String type) {
        System.out.println("\nHere are the current " + type + ", do you want to see more details or go back?\n");
    }

    /**
     * Display message to delete type of entity selected.
     * @param type The type of entity.
     */
    public void showDelete(String type) {
        System.out.println("\nWhich " + type + " do you want to delete?\n\n");
    }
}
