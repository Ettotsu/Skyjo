package org.skyjo.ui;

import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class CardLabel extends JLabel implements CardInterface {
    private int rank, id, value;
    private boolean isFaceUp = false;

    private Assets assets;

    public CardLabel(int rank, int id, Assets assets, Game game) {
        this.assets = assets;
        this.setIcon(this.assets.getCardBackSmall());
    }

    public void setValue() {
    }

    public void setFaceUp() {
        this.isFaceUp = !this.isFaceUp;
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
