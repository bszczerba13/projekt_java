package com.practicesoftwaretesting.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "email")
    private WebElement loginEmail;

    @FindBy(id = "password")
    private WebElement loginPassword;

    @FindBy(css = "[data-test='login-submit']")
    private WebElement loginButton;

    @FindBy(css = "[data-test='register-link']")
    private WebElement registerAccountLink;

    @FindBy(xpath = "//h3[text()='Login']")
    private WebElement loginPageTitle;

    @FindBy(css = "[data-test='login-error']")
    private WebElement invalidLoginDataMessage;

    @FindBy(xpath = "//div[contains(text(),'Account locked')]")
    private WebElement lockedAccountMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter email: {email}")
    public void enterEmail(String email){
        enterText(loginEmail, email);
    }

    @Step("Enter password")
    public void enterPassword(String password){
        enterText(loginPassword, password);
    }

    @Step("Click login button")
    public void clickLoginButton(){
        click(loginButton);
    }

    @Step("Go to registration page")
    public RegistrationPage clickRegisterLink(){
        click(registerAccountLink);
        return new RegistrationPage(driver);
    }

    public boolean isPageTitleVisible(){
        return isDisplayed(loginPageTitle);
    }

    public String getInvalidLoginError(){
        return getText(invalidLoginDataMessage);
    }

    public boolean isAccountLocked(){
        try{
            quickWaitForVisibility(lockedAccountMessage);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Override
    protected void verifyPage(){
        waitForVisibility(loginEmail);
    }
}