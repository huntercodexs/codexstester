package codexstester.bdd.runner.login;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "codexstester.test.bdd.stepsdef",
        features = "src/test/resources/features/login/Login.feature",
        plugin = {"pretty", "html:target/cucumber-reports/report.html"})
public class LoginRunnerTest {
}
