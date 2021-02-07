package com.conduit;

import com.conduit.framework.driver.Browser;
import com.conduit.framework.driver.DriverFactory;
import com.conduit.framework.driver.DriverManager;
import com.conduit.utils.TestListener;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getProperty;

@Slf4j
@Listeners(TestListener.class)
public class BaseTest {

    private DriverFactory driverFactory;

    @BeforeMethod
    protected void beforeMethod() {
        getDriver(getProperty("browser"));
    }

    @AfterMethod(alwaysRun = true)
    protected void afterMethod() {
        quitDriver();
    }

    protected void getDriver(String browser) {
        driverFactory = DriverManager.getFactory(Browser.valueOf(browser.toUpperCase()));
        if (parseBoolean(getProperty("remote"))) {
            log.info(String.format("Creating remote driver for browser %s...", browser));
            driverFactory.getRemoteDriver(getProperty("remoteUrl"));
            return;
        }
        log.info(String.format("Creating local driver for browser %s...", browser));
        driverFactory.getDriver();
    }

    protected void quitDriver() {
        log.info("Stopping browser...");
        driverFactory.quitDriver();
    }
}
