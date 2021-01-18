package com.conduit.pages;

import com.conduit.framework.BrowserActions;
import com.conduit.models.User;
import com.conduit.utils.AuthHelper;
import lombok.extern.slf4j.Slf4j;

import static com.conduit.utils.AuthHelper.getAuthToken;

@Slf4j
public abstract class BasePage<T> {
    protected BrowserActions browser;

    public BasePage() {
        this.browser = new BrowserActions();
    }

    protected abstract String getUrl();

    public T open() {
        String url = getUrl();
        log.info(String.format("Opening %s", url));
        browser.openPage(url);
        return (T) this;
    }

    public T setToken(String token) {
        log.info("Setting jwtToken to Local Storage");
        browser.setLocalStorage("jwtToken", token);
        return (T) this;
    }

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
