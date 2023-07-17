package codexstester.test.internal;

import codexstester.engine.dto.HeadersDto;
import codexstester.engine.dto.RequestDto;
import codexstester.setup.bridge.PostalCodeBridgeTests;
import codexstester.setup.datasource.PostalCodeDataSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;

import static codexstester.engine.util.CodexsHelperTests.codexsHelperStringToJsonSimple;

public class PostalCodeInternalTests extends PostalCodeBridgeTests {

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
    public void whenAnyOkRequest_WithAdvancedTest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        net.minidev.json.JSONObject dataRequest = PostalCodeDataSourceTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProps.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);
        requestDto.setExpectedCode(OK_200);

        String response = codexsTesterInternalDispatcher(requestDto, headersDto);
        net.minidev.json.JSONObject jsonResponse = codexsHelperStringToJsonSimple(response);

        codexsTesterCompareJsonFormat(
                expectedJsonKeysPostalCode(),
                expectedJsonValuesPostalCode(),
                expectedJsonTypedPostalCode(),
                jsonResponse,
                false,
                "none",
                false);
    }

    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = PostalCodeDataSourceTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProps.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyRequestUsingInvalidPostalCode_WithNoAuth_RetrieveError_StatusCode500_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = PostalCodeDataSourceTests.dataSourceOkRequest();
        dataRequest.put("postalCode", "92090002");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProps.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage("Postal Code Not Found");

        codexsTesterInternal_StatusCode500_RetrieveInternalServerError(headersDto, requestDto);
    }

}
