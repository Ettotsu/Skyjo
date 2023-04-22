package org.skyjo.ui;

import javax.swing.*;

public class SettingsWindow extends JFrame {

    private JPanel panel;
    private JComboBox<String> windowSize;
    private JCheckBox borderless;

    public SettingsWindow(){
        super("Settings");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        panel = new JPanel();

        windowSize = new JComboBox<>();
        windowSize.addItem("640x360");
        windowSize.addItem("1280x720 (default)");
        windowSize.addItem("1920x1080");
        borderless = new JCheckBox("Borderless");

        panel.add(windowSize);
        panel.add(borderless);
        this.add(panel);
    }
}
