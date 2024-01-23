package com.gitlab.rmarzec.pages.w3schools;

import com.gitlab.rmarzec.framework.utils.WaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.gitlab.rmarzec.constants.Urls.W3SCHOOLS;

public class W3SchoolsPage {

    WebDriver driver;
    WaitFactory wait;

    public W3SchoolsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitFactory(driver);
    }

    private final By acceptCookiesButton = By.id("accept-choices");
    private final By tryYourselfButton = By.xpath("//*[contains(text(),'Try it Yourself »')]");

    public void clickAcceptCookiesButton() {
        wait.waitForVisibility(acceptCookiesButton).click();
    }

    public void clickTryYourselfButton() {
        wait.waitForVisibility(tryYourselfButton).click();
    }

    public void openHomePage() {
        driver.get(W3SCHOOLS);
    }

}




