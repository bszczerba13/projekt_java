package com.practicesoftwaretesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage{
    @FindBy (css = "[data-test='nav-sign-in']")
    private WebElement signInLink;

    @FindBy (css = "[data-test='product-price']")
    private List<WebElement> productPrice;

    @FindBy (css = "[data-test='sort']")
    private WebElement sortList;

    @FindBy (css = "[data-test='product-name']")
    private List<WebElement> productTitle;

    @FindBy (css = "a[data-test^='product-']")
    private List<WebElement> productCards;

    private By outOfStockProduct = By.cssSelector("[data-test='out-of-stock']");

    private By sortingCompleted = By.cssSelector("[data-test='sorting_completed']");

    private By filteringCompleted = By.cssSelector("[data-test='filter_completed']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickSignIn(){
        click(signInLink);
        return new LoginPage(driver);
    }

    public List<Double> getProductPrices(){
        List<Double> prices = new ArrayList<>();
        List<String> texts = getTexts(productPrice);
        for (String text : texts) {
            String priceValue = text.replace("$", "").trim();
            prices.add(Double.parseDouble(priceValue));
        }
        return prices;
    }

    public void sortBy(String option){
        selectByValue(sortList, option);
        waitForPresence(sortingCompleted);
    }

    public void sortPriceLowToHigh(){
        sortBy("price,asc");
    }

    public void sortPriceHighToLow(){
        sortBy("price,desc");
    }

    public List<String> getProductTitles(){
        List<String> elements = getTexts(productTitle);
        return elements.stream().map(String::toLowerCase).toList();
    }

    public void filterByCategory(String category){
        WebElement locator = driver.findElement(By.xpath("//label[contains(text(), '" + category + "')]"));
        click(locator);
        waitForPresence(filteringCompleted);
    }

    public void openFirstAvailableProduct() {
        waitForVisibility(productCards);
        for (WebElement product : productCards) {
            List<WebElement> outOfStock = product.findElements(outOfStockProduct);
            if (outOfStock.isEmpty()) {
                click(product);
                //return ProductPage
                return;
            }
        }
        throw new RuntimeException("No available products found");
    }

    @Override
    protected void verifyPage(){
        waitForVisibility(sortList);
        waitForVisibility(productCards);
    }

}