package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class DiscardButton extends JButton {
    private final Assets assets;
    private final Game game;
    private Card card;

    public DiscardButton(Assets assets, Game game) {
        this.assets = assets;
        this.game = game;

        this.setBorderPainted(false);

        setCard();
    }
    public void setCard(){
        card = game.getDiscard();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setIcon(assets.getCard(card.getValue() + 2));
        setDisabledIcon(assets.getCard(card.getValue() + 2));
    }
}
