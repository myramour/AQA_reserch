package pageObjects.moodpanda_Selenide;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class NavigationPage {

    private final SelenideElement signUpBtn = $(By.partialLinkText("Sign up"));
    private final SelenideElement loginBtn = $(By.partialLinkText("Login"));
    private final SelenideElement homeBtn = $(By.linkText( "Home"));
    private final SelenideElement whatBtn = $(By.partialLinkText( "What"));
    private final SelenideElement howBtn = $(By.partialLinkText( "How"));
    private final SelenideElement aboutBtn = $(By.partialLinkText( "About"));
    private final SelenideElement contactBtn = $(By.partialLinkText( "Contact"));

    public NavigationPage clickSignUp(){
        this.signUpBtn.click();
        return this;
    }

    public NavigationPage clickLogin(){
        this.loginBtn.click();
        return this;
    }

    public NavigationPage clickHome(){
        this.homeBtn.click();
        return this;
    }

    public NavigationPage clickWhat(){
        this.whatBtn.click();
        return this;
    }

    public NavigationPage clickHow(){
        this.howBtn.click();
        return this;
    }

    public NavigationPage clickAbout(){
        this.aboutBtn.click();
        return this;
    }

    public NavigationPage clickContact(){
        this.contactBtn.click();
        return this;
    }



}
