package codexstester.test.external;

import codexstester.abstractor.dto.HeadersDto;
import codexstester.abstractor.dto.RequestDto;
import codexstester.setup.bridge.ExternalPostalCodeBridgeTests;
import codexstester.setup.datasource.DataSourceSampleTests;
import org.junit.Test;

public class ExternalSampleTests extends ExternalPostalCodeBridgeTests {

    /**
     * ExternalSampleTests Helpers
     * THIS TESTS CAN BE REMOVED
     * */

    @Test
    public void test1xx() throws Exception {
        isOk1xxExternalTest();
    }

    @Test
    public void test2xx() throws Exception {
        isOk2xxExternalTest();
    }

    @Test
    public void test3xx() throws Exception {
        isOk3xxExternalTest();
    }

    @Test
    public void test4xx() throws Exception {
        isOk4xxExternalTest();
    }

    @Test
    public void test5xx() throws Exception {
        isOk5xxExternalTest();
    }

    /**
     * ExternalSampleTests Samples
     * THESE TESTS BELOW CAN BE REMOVED OR CHANGED IF NEEDED
     * */

    @Test
    public void whenSimpleTestUsingString_AssertExact() throws Exception {
        String result = DataSourceSampleTests.dataSourceSampleResponse();
        codexsTesterAssertExact("This is a expected sample response", result);
    }

    /**
     * @implNote Before run this test have a sure that the target service is running
     */

    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProp.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage("Welcome to sample from Codexs Tester");

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveCreated_StatusCode201_ByHttpMethodPOST() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProp.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode201_RetrieveCreated(headersDto, requestDto);
    }

}
