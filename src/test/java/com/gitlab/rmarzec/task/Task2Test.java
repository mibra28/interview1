package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.pages.wikipedia.WikiPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;

import static com.gitlab.rmarzec.constants.Urls.WIKI;


public class Task2Test {
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();
    WikiPage wikiPage = new WikiPage(webDriver);

    @Test
    public void Task2Test() {
        wikiPage.openHomePage();
        wikiPage.clickOnLanguageButton();
        List<WebElement> allLanguages = wikiPage.getAllLanguages();

        for (WebElement language : allLanguages) {
            if (language.getText().equals("English")) {
                System.out.println(language.getText() + " Url: " + wikiPage.getHref(language));
            }
            System.out.println(language.getText());
        }
    }

    @AfterTest
    public void cleanUp() {
        webDriver.quit();
    }
}
