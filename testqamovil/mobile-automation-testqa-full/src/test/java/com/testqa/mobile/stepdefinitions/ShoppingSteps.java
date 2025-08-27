package com.testqa.mobile.stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;

import java.net.URL;
import java.time.Duration;

public class ShoppingSteps {

    private static AndroidDriver driver;

    @Given("el usuario ha iniciado sesión")
    public void elUsuarioEstaLogueado() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setUdid("emulator-5554")
                
                .setApp("/home/giovanni/testqamovil/mobile-automation-testqa-full/saucelabs-sample.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        
        driver.findElement(AppiumBy.accessibilityId("test-Username")).sendKeys("standard_user");
        driver.findElement(AppiumBy.accessibilityId("test-Password")).sendKeys("secret_sauce");
        driver.findElement(AppiumBy.accessibilityId("test-LOGIN")).click();
    }

    @And("agrega un producto al carrito")
    public void agregaUnProductoAlCarrito() {
        WebElement firstAddBtn = driver.findElement(
                AppiumBy.xpath("(//android.widget.Button[@content-desc='test-ADD TO CART'])[1]")
        );
        firstAddBtn.click();
    }

    @Then("el producto debería aparecer en el carrito")
    public void elProductoDeberiaAparecerEnElCarrito() {
        WebElement cartBadge = driver.findElement(AppiumBy.xpath("//*[@text='1']"));
        assert cartBadge.isDisplayed();
    }
}



