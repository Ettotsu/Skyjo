package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class StackButton extends JButton implements CardInterface {

    private final Assets assets;
    private final Game game;
    private Card card;

    public StackButton(Assets assets, Game game) {
        this.assets = assets;
        this.game = game;

        this.setBorderPainted(false);

        setCard();

        this.addActionListener(e -> {
            if(game.getFirstRoundDone()) {
                game.setStackChosen(true);
                game.getStack().faceUpCard();
            }
            else {
                System.out.println("First round still not over");
            }
        });
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
