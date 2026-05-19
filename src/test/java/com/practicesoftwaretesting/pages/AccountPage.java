package com.practicesoftwaretesting.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{
    @FindBy(css = "[data-test='page-title']")
    private WebElement pageTitle;

    public AccountPage(WebDriver driver){
        super(driver);
    }

    public String getPageTitle(){
        return getText(pageTitle);
    }

    @Override
    protected void verifyPage(){
        waitForVisibility(pageTitle);
    }
}
