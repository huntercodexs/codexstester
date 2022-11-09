package codexstester.setup;

import codexstester.abstractor.unitary.AbstractTestsUnitaryTests;
import com.huntercodexs.postalcode.PostalCodeApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = PostalCodeApplication.class) /*INSERT HERE THEM MAIN CLASS FROM PROJECT (EXAMPLE: ApplicationName.class)*/
public class SetupUnitaryTests extends AbstractTestsUnitaryTests {

}