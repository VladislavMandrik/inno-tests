package org.example.ui;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import net.lightbody.bmp.BrowserMobProxy;
import org.example.config.Constants;
import org.example.pages.SearchPage;
import org.example.ui.base.BaseUITest;
import org.example.ui.operations.MainPageOperations;
import org.example.utils.proxy.ProxyManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("E2E Tests")
@Feature("AJAX Load More в поиске")
@Tag("e2e")
@DisplayName("E2E: Обработка ошибок AJAX через BrowserMob Proxy при поиске на странице")
class AjaxLoadMoreErrorTest extends BaseUITest {
    private MainPageOperations mainPageOperations;
    private SearchPage searchPage;

    @BeforeAll
    static void startProxy() {
        ProxyManager.start();
        System.setProperty(Constants.ConfigKeys.PROXY_ENABLED, "true");

        BrowserMobProxy bmp = ProxyManager.getProxyInstance();
        bmp.addResponseFilter((response, contents, messageInfo) -> {
            String url = messageInfo.getOriginalUrl();
            if (url != null
                    && url.contains(Constants.Selectors.AJAX_URL_PATTERN)
                    && url.contains(Constants.Selectors.AJAX_ACTION)) {
                response.setStatus(HttpResponseStatus.BAD_REQUEST);
                contents.setTextContents(Constants.TestData.AJAX_EMPTY_RESPONSE);
                response.headers().set("Content-Type", "application/json");
            }
        });
    }

    @AfterAll
    static void stopProxy() {
        ProxyManager.stop();
        System.clearProperty(Constants.ConfigKeys.PROXY_ENABLED);
    }

    @BeforeEach
    void init() {
        mainPageOperations = new MainPageOperations(driver);
        searchPage = new SearchPage(driver);
    }

    @Test
    @Story("Ошибка 400 при AJAX Load More")
    @DisplayName("E2E: Обработка 400 ошибки AJAX")
    @Description("Проверка, что при 400 ошибке AJAX результаты поиска не подгружаются")
    void shouldHandleAjax400Error() {
        mainPageOperations
                .openAndWait()
                .performSearch(Constants.TestData.SEARCH_KEYWORD)
                .waitForSearchContainer();

        assertTrue(searchPage.waitForSearchResultsHidden(),
                Constants.Errors.SEARCH_RESULTS_SHOULD_BE_EMPTY);
    }
}