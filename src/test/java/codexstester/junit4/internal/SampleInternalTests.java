package codexstester.junit4.internal;

import codexstester.setup.bridge.SampleBridgeTest;
import codexstester.setup.datasource.SampleDataSource;
import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import net.minidev.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import static codexstester.setup.datasource.SampleDataSource.*;

public class SampleInternalTests extends SampleBridgeTest {

    /*
     * SAMPLES: THIS TESTS CAN BE REMOVED
     * [IMPORTANT NOTE] Before run these test have a sure that the target service is running
     */

    @Test
    public void propsTest() {
        System.out.println(internalProps);
    }

    @Test
    public void test1xx() throws Exception {
        isOk1xxInternalTest();
    }

    @Test
    public void test2xx() throws Exception {
        isOk2xxInternalTest();
    }

    @Test
    public void test3xx() throws Exception {
        isOk3xxInternalTest();
    }

    @Test
    public void test4xx() throws Exception {
        isOk4xxInternalTest();
    }

    @Test
    public void test5xx() throws Exception {
        isOk5xxInternalTest();
    }

    @Test
    public void whenSimpleTestUsingString_AssertExact() throws Exception {
        String result = SampleDataSource.dataSourceSampleResponse();
        codexsTesterAssertExact("This is a expected sample response", result, null);
    }

    /*SAMPLE*/
    @Test
    public void whenAnyOkRequestSample_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProps.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage("Welcome to codexstester sample API");

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    /*SAMPLE*/
    @Test
    @Ignore
    public void whenAnyOkRequestSample_WithNoAuth_RetrieveCreated_StatusCode201_ByHttpMethodPOST() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProps.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode201_RetrieveCreated(headersDto, requestDto);
    }

    /*SAMPLE - GET*/
    @Test
    public void whenRequestTest_RetrieveOK_StatusCode200_ByHttpMethodGET() throws Exception {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(OK_200);
        requestDto.setUri(endpointGet);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        MockHttpServletResponse dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        codexsTesterAssertExact("GET OK - 123456", dispatcherResult.getContentAsString(), null);
    }

    /*SAMPLE - POST*/
    @Test
    public void whenRequestTest_RetrieveCreated_StatusCode201_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(CREATED_201);
        requestDto.setUri(endpointPost);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        MockHttpServletResponse dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        codexsTesterAssertExact("POST OK - 123456", dispatcherResult.getContentAsString(), null);
    }

    /*SAMPLE - PUT*/
    @Test
    public void whenRequestTest_RetrieveAccepted_StatusCode202_ByHttpMethodPUT() throws Exception {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_PUT);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(ACCEPTED_202);
        requestDto.setUri(endpointPut);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        MockHttpServletResponse dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        codexsTesterAssertExact("PUT OK - 123456", dispatcherResult.getContentAsString(), null);
    }

    /*SAMPLE - DELETE*/
    @Test
    public void whenRequestTest_RetrieveAccepted_StatusCode202_ByHttpMethodDELETE() throws Exception {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_DELETE);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(ACCEPTED_202);
        requestDto.setUri(endpointDelete);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        MockHttpServletResponse dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        codexsTesterAssertExact("DELETE OK - 123456", dispatcherResult.getContentAsString(), null);
    }

    /*SAMPLE - PATCH*/
    @Test
    public void whenRequestTest_RetrieveAccepted_StatusCode202_ByHttpMethodPATCH() throws Exception {
        JSONObject dataRequest = new JSONObject();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_PATCH);
        headersDto.setAuthorizationBasic("123-TEST");

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(ACCEPTED_202);
        requestDto.setUri(endpointPatch);
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        MockHttpServletResponse dispatcherResult = codexsTesterInternalDispatcher(requestDto, headersDto);
        codexsTesterAssertExact("PATCH OK - 123456", dispatcherResult.getContentAsString(), null);
    }

}