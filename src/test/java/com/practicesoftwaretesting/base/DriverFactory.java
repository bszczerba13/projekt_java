package com.practicesoftwaretesting.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Creates and manages WebDriver instance.
 */
public class DriverFactory {

    private static WebDriver driver;
    private static ChromeOptions options;

    /**
     * Initializes browser instance based on provided browser name.
     *
     * @param browser browser type
     * @return WebDriver instance
     */
    public static WebDriver getDriver(String browser){
        if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        }
        else {
            options = new ChromeOptions();
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    /**
     * Closes browser and quits WebDriver session.
     */
    public static void quitDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }

}
