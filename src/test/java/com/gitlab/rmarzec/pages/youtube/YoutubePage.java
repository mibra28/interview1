package com.gitlab.rmarzec.pages.youtube;

import com.gitlab.rmarzec.framework.utils.WaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.gitlab.rmarzec.constants.Urls.WIKI;
import static com.gitlab.rmarzec.constants.Urls.YOUTUBE;

public class YoutubePage {

    WebDriver driver;
    WaitFactory wait;

    public YoutubePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitFactory(driver);
    }

    private final By acceptCookiesButton = By.xpath("//ytd-button-renderer[2]");
    private final By cookieContent = By.id("content");
    private final By videoTitle = By.id("video-title");
    private final By chanelTitle = By.className("ytd-channel-name");
    private final By timeStatus = By.id("time-status");
    private final By videoElement = By.xpath("//ytd-rich-item-renderer");
    private final String videoElementWithIndex = "//ytd-rich-item-renderer[%s]";
    private final By videoContainer = By.className("ytd-rich-grid-renderer");
    private final By liveBadge = By.xpath("//*[@id=\"meta\"]/ytd-badge-supported-renderer[1]/div");

    public void acceptCookiesButton() {
        wait.waitForVisibility(acceptCookiesButton).click();
    }

    public List<WebElement> getAllVideos() {

        return wait.waitForVisibility(videoContainer).findElements(videoElement);

    }

    public String getVideoTitle(WebElement element) {
         return wait.waitForVisibility(element.findElement(videoTitle)).getText();
    }

    public WebElement getVideoWithIndex(int index) {
        return wait.waitForVisibility(By.xpath(String.format(videoElementWithIndex, index)));
    }

    public String getVideoLength(WebElement element) {
        WebElement erb = wait.waitForVisibility(element.findElement(timeStatus));
        if (erb==null) {
            return "Live";
        }
        return wait.waitForVisibility(getVideoWithIndex(1).findElement(timeStatus)).getText();
    }

    public String getChannelName(WebElement element) {
        WebElement ssss = wait.waitForVisibility(element.findElement(chanelTitle));
        if(ssss==null) {
            return "Short";
        }
       return wait.waitForVisibility(element.findElement(chanelTitle)).getText();
    }


    public void openHomePage() {
        driver.get(YOUTUBE);
    }

}




