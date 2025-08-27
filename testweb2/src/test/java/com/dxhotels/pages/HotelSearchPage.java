package com.dxhotels.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class HotelSearchPage extends PageObject {
    private By checkinField = By.id("CheckIn");
    private By checkoutField = By.id("CheckOut");
    private By roomsField = By.id("Rooms");
    private By adultsField = By.id("Adults");
    private By childrenField = By.id("Children");
    private By priceMinSlider = By.cssSelector(".price-slider .min-slider-handle");
    private By starsCheckbox = By.cssSelector(".star-rating input[type='checkbox']");
    private By searchButton = By.cssSelector("button[type='submit']");
    private By hotelList = By.cssSelector(".hotel-list .hotel-item");
    private By hotelPrice = By.cssSelector(".hotel-price");
    private By bookButton = By.cssSelector(".book-hotel");

    public void setCheckin(String date) {
        $(checkinField).clear();
        $(checkinField).type(date);
    }

    public void setCheckout(String date) {
        $(checkoutField).clear();
        $(checkoutField).type(date);
    }

    public void setRooms(String rooms) {
        $(roomsField).selectByVisibleText(rooms);
    }

    public void setAdults(String adults) {
        $(adultsField).selectByVisibleText(adults);
    }

    public void setChildren(String children) {
        $(childrenField).selectByVisibleText(children);
    }

    public void setMinPrice(int minPrice) {
        // Implementar lógica para mover el slider si es necesario
    }

    public void selectStars(int minStars) {
        // Selecciona todas las estrellas >= minStars
        for (int i = minStars; i <= 5; i++) {
            if (!$(By.cssSelector(".star-rating input[value='" + i + "']")).isSelected()) {
                $(By.cssSelector(".star-rating input[value='" + i + "']")).click();
            }
        }
    }

    public void clickSearch() {
        $(searchButton).click();
    }

    public void applyFilters(String checkin, String checkout, String rooms, String adults, String children, int minPrice, int minStars) {
        setCheckin(checkin);
        setCheckout(checkout);
        setRooms(rooms);
        setAdults(adults);
        setChildren(children);
        setMinPrice(minPrice);
        selectStars(minStars);
    }

    public void selectCheapestHotel() {
        // Encuentra el hotel más barato y haz clic en reservar
        int min = Integer.MAX_VALUE;
        org.openqa.selenium.WebElement cheapest = null;
        for (org.openqa.selenium.WebElement hotel : getDriver().findElements(hotelList)) {
            String priceText = hotel.findElement(hotelPrice).getText().replaceAll("[^0-9]", "");
            int price = Integer.parseInt(priceText);
            if (price < min) {
                min = price;
                cheapest = hotel;
            }
        }
        if (cheapest != null) {
            cheapest.findElement(bookButton).click();
        }
    }
}
