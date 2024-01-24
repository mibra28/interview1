package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import com.gitlab.rmarzec.pages.youtube.YoutubePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class Task4Test {
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();
    List<YTTile> ytTileList = new ArrayList<>();
    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
    YoutubePage youtubePage = new YoutubePage(webDriver);
    int i = 0;

    @Test
    public void Task4Test() {
        youtubePage.openHomePage();
        youtubePage.acceptCookiesButton();

        List<WebElement> allVideos = youtubePage.getVideos();


        while (ytTileList.size() < 12) {

            WebElement element = allVideos.get(i);
            i++;
            javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
            String videoLength = youtubePage.getVideoLength(element);
            String videoTitle = youtubePage.getVideoTitle(element);
            String chanelTitle = youtubePage.getChanelTitle(element);


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

}


