package org.skyjo.settings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFile extends Properties {
    private FileInputStream input; // input stream
    private FileOutputStream output; // output stream

    /**
     * Constructor
     * Loads the properties file
     */
    public PropertiesFile(){
        try {
            input = new FileInputStream("src/main/resources/config.properties");
            this.load(input);
            input.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the property as an int
     * @param key the key
     * @return the property as an int
     */
    public int getIntProperty(String key){
        return Integer.parseInt(this.getProperty(key));
    }

    /**
     * Gets the property as a boolean
     * @param key the key
     * @return the property as a boolean
     */
    public boolean getBooleanProperty(String key){
        return Boolean.parseBoolean(this.getProperty(key));
    }

    /**
     * Sets the int property
     * @param key the key as a string
     * @param value the value as an int
     */
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

    /**
     * Sets the boolean property
     * @param key the key as a string
     * @param value the value as a boolean
     */
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
