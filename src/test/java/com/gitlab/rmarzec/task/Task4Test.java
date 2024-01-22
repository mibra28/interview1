package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.window;
import static java.lang.Thread.sleep;


public class Task4Test {
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();

    Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    List<YTTile> ytTileList = new ArrayList<YTTile>();

    JavascriptExecutor js = (JavascriptExecutor) webDriver;


    @Test
    public void Task4Test() throws InterruptedException {
        int i = 0;

        webDriver.get("https://www.youtube.com/");
        sleep(2000);
        webDriver.findElement(By.xpath("//ytd-button-renderer[2]")).click();
        sleep(2000);
       List<WebElement> elements = webDriver.findElements(By.xpath("//ytd-rich-item-renderer"));

        while (ytTileList.size()<12) {

            WebElement element = elements.get(i);
            i++;
            js.executeScript("arguments[0].scrollIntoView();", element);
            String videoTitle = getVideoTitle(element);
            String chanelTitle = getChanelTitle(element);
            String videoLength = getVideoLength(element);

            if(chanelTitle.equals("Short"))
                continue;
            ytTileList.add(new YTTile(
                    videoTitle,
                    chanelTitle,
                    videoLength
            ));
        }

        for(YTTile ytTile : ytTileList) {
            if(!ytTile.getLength().equals("Live")) {
                System.out.println(ytTile.getTitle() + " length is " + ytTile.getLength() );
            }
        }
    }
    @AfterTest
    public void cleanUp() {
        webDriver.quit();
    }

    private String getVideoTitle(WebElement element) {
        return element.findElement(By.id("video-title")).getText();
    }

    private String getChanelTitle(WebElement element) {
        try {
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOf(element.findElement(By.id("avatar-link"))));
            return titleElement.getAttribute("title");
        } catch (NoSuchElementException e) {
            return "Short";
        }
    }

    private String getVideoLength(WebElement element) {

        try {
            WebElement lengthElement = wait.until(ExpectedConditions.visibilityOf(element.findElement(By.id("time-status"))));
            return lengthElement.getText();
        } catch (TimeoutException | NoSuchElementException e) {
            return "Live";
        }
    }
}


