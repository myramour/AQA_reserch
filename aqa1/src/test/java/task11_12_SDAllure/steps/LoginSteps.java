package task11_12_SDAllure.steps;

import pageObjects.baseObjects.BasePage;
import pageObjects.saucedemo.LoginPage;

public class LoginSteps extends BasePage {
    LoginPage loginPage = new LoginPage();

    public LoginSteps login(String url, String username, String password){
        loginPage.open(url).verifyLoginPageIsOpened().enterUsername(username).enterPassword(password).clickLoginBtn().verifyThatLoginPageIsClosed();
        return this;
    }
}
