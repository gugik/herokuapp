package org.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDates {
    String checkin;
    String checkout;

    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
}
