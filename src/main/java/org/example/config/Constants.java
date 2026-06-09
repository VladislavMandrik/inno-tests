package org.example.config;

import java.time.Duration;

public final class Constants {

    private Constants() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static final Duration POLLING_INTERVAL = Duration.ofMillis(200);

    public static final String INVALID_USERNAME = "INVALID_USERNAME";
    public static final String INVALID_PASSWORD = "INVALID_PASSWORD";
    public static final String EMPTY_STRING = "";

    public static final class Headers {
        public static final String ACCEPT = "accept";
        public static final String X_REQUESTED_WITH = "x-requested-with";
        public static final String XML_HTTP_REQUEST = "XMLHttpRequest";
        public static final String REFERER = "referer";
    }

    public static final class MimeTypes {
        public static final String APPLICATION_JSON_TEXT = "application/json, text/plain, */*";
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
        public static final String INNOWISE_URL = "https://innowise.com";
        public static final String LOCALHOST_URL = "http://localhost:8081";
        public static final String DEFAULT_SELENIUM_HUB_URL = "http://localhost:4444/wd/hub";
    }

    public static final class Endpoints {
        public static final String MAIN_PAGE = "/";
        public static final String ABOUT_US = "/about-us/";
        public static final String ABOUT_US_RU = "/ru/about-us/";
        public static final String AJAX = "/wp-admin/admin-ajax.php";
        public static final String LOGIN_PAGE_ENDPOINT = "/login";
    }

    public static final class Auth {
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
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
        public static final String AJAX_URL_PATTERN = "admin-ajax.php";
        public static final String AJAX_ACTION = "alm_get_posts";
        public static final String FOOTER_PHONE_LINKS = ".grid-column-footer a[href^='tel:']";
        public static final String MOBILE_MENU = ".new-menu-in";
    }

    public static final class TestData {
        public static final String SEARCH_KEYWORD = "test";
        public static final String SEARCH_KEYWORD_TYPO = "vbfbfg";
        public static final String AJAX_EMPTY_RESPONSE = "{\"html\":\"\",\"meta\":{\"postcount\":0}}";
        public static final String SEARCH_PATTERN = "property=\"og:title\" content=\"You searched for : %s - Innowise\"";

        public static final String SEARCH_FIELD = "search-field";
        public static final String SEARCH_RESULT = "search-result";
        public static final String SEARCH_RESULTS_TEXT = "Search results";
        public static final String CONTENT_MASONRY = "content-masonry";

        public static final String SQL_INJECTION = "' OR '1'='1";
        public static final String XSS_SCRIPT = "<script>alert('xss')</script>";
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
        public static final String SEARCH_RESULTS_SHOULD_BE_EMPTY = "Результаты поиска не должны отображаться";
        public static final String FOOTER_LINK_NOT_FOUND = "Ссылка не найдена в футере: %s";
        public static final String PROXY_NOT_STARTED = "Proxy is not started. Call ProxyManager.start() first.";

        // 404 Page
        public static final String TITLE_404 = "Страница не найдена - Innowise";
        public static final String CONTENT_404_EN = "The page you were looking for couldn't be found";
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
        public static final String SEARCH_REFERER = "/?s=";
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
        public static final String SCROLL_POSITION = "return window.scrollY;";
        public static final String WINDOW_INNER_HEIGHT = "return window.innerHeight;";
        public static final String BODY_SCROLL_HEIGHT = "return document.body.scrollHeight;";
    }

    public static final class Api {
        public static final String DEFAULT_CONTENT_TYPE = "text/html";
        public static final String HTML_UTF8 = "text/html; charset=UTF-8";

        public static final class QueryParams {
            public static final String SEARCH = "s";
            public static final String ACTION = "action";
            public static final String ALM_GET_POSTS = "alm_get_posts";
            public static final String POST_TYPE = "post_type";
            public static final String POST_TYPE_VALUE = "post, page, case";
            public static final String POSTS_PER_PAGE = "posts_per_page";
            public static final String POSTS_PER_PAGE_VALUE = "-1";
            public static final String ORDERBY = "orderby";
            public static final String ORDERBY_VALUE = "relevance";
        }
    }

    public static final class Messages {
        public static final String LOGO_SHOULD_BE_DISPLAYED = "Логотип должен отображаться на главной странице";
        public static final String SEARCH_RESULTS_SHOULD_BE_DISPLAYED = "Результаты поиска должны отображаться";
        public static final String SEARCH_INPUT_SHOULD_BE_DISPLAYED = "Поле поиска должно отображаться после клика на иконку";
        public static final String SEARCH_CONTAINER_SHOULD_BE_DISPLAYED = "Контейнер поиска должен отображаться";
        public static final String AJAX_INTERCEPTED = "BrowserMob Proxy перехватил AJAX";
        public static final String FOOTER_LINK_TEXT_MISMATCH = "Ожидался текст '%s' для ссылки '%s'";
        public static final String HEADER_SHOULD_BE_VISIBLE_AT_START = "Хедер должен быть виден в начале";
        public static final String HEADER_SHOULD_BE_VISIBLE_AFTER_SCROLL = "Хедер должен быть виден после скролла";
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
        public static final String PROXY_ENABLED = "proxy.enabled";

        // Timeouts
        public static final String TIMEOUT_EXPLICIT = "timeout.explicit";
        public static final String TIMEOUT_PAGE = "timeout.page";
        public static final String TIMEOUT_SCRIPT = "timeout.script";
        public static final String TIMEOUT_POLLING = "timeout.polling";

        // Screenshots
        public static final String SCREENSHOTS_ENABLED = "screenshots.enabled";
        public static final String SCREENSHOTS_DIR = "screenshots.dir";
    }
}