package org.skyjo.minigame;

import org.skyjo.settings.PropertiesFile;

import javax.swing.*;
import java.awt.*;

public class MiniGameWindow extends JFrame {

    PropertiesFile prop;
    public MiniGameWindow() {
        super("MiniGame");

        prop=new PropertiesFile();
        int width = Integer.parseInt(prop.getProperty("window_width"));
        int height = Integer.parseInt(prop.getProperty("window_height"));


        setSize(width, height);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

        JLabel test = new JLabel();
        this.setLayout(null);
        add(test);
        test.setBackground(Color.RED);
        test.setOpaque(true);
        test.setBounds(500, 500, 80, 112);
        JLabel testmini = new JLabel();
        add(testmini);
        testmini.setBackground(Color.BLUE);
        testmini.setOpaque(true);
        testmini.setBounds(300, 500, 40, 56);



    }

}
