package com.huntercodexs.codexstester.abstractor.internal;

import com.huntercodexs.codexstester.setup.CodexsTesterApplicationTests;
import com.huntercodexs.codexstester.abstractor.AvailableHttpMethodTests;
import com.huntercodexs.codexstester.abstractor.dto.HeadersDto;
import com.huntercodexs.codexstester.abstractor.dto.RequestDto;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CodexsTesterApplicationTests.class)
@WebAppConfiguration
public abstract class AbstractInternalRequestTests extends AvailableHttpMethodTests {

    void executeInternalTest(RequestDto requestDto, HeadersDto headersDto) throws Exception {

        /*Fix a bug when getHttpMethod is null*/
        try {
            String method = headersDto.getHttpMethod();
            if (method == null) headersDto.setHttpMethod(HTTP_METHOD_TESTER);
        } catch (Exception ex) {
            headersDto.setHttpMethod(HTTP_METHOD_TESTER);
        }

        switch (headersDto.getHttpMethod()) {
            case HTTP_METHOD_GET:
                assertResultFromRequestByHttpPost(requestDto, headersDto);
                break;
            case HTTP_METHOD_POST:
                assertResultFromRequestByHttpGet(requestDto, headersDto);
                break;
            case HTTP_METHOD_PUT:
                assertResultFromRequestByHttpPut(requestDto, headersDto);
                break;
            case HTTP_METHOD_DELETE:
                assertResultFromRequestByHttpDelete(requestDto, headersDto);
                break;
            case HTTP_METHOD_PATCH:
                assertResultFromRequestByHttpPatch(requestDto, headersDto);
                break;
            case HTTP_METHOD_HEAD:
                assertResultFromRequestByHttpHead(requestDto, headersDto);
                break;
            case HTTP_METHOD_OPTIONS:
                assertResultFromRequestByHttpOptions(requestDto, headersDto);
                break;
            default:
                assertResultInternalTest();
        }
    }

    private void dispatcher(RequestDto requestDto, HeadersDto headersDto, String method) throws Exception {

        MockHttpServletRequestBuilder requestBuilder = null;
        String uri = internalUriBaseTest;
        ResultMatcher status = statusMockMvcTranslator(requestDto);

        if (requestDto.getUri() != null && !requestDto.getUri().equals("")) uri = requestDto.getUri();
        if (requestDto.getId() != null && !requestDto.getId().equals("")) uri = uri +"/"+ requestDto.getId();

        switch (method) {
            case "GET":
                requestBuilder = MockMvcRequestBuilders.get(internalUrlBaseTest+internalUriBaseTest);
                break;
            case "POST":
                requestBuilder = MockMvcRequestBuilders.post(internalUrlBaseTest+internalUriBaseTest);
                break;
            case "PUT":
                requestBuilder = MockMvcRequestBuilders.put(internalUrlBaseTest+internalUriBaseTest);
                break;
            case "DELETE":
                requestBuilder = MockMvcRequestBuilders.delete(internalUrlBaseTest+internalUriBaseTest);
                break;
            case "PATCH":
                requestBuilder = MockMvcRequestBuilders.patch(internalUrlBaseTest+internalUriBaseTest);
                break;
            case "HEAD":
                requestBuilder = MockMvcRequestBuilders.head(internalUrlBaseTest+internalUriBaseTest);
                break;
            case "OPTIONS":
                requestBuilder = MockMvcRequestBuilders.options(internalUrlBaseTest+internalUriBaseTest);
                break;
            default:
                throw new RuntimeException("INVALID HTTP METHOD: " + method);
        }

        MvcResult result = internalMockMvc.perform(
                requestBuilder
                        .content(requestDto.getDataRequest())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .headers(codexsTesterInternalBuilderHeaders(requestDto, headersDto))
        ).andExpect(status).andReturn();

        System.out.println("RESULT[DEBUG]: "+result.getResponse().getContentAsString());

        /*Assert Content as String*/
        if (requestDto.getExpetecdMessage() != null && !requestDto.getExpetecdMessage().equals("")) {
            System.out.println("Try assertIntegration: ");
            System.out.println("> "+requestDto.getExpetecdMessage());
            System.out.println("> "+result.getResponse().getContentAsString());
            assertInternalTests(requestDto.getExpetecdMessage(), result.getResponse().getContentAsString());
        }

    }

    /**
     * @apiNote Using Http GET with Rest Template
     */
    private void assertResultFromRequestByHttpGet(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_GET);
    }

    /**
     * @apiNote Using Http POST with Rest Template
     */
    private void assertResultFromRequestByHttpPost(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_POST);
    }

    /**
     * @apiNote Using Http DELETE with Rest Template
     */
    private void assertResultFromRequestByHttpDelete(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_DELETE);
    }

    /**
     * @apiNote Using Http PUT with Rest Template
     */
    private void assertResultFromRequestByHttpPut(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_PUT);
    }

    /**
     * @apiNote Using Http PATCH with Rest Template
     */
    private void assertResultFromRequestByHttpPatch(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_PATCH);
    }

    /**
     * @apiNote Using Http HEAD with Rest Template
     */
    private void assertResultFromRequestByHttpHead(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_HEAD);
    }

    /**
     * @apiNote Using Http OPTIONS with Rest Template
     */
    private void assertResultFromRequestByHttpOptions(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_OPTIONS);
    }

    private void assertResultInternalTest() {
        System.out.println("CODEXS TESTER IS RUNNING IN INTERNAL MODE");
    }

}