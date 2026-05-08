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

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        verifyPage();
    }

    protected void verifyPage(){
        //override in child classes
    }

    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected List<WebElement> waitForVisibility(List<WebElement> elements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    protected WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitForPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected boolean waitForInvisibility(WebElement element) {
        return wait.until(ExpectedConditions.invisibilityOf(element));
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

    protected void selectRandomOption(WebElement element) {
        Select select = new Select(waitForVisibility(element));
        Random random = new Random();
        int index = random.nextInt(20) + 1;
        select.selectByIndex(index);
    }

    protected String getValue(WebElement element){
        return waitForVisibility(element).getAttribute("value");
    }
}