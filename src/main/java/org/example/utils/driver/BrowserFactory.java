package org.example.utils.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.config.BrowserConfig;
import org.example.config.Constants;
import org.example.config.DriverConfig;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.config.BrowserConfig.*;

public final class BrowserFactory {

    private BrowserFactory() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static WebDriver createDriver(Browser browser) {
        return createLocalDriver(browser);
    }

    private static WebDriver createLocalDriver(Browser browser) {
        WebDriver driver;

        switch (browser) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(getChromeOptions());
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(getFirefoxOptions());
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(getEdgeOptions());
                break;
            case SAFARI:
                driver = new SafariDriver(getSafariOptions());
                break;
            default:
                throw new IllegalArgumentException(
                        String.format(Constants.Errors.UNSUPPORTED_BROWSER, browser));
        }

        configureTimeouts(driver);
        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();

        List<String> chromeArgs = BrowserConfig.getList(CHROME_ARGS);
        options.addArguments(chromeArgs.toArray(new String[0]));

        if (DriverConfig.isHeadless()) {
            options.addArguments(BrowserConfig.get(CHROME_HEADLESS));
            options.addArguments(String.format(
                    BrowserConfig.get(CHROME_WINDOW_SIZE),
                    BrowserConfig.getInt(BROWSER_WIDTH),
                    BrowserConfig.getInt(BROWSER_HEIGHT)));
        }

        if (isRunningInCI()) {
            List<String> ciArgs = BrowserConfig.getList(CI_ARGS);
            options.addArguments(ciArgs.toArray(new String[0]));
        }

        configureWireMockProxy(options);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put(BrowserConfig.get(CHROME_PREFS_KEY_NOTIFICATIONS),
                BrowserConfig.getInt(CHROME_PREFS_NOTIFICATIONS));
        prefs.put(BrowserConfig.get(CHROME_PREFS_KEY_CREDENTIALS),
                BrowserConfig.getBoolean(CHROME_PREFS_CREDENTIALS));
        prefs.put(BrowserConfig.get(CHROME_PREFS_KEY_PASSWORD),
                BrowserConfig.getBoolean(CHROME_PREFS_PASSWORD));
        options.setExperimentalOption(BrowserConfig.get(PREFS_KEY), prefs);

        options.setExperimentalOption(BrowserConfig.get(EXCLUDE_SWITCHES_KEY),
                new String[]{BrowserConfig.get(CHROME_EXCLUDE_SWITCHES)});
        options.setExperimentalOption(BrowserConfig.get(USE_AUTOMATION_EXTENSION_KEY),
                BrowserConfig.getBoolean(USE_AUTOMATION_EXTENSION_VALUE));

        options.setPageLoadStrategy(DriverConfig.getPageLoadStrategy());
        return options;
    }

    private static void configureWireMockProxy(ChromeOptions options) {
        String wireMockProxy = System.getProperty(BrowserConfig.get(WIREMOCK_PROXY_PROPERTY));
        if (wireMockProxy != null) {
            Proxy proxy = new Proxy();
            proxy.setHttpProxy(wireMockProxy);
            options.setProxy(proxy);
            options.setAcceptInsecureCerts(BrowserConfig.getBoolean(ACCEPT_INSECURE_CERTS));
            options.addArguments(BrowserConfig.get(IGNORE_CERTIFICATE_ERRORS));
        }
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions options = new FirefoxOptions();

        if (DriverConfig.isHeadless()) {
            options.addArguments(BrowserConfig.get(FIREFOX_HEADLESS));
            options.addArguments(String.format(
                    BrowserConfig.get(FIREFOX_WIDTH),
                    BrowserConfig.getInt(BROWSER_WIDTH)));
            options.addArguments(String.format(
                    BrowserConfig.get(FIREFOX_HEIGHT),
                    BrowserConfig.getInt(BROWSER_HEIGHT)));
        }

        options.addPreference(BrowserConfig.get(FIREFOX_PREFS_KEY_WEB),
                BrowserConfig.getBoolean(FIREFOX_PREFS_WEB));
        options.addPreference(BrowserConfig.get(FIREFOX_PREFS_KEY_PUSH),
                BrowserConfig.getBoolean(FIREFOX_PREFS_PUSH));
        options.addPreference(BrowserConfig.get(FIREFOX_PREFS_KEY_DOWNLOAD),
                BrowserConfig.getInt(FIREFOX_PREFS_DOWNLOAD));
        options.addPreference(BrowserConfig.get(FIREFOX_PREFS_KEY_NEVER_ASK),
                BrowserConfig.get(FIREFOX_PREFS_NEVER_ASK));

        options.setPageLoadStrategy(DriverConfig.getPageLoadStrategy());
        return options;
    }

    private static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();

        if (DriverConfig.isHeadless()) {
            options.addArguments(BrowserConfig.get(CHROME_HEADLESS));
            options.addArguments(String.format(
                    BrowserConfig.get(CHROME_WINDOW_SIZE),
                    BrowserConfig.getInt(BROWSER_WIDTH),
                    BrowserConfig.getInt(BROWSER_HEIGHT)));
        }

        List<String> chromeArgs = BrowserConfig.getList(CHROME_ARGS);
        options.addArguments(chromeArgs.toArray(new String[0]));

        options.setPageLoadStrategy(DriverConfig.getPageLoadStrategy());
        return options;
    }

    private static SafariOptions getSafariOptions() {
        SafariOptions options = new SafariOptions();
        options.setAutomaticInspection(BrowserConfig.getBoolean(SAFARI_AUTOMATIC_INSPECTION));
        options.setCapability(BrowserConfig.get(SAFARI_OPTIONS_KEY), Map.of(
                BrowserConfig.get(SAFARI_TECHNOLOGY_PREVIEW), BrowserConfig.getBoolean(SAFARI_TECHNOLOGY_PREVIEW),
                BrowserConfig.get(SAFARI_AUTOMATIC_INSPECTION), BrowserConfig.getBoolean(SAFARI_AUTOMATIC_INSPECTION)));
        return options;
    }

    private static void configureTimeouts(WebDriver driver) {
        driver.manage().timeouts().pageLoadTimeout(DriverConfig.getPageLoadTimeout());
        driver.manage().timeouts().scriptTimeout(DriverConfig.getScriptTimeout());

        if (DriverConfig.isHeadless()) {
            driver.manage().window().setSize(new Dimension(
                    BrowserConfig.getInt(BROWSER_WIDTH),
                    BrowserConfig.getInt(BROWSER_HEIGHT)));
        } else {
            try {
                driver.manage().window().maximize();
            } catch (Exception e) {
                driver.manage().window().setSize(new Dimension(
                        BrowserConfig.getInt(BROWSER_WIDTH),
                        BrowserConfig.getInt(BROWSER_HEIGHT)));
            }
        }
    }

    private static boolean isRunningInCI() {
        return System.getenv(Constants.EnvVars.CI) != null ||
                System.getenv(Constants.EnvVars.JENKINS_HOME) != null ||
                System.getenv(Constants.EnvVars.GITHUB_ACTIONS) != null ||
                System.getenv(Constants.EnvVars.TEAMCITY_VERSION) != null ||
                System.getenv(Constants.EnvVars.GITLAB_CI) != null;
    }
}