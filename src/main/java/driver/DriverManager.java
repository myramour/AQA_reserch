package driver;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class DriverManager {
    public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>(); //переменная для многопоточности от нее создаем геттер

    {
        if (webDriver.get() == null) { //инициализауия драйвера через блок инициализации(запускает метод createDriver если драйвер в этом инстансе 0 )
            createDriver();
        }
    }

    public abstract void createDriver(); //обязывает наследников создать драйвер

    public static WebDriver getDriver() { //возвращает драйвер
        webDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        webDriver.get().manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
        webDriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        return webDriver.get();
    }

    public static void closeWebDriver() {
        if (webDriver != null) {
            webDriver.get().close();
            webDriver.get().quit();
            webDriver.remove();
        }
    }
}
