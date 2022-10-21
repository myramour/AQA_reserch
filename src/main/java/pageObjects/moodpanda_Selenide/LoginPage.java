package pageObjects.moodpanda_Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class LoginPage {

    private final SelenideElement email = $("[type='text']");
    private final SelenideElement password = $("[type='password']");
    private final SelenideElement loginBtn = $(By.xpath("//button[@class]"));
    private final SelenideElement emailAlert = $(By.xpath("//div[contains(text(), 'email')]"));
    private final SelenideElement passwordAlert = $(By.xpath("//div[contains(text(), 'Password')]"));
    private final SelenideElement validationAlert = $(By.xpath(" //div[contains(text(), 'validation')]"));
    private final SelenideElement dontAccountBtn = $(By.partialLinkText("Don't have"));
    private final SelenideElement forgotBtn = $(By.partialLinkText("Forgot password"));
    private final SelenideElement cantLoginBtn = $(By.partialLinkText("I can't login"));

    public LoginPage verifyPageUri() {
        webdriver().shouldHave(urlContaining("login"));
        return this;
    }

    public LoginPage enterEmail(String email) {
        this.email.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        this.email.sendKeys(Keys.chord(Keys.DELETE));
        this.email.sendKeys(email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        this.password.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        this.password.sendKeys(Keys.chord(Keys.DELETE));
        this.password.sendKeys(password);
        return this;
    }

    public LoginPage clickLogin() {
        this.loginBtn.click();
        return this;
    }

    public LoginPage clickDontAccount() {
        this.dontAccountBtn.click();
        return this;
    }

    public LoginPage clickForgotPassword() {
        this.forgotBtn.click();
        return this;
    }

    public LoginPage clickCantLogin() {
        this.cantLoginBtn.click();
        return this;
    }

    public LoginPage verify() {
        this.cantLoginBtn.click();
        return this;
    }

    public LoginPage verifyEmailAlert(String errorMessage) {
        if (emailAlert.exists()) {
            this.emailAlert.shouldBe(matchText(errorMessage));
        }
        return this;
    }

    public LoginPage verifyPasswordAlert(String errorMessage) {
        if (passwordAlert.exists()) {
            this.passwordAlert.shouldBe(matchText(errorMessage));
        }
        return this;
    }

    public LoginPage verifyValidationAlert(String errorMessage) {
        if (validationAlert.exists()) {
            this.validationAlert.shouldBe(matchText(errorMessage));
        }
        return this;
    }
}
