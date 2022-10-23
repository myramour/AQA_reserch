package task_17;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.moodpanda_Selenide.FeedNavigationPage;
import pageObjects.moodpanda_Selenide.LoginPage;
import pageObjects.moodpanda_Selenide.YourDashboardPage;
import pageObjects.moodpanda_Selenide.navigation.NavigationPage;

public class YourDashboard_Test extends SelenideBaseTest {
    @BeforeTest
    public void precondition() {
        get(NavigationPage.class).clickLogin();
        get(LoginPage.class)
                .enterEmail(properties.getProperty("validEmail"))
                .enterPassword(properties.getProperty("validPassword")).clickLogin();
    }

    @Test
    public void test(){
        get(FeedNavigationPage.class).clickDashboard();
        get(YourDashboardPage.class).createPost("23 Oct 2022","Hello",4);
    }

}
