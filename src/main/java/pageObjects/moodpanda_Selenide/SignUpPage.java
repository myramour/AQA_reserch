package pageObjects.moodpanda_Selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import pageObjects.moodpanda_Selenide.lombok.SignUp;


import java.util.Objects;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class SignUpPage {
    private final SelenideElement firstName = $("[placeholder='Your first name']");
    private final SelenideElement lastNameInitial = $("[placeholder=\"e.g. 'S'\"]");
    private final SelenideElement email = $("[placeholder='Your email address']");
    private final SelenideElement password = $("[type='password']");
    private final SelenideElement checkbox = $("[type='checkbox']");
    private final SelenideElement signUpBtn = $(By.xpath("//*[@class='box']//button"));
    private final SelenideElement signMeUpBtn = $(By.xpath("//*[@class='box']//span[contains(text(),'Sign me up')]"));

    private final SelenideElement informText=$(By.xpath("//div/small"));
    private final SelenideElement alertOver16 = $("[class$='small']");
    private final SelenideElement alertValidation = $("[class$='notification is-danger']");
    private final SelenideElement alertFiledRequired = $("[class$='is-small']");

    public SignUpPage verifyPageUri() {
        webdriver().shouldHave(urlContaining("signup"));
        return this;
    }

    public SignUpPage enterFirstName(String firstname) {
        this.firstName.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        this.firstName.sendKeys(Keys.chord(Keys.DELETE));
        this.firstName.shouldBe(visible).sendKeys(firstname);
        return this;
    }

    public SignUpPage enterLastNameInitial(String lastname) {
        this.lastNameInitial.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        this.lastNameInitial.sendKeys(Keys.chord(Keys.DELETE));
        this.lastNameInitial.shouldBe(visible).sendKeys(lastname);
        return this;
    }
    public SignUpPage enterEmail(String email) {
        this.email.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        this.email.sendKeys(Keys.chord(Keys.DELETE));
        this.email.shouldBe(visible).sendKeys(email);
        return this;
    }

    public SignUpPage enterPassword(String password) {
       this.password.sendKeys(Keys.chord(Keys.COMMAND, "a"));
       this.password.sendKeys(Keys.chord(Keys.DELETE));
       this.password.shouldBe(visible).sendKeys(password);
       return this;
    }


    public SignUpPage enterData(SignUp signUp) {
        enterFirstName(signUp.getFirstName());
        enterLastNameInitial(signUp.getLastName());
        enterEmail(signUp.getEmail());
        enterPassword(signUp.getPassword());
        if (signUp.getCheckbox()==true && checkbox.isSelected()==false) {
                checkbox.click();
        }else if(signUp.getCheckbox()==false && checkbox.isSelected()==true) {
            checkbox.click();
        }
        signUpBtn.click();
        return this;
    }

    public SignUpPage clickSignUpMe(){
        this.signMeUpBtn.click();
        return this;
    }

    public SignUpPage checkAlertIfCheckboxUncheck() {
        this.alertOver16.shouldBe(matchText("You must be over 16 and agree to our terms to use MoodPanda."));
        return this;
    }

    public SignUpPage checkAlertValidation(String alert) {
        this.alertValidation.shouldBe(matchText(alert));
        return this;
    }

    public SignUpPage checkAlertFiledRequired(String alert) {
        if (alertFiledRequired.exists()) {
            this.alertFiledRequired.shouldBe(matchText(alert));
        }
        return this;
    }

    public SignUpPage checkInformingText() {
            informText.shouldBe(matchText("We use essential cookies to allow users to login."));
        return this;
    }
}
