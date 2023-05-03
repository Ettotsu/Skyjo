package org.skyjo.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuButton extends JButton {

    private Image img;

    public MenuButton(String path, int width, int height){

        this.img = new ImageIcon(path).getImage();
        this.img = this.img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        this.setIcon(new ImageIcon(this.img));

        this.setPreferredSize(new java.awt.Dimension(width, height));
        this.setBorder(BorderFactory.createEmptyBorder());

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                setBorder(BorderFactory.createEmptyBorder());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLineBorder(Color.gray, 8));
            }

        });
    }
}
