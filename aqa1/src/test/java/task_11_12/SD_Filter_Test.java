package task_11_12;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.ProductPage;
import task_11_12.steps.LoginSteps;
import task_11_12.steps.ProductStep;

public class SD_Filter_Test extends BaseTest {

    @Test
    @Description("Sorting of products test")
    @Step("Login and verify Product Page")
    @Parameters({"url", "username", "password"})
    public void SortFilterTest(String url, String username, String password){

        get(LoginSteps.class).login(url, username, password);

        get(ProductPage.class)
                .clickFilterBtn().selectByName("Name (A to Z)").sortNameAtoZ()
                .selectByName("Name (Z to A)").sortNameZtoA()
                .selectByName("Price (low to high)").sortPriceLowToHigh()
                .selectByName("Price (high to low)").sortPriceHighToLow();

    }
}
