package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class CardLabel extends JLabel implements CardInterface {
    private final int rank, id;

    private final Assets assets;
    private final Game game;

    private Card card;

    public CardLabel(int rank, int id, Assets assets, Game game) {
        this.rank = rank;
        this.id = id;
        this.assets = assets;
        this.game = game;

        this.setCard();

        this.setIcon(this.assets.getCardBackSmall());
    }

    /**
     * Sets the reference to the card
     */
    @Override
    public void setCard() {
        int player = 1 + ((game.getCurrentPlayer() + rank) % (game.getNbPlayers()));
        card = game.getPlayer(player).getCard(this.id / (Game.DECK_COLS), this.id % (Game.DECK_COLS));
    }

    /**
     * Paints the card
     * @param g the graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(card.isFaceUp()) {
            setIcon(assets.getCardSmall(card.getValue()+2));
            setDisabledIcon(assets.getCardSmall(card.getValue()+2));
        } else {
            setIcon(assets.getCardBackSmall());
            setDisabledIcon(assets.getCardBackSmall());
        }
        this.repaint();
    }
}
