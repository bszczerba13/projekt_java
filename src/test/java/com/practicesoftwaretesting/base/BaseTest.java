package com.practicesoftwaretesting.base;

import com.practicesoftwaretesting.pages.HomePage;
import com.practicesoftwaretesting.utils.allure.AllureAttachments;
import com.practicesoftwaretesting.utils.allure.AllureEnvironment;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    @Parameters({"browser", "baseUrl"})
    @BeforeSuite(alwaysRun = true)
    public void setUpAllureEnvironment() {
        AllureEnvironment.writeEnvironment();
    }

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
