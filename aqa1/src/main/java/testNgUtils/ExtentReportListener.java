package testNgUtils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ExtentReportListener implements ITestListener {
    private static ExtentReports extentReports;
    private static ExtentTest extentTest;

    //инициализируем объект екстент репорта и записываем название теста
    @Override
    public void onStart(ITestContext context) { // создаются тут и записываются в переменные экземпляра класса. дальше переиспользуем в других методах
        //инстанс для ExtentReports. У него есть разные конструкторы, используем тот,где будем передавать путь, куда будем записывать репорт+ имя и формат
        extentReports = new ExtentReports(System.getProperty("user.dir") + "/target/" + context.getName() + ".html");
        //инстанс extentTest который передаем методам onTestFailure ... (чтобы записать тест со статусом выполнения)
        extentTest = extentReports.startTest(context.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(LogStatus.FAIL, result.getName()); //логирует статус фейл и имя
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(LogStatus.PASS, result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.endTest(extentTest); //зкончить тесты
        extentReports.flush(); //сформировать отчет
    }
}
