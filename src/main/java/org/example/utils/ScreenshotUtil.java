package org.example.utils;

import org.example.config.Constants;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtil {

    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtil.class);
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern(Constants.Formats.SCREENSHOT_TIMESTAMP);

    static {
        try {
            Files.createDirectories(Paths.get(Constants.Paths.SCREENSHOT_DIR));
        } catch (Exception e) {
//            logger.warn(Constants.Logs.SCREENSHOT_DIR_ERROR, Constants.Paths.SCREENSHOT_DIR);
        }
    }

    private ScreenshotUtil() {
        throw new UnsupportedOperationException(Constants.Errors.UTILITY_CLASS_INSTANTIATION);
    }

    public static File takeScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

            String timestamp = LocalDateTime.now().format(FORMATTER);
            String fileName = String.format(Constants.Formats.SCREENSHOT_FILENAME, testName, timestamp);
            String filePath = Constants.Paths.SCREENSHOT_DIR + File.separator + fileName;

            File destFile = new File(filePath);
            Files.copy(srcFile.toPath(), destFile.toPath());

//            logger.info(Constants.Logs.SCREENSHOT_SAVED, filePath);
            return destFile;
        } catch (Exception e) {
//            logger.error(Constants.Logs.SCREENSHOT_SAVE_ERROR, e);
            return null;
        }
    }

    public static byte[] takeScreenshotAsBytes(WebDriver driver) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            return screenshot.getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
//            logger.error(Constants.Logs.SCREENSHOT_BYTES_ERROR, e);
            return new byte[0];
        }
    }
}