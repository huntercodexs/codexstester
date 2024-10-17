package codexstester.bdd.runner.samples;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.stepsdef.samples",
        features = "src/test/resources/features/samples/lambda/Lambda.feature",
        plugin = {"pretty", "html:target/cucumber-reports/lambda-report.html"},
        tags = "@LambdaTag"
)
public class LambdaRunnerTest {
}
