package lesson18;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
//связыает Steps and feature files
@CucumberOptions(
        features = {"src/test/resources/features/Moodpanda.feature"},
        tags = "@smoke",
        plugin = { //отвечает за отчеты
                "json:target/cucumber.json",
                "html:target/site/cucumber-pretty"
        },
        glue = "cucumberSteps") //где лежат шаги
public class RunMoodpandaCucumber extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider //берет сценарии и попорядку их передает
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
