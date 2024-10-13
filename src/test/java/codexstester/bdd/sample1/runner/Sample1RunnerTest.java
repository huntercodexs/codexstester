package codexstester.bdd.sample1.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.sample1.stepsdef",
        features = "src/test/resources/features/sample1/Sample1.feature",
        plugin = {"pretty", "html:target/cucumber-reports/sample1-report.html"})
public class Sample1RunnerTest {
}
