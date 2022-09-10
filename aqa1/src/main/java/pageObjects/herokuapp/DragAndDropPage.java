package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;


public class DragAndDropPage extends BasePage {

    private final WebElement elementA =driver.findElement(By.id("column-a"));
    private final WebElement elementB =driver.findElement(By.id("column-b"));

    /** Срабатывает, только если в момент выполнения теста курсор находится там,куда нужно перетащить элемент */
    public DragAndDropPage FromAToB(){
      actions.dragAndDrop(elementA, elementB).build().perform();
       //actions.clickAndHold(elementA).moveByOffset(1000,0).release().build().perform();
       //actions.clickAndHold(elementA).moveToElement(elementB).release().build().perform();

        return this;
    }

    public DragAndDropPage FromBToA(){
        actions.dragAndDrop(elementB, elementA).build().perform();
        return this;
    }

    public DragAndDropPage verifyChangeA(){
        System.out.println(elementA.getText());
        Assert.assertTrue(elementA.getText().equals("B"));
        return this;
    }

    public DragAndDropPage verifyChangeB(){
        System.out.println(elementB.getText());
        Assert.assertTrue(elementB.getText().equals("B"));
        return this;
    }
}
