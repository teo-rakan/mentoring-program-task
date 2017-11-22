package com.epam.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {

    private static final Properties properties;
    private static final String PROPERTIES_FILE_PATH = "./configs/application.properties";

    static {
        properties = new Properties();
        InputStream is = null;
        try {
            is = new FileInputStream(PROPERTIES_FILE_PATH);
            properties.load(is);
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("The app properties file cannot be read from '"
                    + PROPERTIES_FILE_PATH, e);
        }
    }

    public static String get(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
