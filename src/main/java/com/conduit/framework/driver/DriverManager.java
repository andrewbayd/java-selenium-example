package com.conduit.framework.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getProperty;

public abstract class DriverManager {

    protected static final ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();

    protected abstract void createDriver();

    protected abstract void createDriver(String remoteUrl);

    public synchronized WebDriver getDriver() {
        if (isPoolEmpty()) {
            createDriver();
        }
        return DRIVER_POOL.get();
    }

    public synchronized WebDriver getDriver(String remoteUrl) {
        if (isPoolEmpty()) {
            createDriver(remoteUrl);
        }
        return DRIVER_POOL.get();
    }

    public synchronized void quitDriver() {
        if (!isPoolEmpty()) {
            try {
                DRIVER_POOL.get().quit();
            } finally {
                DRIVER_POOL.remove();
            }
        }
    }

    protected void createRemoteDriver(String remoteUrl, DesiredCapabilities capabilities) {
        try {
            DRIVER_POOL.set(
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
        DRIVER_POOL.get().manage().window().maximize();
    }

    private boolean isPoolEmpty() {
        return (Objects.isNull(DRIVER_POOL.get()));
    }
}
