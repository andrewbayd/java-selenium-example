package com.conduit.framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getProperty;
import static java.util.Objects.isNull;

public abstract class DriverFactory {

    protected static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    protected abstract void createDriver();

    protected abstract void createRemoteDriver(String remoteUrl);

    public synchronized WebDriver getDriver() {
        if (isDriverEmpty()) {
            createDriver();
        }
        return DRIVER.get();
    }

    public synchronized WebDriver getRemoteDriver(String remoteUrl) {
        if (isDriverEmpty()) {
            createRemoteDriver(remoteUrl);
        }
        return DRIVER.get();
    }

    public synchronized void quitDriver() {
        if (!isDriverEmpty()) {
            try {
                DRIVER.get().quit();
            } finally {
                DRIVER.remove();
            }
        }
    }

    protected void createRemoteDriver(String remoteUrl, DesiredCapabilities capabilities) {
        try {
            DRIVER.set(
                    new RemoteWebDriver(
                            URI.create(remoteUrl).toURL(),
                            getCapabilities(capabilities)
                    )
            );
            maximizeBrowserWindow();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    protected DesiredCapabilities getCapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", parseBoolean(getProperty("enableVNC")),
                "enableVideo", parseBoolean(getProperty("enableVideo"))
        ));
        return capabilities;
    }

    protected void maximizeBrowserWindow() {
        DRIVER.get().manage().window().maximize();
    }

    private boolean isDriverEmpty() {
        return (isNull(DRIVER.get()));
    }
}
