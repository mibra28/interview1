package com.gitlab.rmarzec.pages.youtube;

import com.gitlab.rmarzec.framework.utils.WaitFactory;
import com.gitlab.rmarzec.model.YTTile;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.gitlab.rmarzec.constants.Urls.YOUTUBE;

public class YoutubePage {

    WebDriver driver;
    WaitFactory wait;
    JavascriptExecutor javascriptExecutor;

    public YoutubePage(WebDriver driver) {
        this.driver = driver;
        this.javascriptExecutor = (JavascriptExecutor) driver;
        this.wait = new WaitFactory(driver);
    }

    private final By acceptCookiesButton = By.xpath("//ytd-button-renderer[2]");
    private final By videoTitle = By.id("video-title");
    private final By chanelTitle = By.className("ytd-channel-name");
    private final By timeStatus = By.id("time-status");
    private final By videoElement = By.xpath("(//ytd-rich-grid-row//child::ytd-rich-item-renderer)[position()<13]");

    public void acceptCookiesButton() {
        wait.waitForVisibility((acceptCookiesButton)).click();
    }

    public List<WebElement> getVideos() {
        return wait.waitForAllElements((videoElement));
    }

    public String getVideoTitle(WebElement element) {
        WebElement titleElement = wait.waitForChild(element, videoTitle);
        if (titleElement == null) {
            return "Unexpected commercial";
        }
        return titleElement.getText();
    }

    public String getVideoLength(WebElement element) {
        WebElement timeStatusElement = wait.waitForChild(element, timeStatus);
        if (timeStatusElement == null) {
            return "Live";
        }
        return timeStatusElement.getText();
    }

    public String getChanelTitle(WebElement element) {
        WebElement chanelTitleElement = wait.waitForChild(element, chanelTitle);
        if (chanelTitleElement == null) {
            return "Unexpected commercial";
        }
        return chanelTitleElement.getText();
    }

    public List<YTTile> getYTTiles(List<WebElement> elementList) {
        List<YTTile> ytTileList = new ArrayList<>();
        for (WebElement element : elementList) {
            javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
            ytTileList.add(new YTTile(getVideoTitle(element), getChanelTitle(element), getVideoLength(element)));
        }
        return ytTileList;
    }

    public void openHomePage() {
        driver.get(YOUTUBE);
    }

}




