package codexstester.bdd.runner.samples;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.stepsdef.samples",
        features = "src/test/resources/features/samples/background/Background.feature",
        plugin = {"pretty", "html:target/cucumber-reports/background-report.html"},
        tags = "@BackgroundUser"
)
public class BackgroundRunnerTest {
}
