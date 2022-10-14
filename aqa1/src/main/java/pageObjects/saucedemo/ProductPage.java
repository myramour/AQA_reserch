package pageObjects.saucedemo;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static driver.SimpleDriver.getWebDriver;

//описываем страницу с товарами
@Log4j
public class ProductPage extends BasePage {
    private final By title = By.xpath("//span[@class='title']");
    private final By getFilterOptions = By.tagName("option");
    private final By addToCartBtn = By.cssSelector("[id|=add-to-cart]");
    private final By logo = By.cssSelector(".app_logo");
    private final By filterBtn = By.className("product_sort_container");
    private final By allProducts = By.className("inventory_item_name");
    private final By allProductPrices = By.className("inventory_item_price");
    private By productSort = By.className("product_sort_container");

    //метод, который позволяет обратиться к форме товара
    private WebElement getElementProduct(String productName) {
        return getWebDriver().findElement(By.xpath("//*[@class='inventory_item_name' and text()='" + productName + "']/ancestor::div[@class='inventory_item']"));
    }

    private WebElement getProductPrice(String productName) {
        return getElementProduct(productName).findElement(By.className("inventory_item_price"));
    }

    private WebElement getAddToCartBtn(String productName) {
        return getElementProduct(productName).findElement(By.tagName("button"));
    }

    //тк uri уникальна для данной страницы, проверку можно вызвать при создании сущности
    public ProductPage() {
        verifyPageUri();
        verifyProductPageIsOpened();
        verifyPageTitle();
    }

    public ProductPage verifyProductPageIsOpened() {
       waitVisibilityOfElements(addToCartBtn, logo);
        return this;
    }

    //проверка Uri страницы
    public void verifyPageUri() {
        Assert.assertTrue(getWebDriver().getCurrentUrl().contains("inventory.html"));
    }


    public ProductPage verifyPageTitle() {
        Assert.assertEquals(getText(title), "PRODUCTS");
        return this;
    }

    public ProductPage verifyFilterOptions() {
        List<String> expectedData = Arrays.asList(
                "Name (A to Z)",
                "Name (Z to A)",
                "Price (low to high)",
                "Price (high to low)");
        Assert.assertEquals(getTexts(getFilterOptions), expectedData);
        return this;
    }

    public ProductPage addProductToBasket(String productName) { //добавить продукт по имени в корзину
        click(getAddToCartBtn(productName));
        return this;
    }

    public ProductPage addProductToBasketForCount(int count) {
        for (int i=0; i<count; i++) {
            click(addToCartBtn);
        }
        return this;
    }

    //для реализации с invocationCount
    public ProductPage addAllProductToBasket() {
        clickAll(addToCartBtn);
        return this;
    }

    public ProductPage clickFilterBtn() {
        click(filterBtn);
        return this;
    }

    public ProductPage selectByName(String value) {
        selectByText(productSort, value);
        return this;
    }

    public String getProductCost(String productName) { //получить стоимость продукта по имени
        return getText(getProductPrice(productName));
    }

    public ProductPage VerifySortNameAtoZ() {
        Assert.assertEquals(getTexts(allProducts),getSortAscendingByTexts(allProducts));
        return this;
    }

    public ProductPage VerifySortNameZtoA() {
        Assert.assertEquals(getTexts(allProducts),getSortDescendingByTexts(allProducts));
        return this;
    }

    public ProductPage VerifySortPriceLowToHigh() {
       Assert.assertEquals(getValues(allProductPrices),getSortAscendingByValues(allProductPrices));
        return this;
    }

    public ProductPage VerifySortPriceHighToLow() {
        Assert.assertEquals(getValues(allProductPrices),getSortDescendingByValues(allProductPrices));
        return this;
    }
}
