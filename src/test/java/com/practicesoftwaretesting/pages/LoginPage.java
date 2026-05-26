package com.practicesoftwaretesting.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

    public void enterEmail(String email){
        enterText(loginEmail, email);
    }

    public void enterPassword(String password){
        enterText(loginPassword, password);
    }

    public void clickLoginButton(){
        click(loginButton);
    }

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