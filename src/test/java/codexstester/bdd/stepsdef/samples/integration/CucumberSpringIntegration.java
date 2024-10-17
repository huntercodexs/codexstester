package codexstester.bdd.stepsdef.samples.integration;

import com.huntercodexs.codexstester.SampleApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {SampleApplication.class}
)
public class CucumberSpringIntegration {
}
