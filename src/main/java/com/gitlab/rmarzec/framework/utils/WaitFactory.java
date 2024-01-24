package com.gitlab.rmarzec.framework.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class WaitFactory {

    WebDriver driver;
    Wait<WebDriver> wait;

    public WaitFactory(WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(6))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class);
    }

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> waitForAllElements(By locator) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public WebElement waitForChild(WebElement parent, By childLocator) {
        try {
            List<WebElement> until = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(parent, childLocator));
            return until.get(0);
        } catch (NoSuchElementException | TimeoutException e) {
            return null;
        }
    }
}
