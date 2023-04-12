package org.skyjo;

import org.skyjo.game.*;
import org.skyjo.settings.PropertiesFile;
import org.skyjo.ui.UI;

public class Main {

    public static void main(String[] args) {
        final int NB_PLAYER=2;


        Game game = new Game();
        game.generatePlayer(NB_PLAYER);
        UI window = new UI();
    }
}