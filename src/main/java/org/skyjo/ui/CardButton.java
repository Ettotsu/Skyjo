package org.skyjo.ui;

import javax.swing.*;
import java.awt.*;

public class CardButton extends JButton {
    private int value;
    private boolean isFaceUp;
    private Image img;

    public CardButton(int value, boolean isFaceUp){
        this.value = value;
        this.isFaceUp = isFaceUp;

        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.updateImage();
    }


    public void setValue(int value) {
        this.value = value;
    }
    public int getValue() {
        return this.value;
    }


    private void updateImage(){
        if(isFaceUp) {
            this.img = new ImageIcon("src/main/resources/assets/cards/front_" + this.value + ".png").getImage();
        }
        else{
            this.img = new ImageIcon("src/main/resources/assets/cards/back.png").getImage();
        }
        //img = img.getScaledInstance(80,112,Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(this.img));
    }

}
