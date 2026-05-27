package com.practicesoftwaretesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static com.practicesoftwaretesting.utils.Constants.SORT_PRICE_ASC;
import static com.practicesoftwaretesting.utils.Constants.SORT_PRICE_DESC;
import com.practicesoftwaretesting.pages.components.FilterComponent;
import com.practicesoftwaretesting.pages.components.HeaderComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Home page containing products, sorting and filtering actions.
 */
public class HomePage extends BasePage{

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

    public FilterComponent filter;
    public HeaderComponent header;

    public HomePage(WebDriver driver) {
        super(driver);
        filter = new FilterComponent(driver);
        header = new HeaderComponent(driver);
    }

    /**
     * Returns list of product prices converted to Double values.
     *
     * @return list of prices
     */
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
        sortBy(SORT_PRICE_ASC);
    }

    public void sortPriceHighToLow(){
        sortBy(SORT_PRICE_DESC);
    }

    public List<String> getProductTitles(){
        List<String> elements = getTexts(productTitle);
        return elements.stream().map(String::toLowerCase).toList();
    }

    /**
     * Opens first product that is not marked as out of stock.
     *
     * @return ProductPage
     * @throws RuntimeException when no products are available
     */
    public ProductPage openFirstAvailableProduct() {
        waitForVisibility(productCards);
        for (WebElement product : productCards) {
            List<WebElement> outOfStock = product.findElements(outOfStockProduct);
            if (outOfStock.isEmpty()) {
                click(product);
                return new ProductPage(driver);
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