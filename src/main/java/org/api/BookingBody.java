package org.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingBody {
    String firstName;
    String lastName;
    Integer totalPrice;
    Boolean depositPaid;
    BookingDates bookingDates;
    String additionalNeeds;

    public BookingBody(String firstName, String lastName,
                       Integer totalPrice, Boolean depositPaid, BookingDates bookingDates, String additionalNeeds) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.totalPrice = totalPrice;
        this.depositPaid = depositPaid;
        this.bookingDates = bookingDates;
        this.additionalNeeds = additionalNeeds;
    }
}
