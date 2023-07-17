package codexstester.test.external;

import codexstester.engine.dto.*;
import codexstester.setup.bridge.PostalCodeBridgeTests;
import codexstester.setup.datasource.PostalCodeDataSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static codexstester.engine.security.SecurityTests.codexsTesterSecurityOAuth2CheckToken;
import static codexstester.engine.security.SecurityTests.codexsTesterSecurityOAuth2Token;
import static codexstester.engine.util.CodexsHelperTests.codexsHelperStringToJsonSimple;
import static codexstester.setup.datasource.PostalCodeDataSourceTests.ignoreOAuth2Tests;

public class PostalCodeExternalTests extends PostalCodeBridgeTests {

    public String oauth2Token() {
        Oauth2RequestTokenDto oauth2RequestTokenDto = codexsTesterSecurityOAuth2Token(
                externalProps.getProperty("external.tests.environment"));
        ResponseEntity<Oauth2ResponseTokenDto> response = codexsTesterExternalOAuth2GetToken(oauth2RequestTokenDto);
        if (response.getBody() != null) return response.getBody().getAccess_token();
        return null;
    }

    /**
     * THIS TESTS CAN BE REMOVED
     * */

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

    /**
     * THESE TESTS BELOW CAN BE REMOVED OR CHANGED IF NEEDED
     * */

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

    /** IMPORTANT NOTE
     * @implNote Before run this test have a sure that the target service is running
     */

    @Test
    public void whenAnyOkRequest_WithAdvancedTest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        JSONObject dataRequest = PostalCodeDataSourceTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
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
                true,
                "complex",
                true);
    }

    @Test
    public void whenAnyOkRequest_WithDataTree_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodPOST() throws Exception {
        net.minidev.json.JSONObject dataRequest = PostalCodeDataSourceTests.dataSourceOkRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
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

}
