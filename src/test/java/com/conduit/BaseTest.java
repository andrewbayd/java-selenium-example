package com.conduit;

import com.conduit.framework.driver.Browser;
import com.conduit.framework.driver.DriverManager;
import com.conduit.framework.driver.DriverManagerFactory;
import com.conduit.utils.TestListener;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;

import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getProperty;

@Slf4j
@Listeners(TestListener.class)
public class BaseTest {

    private DriverManager driverManager;

    @BeforeMethod
    protected void beforeMethod() {
        getDriver(getProperty("browser"));
    }

    @AfterMethod(alwaysRun = true)
    protected void afterMethod() {
        quitDriver();
    }

    protected void getDriver(String browser) {
        driverManager = DriverManagerFactory.getManager(Browser.valueOf(browser.toUpperCase()));
        String remoteProperty = getProperty("remote");
        boolean remote = parseBoolean(remoteProperty);
        if (remote) {
            log.info(String.format("Creating remote driver for browser %s...", browser));
            driverManager.getDriver(getProperty("remoteUrl"));
        } else {
            log.info(String.format("Creating local driver for browser %s...", browser));
            driverManager.getDriver();
        }
    }

    protected void quitDriver() {
        log.info("Stopping browser...");
        driverManager.quitDriver();
    }

}
