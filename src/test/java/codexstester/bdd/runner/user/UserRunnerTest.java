package codexstester.bdd.runner.user;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.test.bdd.stepsdef",
        features = "src/test/resources/features/user/User.feature",
        plugin = {"pretty", "html:target/cucumber-reports/report.html"})
public class UserRunnerTest {
}
