package com.practicesoftwaretesting.tests;

import com.practicesoftwaretesting.base.BaseTest;
import com.practicesoftwaretesting.pages.CartPage;
import com.practicesoftwaretesting.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setProductPage(){
        productPage = homePage.openFirstAvailableProduct();
    }

    @Test
    public void addProductToCartTest(){
        productPage.addProductToCart();
        Integer quantity = productPage.getCartQuantity();
        String productName = productPage.getProductName();
        Double productPrice = productPage.getProductPrice();
        Assert.assertEquals(quantity, 1);
        cartPage = productPage.goToCart();
        String cartProductName = cartPage.getCartProductName();
        Double cartTotalPrice = cartPage.getCartTotalPrice();
        Assert.assertEquals(cartProductName, productName);
        Assert.assertEquals(productPrice * quantity, cartTotalPrice);
    }

    @Test
    public void removeProductFromCartTest(){
        productPage.addProductToCart();
        cartPage = productPage.goToCart();
        cartPage.removeProduct();
        Assert.assertTrue(cartPage.getEmptyCartInfo().contains("The cart is empty"));
    }
}
