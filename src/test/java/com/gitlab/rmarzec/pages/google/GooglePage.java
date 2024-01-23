package com.gitlab.rmarzec.pages.google;

import com.gitlab.rmarzec.framework.utils.WaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.gitlab.rmarzec.constants.Urls.GOOGLE;

public class GooglePage {

    WebDriver driver;
    WaitFactory wait;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitFactory(driver);
    }

    private final By acceptCookiesButton = By.id("W0wltc");
    private final By searchInput = By.className("gLFyf");
    private final By feelingLuckyButton = By.name("btnI");

    public void clickAcceptCookiesButton() {
        wait.waitForVisibility(acceptCookiesButton).click();
    }

    public void clickFeelingLuckyButton() {
        wait.waitForVisibility(feelingLuckyButton).click();
    }

    public void inputSearchedText(String text) {
        wait.waitForVisibility(searchInput).sendKeys(text);
    }

    public void openHomePage() {
        driver.get(GOOGLE);
    }

}




