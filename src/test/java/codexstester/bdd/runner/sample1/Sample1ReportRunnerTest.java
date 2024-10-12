package codexstester.bdd.runner.sample1;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

@Suite
@SelectClasspathResource("features/sample1/Sample1.feature")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "codexstester.bdd.stepsdef"
)
public class Sample1ReportRunnerTest {
}
