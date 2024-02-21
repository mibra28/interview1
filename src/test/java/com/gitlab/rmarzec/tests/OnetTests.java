package com.gitlab.rmarzec.tests;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static com.gitlab.rmarzec.constants.Urls.ONET;
import static org.testng.AssertJUnit.assertEquals;


public class OnetTests {
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();

    @Test
    public void checkThatOnetIsOpened() {
        webDriver.get(ONET);
        assertEquals(webDriver.getCurrentUrl(), ONET);
    }

    @AfterTest
    public void cleanUp() {
        webDriver.quit();
    }
}
