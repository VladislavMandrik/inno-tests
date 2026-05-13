package org.example.ui.base;

import org.example.utils.driver.DriverManager;
import org.example.utils.helpers.PageActionsHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseUITest {

    protected WebDriver driver;


    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        PageActionsHelper.init(driver);
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }
}