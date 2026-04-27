package org.example.utils.helpers;

import io.qameta.allure.Allure;
import org.example.config.Constants;
import org.example.utils.ScreenshotUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public final class AllureHelper {

    private AllureHelper() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static void attachScreenshot(WebDriver driver, String name) {
        try {
            byte[] screenshot = ScreenshotUtil.takeScreenshotAsBytes(driver);
            Allure.addAttachment(name, Constants.Allure.MIME_PNG,
                    new ByteArrayInputStream(screenshot), Constants.Allure.EXT_PNG);
        } catch (Exception e) {
        }
    }

    public static void attachText(String name, String text) {
        try {
            Allure.addAttachment(name, Constants.Allure.MIME_TEXT, text);
        } catch (Exception e) {
        }
    }

    public static void attachJson(String name, String json) {
        try {
            Allure.addAttachment(name, Constants.Allure.MIME_JSON, json);
        } catch (Exception e) {
        }
    }

    public static void attachHtml(String name, String html) {
        try {
            Allure.addAttachment(name, Constants.Allure.MIME_HTML, html);
        } catch (Exception e) {
        }
    }
}