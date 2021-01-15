package com.conduit.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager extends DriverManager{

    @Override
    protected void createDriver() {
        WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
        DRIVER_POOL.set(new FirefoxDriver());
        DRIVER_POOL.get().manage().window().maximize();
    }
}
