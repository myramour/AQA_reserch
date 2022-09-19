package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

/** Провести тестирование поля Number с использованием классов эквивалентности */

public class InputsPage extends BasePage {

    private final WebElement input = driver.findElement(By.tagName("input"));

    public InputsPage sendKeysAndVerify(String value){
        input.clear();
        input.sendKeys(value);
        Assert.assertEquals(value,input.getAttribute("value"));
        return this;
    }


    public InputsPage setUpAndVerify(int startValue,int countIteration){
        //сюда добавить input.sendKeys("value"); и в переменных метода String value тогда верхнее можно убрать
        String valueFromInput = input.getAttribute("value");
        for (int i = 0; i <= countIteration; i++) {
            input.sendKeys(Keys.UP);
            int expectedValue = startValue;
            String expectedStrValue = String.valueOf(expectedValue);
            Assert.assertEquals(valueFromInput, expectedStrValue);
            expectedValue++;
        }
        return this;
    }

    public InputsPage setDownAndVerify(int startValue,int countIteration){
        String valueFromInput = input.getAttribute("value");
        for (int i = countIteration; i >= 0; i--) {
            int expectedValue = startValue;
            input.sendKeys(Keys.DOWN);
            String expectedStrValue = String.valueOf(expectedValue);
            Assert.assertEquals(valueFromInput, expectedStrValue);
            expectedValue--;
        }
        return this;
    }
}
