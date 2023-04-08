package org.skyjo;

import org.skyjo.game.*;
import org.skyjo.settings.PropertiesFile;
import org.skyjo.ui.UI;

public class Main {

    public static void main(String[] args) {
        int round=0;
        int nb_player=0;

        Game game = new Game();
        game.generatePlayer(nb_player);
        UI window = new UI();
    }
}