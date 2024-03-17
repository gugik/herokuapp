package org.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MainPage {

    protected WebDriver driver;
    @FindBy(id = "gbwa")
    private WebElement mainElement;

    @FindBy(className = "gLFyf")
    private WebElement textField;
    @FindBy(id = "L2AGLb")
    private WebElement cookiesButton;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        Assert.assertTrue(new Wait(driver).isElementPresent(mainElement, 5),
                "MainPage not loaded");
    }

    public SearchResultsPage doSearch(String searchText) {
        textField.click();
        textField.clear();
        textField.sendKeys(searchText);
        textField.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    public void clickOnCookiesButton() {
        cookiesButton.click();
    }
}