package com.huntercodexs.codexstester.setup;

import com.huntercodexs.codexstester.abstractor.internal.InternalRequest1XxTestsTests;
import com.huntercodexs.codexstester.postalcode.PostalCodeApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = PostalCodeApplication.class) /*INSERT HERE THEM MAIN CLASS FROM PROJECT (EXAMPLE: ApplicationName.class)*/
public class SetupInternalTests extends InternalRequest1XxTestsTests {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

}