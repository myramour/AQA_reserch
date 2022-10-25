package cucumberSteps.rabotaby;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.rabotaby_selenide.SearchPage;

import static com.codeborne.selenide.Selenide.webdriver;

public class SearchSteps extends SelenideBaseTest {


    @Then("I'm go to search page and check search criteria {string}")
    public void checkSearch(String text) {
        get(SearchPage.class).verifySearchCriteria(text);
    }

    @And("I'm check search result count more {int}")
    public void checkSearchCount(int contForFeature) {
        get(SearchPage.class).verifySearchCount(contForFeature);
    }

    @Then("I'm return to Home Page")
    public void returnHome() {
        get(SearchPage.class).returnHome();
    }

    @After
    public void clearData() {
        webdriver().driver().close();
    }
}
