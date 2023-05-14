package org.skyjo.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuButton extends JButton {
    private final ImageIcon icon;
    private final ImageIcon iconHover;
    private boolean isHovered = false;

    public MenuButton(ImageIcon icon, ImageIcon iconHover){
        this.setBorder(BorderFactory.createEmptyBorder());

        this.icon = icon;
        this.iconHover = iconHover;
        this.setIcon(this.icon);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isHovered) {
            this.setIcon(this.iconHover);
        } else {
            this.setIcon(this.icon);
        }
        this.repaint();
    }
}