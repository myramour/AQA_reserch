package task11_12_SDAllure;

import org.testng.annotations.Test;
import pageObjects.baseObjects.BaseTest;
import pageObjects.herokuapp.DataTables2;
import pageObjects.herokuapp.NavigationItems;
import pageObjects.herokuapp.NavigationPage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** Stream API
 1. [-79, 99, -83, -75, -78, -22, -57, 84, 11, 15]
 Преобразовать элементы коллекции в положительные и отсортировать по возрастанию.

 Результат вывода в консоль:
 [11, 15, 22, 57, 75, 78, 79, 83, 84, 99]

 2. http://the-internet.herokuapp.com/tables
 - Получить данные таблицы 1 колонки Email
 - Данные колонки Email преобразовать в формат <name>@gmail.com
 Результат вывода в консоль
 [fbach@gmail.com, jdoe@gmail.com, jsmith@gmail.com, tconway@gmail.com] */

public class StreamAPI extends BaseTest {

    @Test//(enabled = false)
    public void sortTest(){
        List<Integer> data = Arrays.asList(-79, 99, -83, -75, -78, -22, -57, 84, 11, 15);
        System.out.println("Initial data :: " + data);

       /** 1-способ */
        List<Integer> expectedData = data.stream().map(number ->Math.abs(number)).sorted().collect(Collectors.toList());
        System.out.println("Positive numbers sort ascending :: " + expectedData);

        /** 2-способ */
        List<String> expectedData2 = data.stream().map(number->number.toString().replace("-","")).sorted().collect(Collectors.toList());
        System.out.println("Sort ascending by convert to string  :: " + expectedData2);

    }

    @Test (enabled = false)
    public void emailReplaceTest(){
        get(NavigationPage.class).open().navigateTo(NavigationItems.DATA_TABLES);
        Map<String,List<String>> mapTableData = get(DataTables2.class).checkTableIsDisplayed().getTableData();

        /** 1-способ */
        List<String> changeEmail = mapTableData.get("Email").stream().map(data->data.substring(0,data.indexOf("@"))+"@gmail.com").sorted().collect(Collectors.toList());
        System.out.println("Replace and sorted result :: " + changeEmail);
        System.out.println("----------------");

        /** 2-способ */
        List<String> gmailData = mapTableData.get("Email").stream().map(data -> data.split("@")[0] + "@gmail.com").sorted().collect(Collectors.toList());
        gmailData.forEach(System.out::println); // запись в столбик
        System.out.println("----------------");
        //.split - расщепляет строку на две. @ - параметр по которому делим получаем массив [jdoe, gmail.com].Забираем первую часть 0-элемент массива и добавляем  + "@gmail.com"

        /** 3-способ */
        List<String> gmail = mapTableData.get("Email").stream().map(data-> {
            String newData =" ";
            if(data.contains("yaho")) {
                newData =data.replace("yahoo","gmail");
            } else if(data.contains("hotmail")){
                newData = data.replace("hotmail","gmail");
            } else if(data.contains("earthlink")){
                newData = data.replace("earthlink.net","gmail.com");
            }else{
                newData=data;
            }
            return newData;
        }).sorted().collect(Collectors.toList());
        System.out.println("Replace and sorted result with else if :: " + gmail);
    }


}
