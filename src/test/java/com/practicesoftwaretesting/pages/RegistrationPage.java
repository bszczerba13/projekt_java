package com.practicesoftwaretesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

    @FindBy (css = "[data-test='first-name']")
    protected WebElement firstName;

    @FindBy (css = "[data-test='last-name']")
    protected WebElement lastName;

    @FindBy (css = "[data-test='dob']")
    protected WebElement dateOfBirth;

    @FindBy (css = "[data-test='country']")
    protected WebElement country;

    @FindBy (css = "[data-test='postal_code']")
    protected WebElement postalCode;

    @FindBy (css = "[data-test='house_number']")
    protected WebElement houseNumber;

    @FindBy (css = "[data-test='street']")
    protected WebElement street;

    @FindBy (css = "[data-test='city']")
    protected WebElement city;

    @FindBy (css = "[data-test='state']")
    protected WebElement state;

    @FindBy (css = "[data-test='phone']")
    protected WebElement phone;

    @FindBy (css = "[data-test='email']")
    protected WebElement email;

    @FindBy (css = "[data-test='password']")
    protected WebElement password;

    @FindBy (css = "[data-test='register-submit']")
    protected WebElement registerButton;

    @FindBy (css = "[data-test='postcode-lookup-loading']")
    protected WebElement autofillLoader;

    @FindBy (css = "[data-test='email-error']")
    protected WebElement missingEmailMessage;

    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    public void enterFirstName(String userFirstName){
        enterText(firstName, userFirstName);
    }

    public void enterLastName(String userLastName){
        enterText(lastName, userLastName);
    }

    public void enterDateOfBirth(String userDateOfBirth){
        enterText(dateOfBirth, userDateOfBirth);
    }

    public void selectCountry(){
        selectRandomOption(country);
    }

    public void enterPostalCode(String userPostalCode){
        enterText(postalCode, userPostalCode);
    }

    public void enterHouseNumber(String userHouseNumber){
        enterText(houseNumber, userHouseNumber);
    }

    public void enterStreet(String userStreet){
        enterText(street, userStreet);
    }

    public String getStreetValue(){
        return getValue(street);
    }

    public void enterCity(String userCity){
        enterText(city, userCity);
    }

    public String getCityValue(){
        return getValue(city);
    }

    public void enterState(String userState){
        enterText(state, userState);
    }

    public String getStateValue(){
        return getValue(state);
    }

    public void enterPhone(String userPhone){
        enterText(phone, userPhone);
    }

    public void enterEmail(String userEmail){
        enterText(email, userEmail);
    }

    public void enterPassword(String userPassword){
        enterText(password, userPassword);
    }

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