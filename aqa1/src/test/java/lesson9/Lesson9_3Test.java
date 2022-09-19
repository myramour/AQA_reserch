package lesson9;

import org.testng.annotations.*;
import pageObjects.baseObjects.BaseTest;
import pageObjects.saucedemo.LoginPage;
import testNgUtils.Listener;

@Listeners(Listener.class) // если прописать тут,то будет работать только с указанным классом, если в xml, то там можно для всего сюта

public class Lesson9_3Test extends BaseTest {
    @Parameters("url") //прописываем параметр, котрый пробрасываем

    @BeforeMethod
    public void preconditions(@Optional("https://www.google.com/") String url) { //емди значение параметра пустое, то будет использовано опциональное
        new LoginPage()
                .open(url);
    }

    @Test(dataProvider = "login data")
    public void login(String username, String password) {
        new LoginPage()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginBtn();
    }

    //создание дата провайдер
    @DataProvider(name = "login data")
    public Object[][] getData() {
        return new Object[][]{ //первая - общий массив, вторая элемент массива, по сути массив в массиве
                {"standard_user", "secret_sauce"}, //данные номер 1
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                // [["standard_user", "secret_sauce"],["locked_out_user", "secret_sauce"],["problem_user", "secret_sauce"]] - так он возращает данные
        };
    }
}
