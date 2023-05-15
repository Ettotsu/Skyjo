package org.skyjo.minigame;

import org.skyjo.settings.PropertiesFile;

import javax.swing.*;
import java.awt.*;

public class MiniGameWindow extends JFrame {

    PropertiesFile prop; // Properties file

    /**
     * Constructor
     */
    public MiniGameWindow() {
        super("MiniGame"); // Title of the window

        prop=new PropertiesFile();
        int width = Integer.parseInt(prop.getProperty("window_width"));
        int height = Integer.parseInt(prop.getProperty("window_height"));


        setSize(width, height); // Sets the size of the window
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close the window when clicking on the cross
        setVisible(true); // Sets the window visible


    }

}
