package com.gitlab.rmarzec.pages.youtube;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.gitlab.rmarzec.constants.Urls.YOUTUBE;

public class YoutubePage {

    WebDriver driver;
    Wait<WebDriver> wait;


    public YoutubePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private final By acceptCookiesButton = By.xpath("//ytd-button-renderer[2]");
    private final By videoTitle = By.id("video-title");
    private final By chanelTitle = By.className("ytd-channel-name");
    private final By timeStatus = By.id("time-status");
    private final By liveBadge = By.xpath("//*[@id=\"meta\"]/ytd-badge-supported-renderer[1]/div");
    private final By videoElement = By.xpath("//ytd-rich-grid-row//child::ytd-rich-item-renderer");

    public void acceptCookiesButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(acceptCookiesButton)).click();
    }

    public List<WebElement> getVideos() {
       return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(videoElement));
    }

    public String getVideoTitle(WebElement element) {
         return element.findElement(videoTitle).getText();
    }

    public String getVideoLength(WebElement element) {

        try {
            return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(element, timeStatus)).getText();
        } catch (TimeoutException | NoSuchElementException e) {
            return "Live";
        }
    }

    public String getChanelTitle(WebElement element) {
        return element.findElement(chanelTitle).getText();
    }

    public void openHomePage() {
        driver.get(YOUTUBE);
    }

}




