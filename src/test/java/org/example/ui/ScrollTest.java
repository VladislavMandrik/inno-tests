package org.example.ui;

import io.qameta.allure.*;
import org.example.config.Constants;
import org.example.pages.MainPage;
import org.example.ui.operations.MainPageOperations;
import org.example.ui.base.BaseUITest;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("E2E Tests")
@Feature("Хедер")
@Tag("e2e")
@DisplayName("E2E: Проверка хедера после скролла")
public class ScrollTest extends BaseUITest {
    private MainPageOperations mainPageOperations;
    private MainPage mainPage;

    @BeforeEach
    void init() {
        mainPageOperations = new MainPageOperations(driver);
        mainPage = new MainPage(driver);
    }

    @Test
    @Story("Хедер")
    @DisplayName("E2E: Хедер виден после скролла")
    @Description("Проверка, что хедер не пропадает после скролла")
    public void testHeaderVisibleAfterScroll() {
        mainPageOperations.openAndWait();
        assertTrue(mainPage.isMenuDisplayed(), Constants.Messages.HEADER_SHOULD_BE_VISIBLE_AT_START);
        mainPageOperations.scrollPage(800);
        assertTrue(mainPage.isMenuDisplayed(), Constants.Messages.HEADER_SHOULD_BE_VISIBLE_AFTER_SCROLL);
    }
}