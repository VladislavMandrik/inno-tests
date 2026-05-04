package org.example.ui.smoke;

import io.qameta.allure.*;
import org.example.config.Constants;
import org.example.ui.base.BaseUITest;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("E2E Tests")
@Feature("Smoke")
@Tag("smoke")
@Tag("e2e")
@DisplayName("Smoke: Критические проверки")
public class SmokeTests extends BaseUITest {

    @Test
    @Story("Главная страница")
    @DisplayName("Smoke: Главная страница загружается")
    @Description("Проверка доступности главной страницы")
    public void shouldLoadMainPage() {
        mainPage
                .open()
                .waitForPageLoaded();
        assertTrue(mainPage.isLogoDisplayed(), Constants.Messages.LOGO_SHOULD_BE_DISPLAYED);
    }

    @Test
    @Story("Поиск")
    @DisplayName("Smoke: Быстрый поиск работает")
    @Description("Smoke проверка поиска")
    public void shouldPerformQuickSearch() {
        mainPage.open();
        mainPageOperations.performSearch(Constants.TestData.SEARCH_KEYWORD);
        assertTrue(searchPageOperations.hasResults(), Constants.Messages.SEARCH_RESULTS_SHOULD_BE_DISPLAYED);
    }

    @Test
    @Story("Поиск")
    @DisplayName("Smoke: Иконка поиска кликабельна")
    @Description("Проверка доступности иконки поиска")
    void shouldHaveClickableSearchIcon() {
        mainPage
                .open()
                .waitForPageLoaded()
                .clickSearchIcon();
        assertTrue(mainPage.isSearchInputDisplayed(), Constants.Messages.SEARCH_INPUT_SHOULD_BE_DISPLAYED);
    }
}