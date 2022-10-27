package pageObjects.mpandaSelenideCucumber;

import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

@Log4j
public class NavigationPage {

    private SelenideElement getNavigationLink(String linkText) {
        return $(By.partialLinkText(linkText));
    }


    public NavigationPage clickNavigationItem(String linkText) {
        log.debug("click navigation item");
        getNavigationLink(linkText).click();
        return this;
    }

    public NavigationPage verifyPageUri(String uri) {
        log.debug("verify page uri");
        webdriver().shouldHave(urlContaining(uri));
        return this;
    }

}
