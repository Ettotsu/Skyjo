package org.skyjo.ui;

import org.skyjo.game.Card;
import org.skyjo.game.Game;

import javax.swing.*;

public class StackButton extends JButton {

    private Assets assets;
    private Game game;
    private Card card;

    public StackButton(Assets assets, Game game) {
        this.assets = assets;
        this.game = game;

        this.setBorderPainted(false);

        setCard();
    }

    public void setCard(){
        setIcon(assets.getCardBack());
    }



}
