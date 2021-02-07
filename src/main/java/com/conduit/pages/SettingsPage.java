package com.conduit.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class SettingsPage extends BasePage<SettingsPage> {

    public static final String PATH = "/settings";

    private static final By LOGOUT_BUTTON = By.className("btn-outline-danger");

    @Override
    protected String getPath() {
        return PATH;
    }

    @Step("Logout from account")
    public HomePage logout() {
        log.info("Logging out from account");
        browser.click(LOGOUT_BUTTON);
        return new HomePage();
    }
}
