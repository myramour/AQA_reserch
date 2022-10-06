package task_11_12;

import io.qameta.allure.Step;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.ProductPage;
import task_11_12.steps.LoginSteps;

public class SD_Product_Test extends BaseTest {

    @Parameters({"url", "username", "password"})
    @Step("Login by user: {username}")
    @Test
    public void productTest(String url, String username, String password){
        get(LoginSteps.class).login(url, username, password);
        get(ProductPage.class).verifyProductPage().verifyPageTitle().verifyFilterOptions();
    }
}
