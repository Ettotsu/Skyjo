package org.skyjo.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuButton extends JButton {

    private Image img;
    private Image imgHover;

    public MenuButton(int width, int height, String path){
        String pathHover = path.replace(".png", "_hover.png");

        this.setPreferredSize(new java.awt.Dimension(width, height));
        this.setBorder(BorderFactory.createEmptyBorder());

        this.img = new ImageIcon(path).getImage();
        this.img = this.img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        this.imgHover = new ImageIcon(pathHover).getImage();
        this.imgHover = this.imgHover.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        setIcon(new ImageIcon(img));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(new ImageIcon(img));
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(new ImageIcon(imgHover));
            }

        });
    }
}
