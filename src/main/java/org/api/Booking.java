package org.api;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Booking {
    Integer bookingId;
    BookingBody booking;

    public Booking(Integer bookingId, BookingBody booking) {
        this.bookingId = bookingId;
        this.booking = booking;
    }
}
