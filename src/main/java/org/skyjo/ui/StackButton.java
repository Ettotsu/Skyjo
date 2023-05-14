package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class StackButton extends JButton {

    private final Assets assets;
    private final Game game;
    private Card card;

    public StackButton(Assets assets, Game game) {
        this.assets = assets;
        this.game = game;

        this.setBorderPainted(false);

        setCard();
    }

    public void setCard(){
        card = game.getStack().getLastCard();
    }

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
