package task_17;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.moodpanda_Selenide.FeedNavigationPage;
import pageObjects.moodpanda_Selenide.HomePage;
import pageObjects.moodpanda_Selenide.LoginPage;


public class Login_Test extends SelenideBaseTest {
    @BeforeTest
    public void precondition(){
        get(HomePage.class).clickGetStarted();
    }

    @Test(description = "Tests with incorrect credentials :: {email}, {password}", dataProvider = "login data")
    public void negativeDataTest(String email, String password,String emailError, String passwordError, String validationError){
        get(LoginPage.class)
                .enterEmail(email)
                .enterPassword(password)
                .clickLogin()
                .verifyEmailAlert(emailError)
                .verifyPasswordAlert(passwordError)
                .verifyValidationAlert(validationError);
    }

    @Test(description = "Tests with correct credentials :: {email}, {password}")
    public void positiveDataTest(){
        get(LoginPage.class)
                .enterEmail(properties.getProperty("validEmail"))
                .enterPassword(properties.getProperty("validPassword"))
                .clickLogin();
        get(FeedNavigationPage.class).verifyPageTitle().verifyPageUri();

    }

        @DataProvider(name = "login data")
    public Object [][] data(){
        return new Object[][] {
                {"test", "test", "Invalid email address", "", "One or more validation errors occurred."},
                {"test@test.ru", "", "", "Password is required", "One or more validation errors occurred."},
                {" ","test","", "Password is required", "One or more validation errors occurred."},
                {"test@test.ru", "123456789", "", "", "Your email or password is wrong"},
        };
    }
}
