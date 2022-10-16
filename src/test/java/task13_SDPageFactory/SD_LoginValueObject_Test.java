package task13_SDPageFactory;

import org.testng.annotations.*;
import pageFactory.saucedemo.ProductPage;
import pageFactory.saucedemo.entity.Login_VObject;
import pageObjects.baseObjects.BaseTest;
import pageFactory.saucedemo.LoginPage;

/** Implementation of patterns Page Factory, Chain of invocation, Value Object, Data Provider, Loadable Page */

public class SD_LoginValueObject_Test extends BaseTest {
    LoginPage loginPage;
    Login_VObject login_VObject;

    @BeforeMethod

    public void precondition(){
        loginPage= new LoginPage();
        loginPage.open();
    }

    @Test(description = "Test(Value Object) with standard user data {username}, {password}")
    public void standardUserTest(){

        login_VObject = new Login_VObject(){{
            setUsername(properties.getProperty("username"));
            setPassword(properties.getProperty("password"));
        }};
        loginPage.enterData(login_VObject).clickLoginBtn();
        new ProductPage().verifyProductPageIsOpened();
    }

    @Test(description = "Login test (Value Object Pattern) with all user data :: {username}, {password}", dataProvider = "user data")
    public void AllUserLoginTest(String username, String password, String errorMessage) {

        login_VObject = new Login_VObject() {{
            setUsername(username);
            setPassword(password);
        }};
        loginPage.enterData(login_VObject).clickLoginBtn();
        if (errorMessage.isEmpty()) {
            new ProductPage().verifyProductPageIsOpened();
        } else {
            loginPage.verifyErrorMessage(errorMessage);
        }
    }

    @DataProvider(name = "user data")
    public Object [][] data(){
        return new Object[][] {
                {"standard_user", "secret_sauce", ""},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"problem_user", "secret_sauce", ""},
                {"performance_glitch_user", "secret_sauce", ""},
        };
    }
}
