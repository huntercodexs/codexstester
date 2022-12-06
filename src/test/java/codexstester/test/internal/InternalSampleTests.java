package codexstester.test.internal;

import codexstester.abstractor.dto.HeadersDto;
import codexstester.abstractor.dto.RequestDto;
import codexstester.setup.bridge.InternalSampleBridgeTests;
import codexstester.setup.datasource.DataSourceSampleTests;
import org.junit.Test;

public class InternalSampleTests extends InternalSampleBridgeTests {

    /**
     * DataSourcePostalCodeTests Helpers
     * THIS TESTS CAN BE REMOVED
     * */

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
     * Sample ExternalSampleSetupTests
     * THESE TESTS BELOW CAN BE REMOVED OR CHANGED IF NEEDED
     * */

    @Test
    public void whenSimpleTestUsingString_AssertExact() throws Exception {
        String result = DataSourceSampleTests.dataSourceSampleResponse();
        codexsTesterAssertExact("This is a expected sample response", result);
    }

    /**
     * @implNote In this case the target service not needed is running
     */

    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProp.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage("Welcome to sample from Codexs Tester");

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveCreated_StatusCode201_ByHttpMethodPOST() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProp.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode201_RetrieveCreated(headersDto, requestDto);
    }

}