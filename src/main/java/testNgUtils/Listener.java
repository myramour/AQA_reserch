package testNgUtils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import propertyHelper.PropertyReader;

/**Listener для TestNG Report и для получения параметра для работы с property*/
public class Listener implements ITestListener {
    @Override
    public void onStart(ITestContext context) { //ITestContext - интерфейс, котопый содержит всю информацию о тесте и о параметризации внутри xml
        Reporter.log(context.getSuite().getXmlSuite().getTest()); // получаем текст свита и записываем в лог
        // <уcловие> ? <если уcловие = true> : <если уcловие = false>
        String propertyName = context.getSuite().getParameter("config") == null ? System.getProperty("config") : context.getSuite().getParameter("config"); // получаем "config" из из системы или из свита
        new PropertyReader(propertyName);//передаем название файла из параметра "config", который прописываем в xml
    }

    @Override
    public void onTestFailure(ITestResult result) { // при фейле
        Reporter.log("Ohh... this test was failed => " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log(context.getSuite().getXmlSuite().getTest());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("Cool... this test was passed => " + result.getName());
    }
}
