package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import com.gitlab.rmarzec.pages.youtube.YoutubePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;

public class YoutubeTests {
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();
    YoutubePage youtubePage = new YoutubePage(webDriver);

    @Test
    public void checkFirst12Videos() {
        youtubePage.openHomePage();
        youtubePage.acceptCookiesButton();
        List<WebElement> allVideos = youtubePage.getVideos();
        List<YTTile> ytTiles = youtubePage.getYTTiles(allVideos);

        for (YTTile ytTile : ytTiles) {
            if (!ytTile.getLength().equals("Live")) {
                System.out.printf("Video with title '%s' is %s long%n", ytTile.getTitle(), ytTile.getLength());
            }
        }
        assertEquals(ytTiles.size(), 12);
    }

    @AfterTest
    public void cleanUp() {
        webDriver.quit();
    }

}


