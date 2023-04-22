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


    }

}
