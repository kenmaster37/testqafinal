package com.dxhotels.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;

import com.dxhotels.pages.HotelSearchPage;
import com.dxhotels.pages.HotelReservationPage;
import com.dxhotels.pages.LoginPage;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class HotelReservationStepDefinitions {
    LoginPage loginPage = new LoginPage();
    HotelSearchPage searchPage = new HotelSearchPage();
    HotelReservationPage reservationPage = new HotelReservationPage();
    String mensajeExito;

    @Given("the user is authenticated and on the hotel search page")
    public void user_authenticated_on_search_page() {
        loginPage.openUrl("https://demos.devexpress.com/rwa/dxhotels/");
        loginPage.enterUsername("Sam");
        loginPage.enterPassword("sam");
        loginPage.clickLogin();
    }

    @When("they search with the following filters:")
    public void they_search_with_the_following_filters(DataTable dataTable) {
        List<Map<String, String>> filtros = dataTable.asMaps(String.class, String.class);
        Map<String, String> filtro = filtros.get(0);
        // Calcular fechas
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String checkin = hoy.plusDays(2).format(fmt);
        String checkout = hoy.plusDays(7).format(fmt);
        searchPage.applyFilters(
            checkin,
            checkout,
            filtro.get("habitaciones"),
            filtro.get("adultos"),
            filtro.get("niños"),
            Integer.parseInt(filtro.get("precio_min")),
            Integer.parseInt(filtro.get("estrellas_min"))
        );
    }

    @And("they apply the selected filters")
    public void they_apply_the_selected_filters() {
        searchPage.clickSearch();
    }

    @And("they select the cheapest hotel that matches the filters")
    public void they_select_the_cheapest_hotel() {
        searchPage.selectCheapestHotel();
    }

    @And("they make the reservation")
    public void they_make_the_reservation() {
        reservationPage.confirmReservation();
    }

    @Then("the successful reservation message is validated")
    public void the_successful_reservation_message_is_validated() {
        mensajeExito = reservationPage.getSuccessMessage();
        Assert.assertTrue(mensajeExito.toLowerCase().contains("reserva exitosa") || mensajeExito.toLowerCase().contains("success"));
    }
}
