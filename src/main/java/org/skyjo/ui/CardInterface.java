package org.skyjo.ui;

import java.awt.*;

public interface CardInterface {

        public void setValue(int value);
        public void setFaceUp(boolean isfaceUp); // Je suis face up nahahaaaa
        public void updateImage();
        public void paint(Graphics g);
}
