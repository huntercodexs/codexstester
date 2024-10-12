package codexstester.junit4.external;

import codexstester.setup.bridge.SampleBridgeTest;
import codexstester.setup.datasource.SampleDataSource;
import codexstester.setup.security.SecuritySetup;
import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.JwtResponseDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import com.huntercodexs.codexstester.resource.quickjson.QuickJson;
import com.huntercodexs.codexstester.security.CodexsSecurity;
import com.huntercodexs.codexstester.security.dto.BasicAuthRequestDto;
import com.huntercodexs.codexstester.security.dto.JwtAuthRequestDto;
import com.huntercodexs.codexstester.security.dto.Oauth2RequestCheckTokenDto;
import com.huntercodexs.codexstester.security.dto.Oauth2RequestTokenDto;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;

public class SampleExternalTests extends SampleBridgeTest {

    SecuritySetup securitySetup;

    @Before
    public void setup() {
        this.securitySetup = new SecuritySetup();
    }

    /*
     * SAMPLES: THIS TESTS CAN BE REMOVED
     * [IMPORTANT NOTE] Before run these test have a sure that the target service is running
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

    @Test
    public void whenSimpleTestUsingString_AssertExact() throws Exception {
        String result = SampleDataSource.dataSourceSampleResponse();
        codexsTesterAssertExact("This is a expected sample response", result);
    }

    @Test
    public void oauth2Test() {
        SecuritySetup securitySetup = new SecuritySetup();
        Oauth2RequestTokenDto requestTokenDto = securitySetup.oauth2Token("local");
        CodexsSecurity codexsSecurity = new CodexsSecurity(requestTokenDto);
        String token = codexsSecurity.token();

        codexsTesterAssertGuid(token);

        Oauth2RequestCheckTokenDto requestCheckTokenDto = securitySetup.oauth2CheckToken("local", token);
        boolean status = codexsSecurity.checkToken(requestCheckTokenDto);

        codexsTesterAssertBool(true, status);
    }

    @Test
    public void basicAuthTest() {
        SecuritySetup securitySetup = new SecuritySetup();
        BasicAuthRequestDto authRequestDto = securitySetup.basicAuth("local");
        CodexsSecurity codexsSecurity = new CodexsSecurity(authRequestDto);
        Object response = codexsSecurity.basicAuth();

        codexsTesterAssertGuid(String.valueOf(response));
    }

    @Test
    public void jwtAuthTest() {
        SecuritySetup securitySetup = new SecuritySetup();
        JwtAuthRequestDto jwtAuthRequestDto = securitySetup.jwtAuth("local");
        CodexsSecurity codexsSecurity = new CodexsSecurity(jwtAuthRequestDto);

        JwtResponseDto response = codexsSecurity.jwtAuth(JwtResponseDto.class);

        //JWT
        codexsTesterAssertJwtHS256(response.getJwt());
        //codexsTesterAssertJwtHS384(response.getJwt());
        //codexsTesterAssertJwtHS512(response.getJwt());

        //JWT-ASSIGN
        //codexsTesterAssertJwtRS256(response.getJwt());
        //codexsTesterAssertJwtRS384(response.getJwt());
        //codexsTesterAssertJwtRS512(response.getJwt());
    }

    /*SAMPLE*/
    @Test
    @Ignore
    public void whenAnyOkRequest_WithNoAuth_RetrieveOk_StatusCode200_ByHttpMethodGET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage("Welcome to sample from Codexs Tester");

        codexsTesterExternal_StatusCode200_RetrieveOK(headersDto, requestDto);
    }

    /*SAMPLE*/
    @Test
    @Ignore
    public void whenAnyOkRequest_WithNoAuth_RetrieveCreated_StatusCode201_ByHttpMethodPOST() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(externalProps.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest("");
        requestDto.setExpectedMessage(null);

        codexsTesterExternal_StatusCode201_RetrieveCreated(headersDto, requestDto);
    }

    /**
     * OAuth2 Example
     * */
    @Test
    public void whenAnyRequest_Oauth2_Correctly_RetrieveCreated_StatusCode200_GET() throws Exception {
        QuickJson dataRequest = new QuickJson("{\"test\":\"123 testing...\"}");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(securitySetup.oauth2Authorization("local"));//OAUTH2
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);
        headersDto.setObjectResponse(String.class);

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(OK_200);
        requestDto.setUrl("http://localhost:33009");
        requestDto.setUri("/huntercodexs/client/api/admin");
        requestDto.setId(""); // /huntercodexs/client/api/admin/{id}
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedMessage("Admin is running on OAUTH2-CLIENT-DEMO");

        Object response = codexsTesterExternalDispatcher(requestDto, headersDto).getBody();

        codexsTesterAssertExact("Admin is running on OAUTH2-CLIENT-DEMO", String.valueOf(response));
    }

    /**
     * Basic Auth Example
     * */
    @Test
    public void whenAnyRequest_BasicAuth_Correctly_RetrieveCreated_StatusCode200_GET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(securitySetup.basicAuthorization("local")); //BASIC AUTH
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_POST);
        headersDto.setObjectResponse(String.class);

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(OK_200);
        requestDto.setUrl("http://localhost:35000");
        requestDto.setUri("/api/auth/basic");
        requestDto.setId(""); // /api/auth/basic/{id}
        requestDto.setDataRequest(null);
        requestDto.setExpectedMessage(null);

        Object response = codexsTesterExternalDispatcher(requestDto, headersDto).getBody();

        codexsTesterAssertGuid(String.valueOf(response));
    }

    /**
     * JWT Auth Example
     * */
    @Test
    public void whenAnyRequest_JwtAuth_Correctly_RetrieveCreated_StatusCode200_GET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(securitySetup.jwtAuthorization("local", false)); //JWT AUTH
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);
        headersDto.setObjectResponse(String.class);

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(OK_200);
        requestDto.setUrl("http://localhost:35000");
        requestDto.setUri("/api/auth/jwt/check");
        requestDto.setId(""); // /api/auth/basic/{id}
        requestDto.setDataRequest(null);
        requestDto.setExpectedMessage(null);

        Object response = codexsTesterExternalDispatcher(requestDto, headersDto).getBody();

        codexsTesterAssertExact("OK", String.valueOf(response));
    }

    /**
     * JWT Assign Auth Example
     * */
    @Test
    public void whenAnyRequest_JwtAssignAuth_Correctly_RetrieveCreated_StatusCode200_GET() throws Exception {
        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(securitySetup.jwtAuthorization("local", true)); //JWT AUTH
        headersDto.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDto.setHttpMethod(HTTP_METHOD_GET);
        headersDto.setObjectResponse(String.class);

        RequestDto requestDto = new RequestDto();
        requestDto.setExpectedCode(OK_200);
        requestDto.setUrl("http://localhost:35000");
        requestDto.setUri("/api/auth/jwt-assign/check");
        requestDto.setId(""); // /api/auth/basic/{id}
        requestDto.setDataRequest(null);
        requestDto.setExpectedMessage(null);

        Object response = codexsTesterExternalDispatcher(requestDto, headersDto).getBody();

        codexsTesterAssertExact("OK", String.valueOf(response));
    }

}
