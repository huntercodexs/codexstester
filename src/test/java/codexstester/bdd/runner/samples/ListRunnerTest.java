package codexstester.bdd.runner.samples;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.stepsdef.samples",
        features = "src/test/resources/features/samples/list/List.feature",
        plugin = {"pretty", "html:target/cucumber-reports/list-report.html"},
        tags = "@Things"
)
public class ListRunnerTest {
}
