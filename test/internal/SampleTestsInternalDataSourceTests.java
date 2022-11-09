package codexstester.test.internal;

import codexstester.abstractor.dto.HeadersDto;
import codexstester.abstractor.dto.Oauth2RequestTokenDto;
import codexstester.abstractor.dto.Oauth2ResponseTokenDto;
import codexstester.abstractor.dto.RequestDto;
import codexstester.setup.SetupInternalDataSourceTests;
import codexstester.setup.datasource.DataSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static codexstester.abstractor.SecurityTests.*;

public class SampleTestsInternalDataSourceTests extends SetupInternalDataSourceTests {

    /**
     * DataSourceTests Helpers
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
     * Sample DataSourceTests
     * THESE TESTS BELOW CAN BE REMOVED OR CHANGED IF NEEDED
     * */

    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProp.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyRequestUsingInvalidPostalCode_WithNoAuth_RetrieveError_StatusCode500_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceOkRequest();
        dataRequest.put("postalCode", "92090002");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProp.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage("Postal Code Not Found");

        codexsTesterInternal_StatusCode500_RetrieveInternalServerError(headersDto, requestDto);
    }

    @Test
    public void whenAnyBadRequest_WithBasicAuth_RetrieveBadRequest_StatusCode400_ByHttpMethodPOST() throws Exception {
        String basicAuth = "Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==";
        JSONObject dataRequest = DataSourceTests.dataSourceBadRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("Access-Code");
        headersDto.setAddtionalValue("XYZ-123");
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProp.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode400_RetrieveBadRequest(headersDto, requestDto);
    }

    @Test
    public void whenAnyOkRequest_WithBasicAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        String basicAuth = "Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==";
        JSONObject dataRequest = DataSourceTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("Access-Code");
        headersDto.setAddtionalValue("XYZ-123");
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProp.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyOkRequest_WithBearerToken_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        String bearerToken = "Bearer d4cd86a0-aaaa-dddd-a590-ef68873d1234";
        JSONObject dataRequest = DataSourceTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(bearerToken);
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProp.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyOkRequest_WithOAuth2_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = codexsTesterSecurityOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = codexsTesterInternalOAuth2GetToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(response.getBody().getAccess_token());
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProp.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProp.getProperty("internal.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyOkRequest_Specific_RetrieveOk_StatusCode200_ByHttpMethodDELETE() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_DELETE);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProp.getProperty("internal.tests.base-uri"));
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyNotFoundRequest_Specific_RetrieveNotFound_StatusCode404_ByHttpMethodDELETE() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType("application/json;charset=UTF-8");
        headersDto.setHttpMethod(HTTP_METHOD_DELETE);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProp.getProperty("internal.tests.base-uri"));
        requestDto.setId("1234569999");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterInternal_StatusCode404_RetrieveNotFound(headersDto, requestDto);
    }

}
