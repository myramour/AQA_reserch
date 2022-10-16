package task14_SDProperties;

import io.qameta.allure.Step;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.BasketPage;
import pageObjects.saucedemo.HeaderPage;
import pageObjects.saucedemo.ProductPage;
import task14_SDProperties.steps.LoginSteps;
import task14_SDProperties.steps.ProductStep;


public class Cart_Test extends BaseTest {

    @BeforeMethod
    public void preconditions(){
        get(LoginSteps.class).login();
    }

    @Test(description = "Test add and remove all products")
    public void addRemoveAllProductsTest() {
        get(ProductStep.class).addAllProduct().removeAllProduct();
        get(BasketPage.class).verifyBasketIsEmpty();
    }

    @Test(description = "Test to add and remove products by name")
    public void addRemoveProductByNameTest(){
        get(ProductStep.class).addProductByName(properties.getProperty("productName3"));
        get(BasketPage.class)
                .verifyQuantityProductInCart(properties.getProperty("productName3"))
                .clickContinueShopping();
        get(ProductStep.class).addProductByName(properties.getProperty("productName2"));
        get(BasketPage.class)
                .removeProduct(properties.getProperty("productName3"))
                .removeProduct(properties.getProperty("productName2"))
                .verifyBasketIsEmpty();
    }

    @Test(description = "Test to add and remove products by count")
    public void addRemoveProductByCount(){
        get(ProductStep.class).addProductByCount(5);
        get(BasketPage.class).clickContinueShopping();
        get(ProductStep.class).addProductByCount(1).removeProductByCount(6);
        get(BasketPage.class).verifyBasketIsEmpty();
    }
    @Test(dataProvider = "product data", description = "Test to add and remove products with DataProvider ")
    public void addRemoveProductsTest(String name) {
        get(ProductPage.class).addProductToBasket(name);
        get(HeaderPage.class).clickBasketBtn();
        get(BasketPage.class)
                .verifyTitle()
                .removeProduct(name)
                .verifyBasketIsEmpty()
                .clickContinueShopping();
    }

    @DataProvider(name = "product data")
    public Object[][] getData() {
        return new Object[][]{
                {"Sauce Labs Backpack"},
                {"Sauce Labs Bike Light"},
                {"Sauce Labs Bolt T-Shirt"},
                {"Test.allTheThings() T-Shirt (Red)"}
        };
    }
}
