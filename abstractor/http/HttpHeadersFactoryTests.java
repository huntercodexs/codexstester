package codexstester.abstractor.http;

import codexstester.abstractor.properties.ExternalPropertyTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static codexstester.abstractor.util.CodexsHelperTests.codexsHelperLogTerm;

public abstract class HttpHeadersFactoryTests extends ExternalPropertyTests {

    protected static RestTemplate genericRestTemplate;

    protected MockMvc genericMockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {

        String release = "1.0.1";
        String welcome = "\n" +
                "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| \n" +
                "WELCOME TO \n" +
                "//||||  //|||\\\\  ||||\\\\   ||||||  \\\\  //  //||||     ||||||  ||||||  //||||  ||||||  ||||||  ||||\\\\ \n" +
                "||      ||   ||  ||   ||  ||||      ||    \\\\||\\\\  -    ||    ||||    \\\\||\\\\    ||    ||||    ||  // \n" +
                "\\\\||||  \\\\|||//  ||||//   ||||||  //  \\\\  ||||//       ||    ||||||  ||||//    ||    ||||||  ||  \\\\ \n" +
                "\n" +
                "Release: "+release+" \n" +
                "Powered by Huntercodexs (c) 2022 \n" +
                "https://github.com/huntercodexs \n" +
                "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| \n" +
                "\n" +
                "Codexs Tester is running ... \n";

        System.out.println(welcome);
        codexsHelperLogTerm("SETUP IN HEADERS FACTORY IS START", null, true);

        genericMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        genericRestTemplate = new RestTemplate();
    }
}
