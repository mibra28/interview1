package com.gitlab.rmarzec.framework.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitFactory {

    WebDriver driver;
    Wait<WebDriver> wait;

    public WaitFactory(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300));
    }

    public WebElement waitForVisibility(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Element is not found by locator");
            return null;
        }
    }

    public WebElement waitForVisibility(WebElement webElement) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(webElement));
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("Element is not found by WebElement");
            return null;
        }
    }

}
