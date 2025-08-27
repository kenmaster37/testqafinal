package com.dxhotels.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class HotelReservationPage extends PageObject {
    private By confirmButton = By.cssSelector("button.confirm-booking");
    private By successMessage = By.cssSelector(".reservation-success");

    public void confirmReservation() {
        $(confirmButton).click();
    }

    public String getSuccessMessage() {
        return $(successMessage).getText();
    }
}
