package by.teachmeskills.ps.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtils {
    private static final String CONFIG_FILE_NAME = "application.properties";

    public static String getProperty(String key) {
        String value = "";
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(CONFIG_FILE_NAME)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            value = properties.getProperty(key);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return value;
    }
}