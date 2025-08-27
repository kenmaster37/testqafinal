package com.testapi.steps.booker;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import com.testapi.pages.booker.BookingApiPage;
import com.testapi.models.booker.Booking;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingSteps {
    private Response response;
    private Booking booking;
    private int bookingId;
    private String token = "abc123"; // Token de ejemplo, para pruebas reales obtenerlo con login

    @Given("el usuario consulta la lista de reservas")
    public void consultaListaReservas() {
        response = new BookingApiPage().getAllBookings();
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @When("crea una nueva reserva")
    public void creaNuevaReserva() {
        String jsonBody = "{\"firstname\":\"John\",\"lastname\":\"Doe\",\"totalprice\":123,\"depositpaid\":true,\"bookingdates\":{\"checkin\":\"2025-08-26\",\"checkout\":\"2025-08-27\"},\"additionalneeds\":\"Breakfast\"}";
        response = new BookingApiPage().createBooking(jsonBody);
        assertThat(response.getStatusCode()).isEqualTo(200);
        bookingId = response.jsonPath().getInt("bookingid");
    }

    @When("actualiza la reserva")
    public void actualizaReserva() {
        String jsonBody = "{\"firstname\":\"Jane\",\"lastname\":\"Smith\",\"totalprice\":150,\"depositpaid\":false,\"bookingdates\":{\"checkin\":\"2025-08-28\",\"checkout\":\"2025-08-29\"},\"additionalneeds\":\"Dinner\"}";
        response = new BookingApiPage().updateBooking(bookingId, jsonBody);
        assertThat(response.getStatusCode()).isEqualTo(200);
    }

    @When("elimina la reserva")
    public void eliminaReserva() {
        response = new BookingApiPage().deleteBooking(bookingId);
        assertThat(response.getStatusCode()).isIn(200, 201, 204);
    }

    @Then("la operación de reserva es exitosa")
    public void operacionReservaExitosa() {
        assertThat(response.getStatusCode()).isIn(200, 201, 204);
    }
}
