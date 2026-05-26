package com.practicesoftwaretesting.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage{

    @FindBy (css = "a[href='#guest-tab']")
    private WebElement guestTab;

    @FindBy (id = "guest-email")
    private WebElement emailInput;

    @FindBy (id = "guest-first-name")
    private WebElement firstNameInput;

    @FindBy (id = "guest-last-name")
    private WebElement lastNameInput;

    @FindBy (css = "[data-test='guest-submit']")
    private WebElement continueAsGuestButton;

    @FindBy (xpath = "//p[contains(text(), 'Continuing as guest')]")
    private WebElement guestDataSummary;

    @FindBy (css = "[data-test='proceed-2-guest']")
    private WebElement proceedToCheckoutButtonSignInStep;

    @FindBy (css = "[data-test='country']")
    private WebElement countrySelect;

    @FindBy (css = "[data-test='street']")
    private WebElement orderStreet;

    @FindBy (css = "[data-test='city']")
    private WebElement orderCity;

    @FindBy (css = "[data-test='state']")
    private WebElement orderState;

    @FindBy (css = "[data-test='postal_code']")
    private WebElement orderPostalCode;

    @FindBy (css = "[data-test='house_number']")
    private WebElement orderHouseNumber;

    @FindBy (css = "[data-test='postcode-lookup-loading']")
    private WebElement autofillLoader;

    @FindBy (css = "[data-test='proceed-3']")
    private WebElement proceedToCheckoutButtonBillingAddressStep;

    @FindBy (css = "[data-test='payment-method']")
    private WebElement paymentMethodSelect;

    @FindBy (css = "[data-test='credit_card_number']")
    private WebElement creditCardNumber;

    @FindBy (css = "[data-test='expiration_date']")
    private WebElement cardExpirationDate;

    @FindBy (css = "[data-test='cvv']")
    private WebElement cardCvvNumber;

    @FindBy (css = "[data-test='card_holder_name']")
    private WebElement cardHolderName;

    @FindBy (css = "[data-test='finish']")
    private WebElement confirmButton;

    @FindBy (css = "[data-test='payment-success-message']")
    private WebElement paymentSuccessMessage;

    @FindBy (id = "order-confirmation")
    private WebElement orderConfirmationMessage;

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void goToGuestTab(){
        click(guestTab);
    }

    public void enterGuestEmail(String email){
        enterText(emailInput, email);
    }

    public void enterGuestFirstName(String firstName){
        enterText(firstNameInput, firstName);
    }

    public void enterGuestLastName(String lastName){
        enterText(lastNameInput, lastName);
    }

    public void clickContinueAsGuestButton(){
        click(continueAsGuestButton);
    }

    public String getGuestDataSummary(){
        return getText(guestDataSummary);
    }

    public void goToBillingAddressStep(){
        click(proceedToCheckoutButtonSignInStep);
    }

    public void selectGuestCountry(){
        selectRandomOption(countrySelect);
    }

    public void enterPostalCode(String postalCode){
        enterText(orderPostalCode, postalCode);
    }

    public void enterHouseNumber(String houseNumber){
        enterText(orderHouseNumber, houseNumber);
    }

    public void goToPaymentStep(){
        click(proceedToCheckoutButtonBillingAddressStep);
    }

    public void choosePaymentMethod(String paymentMethod){
        selectByValue(paymentMethodSelect, paymentMethod);
    }

    public void enterCardNumber(String cardNumber){
        enterText(creditCardNumber, cardNumber);
    }

    public void enterCardExpirationDate(String expirationDate){
        enterText(cardExpirationDate, expirationDate);
    }

    public void enterCardCvv(String cardCvv){
        enterText(cardCvvNumber, cardCvv);
    }

    public void enterCardHolderName(String holderName){
        enterText(cardHolderName, holderName);
    }

    public void clickConfirmButton(){
        click(confirmButton);
    }

    public String getPaymentSuccessMessage(){
        return getText(paymentSuccessMessage);
    }

    public String getOrderConfirmationMessage(){
        return getText(orderConfirmationMessage);
    }

    public String getOrderStreet(){
        return getText(orderStreet);
    }

    public String getOrderCity(){
        return getText(orderCity);
    }

    public String getOrderState(){
        return getText(orderState);
    }

    public void waitForAutofill(){
        waitForInvisibility(autofillLoader);
    }

    public void enterStreet(String street){
        enterText(orderStreet, street);
    }

    public void enterCity(String city){
        enterText(orderCity, city);
    }

    public void enterState(String state){
        enterText(orderState, state);
    }

    @Override
    protected void verifyPage() {
        waitForVisibility(guestTab);
    }
}