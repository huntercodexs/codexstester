package codexstester.bdd.runner.samples;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@SelectClasspathResource("features/samples/login")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "codexstester.bdd.stepsdef.samples"
)
public class LoginReportRunnerTest {
}
