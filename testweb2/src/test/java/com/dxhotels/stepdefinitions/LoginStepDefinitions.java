package com.dxhotels.stepdefinitions;

import com.dxhotels.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

public class LoginStepDefinitions {

    LoginPage loginPage;


    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        loginPage.openUrl("https://demos.devexpress.com/rwa/dxhotels/");
        loginPage.openLoginModal();
    }

    @When("they enter valid credentials")
    public void they_enter_valid_credentials() {
        loginPage.enterUsername("Sam");
        loginPage.enterPassword("sam");
        loginPage.clickLogin();
    }

    @When("they enter invalid credentials")
    public void they_enter_invalid_credentials() {
        loginPage.enterUsername("usuario_invalido");
        loginPage.enterPassword("clave_invalida");
        loginPage.clickLogin();
    }


    @Then("they access the system successfully")
    public void they_access_the_system_successfully() {
        Assert.assertFalse(loginPage.getDriver().getCurrentUrl().contains("Account/Login"));
    }

    @Then("an error message is displayed")
    public void an_error_message_is_displayed() {
        Assert.assertTrue(loginPage.getErrorMessage().contains("Invalid login attempt"));
    }
}
