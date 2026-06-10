package org.example.utils.driver;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Browser {
    CHROME,
    FIREFOX,
    EDGE,
    SAFARI;

    private static final String UNSUPPORTED_BROWSER_S_SUPPORTED_S = "Unsupported browser: '%s'. Supported: [%s]";

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
                    String.format(UNSUPPORTED_BROWSER_S_SUPPORTED_S,
                            browserName, supported)
            );
        }
    }
}