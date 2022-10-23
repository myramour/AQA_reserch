package pageObjects.moodpanda_Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class FeedNavigationPage {
    private final SelenideElement title = $(By.xpath("//div[contains(@class,'content-pane')]/child::div[contains(@class, 'is-medium')]//p[@class='subtitle']"));
    private final SelenideElement homeBtn = $(By.partialLinkText("Home"));
    private final SelenideElement diaryBtn = $(By.partialLinkText("Your diary"));
    private final SelenideElement dashboardBtn = $(By.partialLinkText("Your dashboard"));
    private final SelenideElement patronsBtn = $(By.partialLinkText("Patrons"));


    public FeedNavigationPage verifyPageUri() {
        webdriver().shouldHave(urlContaining("/feed/global"));
        return this;
    }

    public FeedNavigationPage verifyPageTitle() {
        this.title.shouldBe(Condition.visible).shouldHave(Condition.matchText("This is a community. Be kind."));

        return this;
    }

    public FeedNavigationPage clickHome(){
        this.homeBtn.click();
        return this;
    }

    public FeedNavigationPage clickDiary(){
        this.diaryBtn.click();
        return this;
    }

    public FeedNavigationPage clickDashboard(){
        this.dashboardBtn.click();
        return this;
    }

    public FeedNavigationPage clickPatron(){
        this.patronsBtn.click();
        return this;
    }

}
