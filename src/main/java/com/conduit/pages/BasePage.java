package com.conduit.pages;

import com.conduit.framework.BrowserActions;
import com.conduit.models.User;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

import static com.conduit.utils.AuthHelper.getAuthToken;

@Slf4j
public abstract class BasePage<T> {
    protected BrowserActions browser;

    public BasePage() {
        this.browser = new BrowserActions();
    }

    protected abstract String url();

    public T open() {
        String url = url();
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
}
