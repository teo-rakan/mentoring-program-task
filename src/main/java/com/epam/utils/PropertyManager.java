package com.epam.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyManager {

    private static final Properties properties;
    private static final String PROPERTIES_FILE_PATH = "./configs/application.properties";

    static {
        properties = new Properties();

        try {
            InputStream is = new FileInputStream(PROPERTIES_FILE_PATH);
            properties.load(is);
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException("Cannot read property file: "
                    + PROPERTIES_FILE_PATH, e);
        }
    }

    public static String get(String propertyName) {
        return properties.getProperty(propertyName);
    }

    public static List<String> getKeysWithPrefix(String prefix) {
        List<String> keys = new ArrayList<>();

        for (String key : properties.stringPropertyNames())
            if (key.toLowerCase().startsWith(prefix.toLowerCase()))
                keys.add(key);

        return keys;
    }
}
