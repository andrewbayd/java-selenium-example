package com.conduit.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxDriverCreator extends DriverFactory {

    @Override
    protected void createDriver() {
        WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
        DRIVER.set(new FirefoxDriver());
        DRIVER.get().manage().window().maximize();
    }

    @Override
    protected void createRemoteDriver(String remoteUrl) {
        createRemoteDriver(remoteUrl, DesiredCapabilities.firefox());
    }
}
