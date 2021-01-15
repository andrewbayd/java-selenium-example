package com.conduit.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        DRIVER_POOL.set(new ChromeDriver());
        DRIVER_POOL.get().manage().window().maximize();
    }
}
