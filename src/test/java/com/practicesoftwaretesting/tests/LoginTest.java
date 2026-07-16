package com.practicesoftwaretesting.tests;

import com.practicesoftwaretesting.data.DataGenerator;
import com.practicesoftwaretesting.data.InvalidLoginData;
import com.practicesoftwaretesting.pages.AccountPage;
import com.practicesoftwaretesting.pages.LoginPage;
import com.practicesoftwaretesting.base.BaseTest;
import com.practicesoftwaretesting.data.LoginDataProvider;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.practicesoftwaretesting.utils.Constants.ADMIN_ROLE;
import static com.practicesoftwaretesting.utils.Constants.USER_ROLE;
import static com.practicesoftwaretesting.utils.Constants.ADMIN_PAGE_TITLE;
import static com.practicesoftwaretesting.utils.Constants.USER_PAGE_TITLE;
import static com.practicesoftwaretesting.utils.Constants.INVALID_LOGIN_MESSAGE;

@Epic("Authentication")
@Feature("Login")
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setLoginPage() {
        loginPage = homePage.header.clickSignIn();
    }

    @Test(dataProvider = "loginData",
            dataProviderClass = LoginDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify users can login with valid credentials")
    public void loginTest(String email, String password, String role){
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        if (loginPage.isAccountLocked()){
            throw new SkipException("User account locked");
        }
        AccountPage accountPage = new AccountPage(driver);
        String pageTitle = accountPage.getPageTitle();
        if (role.equals(ADMIN_ROLE)){
            Assert.assertEquals(pageTitle, ADMIN_PAGE_TITLE);
        } else if (role.equals(USER_ROLE)) {
            Assert.assertEquals(pageTitle, USER_PAGE_TITLE);
        } else {
            Assert.fail("Unexpected role :" + role);
        }
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify error message for invalid login credentials")
    public void invalidLoginDataTest(){
        InvalidLoginData data = new DataGenerator().invalidLoginDataGenerator();
        loginPage.enterEmail(data.email);
        loginPage.enterPassword(data.password);
        loginPage.clickLoginButton();
        String error_message = loginPage.getInvalidLoginError();
        Assert.assertTrue(error_message.contains(INVALID_LOGIN_MESSAGE));
    }
}
