package com.testapi.models.booker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Booking {
    public int bookingid;
    public String firstname;
    public String lastname;
    public int totalprice;
    public boolean depositpaid;
    public BookingDates bookingdates;
    public String additionalneeds;

    public static class BookingDates {
        public String checkin;
        public String checkout;
    }
}
