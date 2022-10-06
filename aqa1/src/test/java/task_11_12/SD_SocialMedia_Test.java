package task_11_12;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.FooterPage;
import task_11_12.steps.LoginSteps;

public class SD_SocialMedia_Test extends BaseTest {

    @BeforeMethod
    @Step("Login and navigate to product page")
    @Parameters({"url", "username", "password"})
    public void preconditions(String url, String username, String password){
        get(LoginSteps.class).login(url, username, password);
    }

    @Test(description = "Go to social media in futer test")
    public void addRemoveAllProductsTest() {
        get(FooterPage.class)
                .goToTwitter().switchToNewTab().verifyPageTwitter().returnToHomeTab().verifyNewTabIsClosed()
                .goToFacebook().switchToNewTab().verifyPageFacebook().returnToHomeTab().verifyNewTabIsClosed();
                //.goToLinkedin().switchToNewTab().verifyPageTwitter().returnToHomeTab().verifyNewTabIsClosed();
    }
}
