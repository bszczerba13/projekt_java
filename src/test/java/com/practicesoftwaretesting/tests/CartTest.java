package com.practicesoftwaretesting.tests;

import com.practicesoftwaretesting.base.BaseTest;
import com.practicesoftwaretesting.pages.CartPage;
import com.practicesoftwaretesting.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.practicesoftwaretesting.utils.Constants.EMPTY_CART_MESSAGE;

public class CartTest extends BaseTest {

    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void setProductPage(){
        productPage = homePage.openFirstAvailableProduct();
    }

    @Test(description = "Verify user can add product to cart")
    public void addProductToCartTest(){
        productPage.addProductToCart();
        Integer quantity = productPage.header.getCartQuantity();
        String productName = productPage.getProductName();
        Double productPrice = productPage.getProductPrice();
        Assert.assertEquals(quantity, 1);
        cartPage = productPage.header.goToCart();
        String cartProductName = cartPage.getCartProductName();
        Double cartTotalPrice = cartPage.getCartTotalPrice();
        Assert.assertEquals(cartProductName, productName);
        Assert.assertEquals(productPrice * quantity, cartTotalPrice);
    }

    @Test(description = "Verify user can remove product from cart")
    public void removeProductFromCartTest(){
        productPage.addProductToCart();
        cartPage = productPage.header.goToCart();
        cartPage.removeProduct();
        Assert.assertTrue(cartPage.getEmptyCartInfo().contains(EMPTY_CART_MESSAGE));
    }
}
