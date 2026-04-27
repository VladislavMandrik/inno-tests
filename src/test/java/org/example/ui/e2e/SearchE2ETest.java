package org.example.ui.e2e;

import io.qameta.allure.*;
import org.example.config.Constants;
import org.example.ui.base.BaseTest;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("E2E Tests")
@Feature("Поиск")
@Tag("e2e")
@DisplayName("E2E: Поиск по сайту")
public class SearchE2ETest extends BaseTest {

    @Test
    @Story("Успешный поиск")
    @DisplayName("E2E: Успешный поиск с валидным запросом")
    @Description("Полный сценарий поиска от главной страницы до результатов")
    public void shouldFindResultsForValidSearchQuery() {
        mainPage
                .open()
                .waitForPageLoaded()
                .performSearch(Constants.TestData.SEARCH_KEYWORD)
                .waitForSearchContainer();

        assertTrue(searchPage.hasResults(), Constants.Messages.SEARCH_RESULTS_SHOULD_BE_DISPLAYED);

        List<String> headers = searchPage.getResultsHeaders();
        assertFalse(headers.isEmpty(), Constants.Errors.HEADERS_NOT_FOUND);

        boolean found = searchPage.headersContainKeyword(Constants.TestData.SEARCH_KEYWORD);
        assertTrue(found, String.format(Constants.Errors.CONTENT_HEADERS_MISMATCH,
                headers.size(), Constants.TestData.SEARCH_KEYWORD, headers));
    }

    @Test
    @Story("Негативный сценарий")
    @DisplayName("E2E: Поиск с опечаткой")
    @Description("Проверка поведения системы при поиске несуществующего слова")
    public void shouldHandleTypoInSearchQuery() {
        mainPage
                .open()
                .waitForPageLoaded()
                .performSearch(Constants.TestData.SEARCH_KEYWORD_TYPO);

        assertTrue(searchPage.waitForSearchContainerHidden(),
                Constants.Errors.SEARCH_RESULTS_SHOULD_BE_EMPTY);
    }
}