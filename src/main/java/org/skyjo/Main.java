package org.skyjo;

import org.skyjo.game.*;
import org.skyjo.settings.PropertiesFile;
import org.skyjo.ui.UI;

public class Main {
    private static final int DECK_COLS = 4, DECK_ROWS = 3;

    public static void main(String[] args) {


        Game game = new Game(DECK_ROWS, DECK_COLS);
        game.generatePlayer(2);
        UI window = new UI(game, DECK_ROWS, DECK_COLS);

    }
}