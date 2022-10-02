package lesson8;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.AddRemoveElementsPage;
import pageObjects.herokuapp.NavigationPage;

import static pageObjects.herokuapp.NavigationItems.ADD_REMOVE_ELEMENTS;

/**
 * - Кликнуть один раз Add Element
 * - Проверить что кнопка Delete добавилась
 * - Кликнуть еще 5 раз Add Element
 * - Проверить что отображается верное количество кнопок Delete
 * - Очистить окно от кнопок Delete и проверить что эта кнопка не отображается после очистки
 */

public class AddRemove_test extends BaseTest {
    @Parameters("url")
    @BeforeMethod
    public void precondition(String url) {
        new NavigationPage()
                .open(url);
    }

    @Test(priority = 1, description = "add and remove test", invocationCount = 2)
    public void test1() {
        new NavigationPage()
                .navigateTo(ADD_REMOVE_ELEMENTS);
        new AddRemoveElementsPage()
                .clickAddElement()
                .verifyAddBtnDelete()
                .clickAddElement()
                .clickAddElement()
                .clickAddElement()
                .clickAddElement()
                .clickAddElement()
                .verifyQuantityDeleteBtn()
                .clickDelete()
                .clickDelete()
                .clickDelete()
                .clickDelete()
                .clickDelete()
                .clickDelete()
                .verifyDeleteBtnNotExist();
    }
}
