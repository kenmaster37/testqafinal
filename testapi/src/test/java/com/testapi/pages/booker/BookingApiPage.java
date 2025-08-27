package com.testapi.pages.booker;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class BookingApiPage {
    private static final String BASE_URL = "https://restful-booker.herokuapp.com/booking";

    public Response getAllBookings() {
        return SerenityRest.when().get(BASE_URL);
    }

    public Response getBookingById(int id) {
        return SerenityRest.when().get(BASE_URL + "/" + id);
    }

    public Response createBooking(String jsonBody) {
        return SerenityRest.given()
            .contentType("application/json")
            .body(jsonBody)
            .post(BASE_URL);
    }

    public Response updateBooking(int id, String jsonBody) {
        return SerenityRest.given()
            .contentType("application/json")
            .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
            .body(jsonBody)
            .put(BASE_URL + "/" + id);
    }

    public Response deleteBooking(int id) {
        return SerenityRest.given()
            .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
            .delete(BASE_URL + "/" + id);
    }
}
