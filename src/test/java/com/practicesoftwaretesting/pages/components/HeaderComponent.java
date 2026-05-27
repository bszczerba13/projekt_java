package com.practicesoftwaretesting.pages.components;

import com.practicesoftwaretesting.pages.BasePage;
import com.practicesoftwaretesting.pages.CartPage;
import com.practicesoftwaretesting.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderComponent extends BasePage {

    @FindBy(css = "[data-test='nav-sign-in']")
    private WebElement signInLink;

    @FindBy (css = "[data-test='nav-cart']")
    private WebElement cartButton;

    @FindBy (css = "[data-test='cart-quantity']")
    private WebElement cartQuantity;

    public LoginPage clickSignIn(){
        click(signInLink);
        return new LoginPage(driver);
    }

    public Integer getCartQuantity(){
        return Integer.parseInt(getText(cartQuantity));
    }

    public CartPage goToCart() {
        click(cartButton);
        return new CartPage(driver);
    }

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }
}
