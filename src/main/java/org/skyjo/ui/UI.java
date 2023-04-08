package org.skyjo.ui;

import org.skyjo.minigame.MiniGameWindow;
import org.skyjo.settings.PropertiesFile;

import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {

    private PropertiesFile prop;
    public UI(){
        super("Fail your Deutec");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MainMenu();
    }

    public void MainMenu(){
        prop=new PropertiesFile();
        int width = Integer.parseInt(prop.getProperty("window_width"));
        int height = Integer.parseInt(prop.getProperty("window_height"));

        this.setSize(width,height);


        JButton start = new JButton("Start a new game");
        JButton settings = new JButton("Settings");
        JButton minigame = new JButton("MiniGame");
        minigame.addActionListener(e -> {
            MiniGameWindow miniGameWindow = new MiniGameWindow();
        });
        JButton quit = new JButton("Quit");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createRigidArea(new Dimension(20, this.getHeight()/2)));
        panel.add(start);
        Dimension gap = new Dimension(0, 14);
        panel.add(Box.createRigidArea(gap));
        panel.add(settings);
        panel.add(Box.createRigidArea(gap));
        panel.add(minigame);
        panel.add(Box.createRigidArea(gap));
        panel.add(quit);
        quit.addActionListener(e -> System.exit(0));
        this.getContentPane().add(panel);
        this.setVisible(true);
        this.setResizable(false);
        //this.repaint();

    }
}
