package com.practicesoftwaretesting.tests;

import com.practicesoftwaretesting.data.DataGenerator;
import com.practicesoftwaretesting.data.InvalidLoginData;
import com.practicesoftwaretesting.pages.AccountPage;
import com.practicesoftwaretesting.pages.LoginPage;
import com.practicesoftwaretesting.base.BaseTest;
import com.practicesoftwaretesting.data.LoginDataProvider;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setLoginPage() {
        loginPage = homePage.clickSignIn();
    }

    @Test(dataProvider = "loginData", dataProviderClass = LoginDataProvider.class)
    public void loginTest(String email, String password, String role){
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        if (loginPage.isAccountLocked()){
            throw new SkipException("User account locked");
        }
        AccountPage accountPage = new AccountPage(driver);
        String pageTitle = accountPage.getPageTitle();
        if (role.equals("admin")){
            Assert.assertEquals(pageTitle, "Sales over the years");
        } else if (role.equals("user")) {
            Assert.assertEquals(pageTitle, "My account");
        } else {
            Assert.fail("Unexpected role :" + role);
        }
    }

    @Test
    public void invalidLoginDataTest(){
        InvalidLoginData data = new DataGenerator().invalidLoginDataGenerator();
        loginPage.enterEmail(data.email);
        loginPage.enterPassword(data.password);
        loginPage.clickLoginButton();
        String error_message = loginPage.getInvalidLoginError();
        Assert.assertTrue(error_message.contains("Invalid email or password"));
    }
}
