package PresentationLayer.Views;

import java.util.Scanner;

/**
 * MainMenuView is a class that displays messages to the user interface.
 */
public class MainMenuView {

    /**
     * Displays menu for user to choose file format
     * @return String representing user choice
     */
    public String selectFormatDisplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                The IEEE needs to know where your allegiance lies.
                
                    I)  People’s Front of Engineering (CSV)
                    II) Engineering People’s Front (JSON)
                """);
        System.out.print("Pick a faction:  ");
        return scanner.next();
    }
    /**
     * Display main menu
     * @return The menu choice.
     */
    public char mainMenuDisplay(){
        Scanner input = new Scanner(System.in);
        char option;
        System.out.println("""
                 _____ _            _____      _       _
                /__   \\ |__   ___  /__   \\_ __(_) __ _| |___
                  / /\\/ '_ \\ / _ \\   / /\\/ '__| |/ _` | / __|
                 / /  | | | |  __/  / /  | |  | | (_| | \\__ \\
                 \\/   |_| |_|\\___|  \\/   |_|  |_|\\__,_|_|___/
                
                Welcome to The Trials. Who are you?
                
                    A) The Composer
                    B) This year’s Conductor
                """);
        System.out.print("Enter a role: ");
        option = input.next().charAt(0);

        return option;
    }

    /**
     * Display error message.
     * @param error The error message.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Display message.
     * @param message The message.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
