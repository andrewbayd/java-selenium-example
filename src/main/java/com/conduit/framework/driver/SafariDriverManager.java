package com.conduit.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SafariDriverManager extends DriverManager{

    @Override
    protected void createDriver() {
        WebDriverManager.getInstance(DriverManagerType.SAFARI).setup();
        DRIVER_POOL.set(new SafariDriver());
        DRIVER_POOL.get().manage().window().maximize();
    }
}
