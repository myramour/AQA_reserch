package lesson8;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.DropdownListPage;
import pageObjects.herokuapp.NavigationPage;

import static pageObjects.herokuapp.NavigationItems.DROPDOWN_LIST;

/**
 * -Выбрать опцию по индексу и проверить что опция выбрана
 * -Выбрать опцию по имени и проверить что опция выбрана
 */
public class DropdownList_test extends BaseTest {

    @Parameters("url")
    @BeforeMethod
    public void precondition(String url) {
        new NavigationPage()
                .open(url);
    }

    @Test(priority = 4, description = " drop down list test")
    public void test1() {
        new NavigationPage().navigateTo(DROPDOWN_LIST);
        new DropdownListPage()
                .selectByName("Option 1")
                .verifyFirstOptionIsSelected()
                .selectByIndex(2)
                .verifySecondOptionIsSelected();
    }
}

