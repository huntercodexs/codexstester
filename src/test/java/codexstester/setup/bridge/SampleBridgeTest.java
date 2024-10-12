package codexstester.setup.bridge;

import com.huntercodexs.codexstester.SampleApplication;
import com.huntercodexs.codexstester.bridge.CodexsTesterCoreBridge;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = SampleApplication.class) /*INSERT HERE THEM MAIN CLASS FROM PROJECT (EXAMPLE: ApplicationName.class)*/
public class SampleBridgeTest extends CodexsTesterCoreBridge {

    protected SampleBridgeTest() {
        super(getDefaultResourcePath("sample"));
    }

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

}