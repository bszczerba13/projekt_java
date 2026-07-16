package com.practicesoftwaretesting.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

    @FindBy (css = "[data-test='first-name']")
    private WebElement firstName;

    @FindBy (css = "[data-test='last-name']")
    private WebElement lastName;

    @FindBy (css = "[data-test='dob']")
    private WebElement dateOfBirth;

    @FindBy (css = "[data-test='country']")
    private WebElement country;

    @FindBy (css = "[data-test='postal_code']")
    private WebElement postalCode;

    @FindBy (css = "[data-test='house_number']")
    private WebElement houseNumber;

    @FindBy (css = "[data-test='street']")
    private WebElement street;

    @FindBy (css = "[data-test='city']")
    private WebElement city;

    @FindBy (css = "[data-test='state']")
    private WebElement state;

    @FindBy (css = "[data-test='phone']")
    private WebElement phone;

    @FindBy (css = "[data-test='email']")
    private WebElement email;

    @FindBy (css = "[data-test='password']")
    private WebElement password;

    @FindBy (css = "[data-test='register-submit']")
    private WebElement registerButton;

    @FindBy (css = "[data-test='postcode-lookup-loading']")
    private WebElement autofillLoader;

    @FindBy (css = "[data-test='email-error']")
    private WebElement missingEmailMessage;

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @Step("Enter first name: {userFirstName}")
    public void enterFirstName(String userFirstName){
        enterText(firstName, userFirstName);
    }

    @Step("Enter last name: {userLastName}")
    public void enterLastName(String userLastName){
        enterText(lastName, userLastName);
    }

    @Step("Enter date of birth: {userDateOfBirth}")
    public void enterDateOfBirth(String userDateOfBirth){
        enterText(dateOfBirth, userDateOfBirth);
    }

    @Step("Select country")
    public void selectCountry(){
        selectRandomOption(country);
    }

    @Step("Enter postal code: {userPostalCode}")
    public void enterPostalCode(String userPostalCode){
        enterText(postalCode, userPostalCode);
    }

    @Step("Enter house number: {userHouseNumber}")
    public void enterHouseNumber(String userHouseNumber){
        enterText(houseNumber, userHouseNumber);
    }

    @Step("Enter street: {userStreet}")
    public void enterStreet(String userStreet){
        enterText(street, userStreet);
    }

    public String getStreetValue(){
        return getValue(street);
    }

    @Step("Enter city: {userCity}")
    public void enterCity(String userCity){
        enterText(city, userCity);
    }

    public String getCityValue(){
        return getValue(city);
    }

    @Step("Enter state: {userState}")
    public void enterState(String userState){
        enterText(state, userState);
    }

    public String getStateValue(){
        return getValue(state);
    }

    @Step("Enter phone: {userPhone}")
    public void enterPhone(String userPhone){
        enterText(phone, userPhone);
    }

    @Step("Enter email: {userEmail}")
    public void enterEmail(String userEmail){
        enterText(email, userEmail);
    }

    @Step("Enter password: {userPassword}")
    public void enterPassword(String userPassword){
        enterText(password, userPassword);
    }

    @Step("Click register button")
    public void clickRegisterButton() {
        click(registerButton);
    }

    public void waitForAutofillLoader(){
        waitForInvisibility(autofillLoader);
    }

    public String getMissingEmailMessage(){
        return getText(missingEmailMessage);
    }

    @Override
    protected void verifyPage(){
        waitForVisibility(firstName);
    }
}