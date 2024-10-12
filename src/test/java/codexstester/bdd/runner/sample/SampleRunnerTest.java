package codexstester.bdd.runner.sample;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.stepsdef",
        features = "src/test/resources/features/sample/Sample.feature",
        plugin = {"pretty", "html:target/cucumber-reports/report.html"})
public class SampleRunnerTest {
}
