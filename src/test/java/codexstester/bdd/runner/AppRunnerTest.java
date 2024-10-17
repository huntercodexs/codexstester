package codexstester.bdd.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.stepsdef",
        features = "src/test/resources/features/app/App.feature",
        plugin = {"pretty", "html:target/cucumber-reports/app-report.html"},
        tags = "@AppScenario"
)
public class AppRunnerTest {
}
