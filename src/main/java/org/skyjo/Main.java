package org.skyjo;

import org.skyjo.game.*;
import org.skyjo.ui.UI;
import org.skyjo.terminal.*;

public class Main {
    /**
     * Main method.
     * @param args // Command line arguments
     */
    public static void main(String[] args) {
            //Game game = new Game();
            //UI window = new UI(game);

            CLI cli = new CLI();
            cli.setPlayers();
            cli.startGame();
    }
}