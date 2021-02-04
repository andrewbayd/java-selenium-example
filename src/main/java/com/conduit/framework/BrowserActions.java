package com.conduit.framework;

import com.conduit.framework.driver.DriverManagerFactory;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserActions {

    private static final int DEFAULT_TIMEOUT = 30;
    private final WebDriver driver;
    private final WebDriverWait wait;

    public BrowserActions() {
        this.driver = DriverManagerFactory.getDriverManager().getDriver();
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    @Step("Open {url}")
    public void openPage(String url) {
        driver.get(url);
    }

    public WebElement findElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement findClickableElement(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement findElementContains(String text) {
        By locator = By.xpath("//*[contains(text(),'" + text + "')]");
        return findElement(locator);
    }

    public void selectFromDropdownByText(By dropdown, String text) {
        new Select(findElement(dropdown)).selectByVisibleText(text);
    }

    public void setLocalStorage(String key, String value) {
        RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) driver);
        RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
        LocalStorage localStorage = webStorage.getLocalStorage();
        localStorage.setItem(key, value);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

}
