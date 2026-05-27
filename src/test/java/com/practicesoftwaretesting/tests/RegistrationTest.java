package com.practicesoftwaretesting.tests;

import com.practicesoftwaretesting.base.BaseTest;
import com.practicesoftwaretesting.data.DataGenerator;
import com.practicesoftwaretesting.data.RegistrationData;
import com.practicesoftwaretesting.pages.LoginPage;
import com.practicesoftwaretesting.pages.RegistrationPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.practicesoftwaretesting.utils.Constants.MISSING_EMAIL_MESSAGE;

public class RegistrationTest extends BaseTest {
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private RegistrationData data;

    @BeforeMethod
    public void setRegistrationPage(){
        loginPage = homePage.header.clickSignIn();
        registrationPage = loginPage.clickRegisterLink();
        data = new DataGenerator().registrationData();
    }

    @Test(description = "Verify user can register new account")
    public void registrationTest(){
        registrationPage.enterFirstName(data.firstName);
        registrationPage.enterLastName(data.lastName);
        registrationPage.enterDateOfBirth(data.dateOfBirth);
        registrationPage.selectCountry();
        registrationPage.enterPostalCode(data.postalCode);
        registrationPage.enterHouseNumber(data.houseNumber);
        registrationPage.waitForAutofillLoader();
        registrationPage.enterPhone(data.phoneNumber);
        registrationPage.enterEmail(data.email);
        registrationPage.enterPassword(data.password);
        if (registrationPage.getStreetValue().isEmpty()){
            registrationPage.enterStreet(data.street);
        }
        if (registrationPage.getCityValue().isEmpty()){
            registrationPage.enterCity(data.city);
        }
        if (registrationPage.getStateValue().isEmpty()){
            registrationPage.enterState(data.state);
        }
        registrationPage.clickRegisterButton();
        Assert.assertTrue(loginPage.isPageTitleVisible());
    }

    @Test(description = "Verify validation message when email is missing")
    public void registrationTestMissingEmail(){
        registrationPage.enterFirstName(data.firstName);
        registrationPage.enterLastName(data.lastName);
        registrationPage.enterDateOfBirth(data.dateOfBirth);
        registrationPage.selectCountry();
        registrationPage.enterPostalCode(data.postalCode);
        registrationPage.enterHouseNumber(data.houseNumber);
        registrationPage.waitForAutofillLoader();
        registrationPage.enterPhone(data.phoneNumber);
        registrationPage.enterPassword(data.password);
        if (registrationPage.getStreetValue().isEmpty()){
            registrationPage.enterStreet(data.street);
        }
        if (registrationPage.getCityValue().isEmpty()){
            registrationPage.enterCity(data.city);
        }
        if (registrationPage.getStateValue().isEmpty()){
            registrationPage.enterState(data.state);
        }
        registrationPage.clickRegisterButton();
        Assert.assertEquals(registrationPage.getMissingEmailMessage(), MISSING_EMAIL_MESSAGE);
    }
}
