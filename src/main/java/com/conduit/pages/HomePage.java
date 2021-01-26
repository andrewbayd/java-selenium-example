package com.conduit.pages;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

@Slf4j
public class HomePage extends BasePage<HomePage> {
    private static final By LOGO = By.className("navbar-brand");
    private static final By SIGN_IN_LINK = By.cssSelector("[routerlink=\"/login\"]");
    private static final By NEW_ARTICLE_BUTTON = By.cssSelector("[routerlink=\"/editor\"]");

    @Override
    protected String url() {
        return "https://angular.realworld.io/";
    }

    @Step("Navigate to login page")
    public LoginPage navigateToLoginPage() {
        log.info("Navigating to Login Page");
        browser.findClickableElement(SIGN_IN_LINK).click();
        return new LoginPage();
    }

    @Step("Check logo is displayed")
    public boolean isLogoDisplayed() {
        log.info("Checking if logo is displayed on Home page");
        return browser
                .findElement(LOGO)
                .isDisplayed();
    }

    @Step("Check New Article button is displayed")
    public boolean isNewArticleButtonDisplayed() {
        log.info("Checking if New Article button is displayed on Home page");
        return browser
                .findElement(NEW_ARTICLE_BUTTON)
                .isDisplayed();
    }
}
