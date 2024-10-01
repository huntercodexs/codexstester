package codexstester.core.ignition;

import codexstester.setup.advanced.CodexsAdvancedSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static codexstester.core.util.CodexsHelper.codexsHelperLogTerm;

public abstract class CodexsTesterIgnition extends CodexsAdvancedSetup {

    private static final String RELEASE = "2.0.0";
    protected static final String DEFAULT_RESOURCE_PATH = "junit4";

    protected MockMvc genericMockMvc;
    protected static RestTemplate genericRestTemplate;

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {

        String welcome = "\n" +
                "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| \n" +
                " \n" +
                "//||||  //|||\\\\  ||||\\\\   ||||||  \\\\  //  //||||     ||||||  ||||||  //||||  ||||||  ||||||  ||||\\\\ \n" +
                "||      ||   ||  ||   ||  ||||      ||    \\\\||\\\\  -    ||    ||||    \\\\||\\\\    ||    ||||    ||  // \n" +
                "\\\\||||  \\\\|||//  ||||//   ||||||  //  \\\\  ||||//       ||    ||||||  ||||//    ||    ||||||  ||  \\\\ \n" +
                "\n" +
                "Release: "+RELEASE+" \n" +
                "Powered by Huntercodexs (c) 2022 (owned by jereelton-devel) \n" +
                "https://github.com/huntercodexs \n" +
                "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| \n" +
                "\n" +
                "CODEXS TESTER IS STARTING ... \n";

        codexsHelperLogTerm("SETUP ENVIRONMENT IS START", null, true);

        genericMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        genericRestTemplate = new RestTemplate();
    }

    protected static String getDefaultResourcePath(String resource) {
        return DEFAULT_RESOURCE_PATH
                .replaceFirst("/$", "") + "/" + resource
                .replaceFirst("^/", "")
                .replaceFirst("/$", "") + "/";
    }
}
