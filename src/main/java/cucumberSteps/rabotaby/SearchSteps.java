package cucumberSteps.rabotaby;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.rabotaby_selenide.SearchPage;

public class SearchSteps extends SelenideBaseTest {

    @Then("I'm go to search page and check search criteria {string}")
    public void checkSearch(String text) {
        get(SearchPage.class).verifySearchCriteria(text);
    }

    @And("I'm check search result count more {int}")
    public void checkSearchCount(int contForFeature) {
        get(SearchPage.class).verifySearchCount(contForFeature);
    }
}
