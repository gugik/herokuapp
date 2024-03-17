package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SearchResultsPage extends MainPage {

    @FindBy(className = "logo")
    private WebElement mainElement;
    @FindBy(css = ".qrShPb.pXs6bb.PZPZlf.q8U8x.aTI8gc")
    private WebElement businessInfoTitle;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        Assert.assertTrue(new Wait(driver).isElementPresent(mainElement, 5),
                "SearchResultsPage not loaded");
    }

    public String getBusinessInformation() {
        return businessInfoTitle.getText();
    }
}
