package com.practicesoftwaretesting.base;

import com.practicesoftwaretesting.utils.configuration.TestConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

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
        return switch (TestConfiguration.getEnvironment()) {
            case LOCAL -> createLocalDriver();
            case DOCKER -> createRemoteDriver();
            case CI -> createRemoteDriver();
        };
    }

    private static ChromeOptions createChromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--window-size=1920,1080");

        if (headless) {
            options.addArguments("--headless=new");
        }

        return options;
    }

    private static FirefoxOptions createFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        options.addArguments("--width=1920");
        options.addArguments("--height=1080");

        if (headless) {
            options.addArguments("--headless");
        }

        return options;
    }

    private static EdgeOptions createEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();

        options.addArguments("--window-size=1920,1080");

        if (headless) {
            options.addArguments("--headless=new");
        }

        return options;
    }

    /**
     * Creates a local WebDriver instance.
     *
     * @return initialized local WebDriver instance
     */
    private static WebDriver createLocalDriver() {
        String browser = TestConfiguration.getBrowser();
        boolean headless = TestConfiguration.isHeadless();

        if ("chrome".equalsIgnoreCase(browser)) {
            driver = new ChromeDriver(createChromeOptions(headless));

        } else if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver(createFirefoxOptions(headless));

        } else if ("edge".equalsIgnoreCase(browser)) {
            driver = new EdgeDriver(createEdgeOptions(headless));

        } else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser + ". Supported browsers: chrome, firefox, edge."
            );
        }

        return driver;
    }

    /**
     * Creates a remote WebDriver instance.
     *
     * @return initialized remote WebDriver instance
     */
    private static WebDriver createRemoteDriver() {
        String browser = TestConfiguration.getBrowser();
        boolean headless = TestConfiguration.isHeadless();

        try {
            switch (browser.toLowerCase()) {
                case "chrome" -> driver =  new RemoteWebDriver(
                        new URL("http://localhost:4444"),
                        createChromeOptions(headless)
                );

                case "firefox" -> driver = new RemoteWebDriver(
                        new URL("http://localhost:4445"),
                        createFirefoxOptions(headless)
                );

                case "edge" -> driver = new RemoteWebDriver(
                        new URL("http://localhost:4446"),
                        createEdgeOptions(headless)
                );

                default -> throw new IllegalArgumentException(
                        "Unsupported browser: " + browser
                                + ". Supported browsers: chrome, firefox, edge."
                );
            };

            return driver;

        } catch (MalformedURLException e) {
            throw new IllegalStateException(
                    "Invalid Selenium URL for browser: " + browser,
                    e
            );
        }
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
