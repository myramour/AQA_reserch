package pageObjects.baseObjects;

import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import testNgUtils.ExtentReportListener;
import testNgUtils.InvokedMethodListener;
import testNgUtils.Listener;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import static driver.DriverManagerFactory.getManager;
import static propertyHelper.PropertyReader.getProperties;

import static driver.SimpleDriver.closeWebDriver;

// класс содержит методы, которые могут быть многократно использованы в конкретных классах страниц
@Listeners({Listener.class, InvokedMethodListener.class, ExtentReportListener.class})
@Log4j
public abstract class BaseTest {
    protected Properties properties;

    @BeforeTest
    public void setup() {
        log.debug("I'm started new wed driver!");
        properties = getProperties();
        //new SimpleDriver(); //instance объекта обращение к конструктору объекта (старая реализация)
        getManager(DriverManagerType.valueOf(properties.getProperty("browser").toUpperCase()));//передаем тип браузера из проперти valueOf- через имя
    }

    //дженерик для создания инстанс, предоставляет обобщение для создания новой сущности
    protected <T> T get(Class<T> page) {
        T instance = null; //создвем инстанс
        try {
            instance = page.getDeclaredConstructor().newInstance(); //передаем в него класс,берем конструктор и создаем инстанс
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return instance; //не знает, что будет возвращать, это определяется когда передаем в него какой-либо класс возвращвет инстанс класса который передаем
    }

    @AfterTest
    public void stop() {
        log.debug("I'm close wed driver!");
        closeWebDriver();
    }

}
