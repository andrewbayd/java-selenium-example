package com.conduit.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class LoginPage extends BasePage<LoginPage> {

    public static final String PATH = "/login";

    private static final By EMAIL_FIELD = By.cssSelector("[formcontrolname=\"email\"]");
    private static final By PASSWORD_FIELD = By.cssSelector("[type=\"password\"]");
    private static final By SIGN_IN_BUTTON = By.cssSelector("[type=\"submit\"]");

    @Override
    protected String getPath() {
        return PATH;
    }

    @Step("Perform login as {email}")
    public HomePage loginAs(String email, String password) {
        log.info("Logging in as {}", email);
        browser.findClickableElement(EMAIL_FIELD).sendKeys(email);
        browser.findClickableElement(PASSWORD_FIELD).sendKeys(password);
        browser.findClickableElement(SIGN_IN_BUTTON).click();
        return new HomePage();
    }
}
