package org.example.config;

import java.time.Duration;

public final class Constants {

    private Constants() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static final Duration POLLING_INTERVAL = Duration.ofMillis(200);

        // Browser
        public static final String DEFAULT_BROWSER = "chrome";
        public static final int BROWSER_WIDTH = 1920;
        public static final int BROWSER_HEIGHT = 1080;

        // Browser Arguments
        public static final class BrowserArgs {
            public static final String NO_SANDBOX = "--no-sandbox";
            public static final String DISABLE_DEV_SHM = "--disable-dev-shm-usage";
            public static final String DISABLE_GPU = "--disable-gpu";
            public static final String DISABLE_EXTENSIONS = "--disable-extensions";
            public static final String DISABLE_NOTIFICATIONS = "--disable-notifications";
            public static final String DISABLE_INFO_BARS = "--disable-infobars";
            public static final String HEADLESS_NEW = "--headless=new";
            public static final String HEADLESS = "--headless";
            public static final String REMOTE_ALLOW_ORIGINS = "--remote-allow-origins=*";

            // CI-specific
            public static final String DISABLE_WEB_SECURITY = "--disable-web-security";
            public static final String ALLOW_INSECURE_CONTENT = "--allow-running-insecure-content";
            public static final String NO_ZYGOTE = "--no-zygote";
            public static final String DISABLE_SETUID_SANDBOX = "--disable-setuid-sandbox";
            public static final String SINGLE_PROCESS = "--single-process";

            // Window size format
            public static final String WINDOW_SIZE = "--window-size=%d,%d";
            public static final String WIDTH = "--width=%d";
            public static final String HEIGHT = "--height=%d";

            // Exclude switches
            public static final String EXCLUDE_AUTOMATION_SWITCH = "enable-automation";
        }

        // Browser Preferences Keys
        public static final class BrowserPrefs {
            public static final String NOTIFICATIONS = "profile.default_content_setting_values.notifications";
            public static final String CREDENTIALS_SERVICE = "credentials_enable_service";
            public static final String PASSWORD_MANAGER = "password_manager_enabled";
            // Firefox
            public static final String WEB_NOTIFICATIONS = "dom.webnotifications.enabled";
            public static final String PUSH_ENABLED = "dom.push.enabled";
            public static final String DOWNLOAD_FOLDER_LIST = "browser.download.folderList";
            public static final String NEVER_ASK_SAVE_TO_DISK = "browser.helperApps.neverAsk.saveToDisk";
        }

        public static final class MimeTypes {
            public static final String OCTET_STREAM = "application/octet-stream";
        }

        public static final class SafariCaps {
            public static final String TECHNOLOGY_PREVIEW = "technologyPreview";
            public static final String AUTOMATIC_INSPECTION = "automaticInspection";
        }

        // Environment Detection
        public static final class EnvVars {
            public static final String CI = "CI";
            public static final String JENKINS_HOME = "JENKINS_HOME";
            public static final String GITHUB_ACTIONS = "GITHUB_ACTIONS";
            public static final String TEAMCITY_VERSION = "TEAMCITY_VERSION";
            public static final String GITLAB_CI = "GITLAB_CI";
    }

    public static final class Environment {
        public static final String BASE_URL = "https://innowise.com";
        public static final String DEFAULT_SELENIUM_HUB_URL = "http://localhost:4444/wd/hub";
    }

    public static final class Endpoints {
        public static final String MAIN_PAGE = "/";
        public static final String ABOUT_US = "/about-us/";
        public static final String ABOUT_US_RU = "/ru/about-us/";
    }

    public static final class Selectors {
        public static final String SEARCH_ICON = "div.bot-serach-cli";
        public static final String SEARCH_INPUT = "input[name='s']";
        public static final String SEARCH_LOGO = "div.new-block-logo-subsite-link";
        public static final String SEARCH_CONTAINER = "#content-masonry";
        public static final String SEARCH_RESULTS = "#ajax-load-more .article-search";
        public static final String SEARCH_RESULTS_TITLE = ".search-result";
        public static final String SEARCH_RESULTS_HEADERS = ".article-search .entry-title a";
        public static final String FOOTER_ABOUT_US_LINK = ".grid-column-footer-menu > div:nth-child(4) a";
        public static final String FOOTER_PHONE_LINKS = ".grid-column-footer a[href^='tel:']";
    }

    public static final class TestData {
        public static final String SEARCH_KEYWORD = "test";
        public static final String SEARCH_KEYWORD_TYPO = "vbfbfg";
        public static final String SEARCH_PATTERN = "property=\"og:title\" content=\"You searched for : %s - Innowise\"";

        // Ожидаемые тексты на странице поиска
        public static final String SEARCH_FIELD = "search-field";
        public static final String SEARCH_RESULT = "search-result";
        public static final String SEARCH_RESULTS_TEXT = "Search results";
        public static final String CONTENT_MASONRY = "content-masonry";
    }

    public static final class Errors {
        public static final String SEARCH_CONTAINER_NOT_DISPLAYED = "Контейнер с результатами поиска не отображается";
        public static final String SEARCH_CONTAINER_NOT_FOUND = "Поисковый контейнер не найден";
        public static final String SEARCH_RESULTS_NOT_DISPLAYED = "Результаты поиска не отображаются";
        public static final String LOGO_NOT_DISPLAYED = "Логотип не отображается";
        public static final String VERIFYING_ERROR = "Ошибка верификации поискового запроса";
        public static final String HEADERS_NOT_FOUND = "Заголовки результатов поиска не найдены";
        public static final String EMPTY_CONTAINER_EXPECTED = "Контейнер должен быть пустым";
        public static final String ICON_NOT_CLICKABLE = "Иконка не кликабельна в течение %d секунд";
        public static final String UTILITY_CLASS_INSTANTIATION = "Невозможно создать экземпляр класса-утилиты";
        public static final String UNSUPPORTED_BROWSER = "Неподдерживаемый браузер: %s";
        public static final String REMOTE_DRIVER_NOT_SUPPORTED = "Удаленный драйвер не поддерживается для браузера: %s";
        public static final String DRIVER_QUIT_ERROR = "Ошибка при закрытии драйвера: ";
        public static final String INVALID_REMOTE_URL = "Некорректный URL удаленного сервера: %s";
        public static final String SCREENSHOT_FAILED = "Не удалось создать скриншот: %s";
        public static final String STABLE_ELEMENT_INTERRUPTED = "Ожидание стабильного элемента прервано";
        public static final String ELEMENT_NOT_STABILIZED = "Элемент %s не стабилизировался после %d попыток";
        public static final String SEARCH_ICON_NOT_CLICKABLE = "Иконка поиска не кликабельна";
        public static final String PLACEHOLDER_MISMATCH = "Ожидался placeholder '%s', но получен '%s'";
        public static final String CONTENT_HEADERS_MISMATCH = "Ни один из %d заголовков не содержит '%s': %s";
        public static final String SEARCH_RESULTS_SHOULD_BE_EMPTY = "Результаты поиска не должны отображаться для несуществующего слова";
        public static final String FOOTER_LINK_NOT_FOUND = "Ссылка не найдена в футере: %s";

        // 404 Page
        public static final String TITLE_404 = "Страница не найдена - Innowise";
        public static final String CONTENT_404_EN = "The page you were looking for couldn't be found";
    }

    public static final class StoreKeys {
        public static final String SCREENSHOT = "screenshot";
        public static final String CLASS_LEVEL_DRIVER = "class-level-driver";
    }

    public static final class Allure {
        public static final String MIME_PNG = "image/png";
        public static final String MIME_TEXT = "text/plain";
        public static final String MIME_JSON = "application/json";
        public static final String MIME_HTML = "text/html";

        public static final String EXT_PNG = "png";

    }

    public static final class Paths {
        public static final String SCREENSHOT_DIR = "target/screenshots";
        public static final String FOOTER_CONTACTS_CSV = "/data/footer_contacts.csv";
    }

    public static final class Formats {
        public static final String SCREENSHOT_TIMESTAMP = "yyyy-MM-dd_HH-mm-ss-SSS";
        public static final String SCREENSHOT_FILENAME = "%s_%s.png";
    }

    public static final class JS {
        public static final String READY_STATE = "return document.readyState";
        public static final String COMPLETE = "complete";
        public static final String SCROLL_INTO_VIEW = "arguments[0].scrollIntoView(true);";
        public static final String SCROLL_TO_TOP = "window.scrollTo(0, 0);";
        public static final String SCROLL_TO_BOTTOM = "window.scrollTo(0, document.body.scrollHeight);";
    }

    public static final class Api {
        public static final String DEFAULT_CONTENT_TYPE = "text/html";
        public static final String HTML_UTF8 = "text/html; charset=UTF-8";

        public static final class QueryParams {
            public static final String SEARCH = "s";
        }
    }

    public static final class Messages {
        public static final String LOGO_SHOULD_BE_DISPLAYED = "Логотип должен отображаться на главной странице";
        public static final String SEARCH_RESULTS_SHOULD_BE_DISPLAYED = "Результаты поиска должны отображаться";
        public static final String SEARCH_INPUT_SHOULD_BE_DISPLAYED = "Поле поиска должно отображаться после клика на иконку";
        public static final String FOOTER_LINK_TEXT_MISMATCH = "Ожидался текст '%s' для ссылки '%s'";
    }

    public static final class Content {
        public static final String ABOUT_US_KEYWORD = "about";
    }

    public static final class ConfigKeys {
        // Browser
        public static final String BROWSER = "browser";
        public static final String HEADLESS = "headless";
        public static final String BROWSER_WIDTH = "browser.width";
        public static final String BROWSER_HEIGHT = "browser.height";

        // Timeouts
        public static final String TIMEOUT_EXPLICIT = "timeout.explicit";
        public static final String TIMEOUT_PAGE = "timeout.page";
        public static final String TIMEOUT_SCRIPT = "timeout.script";
        public static final String TIMEOUT_POLLING = "timeout.polling";

        // Environment
        public static final String BASE_URL = "base.url";
        public static final String HUB_URL = "hub.url";
        public static final String REMOTE = "remote";

        // Screenshots
        public static final String SCREENSHOTS_ENABLED = "screenshots.enabled";
        public static final String SCREENSHOTS_DIR = "screenshots.dir";

        // Debug
        public static final String HIGHLIGHT_ENABLED = "highlight.enabled";
        public static final String DEBUG_MODE = "debug.mode";
    }
}