package com.practicesoftwaretesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

    @FindBy (css = "[data-test='add-to-cart']")
    private WebElement addToCartButton;

    @FindBy (css = "[data-test='nav-cart']")
    private WebElement cartButton;

    @FindBy (css = "[data-test='cart-quantity']")
    private WebElement cartQuantity;

    @FindBy (css = "[data-test='unit-price']")
    private WebElement productPrice;

    @FindBy (css = "[data-test='product-name']")
    private WebElement productName;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart(){
        click(addToCartButton);
    }

    public Double getProductPrice(){
        return Double.parseDouble(getText(productPrice));
    }

    public String getProductName(){
        return getText(productName);
    }

    public Integer getCartQuantity(){
        return Integer.parseInt(getText(cartQuantity));
    }

    public CartPage goToCart(){
        click(cartButton);
        return new CartPage(driver);
    }

    @Override
    protected void verifyPage(){
        waitForVisibility(addToCartButton);
    }
}
