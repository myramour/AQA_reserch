package pageFactory.moodpanda;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pageObjects.baseObjects.BasePage;

public class HomePage extends BasePage {
    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Get started") //первый способ -  how как ищем(тип локатора), using - сам локатор
    WebElement getStartedBtn;

    public HomePage() {  //у каждого класса есть пустой конструктор
        PageFactory.initElements(driver, this);
        //для инициализации паттерна PageFactory, .initElements() ожидает драйвер и ссылку на объект текущего класса
        //this = new LoginPage
    }

    public HomePage open(String url) {
        load(url);
        return this;
    }

    public HomePage clickGetStarted() {
        click(getStartedBtn); //использует клик через веб элемент, как и все элементы в PageFactory
        return this;
    }
}
