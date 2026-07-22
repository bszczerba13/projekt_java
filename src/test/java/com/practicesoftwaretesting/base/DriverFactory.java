package com.practicesoftwaretesting.base;

import com.practicesoftwaretesting.utils.configuration.TestConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Creates and manages WebDriver instance.
 */
public class DriverFactory {

    private static WebDriver driver;

    /**
     * Initializes browser instance based on provided browser name.
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver(){
        String browser = TestConfiguration.getBrowser();
        if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        }
        else {
            ChromeOptions options = new ChromeOptions();
            if (TestConfiguration.isHeadless()) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            } else {
                options.addArguments("start-maximized");
            }
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
