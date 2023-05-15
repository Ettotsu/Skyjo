package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardButton extends JButton implements CardInterface {

    private final int id; // Card id (position in the deck)
    private final Assets assets; // Assets reference
    private final Game game; // Game reference
    private final UI ui; // UI reference
    private Card card; // Card reference

    /**
     * Constructor
     * The action listener is used for the logic the game
     * @param id the id
     * @param assets the assets
     * @param game the game
     * @param ui the ui
     */
    public CardButton(int id, Assets assets, Game game, UI ui) {
        this.id = id;
        this.assets = assets;
        this.game = game;
        this.ui = ui;

        this.setBorderPainted(false); // Removes the border

        this.setCard(); // Sets the card

        this.addActionListener(e -> {
            if(!game.getFirstRoundDone()) { // First round
                if(!card.isFaceUp()) {
                    if (game.getCurrentPlayer() == game.getNbPlayers()) { // Last player
                        if (game.getCardSelected()) {
                            card.setFaceUp();
                            game.getPlayer(game.getCurrentPlayer()).addScore(card.getValue());
                            game.setFirstRoundDone(); // First round done
                            game.setCardSelected(false);
                            game.setCurrentPlayer(game.getMaxScore(true));
                            game.resetlAllScores();
                            ui.setAllCards(); // Sets all cards
                            ui.enableAllCards(true); // Enables all cards
                            ui.putPlayerInTitle(); // Put the player in the title
                            ui.updatePlayerLabel(); // Updates the player label
                            ui.repaint(); // Repaints the ui
                        }
                        else {
                            card.setFaceUp();
                            game.getPlayer(game.getCurrentPlayer()).addScore(card.getValue());
                            game.setCardSelected(true);
                        }
                    }
                    else {
                        card.setFaceUp();
                        game.getPlayer(game.getCurrentPlayer()).addScore(card.getValue());
                        if(game.getCardSelected()) {
                            ui.incrementCurrentPlayer();
                            game.setCardSelected(false);

                            ui.setAllCards();
                        }
                        else {
                            game.setCardSelected(true);
                        }
                    }
                }
                // else card is already face up
            }

            else { // First round done
                if(game.getDiscardChosen()) { // If the discard has been chosen
                    if(game.getStackChosen()) { // If the stack has been chosen

                        if(!card.isFaceUp()) { // Tests if the card is face up to avoid revealing a face up card
                            card.setFaceUp();
                            game.setStackChosen(false);
                            game.setDiscardChosen(false);

                            doEndTests();
                        }
                    }
                    else { // If the stack has not been chosen
                        game.setDiscard(game.getPlayer(game.getCurrentPlayer()).switchCard(game.getDiscard(), this.id / Game.DECK_COLS, this.id % Game.DECK_COLS));
                        game.setStackChosen(false);
                        game.setDiscardChosen(false);
                        this.setCard();
                        ui.getDiscardButton().setCard();

                        doEndTests();
                    }
                }
                else if(game.getStackChosen()) {
                    game.setDiscard(game.getPlayer(game.getCurrentPlayer()).switchCard(game.getStack().drawCard(), this.id / Game.DECK_COLS, this.id % Game.DECK_COLS));
                    ui.getStackButton().setCard();
                    this.setCard();
                    game.setStackChosen(false);
                    game.setDiscardChosen(false);

                    doEndTests();
                }
            }
        });
    }

    /**
     * Sets the reference to the card
     */
    @Override
    public void setCard() {
        card = game.getPlayer(game.getCurrentPlayer()).getCard(this.id / Game.DECK_COLS, this.id % Game.DECK_COLS);
    }

    /**
     * Paints the card
     * @param g the graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(card.isBlank()){
            setIcon(assets.getCardBlank());
            setDisabledIcon(assets.getCardBlank());
        }
        else if(card.isFaceUp()) {
            setIcon(assets.getCard(card.getValue()+2));
            setDisabledIcon(assets.getCard(card.getValue()+2));
        } else {
            setIcon(assets.getCardBack());
            setDisabledIcon(assets.getCardBack());
        }
        this.repaint();
    }

    /**
     * Does the end tests
     */
    private void doEndTests() {
        boolean flag = false; // Flag to reset keep the currentPlayer to 1 if the round is over
        game.checkPerfectColumn(game.getCurrentPlayer());
        if(game.getEndRound()!=0) {
            int localScore=0;
            for(int i=0; i<Game.DECK_ROWS; i++) {
                for(int j=0; j<Game.DECK_COLS; j++) {
                    game.getPlayer(game.getCurrentPlayer()).getCard(i,j).setFaceUp();
                    localScore = localScore + game.getPlayer(game.getCurrentPlayer()).getCard(i,j).getValue();
                }
            }
            if(game.getCurrentPlayer()+1 == game.getEndRound()) { // The next Player is the one who ends the round
                if(localScore!=game.getMaxScore(false)) {
                    localScore = localScore * 2;
                }
            }
            game.getPlayer(game.getCurrentPlayer()).addScore(localScore);
            int loser = game.over120();
            if(loser!=0){
                JOptionPane.showMessageDialog(null, "Oh nooo, " + game.getPlayer(loser).getName() + " lost the game! They got their DEUTEC!");
                ArrayList<Integer> winners= this.game.getWinners() ;
                JOptionPane.showMessageDialog(null, "Congratulations to the winner !!! " + winners.get(0) + "You successfully failed your DEUTEC!");
                for (int i = 1; i<winners.size(); i++){
                    JOptionPane.showMessageDialog(null,"Ex-aequo with" + winners.get(i));
                }
                ui.mainMenu();
            }
            else {
                flag = true;
                game.resetGame();
                ui.setAllCards();
                ui.putPlayerInTitle();
                ui.updatePlayerLabel();
            }
        }
        else if(game.getPlayer(game.getCurrentPlayer()).checkCardsFlipped()) {
            game.setEndRound(game.getCurrentPlayer());
        }
        if(!flag) {
            game.incrementCurrentPlayer();
        }
        ui.setAllCards();
        ui.putPlayerInTitle();
        ui.updatePlayerLabel();
    }
}
