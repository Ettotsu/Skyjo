package org.skyjo.ui;

import org.skyjo.game.Game;
import org.skyjo.game.Player;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class PlayersWindow extends JOptionPane {

        private JSpinner playersSpinner;
        private int nbPlayers=0;

        private JOptionPane namesOptionPane;
        private JPanel panel;
        private JTextField nameField;
        LinkedHashMap<Integer, Player> playersMap;

        public PlayersWindow(Game game){
            super("Number of players");
            this.setSize(200,100);
            this.setVisible(true);


            playersSpinner = new JSpinner(new SpinnerNumberModel(2,2,8,1));
            if(this.showOptionDialog(null,playersSpinner,"Enter player number",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null)==JOptionPane.OK_OPTION){
                this.nbPlayers = (int) playersSpinner.getValue();
                askForNames(game);
            }
        }

        private void askForNames(Game game){
            panel = new JPanel(new GridLayout(nbPlayers,2));
            for(int i=1;i<=nbPlayers;i++){
                panel.add(new JLabel("Player " + i + ": "));
                panel.add(new JTextField());
            }

            if(namesOptionPane.showConfirmDialog(null,panel,"Enter the names of the players",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.OK_OPTION){
                playersMap = new LinkedHashMap<>();
                for(int i=1;i<=nbPlayers;i++){
                    nameField = (JTextField) panel.getComponent(i*2-1);
                    System.out.println(nameField.getText());
                    playersMap.put(i,new Player(i,nameField.getText()));
                }
                game.setNbPlayers(nbPlayers);
                game.setPlayersMap(playersMap);
                game.startGame();
            }
        }
}
