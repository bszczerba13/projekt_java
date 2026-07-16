package com.practicesoftwaretesting.base;

import com.practicesoftwaretesting.pages.HomePage;
import com.practicesoftwaretesting.utils.allure.AllureAttachments;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    @Parameters({"browser", "baseUrl"})
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser, @Optional("https://practicesoftwaretesting.com/") String baseUrl) {
        driver = DriverFactory.getDriver(browser);
        driver.get(baseUrl);
        homePage = new HomePage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            AllureAttachments.attachScreenshot(driver);
            AllureAttachments.attachCurrentUrl(driver);
        }
        DriverFactory.quitDriver();
    }
}
