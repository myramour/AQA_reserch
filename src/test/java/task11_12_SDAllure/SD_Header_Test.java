package task11_12_SDAllure;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.HeaderPage;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductPage;
import task11_12_SDAllure.steps.LoginSteps;
import task11_12_SDAllure.steps.ProductStep;

public class SD_Header_Test extends BaseTest {

    @BeforeMethod
    @Step("Login and navigate to product page")
    @Parameters({"url", "username", "password"})
    public void preconditions(String url, String username, String password){
        get(LoginSteps.class).login(url, username, password);
    }

    @Test(description = "Test for verify log out")
    public void logOutTest() {
        get(HeaderPage.class).clickNavigationBtn().clickLogoutBtn();
        get(LoginPage.class).verifyLoginPageIsOpened();
    }

    @Test(description = "Test for verify open about page")
    public void aboutTest() {
        get(HeaderPage.class).clickNavigationBtn().clickAboutBtn().verifyAboutPageUri().returnBack();
        get(ProductPage.class).verifyProductPageIsOpened();
    }

    @Test(description = "Test for verify return to the Product page after click AllItems")
    public void allItemsTest() {
        get(ProductStep.class).product(properties.getProperty("productName1"));
        get(HeaderPage.class).clickNavigationBtn().clickAllItemsBtn();
        get(ProductPage.class).verifyProductPageIsOpened();
    }

}
