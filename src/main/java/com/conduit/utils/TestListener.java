package com.conduit.utils;

import com.conduit.framework.driver.DriverManager;
import io.qameta.allure.Attachment;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import static java.util.Objects.isNull;

@Slf4j
public class TestListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult testResult) {
        attachScreenshot();
    }

    private void attachScreenshot() {
        WebDriver driver = DriverManager.getDriverFactory().getDriver();
        if (isNull(driver)) {
            log.error("Driver instance is null, cannot take a screenshot");
            return;
        }
        takeScreenshot(driver);
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = ".png")
    private byte[] takeScreenshot(WebDriver driver) {
        try {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            log.error("WebDriver failed taking screenshot: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
