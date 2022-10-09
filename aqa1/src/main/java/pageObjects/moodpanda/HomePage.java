package pageObjects.moodpanda;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageObjects.baseObjects.BasePage;

public class HomePage extends MoodPandaBasePage { //наследуемся от абстрактного класса,который насдедуется от BasePage и подтягивает методы

    private By getStartedBtn = By.partialLinkText("Get started");
    private By title = By.cssSelector("[class^=container] > p[class^='title ']");

    public HomePage open(String url) {
        load(url);
        isPageOpened();
        return this;
    }

    public HomePage clickGetStarted() {
        click(getStartedBtn);
        return this;
    }
 /** паттерн Loadable Page*/
    @Override
    public void isPageOpened() { //реализуем метод из абстрактного класса и закидываем в метод open либо созлаем дженерик в абстрактном классе
        waitVisibilityOfElement(title); // проверка что заголовок отображается
        verifyElementClickable(getStartedBtn); //проверка что кнопка кликабельна
        //3- return this
    }
}
