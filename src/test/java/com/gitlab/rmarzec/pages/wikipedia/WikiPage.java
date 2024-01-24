package com.gitlab.rmarzec.pages.wikipedia;

import com.gitlab.rmarzec.framework.utils.WaitFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.gitlab.rmarzec.constants.Urls.WIKI;

public class WikiPage {

    WebDriver driver;
    WaitFactory wait;

    public WikiPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitFactory(driver);
    }

    private final By languageButton = By.id("p-lang-btn");
    private final By languageListContainer = By.xpath(".//*[@class='row uls-language-list uls-lcd']");
    private final By language = By.tagName("li");
    private final By href = By.tagName("a");

    public void clickOnLanguageButton() {
        wait.waitForVisibility(languageButton).click();
    }

    public List<WebElement> getAllLanguages() {
        return wait.waitForVisibility(languageListContainer).findElements(language);
    }

    public WebElement getSingleLanguage(List<WebElement> languageList, String language) {
        return languageList.stream().filter(lang -> lang.getText().equals(language)).findFirst().orElse(null);
    }

    public String getHref(WebElement element) {
        return wait.waitForVisibility(element.findElement(href)).getAttribute("href");
    }

    public void printLanguages(List<WebElement> webElements) {
        for (WebElement language : webElements) {
            if (language.getText().equals("English")) {
                System.out.println(language.getText() + " Url: " + getHref(language));
            }
            System.out.println(language.getText());
        }
    }

    public void openHomePage() {
        driver.get(WIKI);
    }

}




