package org.example.ui.smoke;

import io.qameta.allure.*;
import org.example.config.Constants;
import org.example.pages.MainPage;
import org.example.ui.base.BaseUITest;
import org.example.ui.operations.MainPageOperations;
import org.example.ui.operations.SearchPageOperations;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("E2E Tests")
@Feature("Smoke")
@Tag("smoke")
@Tag("e2e")
@DisplayName("Smoke: Критические проверки")
public class SmokeTests extends BaseUITest {
    private MainPageOperations mainPageOperations;
    private SearchPageOperations searchPageOperations;
    private MainPage mainPage;

    @BeforeEach
    void init() {
        mainPageOperations = new MainPageOperations(driver);
        searchPageOperations = new SearchPageOperations(driver);
        mainPage = new MainPage(driver);
    }

    @Test
    @Story("Главная страница")
    @DisplayName("Smoke: Главная страница загружается")
    @Description("Проверка доступности главной страницы")
    public void shouldLoadMainPage() {
        mainPageOperations
                .openAndWait();
        assertTrue(mainPage.isLogoDisplayed(), Constants.Messages.LOGO_SHOULD_BE_DISPLAYED);
    }

    @Test
    @Story("Поиск")
    @DisplayName("Smoke: Быстрый поиск работает")
    @Description("Smoke проверка поиска")
    public void shouldPerformQuickSearch() {
        mainPageOperations
                .open()
                .performSearch(Constants.TestData.SEARCH_KEYWORD);
        assertTrue(searchPageOperations.hasResults(), Constants.Messages.SEARCH_RESULTS_SHOULD_BE_DISPLAYED);
    }

    @Test
    @Story("Поиск")
    @DisplayName("Smoke: Иконка поиска кликабельна")
    @Description("Проверка доступности иконки поиска")
    void shouldHaveClickableSearchIcon() {
        mainPageOperations.openAndWait();
        mainPage.clickSearchIcon();
        assertTrue(mainPage.isSearchInputDisplayed(), Constants.Messages.SEARCH_INPUT_SHOULD_BE_DISPLAYED);
    }
}