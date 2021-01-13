package com.conduit.framework.driver;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public abstract class DriverManager {

    protected static final ThreadLocal<WebDriver> DRIVER_POOL = new ThreadLocal<>();

    protected abstract WebDriver createDriver();

    public synchronized WebDriver getDriver() {
        if (isPoolEmpty()) {
            createDriver();
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

    private boolean isPoolEmpty() {
        return (Objects.isNull(DRIVER_POOL.get()));
    }
}
