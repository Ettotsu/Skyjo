package org.skyjo.ui;

import javax.swing.*;
import java.awt.*;

public class CardLabel extends JLabel implements CardInterface {

    private boolean isFaceUp = false;
    private int value;
    private Image img;
    private Image imgHidden; // Image of the hidden card
    private int width, height;


    public CardLabel(int width, int height) {
        this.width = width;
        this.height = height;
        this.setPreferredSize(new java.awt.Dimension(this.width, this.height));

        this.img = new ImageIcon("src/main/resources/assets/cards/front_" + this.value + ".png").getImage();
        this.img = this.img.getScaledInstance(width,height,Image.SCALE_SMOOTH);
        this.imgHidden = new ImageIcon("src/main/resources/assets/cards/back.png").getImage();
        this.imgHidden = this.imgHidden.getScaledInstance(width,height,Image.SCALE_SMOOTH);

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
            setIcon(new ImageIcon(img));
        } else {
            setIcon(new ImageIcon(imgHidden));
        }
    }

    @Override
    public void paint(Graphics g) {
        if(isFaceUp) {
            setIcon(new ImageIcon(img));
        } else {
            setIcon(new ImageIcon(imgHidden));
        }
        super.paint(g);
    }
}
