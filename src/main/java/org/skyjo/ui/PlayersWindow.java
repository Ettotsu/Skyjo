package org.skyjo.ui;

import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class PlayersWindow extends JOptionPane {
    public static final int MIN_PLAYERS = 2, MAX_PLAYERS = 8; // Min a  nd max number of players
    private int nbPlayers; // Number of players in the game

    /**
     * Constructor
     * @param game the game
     */
    public PlayersWindow(Game game) {
        super("Number of players"); // Title of the window
        this.setSize(200,100); // Size of the window
        this.setVisible(true);
        JSpinner playersSpinner = new JSpinner(new SpinnerNumberModel(MIN_PLAYERS, MIN_PLAYERS, MAX_PLAYERS, 1)); // Spinner to choose the number of players
        if(showOptionDialog(null, playersSpinner,"Enter player number",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null)==JOptionPane.OK_OPTION){
            this.nbPlayers = (int) playersSpinner.getValue();
            askForNames(game); // Open the window to ask for the names
        }
    }

    /**
     * Window to ask for the names of the players
     * @param game the game object
     */
    private void askForNames(Game game) {
        JPanel panel = new JPanel(new GridLayout(nbPlayers, 2));
        for(int i=1;i<=nbPlayers;i++){ // For each player, add a label and a text field
            panel.add(new JLabel("Player " + i + ": "));
            panel.add(new JTextField());
        }

        if(showConfirmDialog(null, panel,"Enter the names of the players",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.OK_OPTION){
            String[] names = new String[nbPlayers+1];
            for(int i=1;i<=nbPlayers;i++){
                JTextField nameField = (JTextField) panel.getComponent(i * 2 - 1);
                names[i] = nameField.getText();
            }
            game.makePlayersMap(nbPlayers, names); // Makes the players map
            game.startGame(); // Starts the game
        }
    }
}
