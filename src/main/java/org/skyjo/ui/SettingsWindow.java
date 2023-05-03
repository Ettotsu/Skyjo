package org.skyjo.ui;

import org.skyjo.settings.PropertiesFile;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JOptionPane {

    private JPanel panel;
    private PropertiesFile prop;
    private JComboBox<String> windowSize;
    private JCheckBox borderless;

    public SettingsWindow(){
        super("Settings");
        this.setSize(300,200);
        this.setVisible(true);

        panel = new JPanel();
        prop = new PropertiesFile();


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
        panel.add(windowSize);
        panel.add(borderless);
        this.add(panel);


        if(this.showOptionDialog(null,panel,"Settings",JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null)==JOptionPane.OK_OPTION){
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
        }

    }
}
