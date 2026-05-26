package com.practicesoftwaretesting.tests;

import com.practicesoftwaretesting.base.BaseTest;
import com.practicesoftwaretesting.data.DataGenerator;
import com.practicesoftwaretesting.data.OrderData;
import com.practicesoftwaretesting.pages.CartPage;
import com.practicesoftwaretesting.pages.CheckoutPage;
import com.practicesoftwaretesting.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.practicesoftwaretesting.utils.Constants.CREDIT_CARD;
import static com.practicesoftwaretesting.utils.Constants.PAYMENT_SUCCESS_MESSAGE;
import static com.practicesoftwaretesting.utils.Constants.ORDER_CONFIRMATION_MESSAGE;

public class CheckoutTest extends BaseTest {

    private CheckoutPage checkoutPage;
    private OrderData data;

    @BeforeMethod
    public void setCheckoutPage(){
        ProductPage productPage = homePage.openFirstAvailableProduct();
        productPage.addProductToCart();
        CartPage cartPage = productPage.goToCart();
        checkoutPage = cartPage.goToCheckout();
        data = new DataGenerator().orderData();
    }

    @Test
    public void checkoutTest(){
        checkoutPage.goToGuestTab();
        checkoutPage.enterGuestEmail(data.email);
        checkoutPage.enterGuestFirstName(data.firstName);
        checkoutPage.enterGuestLastName(data.lastName);
        checkoutPage.clickContinueAsGuestButton();
        String guestDataSummary = checkoutPage.getGuestDataSummary();
        Assert.assertTrue(guestDataSummary.contains(data.email));
        Assert.assertTrue(guestDataSummary.contains(data.firstName));
        Assert.assertTrue(guestDataSummary.contains(data.lastName));
        checkoutPage.goToBillingAddressStep();
        checkoutPage.selectGuestCountry();
        checkoutPage.enterPostalCode(data.postalCode);
        checkoutPage.enterHouseNumber(data.houseNumber);
        checkoutPage.waitForAutofill();
        if (checkoutPage.getOrderStreet().isEmpty()){
            checkoutPage.enterStreet(data.street);
        }
        if (checkoutPage.getOrderCity().isEmpty()){
            checkoutPage.enterCity(data.city);
        }
        if (checkoutPage.getOrderState().isEmpty()){
            checkoutPage.enterState(data.state);
        }
        checkoutPage.goToPaymentStep();
        checkoutPage.choosePaymentMethod(CREDIT_CARD);
        checkoutPage.enterCardNumber(data.creditCardNumber);
        checkoutPage.enterCardExpirationDate(data.expirationDate);
        checkoutPage.enterCardCvv(data.cvv);
        checkoutPage.enterCardHolderName(data.firstName + " " + data.lastName);
        checkoutPage.clickConfirmButton();
        String paymentSuccessMessage = checkoutPage.getPaymentSuccessMessage().toLowerCase();
        Assert.assertTrue(paymentSuccessMessage.contains(PAYMENT_SUCCESS_MESSAGE));
        checkoutPage.clickConfirmButton();
        String orderConfirmationMessage = checkoutPage.getOrderConfirmationMessage().toLowerCase();
        Assert.assertTrue(orderConfirmationMessage.contains(ORDER_CONFIRMATION_MESSAGE));
    }
}
