package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.pages.wikipedia.WikiPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;


public class WikiTests {
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();
    WikiPage wikiPage = new WikiPage(webDriver);

    @Test
    public void checkUrlForEnglishLanguage() {
        wikiPage.openHomePage();
        wikiPage.clickOnLanguageButton();
        List<WebElement> allLanguages = wikiPage.getAllLanguages();
        wikiPage.printLanguages(allLanguages);
        String url = wikiPage.getHref(wikiPage.getSingleLanguage(allLanguages, "English"));
        assertEquals(url, "https://en.wikipedia.org/wiki/Wiki");
    }

    @AfterTest
    public void cleanUp() {
        webDriver.quit();
    }
}
