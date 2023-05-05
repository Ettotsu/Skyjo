package org.skyjo.ui;

import javax.swing.*;
import java.awt.*;

public class CardLabel extends JLabel implements CardInterface {

    private boolean isFaceUp = false;
    private int value;
    private Image img;
    private int width, height;


    public CardLabel(int width, int height) {
        this.width = width;
        this.height = height;
        this.updateImage();
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setFaceUp(boolean isFaceUp) {
        this.isFaceUp = isFaceUp;
        this.updateImage();
    }
    public void updateImage() {
        if (isFaceUp) {
            this.img = new ImageIcon("src/main/resources/assets/cards/" + value + ".png").getImage();
        } else {
            this.img = new ImageIcon("src/main/resources/assets/cards/back.png").getImage();
        }
        img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(this.img));
    }
}
