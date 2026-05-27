package com.practicesoftwaretesting.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import static com.practicesoftwaretesting.utils.Constants.DEFAULT_TIMEOUT;
import static com.practicesoftwaretesting.utils.Constants.QUICK_TIMEOUT;

/**
 * Base class for all page objects.
 * Contains shared Selenium helper methods and explicit waits.
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebDriverWait quickWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        this.quickWait = new WebDriverWait(driver, Duration.ofSeconds(QUICK_TIMEOUT));
        PageFactory.initElements(driver, this);
        verifyPage();
    }

    /**
     * Verifies that page is loaded correctly.
     * Override in child classes and provide page specific validation.
     */
    protected void verifyPage(){

    }

    /**
     * Waits until element becomes visible.
     *
     * @param element target element
     * @return visible WebElement
     */
    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void quickWaitForVisibility(WebElement element) {
        quickWait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForVisibility(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until element exists in DOM.
     * Useful for elements that may not be visible.
     *
     * @param locator element locator
     */
    protected void waitForPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitForInvisibility(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void click(WebElement element) {
        waitForClickable(element).click();
    }

    protected void enterText(WebElement element, String text) {
        WebElement el = waitForVisibility(element);
        el.clear();
        el.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return waitForVisibility(element).getText();
    }

    protected List<String> getTexts(List<WebElement> elements) {
        waitForVisibility(elements);
        return elements.stream()
                .map(WebElement::getText)
                .toList();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return waitForVisibility(element).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected void selectByValue(WebElement element, String value) {
        Select select = new Select(waitForVisibility(element));
        select.selectByValue(value);
    }

    /**
     * Selects random option from dropdown list.
     *
     * @param element select element
     */
    protected void selectRandomOption(WebElement element) {
        Select select = new Select(waitForVisibility(element));
        Random random = new Random();
        int index = random.nextInt(20) + 1;
        select.selectByIndex(index);
    }

    /**
     * Returns value attribute from input element.
     *
     * @param element input element
     * @return input value
     */
    protected String getValue(WebElement element){
        return waitForVisibility(element).getAttribute("value");
    }
}