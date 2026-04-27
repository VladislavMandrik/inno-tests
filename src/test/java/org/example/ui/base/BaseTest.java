package org.example.ui.base;

import org.example.pages.MainPage;
import org.example.pages.SearchPage;
import org.example.utils.driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {

    protected WebDriver driver;

    protected MainPage mainPage;
    protected SearchPage searchPage;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        mainPage = new MainPage(driver);
        searchPage = new SearchPage(driver);
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }
}