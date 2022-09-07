package lesson8;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.DynamicControlsPage;
import pageObjects.herokuapp.NavigationPage;
import static pageObjects.herokuapp.NavigationItems.*;

/** Dynamic Controls
 1-Найти чекбокс
 2- Нажать на кнопку
 3- Дождаться надписи “It’s gone”
 4- Проверить, что чекбокса нет
 5- Найти инпут
 6- Проверить, что он disabled
 7- Нажать на кнопку
 8- Дождаться надписи “It's enabled!”
 9- Проверить, что инпут enabled */

public class DynamicControls_test extends BaseTest {
    @BeforeMethod
    public void precondition() {
        new NavigationPage()
                .open();
    }
    @Test
    public void test1(){
        new NavigationPage()
                .navigateTo(DYNAMIC_CONTROLS);
        new DynamicControlsPage()
                .verifyCheckBoxIsFind()
                .clickRemove()
                .waitGoneMessage("It's gone!")
                .verifyCheckBoxIsNotExist()
                .verifyInputIsFind()
                .verifyInputIsDisabled()
                .clickEnable()
                .waitEnabledMessage("It's enabled!")
                .verifyInputIsEnabled();
    }
}
