package codexstester.junit4.external;

import codexstester.setup.bridge.SampleBridgeTest;
import codexstester.setup.datasource.SampleDataSource;
import codexstester.setup.security.SecuritySetup;
import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.JwtResponseDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import com.huntercodexs.codexstester.resource.quickjson.QuickJson;
import com.huntercodexs.codexstester.resource.web.CodexsWebControl;
import com.huntercodexs.codexstester.resource.web.constant.CodexsBrowserForSelenium;
import com.huntercodexs.codexstester.resource.web.constant.CodexsBrowserForSeleniumDto;
import com.huntercodexs.codexstester.security.CodexsSecurity;
import com.huntercodexs.codexstester.security.dto.BasicAuthRequestDto;
import com.huntercodexs.codexstester.security.dto.JwtAuthRequestDto;
import com.huntercodexs.codexstester.security.dto.Oauth2RequestCheckTokenDto;
import com.huntercodexs.codexstester.security.dto.Oauth2RequestTokenDto;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.List;

import static com.huntercodexs.codexstester.resource.web.CodexsWebElements.*;

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
        codexsTesterAssertExact("This is a expected sample response", result, null);
    }

    @Test
    public void oauth2Test() {
        SecuritySetup securitySetup = new SecuritySetup();
        Oauth2RequestTokenDto requestTokenDto = securitySetup.oauth2Token("local");
        CodexsSecurity codexsSecurity = new CodexsSecurity(requestTokenDto);
        String token = codexsSecurity.token();

        codexsTesterAssertGuid(token, null);

        Oauth2RequestCheckTokenDto requestCheckTokenDto = securitySetup.oauth2CheckToken("local", token);
        boolean status = codexsSecurity.checkToken(requestCheckTokenDto);

        codexsTesterAssertBool(true, status, null);
    }

    @Test
    public void basicAuthTest() {
        SecuritySetup securitySetup = new SecuritySetup();
        BasicAuthRequestDto authRequestDto = securitySetup.basicAuth("local");
        CodexsSecurity codexsSecurity = new CodexsSecurity(authRequestDto);
        Object response = codexsSecurity.basicAuth();

        codexsTesterAssertGuid(String.valueOf(response), null);
    }

    @Test
    public void jwtAuthTest() {
        SecuritySetup securitySetup = new SecuritySetup();
        JwtAuthRequestDto jwtAuthRequestDto = securitySetup.jwtAuth("local");
        CodexsSecurity codexsSecurity = new CodexsSecurity(jwtAuthRequestDto);

        JwtResponseDto response = codexsSecurity.jwtAuth(JwtResponseDto.class);

        //JWT
        //codexsTesterAssertJwtHS256(response.getJwt(), null);
        codexsTesterAssertJwtHS384(response.getJwt(), null);
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

        codexsTesterAssertExact("Admin is running on OAUTH2-CLIENT-DEMO", String.valueOf(response), null);
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

        codexsTesterAssertGuid(String.valueOf(response), null);
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

        codexsTesterAssertExact("OK", String.valueOf(response), null);
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

        codexsTesterAssertExact("OK", String.valueOf(response), null);
    }

    /**
     * LOGIN Web Example - using Selenium
     * */
    private static CodexsBrowserForSeleniumDto getCodexsBrowserForSeleniumDto(CodexsBrowserForSelenium browser) {
        CodexsBrowserForSeleniumDto codexsBrowserDto = new CodexsBrowserForSeleniumDto();

        /* > CHROME */
        if (browser.equals(CodexsBrowserForSelenium.CHROME)) {
            codexsBrowserDto.setBrowser(CodexsBrowserForSelenium.CHROME);
            codexsBrowserDto.setQuietMode(false);
            codexsBrowserDto.setOptions(Collections.singletonList("--remote-allow-origins=*"));
            codexsBrowserDto.setWebDriverName("webdriver.chrome.driver");
            codexsBrowserDto.setWebDriverPath("/usr/bin/chromedriver");
            return codexsBrowserDto;
        }

        /* > FIREFOX
         * NOTE: It is required to install the gecko driver for firefox
         * https://github.com/mozilla/geckodriver/releases
         */
        if (browser.equals(CodexsBrowserForSelenium.FIREFOX)) {
            codexsBrowserDto.setBrowser(CodexsBrowserForSelenium.FIREFOX);
            codexsBrowserDto.setQuietMode(false);
            codexsBrowserDto.setWebDriverName("webdriver.gecko.driver");
            codexsBrowserDto.setWebDriverPath("/home/jereelton/.mozilla/webdriver/geckodriver");
            return codexsBrowserDto;
        }

        /* > OPERA
         * NOTE: It is required to install the operadriver
         * https://github.com/operasoftware/operachromiumdriver/releases
         */
        if (browser.equals(CodexsBrowserForSelenium.OPERA)) {
            codexsBrowserDto.setBrowser(CodexsBrowserForSelenium.OPERA);
            codexsBrowserDto.setQuietMode(false);
            codexsBrowserDto.setOptions(Collections.singletonList("--remote-allow-origins=*"));
            codexsBrowserDto.setWebDriverName("webdriver.opera.driver");
            codexsBrowserDto.setWebDriverPath("/home/jereelton/.local/bin/operalinux/operadriver");
            codexsBrowserDto.setBrowserBinaryPath("/usr/bin/opera");
            return codexsBrowserDto;
        }

        throw new RuntimeException("[Critical Error] Invalid Browser: " + browser.name());
    }

    private void loginRequest(String username, String password, boolean status, CodexsBrowserForSelenium browser) {

        //DATA-----------------
        CodexsBrowserForSeleniumDto codexsBrowserDto = getCodexsBrowserForSeleniumDto(browser);

        //SETUP-----------------
        CodexsWebControl codexsWebControl = new CodexsWebControl(codexsBrowserDto);
        codexsWebControl.browserSetup();

        //NAVIGATE--------------------
        codexsWebControl.navigate("https://practicetestautomation.com/practice-test-login/");

        //OPERATION-----------------
        WebElement usernameField = codexsWebControl.await().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(inputElement("username"))));
        usernameField.sendKeys(username);

        WebElement passwordField = codexsWebControl.await().until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath(inputElement("password"))));
        passwordField.sendKeys(password);

        WebElement submitButton = codexsWebControl.await().until(
                ExpectedConditions.elementToBeClickable(By.xpath(buttonElement("submit"))));
        submitButton.click();

        //ASSERT--------------------
        if (status) {

            // When login is successfully the button logout is visible
            WebElement logoutButton = codexsWebControl.await().until(
                    ExpectedConditions.elementToBeClickable(By.xpath(aElement("Log out"))));

            codexsTesterAssertBool(status, logoutButton.isDisplayed(), null);

        } else {

            // When login is wrong the p=text with id=error is visible
            WebElement loginError = codexsWebControl.await().until(
                    ExpectedConditions.elementToBeClickable(By.xpath(divElement("error"))));

            codexsTesterAssertBool(true, loginError.isDisplayed(), null);

            if (loginError.getText().contains("username")) {
                codexsTesterAssertExact("Your username is invalid!", loginError.getText(), null);
            } else {
                codexsTesterAssertExact("Your password is invalid!", loginError.getText(), null);
            }

        }

        //FINISH--------------------
        codexsWebControl.finish();

    }

    @Test
    public void whenLoginIsRequest_WithCorrectUsernamePassword_CHROMEBROWSER_SELENIUM_WEB_Test() {

        String username = "student";
        String password = "Password123";
        loginRequest(username, password, true, CodexsBrowserForSelenium.CHROME);

    }

    @Test
    public void whenLoginIsRequest_WithWrongUsername_CHROMEBROWSER_SELENIUM_WEB_Test() {

        String username = "stud3nt";
        String password = "Password123";
        loginRequest(username, password, false, CodexsBrowserForSelenium.CHROME);

    }

    @Test
    public void whenLoginIsRequest_WithWrongPassword_CHROMEBROWSER_SELENIUM_WEB_Test() {

        String username = "student";
        String password = "Password444";
        loginRequest(username, password, false, CodexsBrowserForSelenium.CHROME);

    }

    @Test
    public void whenLoginIsRequest_WithCorrectUsernamePassword_FIREFOXBROWSER_SELENIUM_WEB_Test() {

        String username = "student";
        String password = "Password123";
        loginRequest(username, password, true, CodexsBrowserForSelenium.FIREFOX);

    }

    @Test
    public void whenLoginIsRequest_WithWrongUsername_FIREFOXBROWSER_SELENIUM_WEB_Test() {

        String username = "stud3nt";
        String password = "Password123";
        loginRequest(username, password, false, CodexsBrowserForSelenium.FIREFOX);

    }

    @Test
    public void whenLoginIsRequest_WithWrongPassword_FIREFOXBROWSER_SELENIUM_WEB_Test() {

        String username = "student";
        String password = "Password444";
        loginRequest(username, password, false, CodexsBrowserForSelenium.FIREFOX);

    }

    @Test
    public void whenLoginIsRequest_WithCorrectUsernamePassword_OPERABROWSER_SELENIUM_WEB_Test() {

        String username = "student";
        String password = "Password123";
        loginRequest(username, password, true, CodexsBrowserForSelenium.OPERA);

    }

    @Test
    public void whenLoginIsRequest_WithWrongUsername_OPERABROWSER_SELENIUM_WEB_Test() {

        String username = "stud3nt";
        String password = "Password123";
        loginRequest(username, password, false, CodexsBrowserForSelenium.OPERA);

    }

    @Test
    public void whenLoginIsRequest_WithWrongPassword_OPERABROWSER_SELENIUM_WEB_Test() {

        String username = "student";
        String password = "Password444";
        loginRequest(username, password, false, CodexsBrowserForSelenium.OPERA);

    }

}
