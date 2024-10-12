package codexstester.bdd.runner.user;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@SelectClasspathResource("features/user")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "codexstester.test.bdd.stepsdef"
)
public class UserReportRunnerTest {
}
