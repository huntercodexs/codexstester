package codexstester.setup.bridge;

import codexstester.abstractor.external.ExternalRequest1XxTests;
import com.huntercodexs.sample.SampleApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static codexstester.abstractor.util.UtilTests.setRuntimeFile;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SampleApplication.class) /*INSERT HERE THEM MAIN CLASS FROM PROJECT (EXAMPLE: ApplicationName.class)*/
public class ExternalSampleBridgeTests extends ExternalRequest1XxTests {

    @Override
    @Before
    public void setUp() {
        setRuntimeFile(externalFilepathPropertiesSample);
        super.setUp();
    }

}