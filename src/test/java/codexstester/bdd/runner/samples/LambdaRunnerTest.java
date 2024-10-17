package codexstester.bdd.runner.samples;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
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
