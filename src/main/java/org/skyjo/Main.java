package org.skyjo;

import org.skyjo.game.*;
import org.skyjo.ui.UI;
import org.skyjo.terminal.*;

import java.util.Scanner;

public class Main {
    /**
     * Main method.
     * @param args // Command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game();

        if (args.length > 0) { // If there is an argument
            if (args[0].equals("cli")) { // If the argument is "cli"
                new CLI(game); // Starts the CLI version of the game
            } else if (args[0].equals("gui")) { // If the argument is "gui"
                new UI(game); // Starts the GUI version of the game
            } else {
                System.out.println("Invalid argument");
            }
        } else {
            System.out.println("Welcome to Fail Your Deutec!\n\nEnter 0 for CLI or 1 for GUI:"); // Welcome message
            int n = -1;
            Scanner scanner = new Scanner(System.in); // Scanner to read the input
            while (n < 0 || n > 1) { // While the input is not 0 or 1
                n = scanner.nextInt();
                if (n < 0 || n > 1) {
                    System.out.println("Are you trying to break the game???"); //Easter egg de zinzin
                }
            }
            if (n == 0) {
                new CLI(game);
            }
            else {
                new UI(game);
            }
        }
    }
}