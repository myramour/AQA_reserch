package pageObjects.moodpanda_Selenide.navigation;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class NavigationPage {
    private final SelenideElement signUpBtn = $(By.partialLinkText("Sign up"));
    private final SelenideElement loginBtn = $(By.partialLinkText("Login"));
    private final SelenideElement title = $(By.xpath("//p[contains(@class,'title is-size-1')]"));

    public NavigationPage clickSignUp(){
        this.signUpBtn.click();
        return this;
    }

    public NavigationPage clickLogin(){
        this.loginBtn.click();
        return this;
    }

    public NavigationPage clickNavigationItem(LinksEnum links) {
        links.getElement().click();
        return this;
    }

    public NavigationPage verifyPageUri(String uri) {
        webdriver().shouldHave(urlContaining(uri));
        return this;
    }

    public NavigationPage verifyTitle(String title) {
        this.title.shouldBe(Condition.visible).shouldHave(Condition.matchText(title));
        return this;
    }

    public NavigationPage goToItemAndVerify(LinksEnum sort) {
        switch (sort) {
            case Home:
               clickNavigationItem(LinksEnum.Home);
               verifyPageUri("moodpanda.com");
               verifyTitle("MoodPanda");
                break;
            case What:
                clickNavigationItem(LinksEnum.What);
                verifyPageUri("monitor-your-mood");
                verifyTitle("Monitor your mood");
                break;
            case How:
                clickNavigationItem(LinksEnum.How);
                verifyPageUri("how-to-use-a-mood-diary");
                verifyTitle("How to use a mood diary");
                break;
            case About:
                clickNavigationItem(LinksEnum.About);
                verifyPageUri("about");
                verifyTitle("About MoodPanda");
                break;
            case Contact:
                clickNavigationItem(LinksEnum.Contact);
                verifyPageUri("contact");
                break;
        }
        return this;
    }
}
