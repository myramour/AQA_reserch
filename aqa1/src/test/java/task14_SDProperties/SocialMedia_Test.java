package task14_SDProperties;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.FooterPage;
import task14_SDProperties.steps.LoginSteps;

public class SocialMedia_Test extends BaseTest {

    @BeforeMethod
    public void preconditions(){
        get(LoginSteps.class).login();
    }

    @Test(description = "Test go to Twitter in Footer page")
    public void TwitterTest() {
        get(FooterPage.class)
                .goToTwitter().switchToNewTab().verifyPageTwitter().returnToHomeTab().verifyNewTabIsClosed();
    }

    @Test(description = "Test go to Facebook in Footer page")
    public void FacebookTest() {
        get(FooterPage.class)
                .goToFacebook().switchToNewTab().verifyPageFacebook().returnToHomeTab().verifyNewTabIsClosed();
    }

    @Test(description = "Test go to Linkedin in Footer page", enabled = false)
    public void LinkedinTest() {
        get(FooterPage.class)
                .goToLinkedin().switchToNewTab().verifyPageTwitter().returnToHomeTab().verifyNewTabIsClosed();
    }
}
