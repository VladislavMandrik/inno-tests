package org.example.config;

import io.github.cdimascio.dotenv.Dotenv;

public final class EnvConfig {
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    private EnvConfig() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static String get(String key) {
        return dotenv.get(key);
    }

    public static String get(String key, String defaultValue) {
        String value = dotenv.get(key);
        return value != null ? value : defaultValue;
    }
}