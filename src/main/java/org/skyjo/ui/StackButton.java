package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class StackButton extends JButton implements CardInterface {

    private final Assets assets; // Assets reference
    private final Game game; // Game reference
    private Card card; // Card reference

    /**
     * Constructor
     * The action listener is used for the logic the game
     * @param assets the assets
     * @param game the game
     */
    public StackButton(Assets assets, Game game) {
        this.assets = assets;
        this.game = game;

        this.setBorderPainted(false); // Removes the border

        setCard(); // Sets the card

        this.addActionListener(e -> {
            if(game.getFirstRoundDone()) { // If the first round is done
                if(!game.getDiscardChosen()) { // If the discard is not chosen (to avoid double click)
                    game.setStackChosen(true);  // The stack is chosen
                    game.getStack().faceUpCard(); // Faces up the card in the stack
                }
            }
        });
    }

    /**
     * Sets the reference to the card
     */
    @Override
    public void setCard(){
        card = game.getStack().getLastCard();
        this.repaint();
    }

    /**
     * Paints the card
     * @param g the graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (card.isFaceUp()) {
            setIcon(assets.getCard(card.getValue() + 2));
            setDisabledIcon(assets.getCard(card.getValue() + 2));
        } else {
            setIcon(assets.getCardBack());
            setDisabledIcon(assets.getCardBack());
        }
        this.repaint();
    }
}
