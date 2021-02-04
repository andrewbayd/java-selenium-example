package com.conduit;

import com.conduit.framework.driver.Browser;
import com.conduit.framework.driver.DriverManager;
import com.conduit.framework.driver.DriverManagerFactory;
import com.conduit.utils.TestListener;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

@Slf4j
@Listeners(TestListener.class)
public class BaseTest {
    private DriverManager driverManager;

    @Parameters("browser")
    @BeforeMethod
    protected void beforeMethod(@Optional("chrome") String browser) {
        getDriver(browser);
    }

    @AfterMethod(alwaysRun = true)
    protected void afterMethod() {
        quitDriver();
    }

    protected void getDriver(String browser) {
        log.info(String.format("Creating driver for browser %s...", browser));
        driverManager = DriverManagerFactory.getManager(Browser.valueOf(browser.toUpperCase()));
        driverManager.getDriver();
    }

    protected void quitDriver() {
        log.info("Stopping browser...");
        driverManager.quitDriver();
    }

}
