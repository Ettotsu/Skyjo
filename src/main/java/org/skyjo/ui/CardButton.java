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
                            game.setFirstRoundDone();
                            game.setCardSelected(false);
                            game.setCurrentPlayer(game.getMaxScore());
                            ui.setAllCards();
                        } else {
                            card.setFaceUp();
                            game.setCardSelected(true);

                        }
                    } else {
                        card.setFaceUp();
                        if (game.getCardSelected()) {

                            game.incrementCurrentPlayer();
                            game.setCardSelected(false);


                            ui.setAllCards();
                            ui.putPlayerInTitle(game.getCurrentPlayer());

                            System.out.println("on a incremente wallah");
                        } else {
                            game.setCardSelected(true);
                        }

                    }
                }
                else {
                    System.out.println("Card already face up");
                }
            }
        });


    }
    public void setCard() {
        card = game.getPlayer(game.getCurrentPlayer()).getCard(this.id / (Game.DECK_COLS), this.id % (Game.DECK_COLS));
    }

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
