package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.gitlab.rmarzec.constants.Urls.GOOGLE;
import static com.gitlab.rmarzec.constants.Urls.W3SCHOOLS;
import static java.lang.Thread.sleep;

public class Task3Test {
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();

    @Test
    public void Task3Test() throws InterruptedException {

        webDriver.get(GOOGLE);
        sleep(2000);

        webDriver.findElement(By.id("W0wltc")).click();
        sleep(2000);
        webDriver.findElement(By.className("gLFyf")).sendKeys("HTML select tag - W3Schools");
        new Actions(webDriver).moveByOffset(200, 100).perform();
        webDriver.findElement(By.name("btnI")).click();
        sleep(2000);

        if (!webDriver.getCurrentUrl().equals(W3SCHOOLS)) {
            System.out.println("Current address: " + webDriver.getCurrentUrl());
            webDriver.get(W3SCHOOLS);
        }

        webDriver.findElement(By.id("accept-choices")).click();

        webDriver.findElement(By.xpath("//*[contains(text(),'Try it Yourself »')]")).click();

        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());

        webDriver.switchTo().window(tabs.get(1));
        sleep(3000);
        webDriver.switchTo().frame("iframeResult");
        WebElement element = webDriver.findElement(By.tagName("h1"));
        System.out.println(element.getText());
        Select select = new Select(webDriver.findElement(By.id("cars")));
        select.selectByVisibleText("Opel");
        WebElement selectedOption = select.getFirstSelectedOption();
        System.out.println(selectedOption.getText() + ", " + selectedOption.getAttribute("value"));

    }


    @AfterTest
    public void cleanUp() {
        webDriver.quit();
    }
}
