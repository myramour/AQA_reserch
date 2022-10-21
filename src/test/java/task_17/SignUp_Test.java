package task_17;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.moodpanda_Selenide.NavigationPage;
import pageObjects.moodpanda_Selenide.SignUpPage;
import pageObjects.moodpanda_Selenide.lombok.SignUp;

public class SignUp_Test extends SelenideBaseTest {

    @BeforeTest
    public void precondition() {
        get(NavigationPage.class).clickSignUp();

    }
    @Test(description = "Sign up tests with an empty one of the required fields ", dataProvider = "data")
    public void negativeDataTest(String firstname, String lastname, String email, String password, Boolean check, String alert, String required) {

        SignUp signUp = new SignUp.SignUpBuilder()
                .withFirstName(firstname)
                .withLastName(lastname)
                .withEmail(email)
                .withPassword(password)
                .withCheckbox(check).create();

        get(SignUpPage.class).verifyPageUri().checkInformingText().enterData(signUp).clickSignUpMe();
        if (signUp.getCheckbox()==false) {
            get(SignUpPage.class).checkAlertIfCheckboxUncheck().checkAlertValidation(alert);
        } else if(signUp.getCheckbox()==true) {
            get(SignUpPage.class).checkAlertValidation(alert).checkAlertFiledRequired(required);
        }
    }

    @Test(description = "Sign up tests with all required fields")
    public void positiveDataTest() {

        SignUp signUp = new SignUp.SignUpBuilder()
                .withFirstName(properties.getProperty("firstName"))
                .withLastName(properties.getProperty("lastNameInitial"))
                .withEmail(properties.getProperty("email"))
                .withPassword(properties.getProperty("password"))
                .withCheckbox(true).create();

        get(SignUpPage.class).verifyPageUri().checkInformingText()
                .enterData(signUp).clickSignUpMe()
                .checkAlertValidation("You already have an account, or this account cannot be created");
    }

    @DataProvider(name = "data")
    public Object [][] data(){
        return new Object[][] {
                {"FirstName", "L", "test@test.ru", "123456789987", false, "One or more validation errors occurred.", ""},
               // {"FirstName", "L", "test@test.ru", "123456789987", true, "You already have an account, or this account cannot be created", ""},
                {"", "L", "test@test.ru", "123456789987", true, "One or more validation errors occurred.", "First name is required"},
                {"FirstName", "", "test@test.ru", "123456789987", true, "One or more validation errors occurred.", "Last name initial is required"},
                {"FirstName", "L", "", "123456789987", true, "One or more validation errors occurred.", "Email address is required"},
                {"FirstName", "L", "test@test.ru", "", true, "One or more validation errors occurred.", "Password is required"},
                {"", "", "", "", false, "One or more validation errors occurred.", ""},
        };
    }
}
