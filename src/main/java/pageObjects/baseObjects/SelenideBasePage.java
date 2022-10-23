package pageObjects.baseObjects;

import com.codeborne.selenide.SelenideElement;
import driver.UIElement;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.Properties;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static propertyHelper.PropertyReader.getProperties;

@Log4j
public abstract class SelenideBasePage {
    protected Properties properties;

    protected SelenideBasePage() {
        properties = getProperties();
    }

    protected void enter(SelenideElement element, CharSequence... enterData) {
        log.debug("I'm enter :: " + enterData + ", by locator :: " + element);
        String os = System.getProperty("os.name");
        if (os.contains("Mac")) {
           element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
           element.sendKeys(Keys.chord(Keys.DELETE));
        } else {
           element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.chord(Keys.DELETE));
        }
        element.shouldBe(visible).sendKeys(enterData);
    }

    protected void click(SelenideElement element) {
        log.debug("I'm click by :: " + element);
        element.should(enabled).click();
    }


}
