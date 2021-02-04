package com.conduit.pages;

import com.conduit.framework.BrowserActions;
import com.conduit.models.User;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import static com.conduit.utils.AuthHelper.getAuthToken;
import static java.lang.System.getProperty;

@Slf4j
public abstract class BasePage<T> {

    private static final By NEW_ARTICLE_BUTTON = By.cssSelector("[routerlink=\"/editor\"]");
    private static final By SETTINGS_BUTTON = By.cssSelector("[routerlink=\"/settings\"]");
    private static final By SIGN_IN_BUTTON = By.cssSelector("[routerlink=\"/login\"]");

    protected static final String BASE_URL = getProperty("baseUrl");

    protected BrowserActions browser;

    public BasePage() {
        this.browser = new BrowserActions();
    }

    protected abstract String getPath();

    public T open() {
        String url = BASE_URL.concat(getPath());
        log.info(String.format("Opening %s", url));
        browser.openPage(url);
        return (T) this;
    }

    @Step("Set jwtToken to local storage")
    public T setToken(String token) {
        log.info("Setting jwtToken to local storage");
        browser.setLocalStorage("jwtToken", token);
        return (T) this;
    }

    @Step("Refresh a page")
    public T refresh() {
        log.info("Refreshing a page");
        browser.refreshPage();
        return (T) this;
    }

    public T openLoggedInAs(User user) {
        open();
        setToken(getAuthToken(user));
        refresh();
        return (T) this;
    }

    @Step("Navigate to Login page")
    public LoginPage navigateToLoginPage() {
        log.info("Navigating to Login page");
        browser.findClickableElement(SIGN_IN_BUTTON).click();
        return new LoginPage();
    }

    @Step("Navigate to Settings page")
    public SettingsPage navigateToSettingsPage() {
        log.info("Navigating to Settings page");
        browser.findClickableElement(SETTINGS_BUTTON).click();
        return new SettingsPage();
    }

    @Step("Check Sign In button is displayed")
    public boolean isSignInButtonDisplayed() {
        log.info("Checking if Sign In button is displayed");
        return browser
                .findElement(SIGN_IN_BUTTON)
                .isDisplayed();
    }

    @Step("Check New Article button is displayed")
    public boolean isNewArticleButtonDisplayed() {
        log.info("Checking if New Article button is displayed");
        return browser
                .findElement(NEW_ARTICLE_BUTTON)
                .isDisplayed();
    }
}
