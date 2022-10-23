package task_17;

import org.testng.annotations.Test;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.moodpanda_Selenide.navigation.LinksEnum;
import pageObjects.moodpanda_Selenide.navigation.NavigationPage;
import static pageObjects.moodpanda_Selenide.navigation.LinksEnum.*;

public class Navigation_Test  extends SelenideBaseTest {

    @Test(description = "Go to all navigation page and verify then all tab was opened ")
    public void navigationTest() {
        get(NavigationPage.class)
                .goToItemAndVerify(What)
                .goToItemAndVerify(How)
                .goToItemAndVerify(About)
                .goToItemAndVerify(Contact)
                .goToItemAndVerify(Home);
    }
}
