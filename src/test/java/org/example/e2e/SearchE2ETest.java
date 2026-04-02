package org.example.e2e;

import org.example.base.BaseTest;
import org.example.test_data.Constants;
import org.example.e2e.pages.MainPage;
import org.example.e2e.pages.SearchPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SearchE2ETest extends BaseTest {
    private MainPage mainPage;
    private SearchPage searchPage;

    @BeforeEach
    public void initPages() {
        mainPage = new MainPage(driver, wait);
        searchPage = new SearchPage(driver, wait);
    }

    @Test
    @DisplayName("E2E: Успешный поиск на сайте")
    public void testSearchText() {
        mainPage.searchText(Constants.SEARCH_KEYWORD);
        searchPage.verifySearchResults();
    }

    @Test
    @DisplayName("E2E: Поиск с опечаткой в запросе")
    public void testSearchTextWithTypo() {
        mainPage.searchText(Constants.SEARCH_KEYWORD_TYPO);
        searchPage.checkEmptySearchContainer();
    }
}