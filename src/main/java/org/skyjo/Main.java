package org.skyjo;

import org.skyjo.game.*;
import org.skyjo.ui.UI;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            UI window = new UI(game);
            game.getUI(window);
        });
    }
}