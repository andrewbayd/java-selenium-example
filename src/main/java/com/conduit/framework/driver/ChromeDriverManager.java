package com.conduit.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

import static java.lang.Boolean.getBoolean;
import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getProperty;

public class ChromeDriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        DRIVER_POOL.set(new ChromeDriver());
        maximizeBrowserWindow();
    }

    @Override
    protected void createDriver(String remoteUrl) {
        createRemoteDriver(remoteUrl, DesiredCapabilities.chrome());
    }
}
