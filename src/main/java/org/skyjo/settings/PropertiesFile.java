package org.skyjo.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile extends Properties {
    private FileInputStream input;
    private FileOutputStream output;

    public PropertiesFile(){
        try {
            input = new FileInputStream("src/main/resources/config.properties");
            this.load(input);
            input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int getIntProperty(String key){
        return Integer.parseInt(this.getProperty(key));
    }
    public boolean getBooleanProperty(String key){
        return Boolean.parseBoolean(this.getProperty(key));
    }
    public void setIntProperty(String key, int value) {
        this.setProperty(key, String.valueOf(value));
        try {
            output = new FileOutputStream("src/main/resources/config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            this.store(output, null);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setBooleanProperty(String key, boolean value) {
        this.setProperty(key, String.valueOf(value));
        try {
            output = new FileOutputStream("src/main/resources/config.properties");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            this.store(output, null);
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
