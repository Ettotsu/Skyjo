package org.skyjo.ui;

import java.awt.*;

public interface CardInterface {
        /**
         * Sets the reference to the card
         */
        void setCard();

        /**
         * Paints the card
         * @param g the graphics
         */
        void paintComponent(Graphics g);
}
