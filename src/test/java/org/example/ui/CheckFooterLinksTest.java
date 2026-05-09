package org.example.ui;

import io.qameta.allure.*;
import org.example.config.Constants;
import org.example.ui.operations.MainPageOperations;
import org.example.ui.base.BaseUITest;
import org.example.utils.driver.DriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.SAME_THREAD)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Epic("E2E Tests")
@Feature("Отображение футера")
@Tag("e2e")
@DisplayName("E2E: Проверка футера")
public class CheckFooterLinksTest extends BaseUITest {
    private MainPageOperations mainPageOperations;

    @BeforeAll
    void openPageOnce() {
        mainPageOperations = new MainPageOperations(DriverManager.getDriver());
        mainPageOperations.openAndWait();
    }

    @AfterEach
    @Override
    public void tearDown() {
    }

    @AfterAll
    void closeDriver() {
        DriverManager.quitDriver();
    }

    @ParameterizedTest(name = "Ссылка \"{0}\" → текст \"{1}\"")
    @CsvSource({
            "/about-us/, About us",
            "/how-we-work/, How we work",
            "/careers/, Career",
            "/partners/, Tech partners",
            "/technologies/, Technologies",
            "/blog/, Blog",
            "/cases/, Case studies"
    })
    @Story("Отображение футера")
    @DisplayName("E2E: Проверка ссылок в футере")
    @Description("Проверка текста ссылок в разделе About Us футера")
    public void shouldDisplayCorrectFooterLinksAU(String href, String expectedText) {
        String actualText = mainPageOperations
                .getFooterLinkText(href);

        assertEquals(expectedText, actualText,
                () -> String.format(Constants.Messages.FOOTER_LINK_TEXT_MISMATCH, expectedText, href));
    }

    @ParameterizedTest(name = "Ссылка \"{0}\" → текст \"{1}\"")
    @CsvFileSource(resources = Constants.Paths.FOOTER_CONTACTS_CSV, numLinesToSkip = 1)
    @Story("Отображение футера")
    @DisplayName("E2E: Проверка ссылок в футере")
    @Description("Проверка текста ссылок в разделе Contact Us футера")
    public void shouldDisplayCorrectFooterLinksCU(String href, String expectedText) {
        String actualText = mainPageOperations
                .getFooterLinkText(href);

        assertEquals(expectedText, actualText,
                () -> String.format(Constants.Messages.FOOTER_LINK_TEXT_MISMATCH, expectedText, href));
    }
}