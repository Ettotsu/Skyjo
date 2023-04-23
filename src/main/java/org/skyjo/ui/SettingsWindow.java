package org.skyjo.ui;

import org.skyjo.settings.PropertiesFile;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {

    private JPanel panel;
    PropertiesFile prop;
    private JComboBox<String> windowSize;
    private JCheckBox borderless;
    private JButton ok;
    private JButton cancel;

    public SettingsWindow(){
        super("Settings");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(300,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        panel = new JPanel();
        prop = new PropertiesFile();
        panel.setLayout(new GridLayout(2,2));


        windowSize = new JComboBox<>();
        windowSize.addItem("640x360");
        windowSize.addItem("1280x720 (default)");
        windowSize.addItem("1920x1080");
        if(prop.getIntProperty("window_width")==640 && prop.getIntProperty("window_height")==360){
            windowSize.setSelectedIndex(0);
        }
        else if (prop.getIntProperty("window_width")==1920 && prop.getIntProperty("window_height")==1080){
            windowSize.setSelectedIndex(2);
        }
        else {
            windowSize.setSelectedIndex(1);
        }
        borderless = new JCheckBox("Borderless");
        borderless.setSelected(prop.getBooleanProperty("borderless"));
        ok = new JButton("OK");
        ok.addActionListener(e -> {
            if(windowSize.getSelectedIndex()==0){
                prop.setIntProperty("window_width",640);
                prop.setIntProperty("window_height",360);
            }
            else if(windowSize.getSelectedIndex()==2){
                prop.setIntProperty("window_width",1920);
                prop.setIntProperty("window_height",1080);
            }
            else{
                prop.setIntProperty("window_width",1280);
                prop.setIntProperty("window_height",720);
            }
            prop.setBooleanProperty("borderless",Boolean.valueOf(borderless.isSelected()));
            this.dispose();
        });
        cancel = new JButton("Cancel");
        cancel.addActionListener(e -> this.dispose());
        panel.add(windowSize);
        panel.add(borderless);
        panel.add(ok);
        panel.add(cancel);
        this.add(panel);
    }
}
