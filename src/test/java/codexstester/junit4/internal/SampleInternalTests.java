package codexstester.junit4.internal;

import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import codexstester.setup.bridge.SampleBridgeTest;
import codexstester.setup.datasource.SampleDataSource;
import org.junit.Test;
import org.springframework.http.MediaType;

public class SampleInternalTests extends SampleBridgeTest {

    /**
     * THIS TESTS CAN BE REMOVED
     * */

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

    /**
     * THESE TESTS BELOW CAN BE REMOVED OR CHANGED IF NEEDED
     * */

    @Test
    public void whenSimpleTestUsingString_AssertExact() throws Exception {
        String result = SampleDataSource.dataSourceSampleResponse();
        codexsTesterAssertExact("This is a expected sample response", result);
    }

    /**
     * @implNote In this case the target service not needed is running
     */

    @Test
    public void whenAnyOkRequestSample_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProps.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage("Welcome to sample from Codexs Tester");

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
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

}
