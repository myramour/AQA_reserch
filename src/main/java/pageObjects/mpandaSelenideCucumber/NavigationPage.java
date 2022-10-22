package pageObjects.mpandaSelenideCucumber;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class NavigationPage {

    private SelenideElement getNavigationLink(String linkText) {
        return $(By.partialLinkText(linkText));
    }


    public NavigationPage clickNavigationItem(String linkText) {
        getNavigationLink(linkText).click();
        return this;
    }

    public NavigationPage verifyPageUri(String uri) {
        webdriver().shouldHave(urlContaining(uri));
        return this;
    }

}
