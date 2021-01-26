package com.conduit.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage<LoginPage> {
    private static final By EMAIL_FIELD = By.cssSelector("[formcontrolname=\"email\"]");
    private static final By PASSWORD_FIELD = By.cssSelector("[type=\"password\"]");
    private static final By SIGN_IN_BUTTON = By.cssSelector("[type=\"submit\"]");

    @Override
    protected String url() {
        return "https://angular.realworld.io/login";
    }

    @Step("Perform login as {email}")
    public HomePage loginAs(String email, String password) {
        browser.findClickableElement(EMAIL_FIELD).sendKeys(email);
        browser.findClickableElement(PASSWORD_FIELD).sendKeys(password);
        browser.findClickableElement(SIGN_IN_BUTTON).click();
        return new HomePage();
    }
}
