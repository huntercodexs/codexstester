package com.huntercodexs.codexstester.ignition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.huntercodexs.codexstester.resource.basic.CodexsStringHandler.repeat;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTerm;

public abstract class CodexsTesterIgnition {

    protected static final String DEFAULT_RESOURCE_PATH = "junit4";

    protected MockMvc genericMockMvc;
    protected static RestTemplate genericRestTemplate;

    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime dateTimeNow = LocalDateTime.now();
        String spaces = repeat(" ", 69);
        String dateTimeFormat = "|  Date now is: "+ dateTimeNow.format(formatter)+spaces+"|\n";

        String welcome =
        " _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _\n" +
        "|                                                                                                       |\n" +
        "|  //||||  //|||\\\\  ||||\\\\   ||||||  \\\\  //  //||||     ||||||  ||||||  //||||  ||||||  ||||||  ||||\\\\  |\n" +
        "|  ||      ||   ||  ||   ||  ||||      ||    \\\\||\\\\  -    ||    ||||    \\\\||\\\\    ||    ||||    ||  //  |\n" +
        "|  \\\\||||  \\\\|||//  ||||//   ||||||  //  \\\\  ||||//       ||    ||||||  ||||//    ||    ||||||  ||  \\\\  |\n" +
        "|                                                                                                       |\n" +
        "|  Release: 2.0.10                                                                                      |\n" +
        "|  https://github.com/huntercodexs                                                                      |\n" +
        "|  Powered by HunterCodexs (c) 2022 (owned by jereelton-devel)                                          |\n" +
        "|                                                                                                       |\n" +
        dateTimeFormat +
        "|_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _|\n";

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
