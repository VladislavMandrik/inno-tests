package org.example.api;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public abstract class BaseApiTest {

    @BeforeEach
    void setUp(TestInfo testInfo) {
        logTestStart(testInfo);

    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        logTestEnd(testInfo);
    }

    @Step("Запуск теста: {testInfo.displayName}")
    private void logTestStart(TestInfo testInfo) {
    }

    @Step("Завершение теста: {testInfo.displayName}")
    private void logTestEnd(TestInfo testInfo) {
    }
}