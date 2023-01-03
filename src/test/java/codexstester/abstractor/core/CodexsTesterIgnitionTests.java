package codexstester.abstractor.core;

import codexstester.setup.properties.FilePropertiesSourceTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static codexstester.abstractor.util.CodexsHelperTests.codexsHelperLogTerm;

public abstract class CodexsTesterIgnitionTests extends FilePropertiesSourceTests {

    protected static RestTemplate genericRestTemplate;

    protected MockMvc genericMockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {

        String release = "1.0.5";
        String welcome = "\n" +
                "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| \n" +
                " \n" +
                "//||||  //|||\\\\  ||||\\\\   ||||||  \\\\  //  //||||     ||||||  ||||||  //||||  ||||||  ||||||  ||||\\\\ \n" +
                "||      ||   ||  ||   ||  ||||      ||    \\\\||\\\\  -    ||    ||||    \\\\||\\\\    ||    ||||    ||  // \n" +
                "\\\\||||  \\\\|||//  ||||//   ||||||  //  \\\\  ||||//       ||    ||||||  ||||//    ||    ||||||  ||  \\\\ \n" +
                "\n" +
                "Release: "+release+" \n" +
                "Powered by Huntercodexs (c) 2022 \n" +
                "https://github.com/huntercodexs \n" +
                "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| \n" +
                "\n" +
                "CODEXS TESTER IS STARTING ... \n";

        codexsHelperLogTerm("WELCOME TO", welcome, true);
        codexsHelperLogTerm("SETUP ENVIRONMENT IS START", null, true);

        genericMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        genericRestTemplate = new RestTemplate();
    }
}
