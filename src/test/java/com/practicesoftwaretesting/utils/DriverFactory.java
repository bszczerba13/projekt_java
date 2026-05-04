package com.practicesoftwaretesting.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver(String browser){
        if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        }
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    public static void quitDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }

}
