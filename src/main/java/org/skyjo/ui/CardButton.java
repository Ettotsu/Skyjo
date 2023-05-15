package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class CardButton extends JButton implements CardInterface {

    private final int id;

    private final Assets assets;
    private final Game game;

    private Card card;


    public CardButton(int id, Assets assets, Game game, UI ui) {
        this.id = id;
        this.assets = assets;
        this.game = game;

        this.setBorderPainted(false);

        this.setCard();

        this.addActionListener(e -> {

            System.out.println("\nCurrentPlayer = " + game.getCurrentPlayer());
            System.out.println("FirstRoundDone = " + game.getFirstRoundDone() + "\nCardSelected = " + game.getCardSelected());

            if(!game.getFirstRoundDone()) {
                if(!card.isFaceUp()) {
                    if (game.getCurrentPlayer() == game.getNbPlayers()) {
                        if (game.getCardSelected()) {
                            card.setFaceUp();
                            game.getPlayer(game.getCurrentPlayer()).addScore(card.getValue());
                            game.setFirstRoundDone();
                            System.out.println("First round done");
                            game.setCardSelected(false);
                            game.setCurrentPlayer(game.getMaxScore());
                            game.resetlAllScores();
                            ui.setAllCards();
                            ui.EnableAllCards(true);
                            ui.putPlayerInTitle();
                            ui.updatePlayerLabel();
                            ui.repaint();
                        } else {
                            card.setFaceUp();
                            game.getPlayer(game.getCurrentPlayer()).addScore(card.getValue());
                            game.setCardSelected(true);
                        }
                    } else {
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
                else {
                    System.out.println("Card already face up");
                }
            }
            else {
                if(game.getDiscardChosen()) {
                    if(game.getStackChosen()) {
                        if(!card.isFaceUp()) {
                            card.setFaceUp();
                            System.out.println("Card clicked");
                            game.checkPerfectColumn(game.getCurrentPlayer());
                            if(game.getEndRound()!=0) {
                                if(game.getCurrentPlayer()+1 == game.getEndRound()) {
                                    //CALCUL SCORE
                                }
                            }
                        }
                        else {
                            System.out.println("Card already face up!");
                        }
                    }
                }
            }
        });


    }
    /**
     * Sets the reference to the card
     */
    @Override
    public void setCard() {
        card = game.getPlayer(game.getCurrentPlayer()).getCard(this.id / (Game.DECK_COLS), this.id % (Game.DECK_COLS));
    }

    /**
     * Paints the card
     * @param g the graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(card.isFaceUp()) {
            setIcon(assets.getCard(card.getValue()+2));
            setDisabledIcon(assets.getCard(card.getValue()+2));
        } else {
            setIcon(assets.getCardBack());
            setDisabledIcon(assets.getCardBack());
        }
        this.repaint();
    }
}
