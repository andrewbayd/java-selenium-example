package com.conduit.pages;

import com.conduit.framework.BrowserActions;

public abstract class BasePage {

    protected BrowserActions browser;

    public BasePage() {
        this.browser = new BrowserActions();
    }
}
