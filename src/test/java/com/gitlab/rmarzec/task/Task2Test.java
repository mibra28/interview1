package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.List;


public class Task2Test {
    DriverFactory driverFactory = new DriverFactory();
    WebDriver webDriver = driverFactory.initDriver();
    @Test
    public void Task2Test() {

        webDriver.get("https://pl.wikipedia.org/wiki/Wiki");
        webDriver.findElement(By.id("p-lang-btn-checkbox")).click();
        WebElement element = webDriver.findElement(By.xpath(".//*[@class='row uls-language-list uls-lcd']"));
        List<WebElement> languages = element.findElements(By.tagName("li"));

        for(WebElement language : languages) {
            if(language.getText().equals("English")) {
                String href = language.findElement(By.tagName("a")).getAttribute("href");
                System.out.println(language.getText() + " Url: " + href);
            }
            System.out.println(language.getText());
        }
    }
    @AfterTest
    public void cleanUp() {
        webDriver.quit();
    }
}
