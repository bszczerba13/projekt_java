package com.practicesoftwaretesting.base;

import com.practicesoftwaretesting.utils.configuration.TestConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

/**
 * Creates and manages WebDriver instance.
 */
public class DriverFactory {

    private static WebDriver driver;

    /**
     * Initializes a WebDriver instance based on the configured browser.
     *
     * @return initialized WebDriver instance
     * @throws IllegalArgumentException if the configured browser is not supported
     */
    public static WebDriver getDriver() {
        String browser = TestConfiguration.getBrowser();
        boolean headless = TestConfiguration.isHeadless();

        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions();

            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            } else {
                options.addArguments("start-maximized");
            }

            driver = new ChromeDriver(options);

        } else if ("firefox".equalsIgnoreCase(browser)) {
            FirefoxOptions options = new FirefoxOptions();

            if (headless) {
                options.addArguments("--headless");
                options.addArguments("--width=1920");
                options.addArguments("--height=1080");
            }

            driver = new FirefoxDriver(options);

            if (!headless) {
                driver.manage().window().maximize();
            }

        } else if ("edge".equalsIgnoreCase(browser)) {
            EdgeOptions options = new EdgeOptions();

            if (headless) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new EdgeDriver(options);

            if (!headless) {
                driver.manage().window().maximize();
            }
        } else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser + ". Supported browsers: chrome, firefox, edge."
            );
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
