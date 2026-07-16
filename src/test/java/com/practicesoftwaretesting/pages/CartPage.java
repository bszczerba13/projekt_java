package com.practicesoftwaretesting.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage{

    @FindBy (css = "[data-test='product-title']")
    private WebElement cartProductName;

    @FindBy (css = "[data-test='cart-total']")
    private WebElement cartTotalPrice;

    @FindBy (css = "a.btn-danger")
    private WebElement removeProductButton;

    @FindBy (css = "p.ng-star-inserted")
    private WebElement emptyCartInfo;

    @FindBy (css = "[data-test='proceed-1']")
    private WebElement proceedToCheckoutButtonCartStep;

    public CartPage(WebDriver driver){
        super(driver);
    }

    public String getCartProductName(){
        return getText(cartProductName).trim();
    }

    public Double getCartTotalPrice(){
        return Double.parseDouble(getText(cartTotalPrice).replace("$", "").trim());
    }

    @Step("Remove product from shopping cart")
    public void removeProduct(){
        click(removeProductButton);
    }

    public String getEmptyCartInfo(){
        return getText(emptyCartInfo);
    }

    @Step("Proceed to checkout")
    public CheckoutPage goToCheckout(){
        click(proceedToCheckoutButtonCartStep);
        return new CheckoutPage(driver);
    }

    @Override
    protected void verifyPage(){
        waitForVisibility(cartProductName);
    }
}
