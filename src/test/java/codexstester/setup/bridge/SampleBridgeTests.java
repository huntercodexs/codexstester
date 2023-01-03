package codexstester.setup.bridge;

import codexstester.abstractor.bridge.CodexsTesterCoreBridgeTests;
import codexstester.abstractor.unitary.AbstractUnitaryTests;
import com.huntercodexs.sample.SampleApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SampleApplication.class) /*INSERT HERE THEM MAIN CLASS FROM PROJECT (EXAMPLE: ApplicationName.class)*/
public class SampleBridgeTests extends CodexsTesterCoreBridgeTests {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
}