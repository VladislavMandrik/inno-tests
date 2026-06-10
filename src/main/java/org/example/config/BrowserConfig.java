package org.example.config;

import org.example.config.Constants.Errors;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public final class BrowserConfig {

    public static final String BROWSER_WIDTH = "browser.width";
    public static final String BROWSER_HEIGHT = "browser.height";
    public static final String CHROME_ARGS = "chrome.args";
    public static final String CHROME_HEADLESS = "chrome.headless";
    public static final String CHROME_WINDOW_SIZE = "chrome.window.size";
    public static final String CHROME_EXCLUDE_SWITCHES = "chrome.exclude.switches";
    public static final String FIREFOX_HEADLESS = "firefox.headless";
    public static final String FIREFOX_WIDTH = "firefox.width";
    public static final String FIREFOX_HEIGHT = "firefox.height";
    public static final String CI_ARGS = "ci.args";
    public static final String CHROME_PREFS_KEY_NOTIFICATIONS = "chrome.prefs.key.notifications";
    public static final String CHROME_PREFS_NOTIFICATIONS = "chrome.prefs.notifications";
    public static final String CHROME_PREFS_KEY_CREDENTIALS = "chrome.prefs.key.credentials";
    public static final String CHROME_PREFS_CREDENTIALS = "chrome.prefs.credentials.service";
    public static final String CHROME_PREFS_KEY_PASSWORD = "chrome.prefs.key.password";
    public static final String CHROME_PREFS_PASSWORD = "chrome.prefs.password.manager";
    public static final String FIREFOX_PREFS_KEY_WEB = "firefox.prefs.key.web.notifications";
    public static final String FIREFOX_PREFS_WEB = "firefox.prefs.web.notifications";
    public static final String FIREFOX_PREFS_KEY_PUSH = "firefox.prefs.key.push";
    public static final String FIREFOX_PREFS_PUSH = "firefox.prefs.push.enabled";
    public static final String FIREFOX_PREFS_KEY_DOWNLOAD = "firefox.prefs.key.download.folder";
    public static final String FIREFOX_PREFS_DOWNLOAD = "firefox.prefs.download.folder.list";
    public static final String FIREFOX_PREFS_KEY_NEVER_ASK = "firefox.prefs.key.never.ask";
    public static final String FIREFOX_PREFS_NEVER_ASK = "firefox.prefs.never.ask.save.to.disk";
    public static final String WIREMOCK_PROXY_PROPERTY = "wiremock.proxy.property";
    public static final String IGNORE_CERTIFICATE_ERRORS = "ignore.certificate.errors";
    public static final String ACCEPT_INSECURE_CERTS = "accept.insecure.certs";
    public static final String PREFS_KEY = "prefs.key";
    public static final String EXCLUDE_SWITCHES_KEY = "exclude.switches.key";
    public static final String USE_AUTOMATION_EXTENSION_KEY = "use.automation.extension.key";
    public static final String USE_AUTOMATION_EXTENSION_VALUE = "use.automation.extension.value";
    public static final String SAFARI_OPTIONS_KEY = "safari.options.key";
    public static final String SAFARI_AUTOMATIC_INSPECTION = "safari.automatic.inspection";
    public static final String SAFARI_TECHNOLOGY_PREVIEW = "safari.technology.preview";
    private static final Properties props = new Properties();
    private static final String CONFIG_PATH = "config/browser.properties";
    private static final String LOAD_ERROR = "Cannot load ";
    private static final String MISSING_REQUIRED_PROPERTY = "Missing required property: %s";

    static {
        try (InputStream input = BrowserConfig.class.getClassLoader()
                .getResourceAsStream(CONFIG_PATH)) {
            if (input != null) {
                props.load(input);
            }
        } catch (IOException e) {
            System.err.println(LOAD_ERROR + CONFIG_PATH);
        }
    }

    private BrowserConfig() {
        throw new UnsupportedOperationException(Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static String get(String key) {
        return props.getProperty(key);
    }

    public static String get(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static int getInt(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new IllegalStateException(String.format(MISSING_REQUIRED_PROPERTY, key));
        }
        return Integer.parseInt(value);
    }

    public static List<String> getList(String key) {
        String value = props.getProperty(key);
        if (value == null || value.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.asList(value.split(","));
    }

    public static boolean getBoolean(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new IllegalStateException(
                    String.format(MISSING_REQUIRED_PROPERTY, key));
        }
        return Boolean.parseBoolean(value);
    }
}