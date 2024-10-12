package codexstester.bdd.runner.sample2;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.stepsdef",
        features = "src/test/resources/features/sample2/Sample2.feature",
        plugin = {"pretty", "html:target/cucumber-reports/sample2-report.html"})
public class Sample2RunnerTest {
}
