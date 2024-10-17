package codexstester.bdd.runner.samples;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
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
