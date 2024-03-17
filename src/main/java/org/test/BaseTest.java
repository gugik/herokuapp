package org.test;

import io.restassured.RestAssured;
import org.api.BookingBody;
import org.api.BookingDates;
import org.api.HerokuApi;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.service.HerokuService;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    protected static final String BUSINESS_TITLE = "VFX Financial PLC";
    protected static final String DATE_FORMAT = "yyyy-MM-dd";
    protected HerokuService herokuService;
    protected SoftAssert softAssert;
    protected static final String mainUrl = "https://www.google.com/";
    protected static WebDriver driver;

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
        this.herokuService = new HerokuService(new HerokuApi());
        this.softAssert = new SoftAssert();

        //setup for UI part
        System.setProperty("webdriver.chrome.driver", "C://chromedriver-win64//chromedriver-win64//chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(mainUrl);
    }

    @AfterTest
    public static void closeDriver() {
        driver.quit();
    }

    public void verifyBookDetailsBody(BookingBody actualBookingBody, BookingBody expectedBookingBody) {
        softAssert.assertEquals(actualBookingBody.getFirstName(), expectedBookingBody.getFirstName(), "Firstname");
        softAssert.assertEquals(actualBookingBody.getLastName(), expectedBookingBody.getLastName(), "Lastname");
        softAssert.assertEquals(actualBookingBody.getTotalPrice(), expectedBookingBody.getTotalPrice(), "Totalprice");
        softAssert.assertEquals(actualBookingBody.getDepositPaid(), expectedBookingBody.getDepositPaid(), "Depositpaid");
        softAssert.assertEquals(actualBookingBody.getAdditionalNeeds(), expectedBookingBody.getAdditionalNeeds(), "Additionalneeds");
        BookingDates actualBookingDates = actualBookingBody.getBookingDates();
        BookingDates expectedBookingDates = expectedBookingBody.getBookingDates();
        softAssert.assertEquals(actualBookingDates.getCheckin(), expectedBookingDates.getCheckin(), "Checkin");
        softAssert.assertEquals(actualBookingDates.getCheckout(), expectedBookingDates.getCheckout(), "Checkout");
    }
}
