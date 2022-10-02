package pageObjects.herokuapp;

import org.openqa.selenium.By;
import org.testng.Assert;
import pageObjects.baseObjects.BasePage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTables2 extends BasePage {

    //отделяем хедеры и записи
    private By table = By.id("table"); //для зафейленного теста(lesson11)

    private By headers = By.xpath("//table[@id='table1']//th");

    private By getTableRow(String index) {
        return By.xpath("//table[@id='table1']//tbody//tr[" + index + "]"); //получить одну строку
    }

    public DataTables2 clickTableColumn(String columnName) {
        driver.findElement(headers).findElement(By.xpath(".//span[text() = '" + columnName + "']")).click();
        return this;
    }

    //фейл для проверки скриншота (lesson11)
    public DataTables2 checkTableIsDisplayed() {
        Assert.assertTrue(driver.findElement(table).isDisplayed());
        return this;
    }
    //создадим методы, которые будут брать данные из таблицы и помещать их в коллекции
    //ключ - название колонки
    //значение - то, что находится в колонке

    //возвращает данные в формате [[строка1],[строка2],[]]
    public List<List<String>> getTableRowsData() {
        Integer rowCount = driver.findElements(getTableRow("..")).size(); //количество строк, ".." - отзначет что переберет все индексы
        List<List<String>> data = new ArrayList<>(); //
        for (int row = 0; row < rowCount; row++) {
            List<String> columnData = new ArrayList<>(); //записываем данные из каждой колонки по определенной строке
            Integer columnCount = driver.findElement(getTableRow(Integer.toString(row + 1))).findElements(By.xpath(".//td")).size(); //+1 потому что индексация с 0
            for (int column = 0; column < columnCount; column++) {
                columnData.add(driver.findElement(getTableRow(Integer.toString(row + 1))).findElement(By.xpath(".//td[" + (column + 1) + "]")).getText());
            }
            data.add(columnData);
        }
        return data;
    }
    //1 - спрашиваем количество строк
    //2 - начинаем перебирать их через цикл
    //3 - заходим на первую строку и спрашиваем у нее количество колонок с локатором td
    //4 - перебераем каждую колонку по номеру из первой строки, забираем оттуда текст и записываем в коллекцию columnData те после первой итеррации получим запись в виде[Smith], после второй [[Smith, John]
    //5 - этот лист записываем в data , те получаем [[Smith John..],[],..]


    //ключ - название колонки
    //значение - то, что находится в колонке
    //можно писать свои локаторы, а можно переиспользовать предыдущий метод
    //для получения данных в столбце
    public Map<String, List<String>> getTableData() {
        Map<String, List<String>> mapData = new HashMap<>();
        Integer headersCount = driver.findElements(headers).size(); //возвращает количество хидеров
        List<List<String>> tableData = getTableRowsData();// предыдущий массив всей таблицы
        for (int header = 0; header < headersCount; header++) {
            List<String> columnData = new ArrayList<>(); //записываем колонки
            for (List<String> data : tableData) {
                columnData.add(data.get(header));
            }
            mapData.put(driver.findElements(headers).get(header).getText(), columnData); //находим и помещаем в мапу текст хидера(как ключ) и записываем данные из колонки
        }
        return mapData;
    }
    // 1 - мапа, куда записываем данные в формате ключ - название столбца, значение - содержимое столбца
    // 2 - Integer headersCount - количество хидеров
    // 3 - берем первый хедер(у джавы 0)
    // 4 - создаем лист columnData для записи данных из колонок
    // 5.6 - перебираем лист по первому хедеру(0) и получаем данные из первой колонки, записываем в columnData [Smith],[John]...
    // 7 - в мапу запишется ключ "Last name" и 4ре значения


}
