package com.infoshareacademy.utilities;

import java.io.*;
import java.util.Properties;

public class PropertiesUtilities {
    public static final String PROPERTY_FILE = "src/main/resources/AppConfig.properties";
    private Properties properties;

    public PropertiesUtilities() {
        this.properties = new Properties();
    }

    public String getProperty(String key) {
        String propertyValue = null;
        try (InputStream input = new FileInputStream(PROPERTY_FILE)) {
            this.properties.load(input);
            propertyValue = this.properties.getProperty(key);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return propertyValue;
    }

    public void setProperties(String key, String value) {
        try (OutputStream output = new FileOutputStream(PROPERTY_FILE)) {
            this.properties.setProperty(key, value);
            this.properties.store(output, null);
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }
}