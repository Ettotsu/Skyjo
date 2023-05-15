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

        if (args.length > 0) {
            if (args[0].equals("cli")) {
                new CLI(game);
            } else if (args[0].equals("gui")) {
                new UI(game);
            } else {
                System.out.println("Invalid argument");
            }
        } else {
            System.out.println("Welcome to Fail Your Deutec!\n\nEnter 0 for CLI or 1 for GUI:");
            int n = -1;
            Scanner scanner = new Scanner(System.in);
            while (n < 0 || n > 1) {
                n = scanner.nextInt();
                if (n < 0 || n > 1) {
                    System.out.println("Are you already trying to break the game ???"); //Easter egg de zinzin
                }
            }
            if (n == 0) {
                new CLI(game);
            }
            else {
               UI window = new UI(game);
            }
        }
    }
}