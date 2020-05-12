package com.infoshareacademy.utilities;

import java.io.*;
import java.util.Properties;

public class PropertiesUtilities {
    public static final String PROPERTY_FILE = "AppConfig.properties";
    private Properties properties;

    public PropertiesUtilities() {
        this.properties = new Properties();
    }

    public String getProperty(String key) {
        loadProperties();
        return properties.getProperty(key);
    }

    private void loadProperties() {
        try (InputStream input = new FileInputStream(PROPERTY_FILE)) {
            this.properties.load(input);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setProperties(String key, String value) {
        loadProperties();
        try (OutputStream output = new FileOutputStream(PROPERTY_FILE)) {
            this.properties.setProperty(key, value);
            this.properties.store(output, null);
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
}