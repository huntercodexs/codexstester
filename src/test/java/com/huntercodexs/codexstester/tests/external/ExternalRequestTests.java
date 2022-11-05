package com.huntercodexs.codexstester.tests.external;

import com.huntercodexs.codexstester.abstractor.AbstractExternalRequestTests;
import com.huntercodexs.codexstester.abstractor.dto.HeadersDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2RequestTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2ResponseTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.RequestDto;
import com.huntercodexs.codexstester.tests.datasource.DataSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public abstract class ExternalRequestTests extends AbstractExternalRequestTests {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    public void postTest_400_DEV(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    /**
     * BAD-REQUEST TESTS - STATUS CODE 400 - HTTP METHOD POST
     * */

    @Test
    public void whenAnyRequestTest_BasicAuth_RetrieveBadRequest_400() throws Exception {
        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(BAD_REQUEST_400);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_BearerToken_RetrieveBadRequest_400() throws Exception {
        String bearerToken = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(bearerToken);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(400);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_OAuth2_RetrieveBadRequest_400() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(400);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_NoAuth_RetrieveBadRequest_400() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(400);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_Specific_RetrieveBadRequest_400() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
        dataRequest.put("PUT-HERE-SPECIFIC-FIELD", "");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(400);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    /**
     * UNAUTHORIZED TESTS - STATUS CODE 401 - HTTP METHOD POST
     * */

    @Test
    public void whenAnyRequestTest_BasicAuth_RetrieveUnauthorized_401() throws Exception {
        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(401);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_BearerToken_RetrieveUnauthorized_401() throws Exception {
        String bearerToken = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(bearerToken);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(401);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_OAuth2_RetrieveUnauthorized_401() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(401);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_NoAuth_RetrieveUnauthorized_401() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(401);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_Specific_RetrieveUnauthorized_401() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
        dataRequest.put("PUT-HERE-SPECIFIC-FIELD", "");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(401);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    /**
     * PAYMENT-REQUIRED TESTS - STATUS CODE 402 - HTTP METHOD POST
     * */

    @Test
    public void whenAnyRequestTest_BasicAuth_RetrievePaymentRequired_402() throws Exception {
        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(402);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_BearerToken_RetrievePaymentRequired_402() throws Exception {
        String bearerToken = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(bearerToken);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(402);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_OAuth2_RetrievePaymentRequired_402() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(402);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_NoAuth_RetrievePaymentRequired_402() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(402);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_Specific_RetrievePaymentRequired_402() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
        dataRequest.put("PUT-HERE-SPECIFIC-FIELD", "");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(402);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    /**
     * FORBIDDEN TESTS - STATUS CODE 403 - HTTP METHOD POST
     * */

    @Test
    public void whenAnyRequestTest_BasicAuth_RetrieveForbidden_403() throws Exception {
        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(403);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_BearerToken_RetrieveForbidden_403() throws Exception {
        String bearerToken = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(bearerToken);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(403);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_OAuth2_RetrieveForbidden_403() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(403);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_NoAuth_RetrieveForbidden_403() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(403);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_Specific_RetrieveForbidden_403() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
        dataRequest.put("PUT-HERE-SPECIFIC-FIELD", "");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(403);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    /**
     * NOT-FOUND TESTS - STATUS CODE 404 - HTTP METHOD POST
     * */

    @Test
    public void whenAnyRequestTest_BasicAuth_RetrieveNotFound_404() throws Exception {
        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(404);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_BearerToken_RetrieveNotFound_404() throws Exception {
        String bearerToken = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(bearerToken);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(404);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_OAuth2_RetrieveNotFound_404() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(404);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_NoAuth_RetrieveNotFound_404() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(404);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_Specific_RetrieveNotFound_404() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
        dataRequest.put("PUT-HERE-SPECIFIC-FIELD", "");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(404);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    /**
     * METHOD-NOT-ALLOWED TESTS - STATUS CODE 405 - HTTP METHOD POST
     * */

    @Test
    public void whenAnyRequestTest_BasicAuth_RetrieveMethodNotAllowed_405() throws Exception {
        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(405);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_BearerToken_RetrieveMethodNotAllowed_405() throws Exception {
        String bearerToken = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(bearerToken);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(405);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_OAuth2_RetrieveMethodNotAllowed_405() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(405);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_NoAuth_RetrieveMethodNotAllowed_405() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(405);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_Specific_RetrieveMethodNotAllowed_405() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
        dataRequest.put("PUT-HERE-SPECIFIC-FIELD", "");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(405);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    /**
     * NOT-ACCEPTABLE TESTS - STATUS CODE 406 - HTTP METHOD POST
     * */

    @Test
    public void whenAnyRequestTest_BasicAuth_RetrieveNotAcceptable_406() throws Exception {
        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(406);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_BearerToken_RetrieveNotAcceptable_406() throws Exception {
        String bearerToken = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(bearerToken);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(406);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_OAuth2_RetrieveNotAcceptable_406() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(406);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_NoAuth_RetrieveNotAcceptable_406() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(406);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_Specific_RetrieveNotAcceptable_406() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
        dataRequest.put("PUT-HERE-SPECIFIC-FIELD", "");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(406);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    /**
     * PROXY-AUTHENTICATION-REQUIRED TESTS - STATUS CODE 407 - HTTP METHOD POST
     * */

    @Test
    public void whenAnyRequestTest_BasicAuth_RetrieveProxyAuthenticationRequired_407() throws Exception {
        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(407);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_BearerToken_RetrieveProxyAuthenticationRequired_407() throws Exception {
        String bearerToken = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(bearerToken);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(407);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_OAuth2_RetrieveProxyAuthenticationRequired_407() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(407);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_NoAuth_RetrieveProxyAuthenticationRequired_407() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(407);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_Specific_RetrieveProxyAuthenticationRequired_407() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
        dataRequest.put("PUT-HERE-SPECIFIC-FIELD", "");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(407);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    /**
     * REQUEST-TIMEOUT TESTS - STATUS CODE 408 - HTTP METHOD POST
     * */

    @Test
    public void whenAnyRequestTest_BasicAuth_RetrieveRequestTimeout_408() throws Exception {
        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(408);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_BearerToken_RetrieveRequestTimeout_408() throws Exception {
        String bearerToken = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(bearerToken);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(408);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_OAuth2_RetrieveRequestTimeout_408() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(408);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_NoAuth_RetrieveRequestTimeout_408() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(408);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_Specific_RetrieveRequestTimeout_408() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
        dataRequest.put("PUT-HERE-SPECIFIC-FIELD", "");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(408);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    /**
     * METHOD-NOT-ALLOWED TESTS - STATUS CODE 409 - HTTP METHOD POST
     * */

    @Test
    public void whenAnyRequestTest_BasicAuth_RetrieveConflict_409() throws Exception {
        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(409);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_BearerToken_RetrieveConflict_409() throws Exception {
        String bearerToken = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(bearerToken);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(409);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_OAuth2_RetrieveConflict_409() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(409);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_NoAuth_RetrieveConflict_409() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(409);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_Specific_RetrieveConflict_409() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
        dataRequest.put("PUT-HERE-SPECIFIC-FIELD", "");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(409);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    /**
     * GONE TESTS - STATUS CODE 410 - HTTP METHOD POST
     * */

    @Test
    public void whenAnyRequestTest_BasicAuth_RetrieveGone_410() throws Exception {
        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(410);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_BearerToken_RetrieveGone_410() throws Exception {
        String bearerToken = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(bearerToken);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(410);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_OAuth2_RetrieveGone_410() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(410);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_NoAuth_RetrieveGone_410() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(410);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_Specific_RetrieveGone_410() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
        dataRequest.put("PUT-HERE-SPECIFIC-FIELD", "");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(410);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    /**
     * LENGTH REQUIRED TESTS - STATUS CODE 411 - HTTP METHOD POST
     * */

    @Test
    public void whenAnyRequestTest_BasicAuth_RetrieveLengthRequired_411() throws Exception {
        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBasic(basicAuth);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(411);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_BearerToken_RetrieveLengthRequired_411() throws Exception {
        String bearerToken = "PUT HERE THE BASIC AUTHORIZATION STRING";
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(bearerToken);
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(411);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_OAuth2_RetrieveLengthRequired_411() throws Exception {
        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(411);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_NoAuth_RetrieveLengthRequired_411() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(411);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

    @Test
    public void whenAnyRequestTest_Specific_RetrieveLengthRequired_411() throws Exception {
        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
        dataRequest.put("PUT-HERE-SPECIFIC-FIELD", "");

        HeadersDto headersDto = new HeadersDto();
        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
        headersDto.setContentType("application/json;charset=UTF-8");

        RequestDto requestDto = new RequestDto();
        requestDto.setUri(props.getProperty("external.tests.base-uri"));
        requestDto.setId("");
        requestDto.setDataRequest(dataRequest.toString());
        requestDto.setExpectedCode(411);
        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/

        assertResultFromRequestByHttpPost(requestDto, headersDto);
    }

}
