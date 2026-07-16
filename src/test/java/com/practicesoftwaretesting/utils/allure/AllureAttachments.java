package com.practicesoftwaretesting.utils.allure;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

/**
 * Utility class responsible for attaching diagnostic artifacts
 * to the Allure report.
 *
 * <p>The methods in this class are intended to be used only for
 * reporting purposes and must never affect the execution or result
 * of a test. Any WebDriver-related exceptions are handled internally
 * to preserve the original test outcome.</p>
 */
public final class AllureAttachments {

    /**
     * Prevent instantiation of utility class.
     */
    private AllureAttachments() {
        throw new UnsupportedOperationException(
                "This is a utility class and cannot be instantiated."
        );
    }

    /**
     * Attaches a PNG screenshot of the current browser window
     * to the Allure report.
     *
     * @param driver active WebDriver instance
     */
    public static void attachScreenshot(WebDriver driver) {
        try {
            byte[] screenshot =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            saveScreenshot(screenshot);

        } catch (WebDriverException e) {
            // Reporting must never affect test execution.
        }
    }

    /**
     * Saves screenshot as an attachment in the Allure report.
     *
     * @param screenshot screenshot in PNG format
     * @return screenshot attached by Allure
     */
    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] saveScreenshot(byte[] screenshot) {
        return screenshot;
    }

    /**
     * Attaches the current browser URL to the Allure report.
     *
     * @param driver active WebDriver instance
     */
    public static void attachCurrentUrl(WebDriver driver) {
        try {
            saveCurrentUrl(driver.getCurrentUrl());

        } catch (WebDriverException e) {
            // Reporting must never affect test execution.
        }
    }

    /**
     * Saves the current browser URL as an attachment
     * in the Allure report.
     *
     * @param url current browser URL
     * @return URL to be attached to the report
     */
    @Attachment(value = "Current URL", type = "text/plain")
    private static String saveCurrentUrl(String url) {
        return url;
    }

}