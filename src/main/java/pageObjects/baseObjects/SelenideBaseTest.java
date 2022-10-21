package pageObjects.baseObjects;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.Listeners;
import testNgUtils.SelenideListener;

import java.util.Properties;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static propertyHelper.PropertyReader.getProperties;

/**реализует метод get, который стартует драйвер, если его нет и создает инстанс страницы */
@Listeners(SelenideListener.class)
public class SelenideBaseTest {

    protected Properties properties;

    protected <T> T get(Class<T> page) {
        properties = getProperties();
        return driver().hasWebDriverStarted() ? page(page) : open(Configuration.baseUrl, page);
    }
}
// page не создает драйвер, но возвращает инстанс
//open создает драйвер и возвращает инстанс
//hasWebDriverStarted() спрашивает состояние драйвера
// когда драйвер не создан(первый запуск тестов), выполняется open :: создает драйвер и инстанс класса. Дальнейшие get создают только инстанс класса