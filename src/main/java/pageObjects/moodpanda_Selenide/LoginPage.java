package pageObjects.moodpanda_Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageObjects.baseObjects.SelenideBasePage;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class LoginPage extends SelenideBasePage {

    private final SelenideElement email = $("[type='text']");
    private final SelenideElement password = $("[type='password']");
    private final SelenideElement loginBtn = $(By.xpath("//button[@class]"));
    private final SelenideElement emailAlert = $(By.xpath("//div[contains(text(), 'email')]"));
    private final SelenideElement passwordAlert = $(By.xpath("//div[contains(text(), 'Password')]"));
    private final SelenideElement validationAlert = $(By.xpath(" //div[contains(text(), 'validation')]"));
    private final SelenideElement dontAccountBtn = $(By.partialLinkText("Don't have"));
    private final SelenideElement forgotBtn = $(By.partialLinkText("Forgot password"));
    private final SelenideElement cantLoginBtn = $(By.partialLinkText("I can't login"));

    public LoginPage() {
        verifyPageUri();
    }
    public LoginPage verifyPageUri() {
        webdriver().shouldHave(urlContaining("login"));
        return this;
    }

    public LoginPage enterEmail(String email) {
        enter(this.email, email);
        return this;
    }

    public LoginPage enterPassword(String password) {
        enter(this.password, password);
        return this;
    }

    public LoginPage clickLogin() {
       click(loginBtn);
        return this;
    }

    public LoginPage clickDontAccount() {
       click(dontAccountBtn);
        return this;
    }

    public LoginPage clickForgotPassword() {
       click(forgotBtn);
        return this;
    }

    public LoginPage clickCantLogin() {
       click(cantLoginBtn);
        return this;
    }

    public LoginPage verify() {
        click(cantLoginBtn);
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
