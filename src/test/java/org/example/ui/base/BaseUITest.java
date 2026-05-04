package org.example.ui.base;

import org.example.pages.MainPage;
import org.example.pages.SearchPage;
import org.example.pages.operations.MainPageOperations;
import org.example.pages.operations.SearchPageOperations;
import org.example.utils.driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseUITest {

    protected WebDriver driver;

    protected MainPage mainPage;
    protected MainPageOperations mainPageOperations;
    protected SearchPage searchPage;
    protected SearchPageOperations searchPageOperations;


    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        mainPage = new MainPage(driver);
        mainPageOperations = new MainPageOperations(driver);
        searchPage = new SearchPage(driver);
        searchPageOperations = new SearchPageOperations(driver);
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }
}