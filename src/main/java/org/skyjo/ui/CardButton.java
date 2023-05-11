package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;
import java.awt.*;

public class CardButton extends JButton implements CardInterface {

    private int id, value;
    private boolean isFaceUp;

    private Assets assets;
    private Game game;


    public CardButton(int id, Assets assets, Game game) {
        this.id = id;
        this.isFaceUp = false;
        this.assets = assets;
        this.game = game;


        this.setIcon(this.assets.getCardBack());

        this.setBorderPainted(false);
        this.setContentAreaFilled(false);

        this.addActionListener(e -> {
            setCard();
            this.isFaceUp=true;


        });
    }
    public void setCard() {
        int row;

        Card card = game.getPlayerCard(game.getCurrentPlayer(),this.id / (Game.DECK_COLS), this.id % (Game.DECK_COLS));
        this.isFaceUp = card.isFaceUp();
        this.value = card.getValue();
    }

    public void setFaceUp() {
        this.isFaceUp = !this.isFaceUp;
    }



    @Override
    public void paint(Graphics g) {
        if(isFaceUp) {
            setIcon(assets.getCard(value+2));
        } else {
            setIcon(assets.getCardBack());
        }
        super.paint(g);
    }
}
