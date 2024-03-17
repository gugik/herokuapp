package org.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.api.Booking;
import org.api.BookingBody;
import org.api.BookingDates;
import org.pages.MainPage;
import org.pages.SearchResultsPage;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Test extends BaseTest {

    @org.testng.annotations.Test
    public void apiTest() {

        //Part 1
        //Create a booking
        BookingBody bookingBody = new BookingBody(RandomStringUtils.randomAlphabetic(8),
                RandomStringUtils.randomAlphabetic(8),
                RandomUtils.nextInt(0, 300), Boolean.FALSE,
                new BookingDates(OffsetDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)),
                        OffsetDateTime.now().plusDays(2).format(DateTimeFormatter.ofPattern(DATE_FORMAT))),
                RandomStringUtils.randomAlphabetic(24));
        Booking myBooking = herokuService.createBooking(bookingBody);
        softAssert.assertNotNull(myBooking.getBookingId(), "BookingId should be present");
        verifyBookDetailsBody(myBooking.getBooking(), bookingBody);

        //Retrieve customer booking by id and verify
        BookingBody myBookingById = herokuService.getBookingById(myBooking.getBookingId());
        verifyBookDetailsBody(myBookingById, bookingBody);

        //Part 2
        //Go to www.google.com and search for ‘ VFX Financial’
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnCookiesButton();
        SearchResultsPage searchResultsPage = mainPage.doSearch("VFX Financial");

        //Check that on the search results page , on the right of the page where
        //the business information is , the title of the business is ‘ VFX Financial PLC’
        softAssert.assertTrue(searchResultsPage.getBusinessInformation().equalsIgnoreCase(BUSINESS_TITLE),
                "Business title");
        softAssert.assertAll();
    }
}
