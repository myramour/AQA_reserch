package pageObjects.moodpanda;

import pageObjects.baseObjects.BasePage;

public abstract class MoodPandaBasePage extends BasePage { //подтягивает методы из общей BasePage
// 1 - MoodPandaBasePage <T> - неизвестный тип для дженерик метода
// 2 - тогда запись ниже переписываем как - public abstract T isPageOpened();
// 3 -  см HomePage метод isOpened
    public abstract void isPageOpened();
}
