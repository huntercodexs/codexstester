package codexstester.bdd.sample3.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.bdd.sample3.stepsdef",
        features = "src/test/resources/features/sample3/Sample3.feature",
        plugin = {"pretty", "html:target/cucumber-reports/sample3-report.html"})
public class Sample3RunnerTest {
}
