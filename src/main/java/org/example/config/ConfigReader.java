package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final Properties properties = new Properties();

    private static final String CONFIG_PATH = "config/application.properties";
    private static final String LOCAL_CONFIG_PATH = "config/application-local.properties";
    private static final String CI_CONFIG_PATH = "config/application-ci.properties";

    private static final String ENV_PROFILE = "env";
    private static final String TEST_ENV = "TEST_ENV";
    private static final String PROFILE_CI = "ci";
    private static final String CANNOT_LOAD_CONFIG = "Конфиг не загружен: ";

    static {
        loadProperties(CONFIG_PATH);
        loadProfileProperties();
        overrideWithSystemProperties();
    }

    private ConfigReader() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    private static void loadProperties(String path) {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(path)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println(CANNOT_LOAD_CONFIG + path);
        }
    }

    private static void loadProfileProperties() {
        String profile = System.getProperty(ENV_PROFILE, System.getenv(TEST_ENV));
        if (PROFILE_CI.equalsIgnoreCase(profile)) {
            loadProperties(CI_CONFIG_PATH);
        }
    }

    private static void overrideWithSystemProperties() {
        properties.stringPropertyNames().forEach(key -> {
            String systemValue = System.getProperty(key);
            if (systemValue != null && !systemValue.isEmpty()) {
                properties.setProperty(key, systemValue);
            }
        });
    }

    public static String getString(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }
}