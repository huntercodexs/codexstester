package com.huntercodexs.codexstester.ignition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTerm;

public abstract class CodexsTesterIgnition {

    protected static final String DEFAULT_RESOURCE_PATH = "junit4";

    protected MockMvc genericMockMvc;
    protected static RestTemplate genericRestTemplate;

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {

        String welcome =
        """
         _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
        |                                                                                                       |
        |  //||||  //|||\\\\  ||||\\\\   ||||||  \\\\  //  //||||     ||||||  ||||||  //||||  ||||||  ||||||  ||||\\\\  |
        |  ||      ||   ||  ||   ||  ||||      ||    \\\\||\\\\  -    ||    ||||    \\\\||\\\\    ||    ||||    ||  //  |
        |  \\\\||||  \\\\|||//  ||||//   ||||||  //  \\\\  ||||//       ||    ||||||  ||||//    ||    ||||||  ||  \\\\  |
        |                                                                                                       |
        |  Release: 2.0.0                                                                                       |
        |  https://github.com/huntercodexs                                                                      |
        |  Powered by HunterCodexs (c) 2022 (owned by jereelton-devel)                                          |
        |                                                                                                       |
        |  Date now is: 2024/10/08 11:00:00                                                                     |
        |_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _|
        """;

        codexsHelperLogTerm(welcome, "Codexs Tester is starting ...", false);

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
