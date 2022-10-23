package pageObjects.moodpanda_Selenide;

import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.drivercommands.WebDriverWrapper;
import com.codeborne.selenide.impl.WebDriverInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class YourDashboardPage {

    private SelenideElement getData(String data){
        return ($(By.xpath(" //div[@class='message-header' and text()='"+ data +"']/ancestor::article")));
    }
    private SelenideElement getTextArea(String data){
        return getData(data).find(By.tagName("textarea"));
    }

    private SelenideElement getRating(String data){
        return getData(data).find(By.tagName("select"));
    }

    private SelenideElement getAdd(String data){
        return getData(data).find(By.tagName("button"));
    }

    //textarea//ancestor::article/div[@class='message-header' and text()='23 Oct 2022']
    //*[@class='message-header' and text()='23 Oct 2022']
    //div[@class='message-header' and text()='23 Oct 2022']/ancestor::article//textarea

    public YourDashboardPage createPost(String data,String text,int mood) {

        //getTextArea(data).sendKeys(Keys.chord(Keys.COMMAND, "a"));
        //getTextArea(data).sendKeys(Keys.chord(Keys.DELETE));
       // getTextArea(data).click();
        getTextArea(data).sendKeys(text);

        assert (0<=mood) && (mood<=10);
        getRating(data).selectOption(mood);

        getAdd(data).click();

        return this;
    }

    public YourDashboardPage selectRating(String data, int mood){
        assert (0<=mood) && (mood<=10);
        getRating(data).selectOption(mood);
        return this;
    }

    public YourDashboardPage clickAdd(String data){
       getAdd(data).click();
        return this;
    }

}
