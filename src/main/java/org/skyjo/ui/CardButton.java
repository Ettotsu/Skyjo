package org.skyjo.ui;

import javax.swing.*;
import java.awt.*;

public class CardButton extends JButton implements CardInterface {

    private boolean isFaceUp;
    private int value;
    private int width, height;
    private Image img;

    public CardButton(int width, int height) {
        this.width = width;
        this.height = height;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.updateImage();
    }


    public void setValue(int value) {
        this.value = value;
    }

    public void setFaceUp(boolean isfaceUp) {
        this.isFaceUp = isFaceUp;
        this.updateImage();
    }


    public void updateImage(){
        if(isFaceUp) {
            this.img = new ImageIcon("src/main/resources/assets/cards/front_" + this.value + ".png").getImage();
        }
        else{
            this.img = new ImageIcon("src/main/resources/assets/cards/back.png").getImage();
        }

        img = img.getScaledInstance(width,height,Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(this.img));
    }

}
