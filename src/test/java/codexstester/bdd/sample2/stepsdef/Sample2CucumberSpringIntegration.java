package codexstester.bdd.sample2.stepsdef;

import com.huntercodexs.codexstester.SampleApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {SampleApplication.class}
)
public class Sample2CucumberSpringIntegration {
}
