package org.example.utils.driver;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Browser {
    CHROME,
    FIREFOX,
    EDGE,
    SAFARI;

    public static Browser fromString(String browserName) {
        if (browserName == null || browserName.isBlank()) {
            return CHROME;
        }

        String normalized = browserName.trim().toUpperCase();

        try {
            return valueOf(normalized);
        } catch (IllegalArgumentException e) {
            String supported = Arrays.stream(values())
                    .map(Browser::name)
                    .collect(Collectors.joining(", "));

            throw new IllegalArgumentException(
                    String.format("Unsupported browser: '%s'. Supported: [%s]",
                            browserName, supported)
            );
        }
    }

    public static boolean isSupported(String browserName) {
        try {
            fromString(browserName);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}