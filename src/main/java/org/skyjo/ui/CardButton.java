package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class CardButton extends JButton implements CardInterface {

    private int id;

    private Assets assets;
    private Game game;

    private Card card;


    public CardButton(int id, Assets assets, Game game) {
        this.id = id;
        this.assets = assets;
        this.game = game;

        this.setBorderPainted(false);

        setCard();

        this.setIcon(this.assets.getCardBack());

        this.addActionListener(e -> {
            card.setFaceUp();
        });
    }
    public void setCard() {
        card = game.getPlayerCard(game.getCurrentPlayer(),this.id / (Game.DECK_COLS), this.id % (Game.DECK_COLS));
    }

    public void setFaceUp() {
        card.setFaceUp();
    }

    @Override
    public void paint(Graphics g) {
        if(card.isFaceUp()) {
            setIcon(assets.getCard(card.getValue()+2));
        } else {
            setIcon(assets.getCardBack());
        }
        super.paint(g);
    }
}
