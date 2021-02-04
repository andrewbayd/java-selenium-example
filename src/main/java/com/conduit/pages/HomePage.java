package com.conduit.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class HomePage extends BasePage<HomePage> {
    private static final By BANNER = By.className("banner");

    @Override
    protected String getPath() {
        return "/";
    }

    @Step("Check banner is displayed")
    public boolean isBannerDisplayed() {
        log.info("Checking if banner is displayed on Home page");
        return browser
                .findElement(BANNER)
                .isDisplayed();
    }
}
