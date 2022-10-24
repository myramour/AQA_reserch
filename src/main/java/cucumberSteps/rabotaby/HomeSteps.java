package cucumberSteps.rabotaby;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.annotations.Test;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.rabotaby_selenide.HomePage;
import pageObjects.rabotaby_selenide.SearchPage;

public class HomeSteps extends SelenideBaseTest {

    @Given("Open home page")
    public void openHomePage() {
        get(HomePage.class);
    }

    @When("I'm confirm with search region")
    public void confirmSearch() {
        get(HomePage.class).confirmRegion();
    }

    @And("I'm enter search data {string}")
    public void enterSearchData(String data) {
        get(HomePage.class).enterSearch(data);
    }

    @And("I'm click search button")
    public void clickSearch() {
        get(HomePage.class).clickSearch();
    }
}
