package task_11_12;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.HeaderPage;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductPage;
import task_11_12.steps.LoginSteps;
import task_11_12.steps.ProductStep;

public class SD_Header_Test extends BaseTest {

    @BeforeMethod
    @Step("Login and navigate to product page")
    @Parameters({"url", "username", "password"})
    public void preconditions(String url, String username, String password){
        get(LoginSteps.class).login(url, username, password);
    }

    @Test
    public void logOutTest() {
        get(HeaderPage.class).clickNavigationBtn().clickLogoutBtn();
        get(LoginPage.class).verifyLoginPage();
    }

    @Test
    public void aboutTest() {
        get(HeaderPage.class).clickNavigationBtn().clickAboutBtn().verifyAboutPageUri().returnBack();
        get(ProductPage.class).verifyProductPage();
    }

    @Test
    @Parameters("productName")
    public void allItemsTest(String productName) {
        get(ProductStep.class).product(productName);
        get(HeaderPage.class).clickNavigationBtn().clickAllItemsBtn();
        get(ProductPage.class).verifyProductPage();
    }

}
