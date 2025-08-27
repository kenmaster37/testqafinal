package com.testqa.mobile.stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.net.URL;
import java.time.Duration;

public class LoginSteps {

  private static AndroidDriver driver;
  private WebDriverWait waitFor() { return new WebDriverWait(driver, Duration.ofSeconds(25)); }

  @Given("el usuario abre la app")
  public void elUsuarioAbreLaApp() throws Exception {

    // --- Intento A: dejar que Appium detecte el launcher (sin appPackage/appActivity)
    UiAutomator2Options opts = new UiAutomator2Options()
        .setPlatformName("Android")
        .setAutomationName("UiAutomator2")
        .setUdid("emulator-5554") // o .setAvd("Medium_Phone_API_36.0")
        .setApp("/home/giovanni/testqamovil/mobile-automation-testqa-full/saucelabs-sample.apk")
        .setAppWaitForLaunch(true)
        .setAppWaitDuration(Duration.ofSeconds(60))
        .setAppWaitActivity("*")
        // robustez extra
        .amend("autoGrantPermissions", true)
        .amend("noReset", true)
        .amend("disableWindowAnimation", true)
        .amend("ignoreHiddenApiPolicyError", true)
        .amend("adbExecTimeout", 120000)
        .amend("uiautomator2ServerInstallTimeout", 120000);

    try {
      driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), opts);
    } catch (org.openqa.selenium.SessionNotCreatedException e) {
      // --- Intento B: forzar LAUNCHER conocido (p.ej. MainActivity) y aceptar cualquiera al esperar
      UiAutomator2Options forced = new UiAutomator2Options()
          .setPlatformName("Android")
          .setAutomationName("UiAutomator2")
          .setUdid("emulator-5554")
          .setApp("/home/giovanni/testqamovil/mobile-automation-testqa-full/saucelabs-sample.apk")
          .setAppPackage("com.swaglabsmobileapp")
          .setAppActivity("com.swaglabsmobileapp.MainActivity")   // <-- AJUSTA si tu APK reporta otra
          .setAppWaitActivity("*")
          .setAppWaitForLaunch(true)
          .setAppWaitDuration(Duration.ofSeconds(60))
          .amend("autoGrantPermissions", true)
          .amend("noReset", true)
          .amend("disableWindowAnimation", true)
          .amend("ignoreHiddenApiPolicyError", true)
          .amend("adbExecTimeout", 120000)
          .amend("uiautomator2ServerInstallTimeout", 120000);

      driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), forced);
    }

    // Log de diagnóstico inmediato
    try {
      System.out.println("Pkg=" + driver.getCurrentPackage() + " Act=" + driver.currentActivity());
    } catch (Exception ignore) {}

    // Espera a que esté visible el login
    waitFor().until(ExpectedConditions.presenceOfElementLocated(
        AppiumBy.accessibilityId("test-Username")));
  }

  @When("ingresa usuario  {string} y contraseña {string}")
  public void ingresaUsuarioYContrasenia(String user, String pass) {
    WebElement userField = waitFor().until(ExpectedConditions.elementToBeClickable(
        AppiumBy.accessibilityId("test-Username")));
    userField.clear(); userField.sendKeys(user);

    WebElement passField = waitFor().until(ExpectedConditions.elementToBeClickable(
        AppiumBy.accessibilityId("test-Password")));
    passField.clear(); passField.sendKeys(pass);
    try { driver.hideKeyboard(); } catch (Exception ignore) {}
  }

  @And("presiona el botón de login")
  public void presionaElBotonDeLogin() {
    waitFor().until(ExpectedConditions.elementToBeClickable(
        AppiumBy.accessibilityId("test-LOGIN"))).click();
  }

  @Then("debería ingresar exitosamente")
  public void deberiaIngresarExitosamente() {
    // Espera a que aparezca PRODUCTS o un mensaje de error
    waitFor().until(d -> {
      boolean ok = !d.findElements(AppiumBy.xpath("//*[@text='PRODUCTS']")).isEmpty();
      boolean err = !d.findElements(AppiumBy.accessibilityId("test-Error message")).isEmpty();
      return ok || err;
    });

    if (!driver.findElements(AppiumBy.accessibilityId("test-Error message")).isEmpty()) {
      String msg = driver.findElement(AppiumBy.accessibilityId("test-Error message")).getText();
      throw new AssertionError("Login falló con mensaje: " + msg);
    }
  }
}


