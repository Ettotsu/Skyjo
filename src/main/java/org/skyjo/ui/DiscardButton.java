package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class DiscardButton extends JButton implements CardInterface {
    private final Assets assets;
    private final Game game;
    private Card card;

    public DiscardButton(Assets assets, Game game, UI ui) {
        this.assets = assets;
        this.game = game;

        this.setBorderPainted(false);

        setCard();

        this.addActionListener(e -> {
            if(game.getFirstRoundDone()) {
                if(game.getStackChosen()) {
                    game.setDiscardChosen(true);
                    game.setDiscard(game.getStack().drawCard());
                    this.setCard();
                    ui.getStackButton().setCard();
                    ui.getStackButton().repaint();
                    ui.repaint();
                    System.out.println("Discard clicked");
                    game.getStack().printCards();
                }
                else {

                }
               this.setEnabled(false);

            }
            else {
                    System.out.println("First round not over");
            }
        });
    }

    /**
     * Sets the reference to the card
     */
    @Override
    public void setCard(){
        card = game.getDiscard();
        this.repaint();
    }

    /**
     * Paints the card
     * @param g the graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setIcon(assets.getCard(card.getValue() + 2));
        setDisabledIcon(assets.getCard(card.getValue() + 2));
        this.repaint();
    }
}
