package org.example.unit;

import org.example.config.Constants;
import org.example.pages.MainPage;
import org.example.ui.operations.MainPageOperations;
import org.example.utils.helpers.PageActionsHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.WebDriver;

import static org.example.utils.helpers.PageActionsHelper.scrollBy;
import static org.example.utils.helpers.PageActionsHelper.waitForScrollAt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainPageOperationsTest {
    private static final int SCROLL_PIXELS = 1500;
    @Mock
    private MainPage mainPage;
    @Mock
    private WebDriver driver;

    private MainPageOperations mainPageOperations;
    private MockedStatic<PageActionsHelper> mockedHelper;

    @BeforeEach
    void setUp() {
        mockedHelper = mockStatic(PageActionsHelper.class);
        mainPageOperations = new MainPageOperations(mainPage);
        when(mainPage.getDriver()).thenReturn(driver);
    }

    @AfterEach
    void tearDown() {
        mockedHelper.close();
    }

    @Test
    @DisplayName("open() должен вызвать driver.get() с правильным URL и waitForPageReady()")
    void shouldNavigateToMainPage() {
        mainPageOperations.open();

        verify(driver).get(Constants.Environment.INNOWISE_URL + Constants.Endpoints.MAIN_PAGE);
        mockedHelper.verify(PageActionsHelper::waitForPageReady);
    }

    @Test
    @DisplayName("scrollPage() должен вызвать scrollBy() и waitForScrollAt() с правильными пикселями")
    void shouldScrollPageWithCorrectPixels() {
        mainPageOperations.scrollPage(SCROLL_PIXELS);

        mockedHelper.verify(() -> scrollBy(driver, SCROLL_PIXELS));
        mockedHelper.verify(() -> waitForScrollAt(SCROLL_PIXELS));
    }
}