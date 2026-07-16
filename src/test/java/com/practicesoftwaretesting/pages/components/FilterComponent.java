package com.practicesoftwaretesting.pages.components;

import com.practicesoftwaretesting.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Component responsible for product filtering.
 */
public class FilterComponent extends BasePage {

    private final By filteringCompleted = By.cssSelector("[data-test='filter_completed']");

    public FilterComponent(WebDriver driver){
        super(driver);
    }

    /**
     * Selects category filter dynamically using category name.
     *
     * @param category category name
     */
    @Step("Filter products by category: {category}")
    public void filterByCategory(String category){
        WebElement locator = driver.findElement(By.xpath("//label[contains(text(), '" + category + "')]"));
        click(locator);
        waitForPresence(filteringCompleted);
    }
}