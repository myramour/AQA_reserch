package pageObjects.baseObjects;

import driver.UIElement;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

import static driver.DriverManager.getDriver;
import static propertyHelper.PropertyReader.getProperties;

//Класс для инициализации объектов страниц
@Log4j
public abstract class BasePage {
    protected WebDriverWait wait;
    protected WebDriver driver;
    protected Actions actions;
    protected Properties properties;

    //выполняется, когда создается экземпляр класса BasePage
    protected BasePage() {
       // driver2 = getWebDriver(); старая реализация драйвера
        driver = getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions = new Actions(driver);
        properties = getProperties();
    }

    //гибкое ожидание
    protected FluentWait<WebDriver> fluentWait(long timeout, long pollingEvery) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofSeconds(pollingEvery))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    protected void load() { //для реализации с проперти
        log.debug("Open page :: " + properties.getProperty("url"));
        driver.get(properties.getProperty("url"));
    }

    protected void load(String url) { //метод для перехода по ссылке (вместо driver get)
        log.debug("Open page :: " + url);
        driver.get(url);
    }

    protected String getPageUrl() { //возвращает ссылку
        log.debug("Get page url");
        return driver.getCurrentUrl();
    }

    protected void enter(WebElement webElement, String enterData) {
        log.debug("I'm enter :: " + enterData + ", by web element :: " + webElement);
        webElement.clear();
        webElement.sendKeys(enterData);
    }

    protected void enter(By locator, CharSequence... enterData) { //вроде бы теперь должно работать с разными ОС(не точно)
        log.debug("I'm enter :: " + enterData + ", by locator :: " + locator);
        String os = System.getProperty("os.name");
        if (os.contains("Mac")) {
            findElement(locator).sendKeys(Keys.chord(Keys.COMMAND, "a", Keys.DELETE));
        } else {
            findElement(locator).sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        }
        findElement(locator).sendKeys(enterData);
    }

    protected void click(By locator) {
        log.debug("I'm click by :: " + locator);
        verifyElementClickable(locator);
        findElement(locator).click();
    }

    protected void click(WebElement webElement) { //клики с оберткой
        log.debug("I'm click by :: " + webElement);
        new UIElement(driver, wait, webElement).click(); //создаем инстанс объекта от UIElement
    }

    protected void clickAll(By locator) {
        List<WebElement> buttons = driver.findElements(locator);
        for (WebElement button : buttons) {
            log.debug("I'm click by :: " + locator);
            button.click();
        }
    }

    protected void clickAll(WebElement... elements) {
        for (WebElement element : elements) {
            log.debug("I'm click by :: " + element);
            List<WebElement> buttons = new ArrayList<>();
            buttons.add(element);
            for (WebElement button : buttons) {
                button.click();
            }
        }
    }

    //или так
    protected void clickAll(By... elements) { //в качестве аргумента(ов) для этого метода могут быть переданы ноль или более объектов By (или их массив).
        for (By element : elements) {
            log.debug("Click on element :: " + element);
            List<WebElement> buttons = driver.findElements(element);
            for (WebElement button : buttons) {
                button.click();
            }
        }
    }

    protected void selectByText(By locator, String value) {
        Select select = new Select(findElement(locator));
        select.selectByVisibleText(value);
        log.debug("I'm select by text :: " + value);
    }

    protected void selectByIndex(By locator, int value) {
        Select select = new Select(findElement(locator));
        select.selectByIndex(value);
        log.debug("I'm select by index :: " + value);
    }

    //лучще исп такой селект
    protected void select(By locator, Integer index) {
        log.debug("Select by locator => " + locator + " with index => " + index);
        Select select = new Select(findElement(locator));
        select.selectByIndex(index);
    }

    protected void select(By locator, String value) {
        log.debug("Select by locator => " + locator + " with value => " + value);
        Select select = new Select(findElement(locator));
        select.selectByValue(value);
    }

    protected void select(WebElement element, Integer index) {
        log.debug("Select by locator => " + element + " with index => " + index);
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    protected void select(WebElement element, String value) {
        log.debug("Select by locator => " + element + " with value => " + value);
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }

    protected String getText(By locator) {
        log.debug("I'm get text by  :: " + locator);
        return findElement(locator).getText();
    }

    protected String getText(WebElement webElement) {
        log.debug("I'm get text by  :: " + webElement);
        return webElement.getText();
    }

//    protected List<String> getTexts(By locator) {
//        log.debug("I'm get text by  :: " + locator);
//        List<String> actualData = new ArrayList<>();
//        for(WebElement webelement :driver.findElements(locator){
//            actualData.add(webElement.getText());
//        });
//        return actualData;
//    }
    /** ВТОРОЙ ВАРИАНТ СБОРА ДАННЫХ ДЛЯ СОРТИРОВКИ ТАБЛИЦЫ ПО ЛОКАТОРУ */
    protected List<String> getTexts(By locator) { //проходим по каждому элементу из списка и забираем у него текст в коллекцию
        log.debug("I'm get texts by  :: " + locator);
        List<String> data =  findElements(locator).stream().map(webElement -> webElement.getText()).collect(Collectors.toList());
        log.debug("I'm actual data :: " + data);
        return data;
        //через stream() представили коллекцию веб элементов в качестве потока данных
        //через map перебираем каждый элемент и переделываем его с типа webElement -> webElement.getText() в строку
        //map представляет собой стрим,через  collect(Collectors.toList() переводим в лист
    }

    protected List<String> getTexts(List<WebElement> webElements) {
        log.debug("I'm get texts by  :: " + webElements);
        return webElements.stream().map(webElement -> webElement.getText()).collect(Collectors.toList());
    }

    protected List<String> getSortAscendingByTexts(By locator) {
        log.debug("I'm sorting texts by  :: " + locator);
        List<String> sortAscendingList = getTexts(locator);
        log.debug("I'm ascending sorted data :: " + sortAscendingList);
         return sortAscendingList;
    }

    protected List<String> getSortAscendingByTexts(List<WebElement> webElements) {
        log.debug("I'm sorting texts by  :: " + webElements);
        List<String> sortAscendingList = getTexts(webElements);
        log.debug("I'm ascending sorted data :: " + sortAscendingList);
        return sortAscendingList;
    }


    protected List<String> getSortDescendingByTexts(By locator) {
        List<String> sortDescendingList = getTexts(locator);
        Collections.sort(sortDescendingList,Collections.reverseOrder());
        log.debug("I'm descending sorted data :: " + sortDescendingList);
        return sortDescendingList;
    }

    protected List<String> getSortDescendingByTexts(List<WebElement> webElements) {
        List<String> sortDescendingList = getTexts(webElements);
        Collections.sort(sortDescendingList,Collections.reverseOrder());
        log.debug("I'm descending sorted data :: " + sortDescendingList);
        return sortDescendingList;
    }

    protected List<Double> getValues(By locator) {
        List<Double> getData = findElements(locator).stream()
                .map(webElement -> webElement.getText())
                .map(webElement -> webElement.replaceAll("[^\\d.]+", "")) //все, кроме цифр и точки, + совпадает один или более раз
               // .map(webElement -> webElement.replace("$", ""))
                //.map(webElement -> webElement.replace(",", "."))
                .map(Double::parseDouble).collect(Collectors.toList());
        log.debug("I'm get values by  :: " + getData);
        return getData;
    }

    protected List<Double> getValues(List<WebElement> webElements) {
        List<Double> getData = webElements.stream()
                .map(webElement -> webElement.getText())
                .map(webElement -> webElement.replaceAll("[^\\d.]+", ""))
                //.map(webElement -> webElement.replace("$", ""))
                .map(Double::parseDouble).collect(Collectors.toList());
        log.debug("I'm get values by  :: " + getData);
        return getData;
    }

    protected List<Double> getSortAscendingByValues(By locator) {
        List<Double> sortAscendingList = getValues(locator);
        Collections.sort(sortAscendingList);
        log.debug("I'm ascending sorted data :: " + sortAscendingList);
        return sortAscendingList;
    }

    protected List<Double> getSortAscendingByValues(List<WebElement> webElements) {
        List<Double> sortAscendingList = getValues(webElements);
        Collections.sort(sortAscendingList);
        log.debug("I'm ascending sorted data :: " + sortAscendingList);
        return sortAscendingList;
    }

    protected List<Double> getSortDescendingByValues(By locator) {
        List<Double> sortDescendingList = getValues(locator);
        Collections.sort(sortDescendingList, Collections.reverseOrder());
        log.debug("I'm descending sorted data :: " + sortDescendingList);
        return sortDescendingList;
    }

    protected List<Double> getSortDescendingByValues(List<WebElement> webElements) {
        List<Double> sortDescendingList = getValues(webElements);
        Collections.sort(sortDescendingList, Collections.reverseOrder());
        log.debug("I'm descending sorted data :: " + sortDescendingList);
        return sortDescendingList;
    }

/** END */
    protected String getElementAttribute(By by, String attribute) { //получение атрибута элемента
        log.debug("Get element => " + by + ", attribute :: " + attribute);
        return findElement(by).getAttribute(attribute);
    }

    protected List<String> getElementsAttribute(By by, String attribute) {
        log.debug("Get element => " + by + ", attribute :: " + attribute);
        return findElements(by).stream().map(webElement -> webElement.getAttribute(attribute)).collect(Collectors.toList());
    }

    //говорит о состоянии элемента в текущий момент времени(спрашиваем о состоянии NotExist)
    protected Boolean elementNotExist(By by) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); //отключаем implicitlyWait(драйвер не ждет выполнения элемента)
        for (int counter = 1; counter < 20; counter++) { //элемент может пропасть не сразу в динамическом контенте,поэтому делаем цикл
            log.debug("Wait element not exist count = " + counter);
            if (findElements(by).size() == 0) { //если элемента нет findElements - потому что если элемент не наден вернет ошибку, а findElement просто пустой массив
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                return true;
            }
            waitUntil(1); //если элемент виден, ждет 1с и снова спрашивает(так до 20 раз)
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20)); //подключаем обратно, чтобы его использовали другие методы
        return false;
    }

    protected Boolean elementNotExist(WebElement webElement) {
        fluentWait(20, 1).until(driver -> ExpectedConditions.not(ExpectedConditions.visibilityOfAllElements(webElement)));
        return true;
    }

    protected void waitVisibilityOfElement(By locator) { //ожидние что элемент будет виден
        log.debug("wait visibility of element => " + locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean waitVisibilityOfElements(By... locators) { //ожидние что элемент будет виден
        for (By locator : locators) {
            log.debug("wait visibility of element => " + locator);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        return true;
    }

    protected boolean waitVisibilityOfElements(WebElement... webElements) {
        for (WebElement webElement : webElements) {
            log.debug("wait visibility of element => " + webElement);
            wait.until(ExpectedConditions.visibilityOf(webElement));
        }
        return true;
    }

    protected void verifyElementTextToBe(By locator, String text) { //что есть текст
        log.debug("verify element text to be => " + locator);
        wait.until(ExpectedConditions.textToBe(locator, text));
    }

    protected void verifyElementClickable(By locator) { //что элемент кликабельный
        log.debug("verify element clickable => " + locator);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private void waitUntil(Integer timeout) { //зыбит поток
        try {
            Thread.sleep(timeout * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * ДЛЯ СОРТИРОВКИ ТАБЛИЦЫ ВАРИАНТ №1
     */
    protected List<String> sortAscending(By element) {
        List<WebElement> webElementsList = driver.findElements(element);
        List<String> sortAscendingList = new ArrayList<>();
        webElementsList.forEach(elements -> {
            sortAscendingList.add(elements.getText());
            Collections.sort(sortAscendingList);
        });
        log.debug("I'm ascending list ::" + sortAscendingList);
        return sortAscendingList;
    }

    protected List<String> sortDescending(By element) {
        List<WebElement> webElementsList = driver.findElements(element);
        List<String> sortDescendingList = new ArrayList<>();
        webElementsList.forEach(elements -> {
            sortDescendingList.add(elements.getText());
            Collections.sort(sortDescendingList, Collections.reverseOrder());
        });
        log.debug("I'm descending list ::" + sortDescendingList);
        return sortDescendingList;
    }

    protected List<String> sortAscendingPrice(By element) {
        List<String> sortAscendingList = getActualList(element);
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // если строки разной длины, то более короткое
                if (o1.length() != o2.length())
                    return Integer.compare(o1.length(), o2.length());
                else
                    //если длины равны - сравниваем как строки
                    return o1.compareTo(o2);
            }
        };
        Collections.sort(sortAscendingList, comparator);
        log.debug("I'm ascending list ::" + sortAscendingList);
        return sortAscendingList;
    }

    protected List<String> sortDescendingPrice(By element) {
        List<String> sortDescendingList = getActualList(element);
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // если строки разной длины, то более длинное
                if (o1.length() != o2.length())
                    return Integer.compare(o2.length(), o1.length());
                else
                    //если длины равны - сравниваем как строки
                    return o2.compareTo(o1);
            }
        };
        Collections.sort(sortDescendingList, comparator);
        log.debug("I'm descending list ::" + sortDescendingList);
        return sortDescendingList;
    }

    protected List<String> getActualList(By element) {
        List<WebElement> webElementsList = driver.findElements(element);
        List<String> actualList = new ArrayList<>();
        webElementsList.forEach(elements -> {
            actualList.add(elements.getText());
        });
        log.debug("I'm actual List :: " + actualList);
        return actualList;
    }
    /**ОКОНЧАНИЕ СПОСОБА 1*/
}

