package com.conduit.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverCreator extends DriverFactory {

    @Override
    protected void createDriver() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        DRIVER.set(new ChromeDriver());
        maximizeBrowserWindow();
    }

    @Override
    protected void createRemoteDriver(String remoteUrl) {
        createRemoteDriver(remoteUrl, DesiredCapabilities.chrome());
    }
}
