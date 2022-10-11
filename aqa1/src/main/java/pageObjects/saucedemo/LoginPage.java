package pageObjects.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;


public class LoginPage extends BasePage {
    private final By username = By.id("user-name");
    private final By password = By.id("password");
    private final By loginBtn = By.id("login-button");
    private final By message = By.xpath("//h3[@data-test='error']");

    //pattern fluent chain of invocations
    //методы возвращают ссылку на самого себя - те могут быть сигнатурами LoginPage
    // Используем для того, чтобы в тестах получать доступ к методам класса (продлжать вызывать методы на основе предыдущих результатов, делать запись в одну строку).
    public LoginPage openWithUrl() { // лучще выносить урлы на уроани параметров, что делает тесты более гибкими (см запись ниже)
        load("https://www.saucedemo.com/"); //вместо driver.get
        return this; //this- каждый из методов возвращает ссылку на данный объект
    }

    //для примера с параметризированными тестами
    public LoginPage open(String url) {//урл прописываем в xml файле
        load(url);
        return this;
    }

    public LoginPage open() {
        load();
        return this;
    }

    public LoginPage verifyLoginPage() {
        Assert.assertTrue(waitVisibilityOfElements(username, password, loginBtn));
        return this;
    }

    public LoginPage enterUsername(String username) {
        enter(this.username, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        enter(this.password, password);
        return this;
    }

    public LoginPage enterUsername() {
        enter(this.username, properties.getProperty("username"));
        return this;
    }

    public LoginPage enterPassword() {
        enter(this.password,  properties.getProperty("password"));
        return this;
    }

    public LoginPage verifyThatLoginPageIsClosed() {
        Assert.assertTrue(elementNotExist(loginBtn));
      //  wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn)); - долгое ожидание, потому что Imlicitу драйвера все равно ждет что элемент появится
        return this;
    }

    public LoginPage clickLoginBtn() {
        click(loginBtn);
        return this;
    }

    public LoginPage verifyErrorMessage() {
        Assert.assertEquals(getText(message), "Epic sadface: Sorry, this user has been locked out.");
        return this;
    }

    //для dataProvider
    public LoginPage verifyErrorMessage(String errorMessage) {
        Assert.assertEquals(getText(message), errorMessage);
        return this;
    }
}
