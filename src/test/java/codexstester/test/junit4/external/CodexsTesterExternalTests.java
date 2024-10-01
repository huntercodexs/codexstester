package codexstester.test.junit4.external;

import codexstester.core.dto.*;
import codexstester.setup.bridge.CodexsTesterBridgeTest;
import codexstester.setup.datasource.PostalCodeDataSource;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static codexstester.core.security.CodexsSecurity.codexsTesterSecurityOAuth2CheckToken;
import static codexstester.core.security.CodexsSecurity.codexsTesterSecurityOAuth2Token;
import static codexstester.core.util.CodexsHelper.codexsHelperStringToJsonSimple;
import static codexstester.setup.datasource.PostalCodeDataSource.ignoreOAuth2Tests;

public class CodexsTesterExternalTests extends CodexsTesterBridgeTest {

    public String oauth2Token(String env) {
        if (env == null || env.isEmpty()) env = externalProps.getProperty("external.tests.environment");
        Oauth2RequestTokenDto oauth2RequestTokenDto = codexsTesterSecurityOAuth2Token(env);
        ResponseEntity<Oauth2ResponseTokenDto> response = codexsTesterExternalOAuth2GetToken(oauth2RequestTokenDto);
        if (response.getBody() != null) return response.getBody().getAccess_token();
        return null;
    }

    /*
     * THIS TESTS CAN BE REMOVED
     */

    @Test
    public void propsTest() {
        System.out.println(externalProps);
    }

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

    /*
     * SAMPLES
     * PUT HERE THE CODE TESTS
     */

    @Test
    public void whenAnyRequestToOAuth2GetToken_AssertRegExp() throws Exception {
        if (!ignoreOAuth2Tests) {
            Oauth2RequestTokenDto oauth2RequestTokenDto = codexsTesterSecurityOAuth2Token(
                    externalProps.getProperty("external.tests.environment"));
            ResponseEntity<Oauth2ResponseTokenDto> response = codexsTesterExternalOAuth2GetToken(oauth2RequestTokenDto);
            System.out.println("TOKEN: " + response.getBody().getAccess_token());
            codexsTesterAssertRegExp("[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}", response.getBody().getAccess_token());
        }
    }

    @Test
    public void whenAnyRequestToOAuth2CheckToken() throws Exception {
        if (!ignoreOAuth2Tests) {
            String token = "ca976420-c93f-4015-b653-939ddc7b8011";
            Oauth2RequestCheckTokenDto oauth2RequestCheckTokenDto = codexsTesterSecurityOAuth2CheckToken(
                    externalProps.getProperty("external.tests.environment"), token);
            ResponseEntity<Object> response = codexsTesterExternalOAuth2CheckToken(oauth2RequestCheckTokenDto);
            System.out.println("RESULT: " + response.getBody());
            codexsTesterAssertBool(true, true);
        }
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = PostalCodeDataSource.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri")+"/address");
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest);
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenAnyOkRequest_WithAdvancedTest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = PostalCodeDataSource.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri")+"/address");
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest);
        requestDto.setExpectedMessage(null);
        requestDto.setExpectedCode(OK_200);

        ResponseEntity<?> response = codexsTesterExternalDispatcher(requestDto, headersDto);
        JSONObject jsonResponse = codexsHelperStringToJsonSimple(response.getBody().toString());

        codexsTesterCompareJsonFormat(
                expectedJsonKeysPostalCode(),
                expectedJsonValuesPostalCode(),
                expectedJsonTypedPostalCode(),
                jsonResponse,
                true,
                "complex",
                false);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenAnyOkRequest_WithDataTree_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        net.minidev.json.JSONObject dataRequest = PostalCodeDataSource.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri")+"/address");
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest);
        requestDto.setExpectedMessage(null);
        requestDto.setExpectedCode(OK_200);

        ResponseEntity<?> response = codexsTesterExternalDispatcher(requestDto, headersDto);
        net.minidev.json.JSONObject jsonResponse = codexsHelperStringToJsonSimple(response.getBody().toString());

        codexsTesterCompareJsonFormat(
                expectedJsonPostalCodeDataTree(),
                jsonResponse,
                true,
                "none",
                true);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenAnyOkRequest_WithOAuth2_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = codexsTesterSecurityOAuth2Token(
                externalProps.getProperty("external.tests.environment"));
        ResponseEntity<Oauth2ResponseTokenDto> response = codexsTesterExternalOAuth2GetToken(oauth2RequestTokenDto);
        JSONObject dataRequest = PostalCodeDataSource.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setAdditionalName("Access-Code");
        headersDto.setAdditionalValue("XYZ-123");
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest);
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenAnyBadRequest_WithBasicAuth_RetrieveBadRequest_StatusCode400_ByHttpMethodPOST() throws Exception {
        String basicAuth = "Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==";
        JSONObject dataRequest = PostalCodeDataSource.dataSourceBadRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAdditionalName("Access-Code");
        headersDto.setAdditionalValue("XYZ-123");
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest);
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode400_RetrieveBadRequest(headersDto, requestDto);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenAnyOkRequest_WithBasicAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        String basicAuth = "Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==";
        JSONObject dataRequest = PostalCodeDataSource.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAdditionalName("Access-Code");
        headersDto.setAdditionalValue("XYZ-123");
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest);
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenAnyOkRequest_WithBearerToken_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        String bearerToken = "Bearer d4cd86a0-aaaa-dddd-a590-ef68873d1234";
        JSONObject dataRequest = PostalCodeDataSource.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(bearerToken);
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest);
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        JSONObject dataRequest = PostalCodeDataSource.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest);
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenAnyOkRequest_Specific_RetrieveOk_StatusCode200_ByHttpMethodDELETE() throws Exception {
        JSONObject dataRequest = PostalCodeDataSource.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_DELETE);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("123456");
        requestDto.setDataRequest(dataRequest);
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenAnyNotFoundRequest_Specific_RetrieveNotFound_StatusCode404_ByHttpMethodDELETE() throws Exception {
        JSONObject dataRequest = PostalCodeDataSource.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_DELETE);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("1234569999");
        requestDto.setDataRequest(dataRequest);
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode404_RetrieveNotFound(headersDto, requestDto);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenAnyOkRequestSample_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri")+ "/junit4/sample");
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage("Welcome to sample from Codexs Tester");

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenAnyOkRequest_WithNoAuth_RetrieveCreated_StatusCode201_ByHttpMethodPOST() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri")+ "/junit4/sample");
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode201_RetrieveCreated(headersDto, requestDto);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenExternalRequest_UsingViaCep_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUrl("https://viacep.com.br");
        requestDto.setUri("/ws/12090002/json/");
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenExternalRequest_UsingViaCep_WithAdvancedTest_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUrl("https://viacep.com.br");
        requestDto.setUri("/ws/12090002/json/");
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage(null);
        requestDto.setExpectedCode(OK_200);

        ResponseEntity<?> response = codexsTesterExternalDispatcher(requestDto, headersDto);
        JSONObject jsonResponse = codexsHelperStringToJsonSimple(response.getBody().toString());

        codexsTesterCompareJsonFormat(
                expectedJsonKeysPostalCode(),
                expectedJsonValuesPostalCode(),
                expectedJsonTypedPostalCode(),
                jsonResponse,
                true,
                "complex",
                false);
    }

    /**
     * @implNote [IMPORTANT NOTE] Before run this test have a sure that the target service is running
     */
    @Test
    public void whenExternalRequest_UsingViaCep_WithDataTree_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        net.minidev.json.JSONObject dataRequest = PostalCodeDataSource.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUrl("https://viacep.com.br");
        requestDto.setUri("/ws/12090002/json/");
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest);
        requestDto.setExpectedMessage(null);
        requestDto.setExpectedCode(OK_200);

        ResponseEntity<?> response = codexsTesterExternalDispatcher(requestDto, headersDto);
        net.minidev.json.JSONObject jsonResponse = codexsHelperStringToJsonSimple(response.getBody().toString());

        codexsTesterCompareJsonFormat(
                expectedJsonPostalCode2DataTree(),
                jsonResponse,
                true,
                "none",
                true);
    }

}
