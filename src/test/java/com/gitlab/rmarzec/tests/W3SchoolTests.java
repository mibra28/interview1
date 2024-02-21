package com.gitlab.rmarzec.tests;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.pages.google.GooglePage;
import com.gitlab.rmarzec.pages.w3schools.W3SchoolsEditorPage;
import com.gitlab.rmarzec.pages.w3schools.W3SchoolsPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static com.gitlab.rmarzec.constants.Urls.W3SCHOOLS;
import static org.testng.AssertJUnit.assertEquals;

public class W3SchoolTests {
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();
    GooglePage googlePage = new GooglePage(webDriver);
    W3SchoolsPage w3SchoolsPage = new W3SchoolsPage(webDriver);
    W3SchoolsEditorPage w3SchoolsEditorPage = new W3SchoolsEditorPage(webDriver);

    @Test
    public void checkValueForSelectedOption() {
        googlePage.openHomePage();
        googlePage.clickAcceptCookiesButton();
        googlePage.inputSearchedText("HTML select tag - W3Schools");
        googlePage.clickFeelingLuckyButton();

        if (!webDriver.getCurrentUrl().equals(W3SCHOOLS)) {
            System.out.println("Current address: " + webDriver.getCurrentUrl());
            w3SchoolsPage.openHomePage();
        }

        w3SchoolsPage.clickAcceptCookiesButton();
        w3SchoolsPage.clickTryYourselfButton();
        w3SchoolsEditorPage.switchToIframeResults();
        w3SchoolsEditorPage.printHeader();
        String value = w3SchoolsEditorPage.getSelectedValue("Opel");
        assertEquals("opel", value);
    }

    @AfterTest
    public void cleanUp() {
        webDriver.quit();
    }
}
