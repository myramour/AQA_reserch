package cucumberSteps.rabotaby;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.baseObjects.SelenideBaseTest;
import pageObjects.mpandaSelenideCucumber.NavigationPage;
import pageObjects.rabotaby_selenide.HomePage;
import pageObjects.rabotaby_selenide.SearchPage;

public class HomeSteps extends SelenideBaseTest {

    @Given("Open home page")
    public void openHomePage() {
        get(HomePage.class);
    }

    @Given("Open home page and enter search data {string} and click search")
    public void openHomePageAndEnter(String data) {
        get(HomePage.class).enterSearch(data).clickSearch();
    }

    @When("I'm confirm with search region")
    public void confirmSearch() {
        get(HomePage.class).confirmRegion();
    }

    @When("I'm click change search region")
    public void changeSearch() {
        get(HomePage.class).changeRegion();
    }

    @When("I'm click to change region button")
    public void clickChange() {
        get(HomePage.class).changeRegion();
    }

    @And("I'm click on region item {string}")
    public void clickOnMenuItem(String string) {
        get(HomePage.class).clickNavigationItem(string);
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
