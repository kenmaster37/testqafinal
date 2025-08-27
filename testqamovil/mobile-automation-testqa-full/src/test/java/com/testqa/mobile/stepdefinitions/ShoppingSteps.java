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
                // Usa UNA: si el emulador ya está encendido:
                .setUdid("emulator-5554")
                // Si prefieres que Appium lo encienda, usa esto en su lugar:
                // .setAvd("Medium_Phone_API_36.0")
                // .setAvdLaunchTimeout(Duration.ofMinutes(3))
                // No fijes platformVersion si no coincide exactamente con el AVD
                .setApp("/home/giovanni/testqamovil/mobile-automation-testqa-full/saucelabs-sample.apk");

        // Appium 2: URL raíz (sin /wd/hub)
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Login
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
        // Opcional: driver.quit(); (o en un @After para cerrar al final)
    }
}



