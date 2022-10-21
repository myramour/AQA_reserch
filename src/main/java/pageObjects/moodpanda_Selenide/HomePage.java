package pageObjects.moodpanda_Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class HomePage {
    private final SelenideElement getStartedBtn = $(By.partialLinkText("Get started"));
    private final SelenideElement startImg = $("[alt^='MoodPanda home']");
    private final SelenideElement title = $(".is-size-1");
    private final SelenideElement signUpForFreeBtn = $(By.linkText(" Sign up for free"));
    private final SelenideElement appStoreBtn = $(By.xpath("//img[ contains(@alt, 'Apple')]"));
    private final SelenideElement googlePlayBtn = $(By.xpath("//img[ contains(@alt, 'Google')]"));

    public HomePage verifyPageUrl() {
        webdriver().shouldHave(urlContaining("moodpanda.com"));
        return this;
    }


    public HomePage verifyTitle(){
        this.title.shouldBe(Condition.exist).shouldBe(Condition.matchText("MoodPanda"));
        return this;
    }

    public HomePage verifyStartImg(){
        this.startImg.shouldBe(Condition.exist);
        return this;
    }

    public HomePage clickGetStarted(){
        this.getStartedBtn.click();
        this.getStartedBtn.should(disappear);
        return this;
    }

    public HomePage signUpForFree(){
        this.signUpForFreeBtn.click();
        return this;
    }

    public HomePage clickGoToAppStore(){
        this.appStoreBtn.click();
        return this;
    }

    public HomePage clickGToGooglePlay(){
        this.googlePlayBtn.click();
        return this;
    }

}
