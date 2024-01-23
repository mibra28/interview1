package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import com.gitlab.rmarzec.pages.youtube.YoutubePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.gitlab.rmarzec.constants.Urls.YOUTUBE;
import static java.lang.Thread.sleep;


public class Task4Test {
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();
    Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    List<YTTile> ytTileList = new ArrayList<>();
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
    YoutubePage youtubePage = new YoutubePage(webDriver);
    int i = 0;

    @Test
    public void Task4Test() throws InterruptedException {
        youtubePage.openHomePage();
        youtubePage.acceptCookiesButton();

//        webDriver.get(YOUTUBE);
//        sleep(2000);
//        webDriver.findElement(By.xpath("//ytd-button-renderer[2]")).click();
//        sleep(2000);
        List<WebElement> allVideos = youtubePage.getAllVideos();
//        List<WebElement> elements = webDriver.findElements(By.xpath("//ytd-rich-item-renderer"));


//        while (ytTileList.size() <12) {
//            i++;
//            WebElement element = youtubePage.getVideoWithIndex(i);
//            javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
//
//            ytTileList.add(new YTTile(
//                    youtubePage.getVideoTitle(element),
//                    youtubePage.getVideoLength(element),
//                    youtubePage.getChannelName(element))
//            );
//
//        }




        while (ytTileList.size() < 12) {

            WebElement element = allVideos.get(i);
            i++;
            javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
            String videoTitle = youtubePage.getVideoTitle(element);
            String chanelTitle = youtubePage.getChannelName(element);
            String videoLength = youtubePage.getVideoLength(element);

            if (chanelTitle.equals("Short"))
                continue;
            ytTileList.add(new YTTile(
                    videoTitle,
                    chanelTitle,
                    videoLength
            ));
        }

        for (YTTile ytTile : ytTileList) {
            if (!ytTile.getLength().equals("Live")) {
                System.out.println(ytTile.getTitle() + " length is " + ytTile.getLength());
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


