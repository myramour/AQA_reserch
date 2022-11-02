import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class RtyuTest {
    @BeforeTest
    public void pre(){
        baseUrl = "https://www.rw.by/";
    }

    @Test
    public void openPage_Test() {
        open(baseUrl);
        $("#acFrom").sendKeys("Витебск");
        $(By.id("acTo")).sendKeys("Минск");
        $("#yDate").click();
        $(byXpath("//*[contains(@class, 'group-middle')]"))
                .find(By.xpath("table"))
                .find(By.linkText("1")).click(); //если есть тег а, лучше писать не xpath, a linkText

        SelenideElement button = $("[class=std-button]");
        button.should(enabled).click();

        collection.should(sizeGreaterThan(0));
        System.out.println(collection.filterBy(matchText("Санкт-Петербург-Витеб")).texts());
    }
}

