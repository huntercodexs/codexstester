package codexstester.test.bdd.stepsdef.integration;

import com.huntercodexs.postalcode.PostalCodeApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {PostalCodeApplication.class}
)
public class SpringBootCucumberIntegration {
}
