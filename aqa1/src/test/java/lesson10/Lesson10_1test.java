package lesson10;

import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.LoginPage;
import pageObjects.saucedemo.ProductPage;

/**предача параметров через системные переменные */
public class Lesson10_1test extends BaseTest {
    @Test
    public void login() {

        new LoginPage()
                .open()
                .enterUsername(System.getProperty("username"))
                .enterPassword(System.getProperty("password"))
                .clickLoginBtn()
                .verifyThatLoginPageIsClosed();
        new ProductPage().verifyPageTitle();
    }
}
//через командную строку mvn clean test -DsuiteXmlFile=Lesson_10 -Dusername=standard_user -Dpassword=secret_sauce