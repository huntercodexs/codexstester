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
        codexsHelperLogTerm("SETUP IN HEADERS FACTORY IS START", null, true);
        genericMockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        genericRestTemplate = new RestTemplate();
    }
}
