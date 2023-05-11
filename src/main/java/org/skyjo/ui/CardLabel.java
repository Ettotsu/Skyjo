package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class CardLabel extends JLabel implements CardInterface {
    private int rank, id, value;
    private boolean isFaceUp = false;

    private Assets assets;
    private Game game;

    public CardLabel(int rank, int id, Assets assets, Game game) {
        this.rank = rank;
        this.id = id;
        this.assets = assets;
        this.game = game;

        this.setIcon(this.assets.getCardBackSmall());
    }

    public void setValue() {
    }

    public void setFaceUp() {
        this.isFaceUp = !this.isFaceUp;
    }

    public void setCard() {
        Card card = game.getPlayerCard(game.getCurrentPlayer() + rank, this.id / (Game.DECK_COLS), this.id % (Game.DECK_COLS));
        this.isFaceUp = card.isFaceUp();
        this.value = card.getValue();
    }


    @Override
    public void paint(Graphics g) {
        if(isFaceUp) {
            setIcon(assets.getCardSmall(value));
        } else {
            setIcon(assets.getCardBackSmall());
        }
        super.paint(g);
    }
}
