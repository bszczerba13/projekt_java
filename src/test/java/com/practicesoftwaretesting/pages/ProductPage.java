package com.practicesoftwaretesting.pages;

import com.practicesoftwaretesting.pages.components.HeaderComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

    @FindBy (css = "[data-test='add-to-cart']")
    private WebElement addToCartButton;

    @FindBy (css = "[data-test='unit-price']")
    private WebElement productPrice;

    @FindBy (css = "[data-test='product-name']")
    private WebElement productName;

    public HeaderComponent header;

    public ProductPage(WebDriver driver) {
        super(driver);
        header = new HeaderComponent(driver);
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

    @Override
    protected void verifyPage(){
        waitForVisibility(addToCartButton);
    }
}
