package com.practicesoftwaretesting.base;

import com.practicesoftwaretesting.pages.HomePage;
import com.practicesoftwaretesting.utils.allure.AllureAttachments;
import com.practicesoftwaretesting.utils.allure.AllureEnvironment;
import com.practicesoftwaretesting.utils.configuration.TestConfiguration;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeSuite(alwaysRun = true)
    public void setUpAllureEnvironment() {
        AllureEnvironment.writeEnvironment();
    }

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.get(TestConfiguration.getBaseUrl());
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
