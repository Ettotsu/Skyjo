package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class DiscardButton extends JButton implements CardInterface {
    private final Assets assets; // Assets reference
    private final Game game; // Game reference
    private Card card; // Card reference

    /**
     * Constructor
     * The action listener is used for the logic the game
     * @param assets the assets
     * @param game the game
     * @param ui the ui
     */
    public DiscardButton(Assets assets, Game game, UI ui) {
        this.assets = assets;
        this.game = game;

        this.setBorderPainted(false); // Removes the border

        setCard(); // Sets the card

        this.addActionListener(e -> {
            if(game.getFirstRoundDone()) { // If the first round is done
                if(!game.getDiscardChosen()) { // If the discard is not chosen
                    game.setDiscardChosen(true); // Set the discard to chosen
                    if (game.getStackChosen()) { // If the stack is chosen
                        game.setDiscard(game.getStack().drawCard()); // Draws a card from the stack and set it to the discard
                        // Sets the cards
                        this.setCard();
                        ui.getStackButton().setCard();
                    }
                }
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
