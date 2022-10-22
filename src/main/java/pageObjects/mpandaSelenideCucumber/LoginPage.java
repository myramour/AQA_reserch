package pageObjects.mpandaSelenideCucumber;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement email = $(By.cssSelector("[type='text']"));
    private SelenideElement password = $(By.cssSelector("[type='password']"));
    private SelenideElement loginBtn = $(By.xpath("//button[@class]"));
    private SelenideElement notification = $(By.cssSelector("[class^=notification]"));


    public LoginPage enterPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public LoginPage enterEmail(String email) {
        this.email.sendKeys(email);
        return this;
    }

    public LoginPage clickLogin() {
        loginBtn.click();
        return this;
    }

    public LoginPage verifyErrorMessage(String message) {
        notification.should(Condition.text(message));
        return this;
    }
}
