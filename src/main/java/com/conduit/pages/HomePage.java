package com.conduit.pages;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class HomePage extends BasePage {

    private static final String URL = "https://angular.realworld.io/";

    private static final By LOGO = By.className("navbar-brand");
    private static final By SIGN_IN_LINK = By.cssSelector("[routerlink=\"/login\"]");
    private static final By NEW_ARTICLE_BUTTON = By.cssSelector("[routerlink=\"/editor\"]");

    public HomePage open() {
        log.info("Opening Home page");
        browser.openPage(URL);
        return this;
    }

    public LoginPage navigateToLoginPage() {
        log.info("Navigating to Login Page");
        browser.findClickableElement(SIGN_IN_LINK).click();
        return new LoginPage();
    }

    public boolean isLogoDisplayed() {
        log.info("Checking if logo is displayed on Home page");
        return browser
                .findElement(LOGO)
                .isDisplayed();
    }

    public boolean isNewArticleButtonDisplayed() {
        log.info("Checking if New Article button is displayed on Home page");
        return browser
                .findElement(NEW_ARTICLE_BUTTON)
                .isDisplayed();
    }
}
