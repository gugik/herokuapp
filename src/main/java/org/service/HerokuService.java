package org.service;

import org.api.Booking;
import org.api.BookingBody;
import org.api.HerokuApi;

public class HerokuService {
    private HerokuApi herokuApi;

    public HerokuService(HerokuApi herokuApi) {
        this.herokuApi = herokuApi;
    }

    /**
     * Create booking
     *
     * @param bookingBody - booking details
     * @return Booking
     */

    public Booking createBooking(BookingBody bookingBody) {
        return herokuApi.createBooking(bookingBody);
    }

    /**
     * Get booking by Id
     *
     * @param bookingid - booking id
     * @return BookingBody
     */
    public BookingBody getBookingById(int bookingid) {
        return herokuApi.getBookingById(bookingid);
    }

}
