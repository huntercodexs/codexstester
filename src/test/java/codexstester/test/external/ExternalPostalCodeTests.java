package codexstester.test.external;

import codexstester.abstractor.dto.*;
import codexstester.setup.bridge.ExternalPostalCodeBridgeTests;
import codexstester.setup.datasource.DataSourcePostalCodeTests;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static codexstester.abstractor.security.SecurityTests.codexsTesterSecurityOAuth2CheckToken;
import static codexstester.abstractor.security.SecurityTests.codexsTesterSecurityOAuth2Token;
import static codexstester.abstractor.util.CodexsHelperTests.codexsHelperStringToJsonSimple;
import static codexstester.setup.datasource.DataSourcePostalCodeTests.ignoreOAuth2Tests;

public class ExternalPostalCodeTests extends ExternalPostalCodeBridgeTests {

    /**
     * DataSourcePostalCodeTests Helpers
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
     * Sample DataSourcePostalCodeTests
     * THESE TESTS BELOW CAN BE REMOVED OR CHANGED IF NEEDED
     * */

    @Test
    public void whenAnyRequestToOAuth2GetToken_AssertRegExp() throws Exception {
        if (!ignoreOAuth2Tests) {
            Oauth2RequestTokenDto oauth2RequestTokenDto = codexsTesterSecurityOAuth2Token();
            ResponseEntity<Oauth2ResponseTokenDto> response = codexsTesterExternalOAuth2GetToken(oauth2RequestTokenDto);
            System.out.println("TOKEN: " + response.getBody().getAccess_token());
            codexsTesterAssertRegExp("[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}", response.getBody().getAccess_token());
        }
    }

    @Test
    public void whenAnyRequestToOAuth2CheckToken() throws Exception {
        if (!ignoreOAuth2Tests) {
            String token = "ca976420-c93f-4015-b653-939ddc7b8011";
            Oauth2RequestCheckTokenDto oauth2RequestCheckTokenDto = codexsTesterSecurityOAuth2CheckToken(token);
            ResponseEntity<Object> response = codexsTesterExternalOAuth2CheckToken(oauth2RequestCheckTokenDto);
            System.out.println("RESULT: " + response.getBody());
            codexsTesterAssertBool(true, true);
        }
    }

    @Test
    public void whenAnyOkRequest_WithAdvancedTest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(internalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);
        requestDto.setExpectedCode(OK_200);

        ResponseEntity<?> response = codexsTesterExternalDispatcher(requestDto, headersDto);
        JSONObject jsonResponse = codexsHelperStringToJsonSimple(response.getBody().toString());

        codexsTesterCompareJsonFormat(
                expectedJsonKeysPostalCode(),
                expectedJsonValuesPostalCode(),
                expectedJsonTypedPostalCode(),
                jsonResponse,
                false,
                "none",
                true);
    }

    @Test
    public void whenAnyOkRequest_WithOAuth2_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = codexsTesterSecurityOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = codexsTesterExternalOAuth2GetToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setAdditionalName("Access-Code");
        headersDto.setAdditionalValue("XYZ-123");
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyBadRequest_WithBasicAuth_RetrieveBadRequest_StatusCode400_ByHttpMethodPOST() throws Exception {
        String basicAuth = "Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==";
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceBadRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAdditionalName("Access-Code");
        headersDto.setAdditionalValue("XYZ-123");
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode400_RetrieveBadRequest(headersDto, requestDto);
    }

    @Test
    public void whenAnyOkRequest_WithBasicAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        String basicAuth = "Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==";
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAdditionalName("Access-Code");
        headersDto.setAdditionalValue("XYZ-123");
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyOkRequest_WithBearerToken_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        String bearerToken = "Bearer d4cd86a0-aaaa-dddd-a590-ef68873d1234";
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(bearerToken);
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyOkRequest_Specific_RetrieveOk_StatusCode200_ByHttpMethodDELETE() throws Exception {
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_DELETE);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    @Test
    public void whenAnyNotFoundRequest_Specific_RetrieveNotFound_StatusCode404_ByHttpMethodDELETE() throws Exception {
        JSONObject dataRequest = DataSourcePostalCodeTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_DELETE);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("1234569999");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode404_RetrieveNotFound(headersDto, requestDto);
    }

}
