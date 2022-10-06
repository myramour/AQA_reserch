package task_11_12.steps;

import pageObjects.baseObjects.BasePage;
import pageObjects.saucedemo.HeaderPage;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductPage;

public class ProductStep extends BasePage {
    ProductPage productPage = new ProductPage();
    HeaderPage headerPage = new HeaderPage();


    public ProductStep product(String name){
        productPage.verifyProductPage().verifyPageTitle().verifyFilterOptions().addProductToBasket(name);
        headerPage.clickBasketBtn();
        return this;
    }
}
