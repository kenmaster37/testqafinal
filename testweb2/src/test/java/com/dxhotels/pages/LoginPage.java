package com.dxhotels.pages;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class LoginPage extends PageObject {
    private By loginMenuButton = By.id("HeaderControl_Login_CD");
    private By usernameField = By.id("HeaderControl_LogonControl_LoginFormLayout_txtEmail_I");
    private By passwordField = By.xpath("(//*[@id='HeaderControl_LogonControl_LoginFormLayout_txtPassword_I'])[1]");
    // Si el captcha es obligatorio, agregarlo aquí
    private By loginButton = By.id("HeaderControl_LogonControl_btnLoginNow_CD");
    private By errorMessage = By.cssSelector(".validation-summary-errors, .message-error");

    public void openLoginModal() {
        $(loginMenuButton).waitUntilClickable().click();
        $(usernameField).waitUntilVisible();
    }

    public void enterUsername(String username) {
        $(usernameField).waitUntilVisible().clear();
        $(usernameField).type(username);
    }

    public void enterPassword(String password) {
        $(passwordField).waitUntilVisible().clear();
        $(passwordField).type(password);
    }

    public void clickLogin() {
        $(loginButton).click();
    }

    public String getErrorMessage() {
        return $(errorMessage).getText();
    }
}
