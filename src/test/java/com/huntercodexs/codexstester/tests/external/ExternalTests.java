package com.huntercodexs.codexstester.tests.external;

import com.huntercodexs.codexstester.abstractor.ExternalRequestTests;
import com.huntercodexs.codexstester.abstractor.dto.HeadersDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2RequestTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2ResponseTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.RequestDto;
import com.huntercodexs.codexstester.tests.datasource.DataSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

public class ExternalTests extends ExternalRequestTests {

    @Test
    public void test1xx() {
        isOk1xxTest();
    }

    @Test
    public void test2xx() {
        isOk2xxTest();
    }

    @Test
    public void test3xx() {
        isOk3xxTest();
    }

    @Test
    public void test4xx() {
        isOk4xxTest();
    }

    @Test
    public void test5xx() {
        isOk5xxTest();
    }

//    @Test
//    public void whenAnyRequestTest_BasicAuth_RetrieveBadRequest_400_SAMPLE() throws Exception {
//        String basicAuth = "PUT HERE THE BASIC AUTHORIZATION STRING";
//        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
//
//        HeadersDto headersDto = new HeadersDto();
//        headersDto.setAuthorizationBasic(basicAuth);
//        headersDto.setAddtionalName("PUT HERE ANY ADDITIONAL HEADER NAME");
//        headersDto.setAddtionalValue("PUT HERE ANY ADDITIONAL HEADER VALUE");
//        headersDto.setContentType("application/json;charset=UTF-8");
//
//        RequestDto requestDto = new RequestDto();
//        requestDto.setUri(props.getProperty("external.tests.base-uri"));
//        requestDto.setId("");
//        requestDto.setDataRequest(dataRequest.toString());
//        requestDto.setExpectedCode(BAD_REQUEST_400);
//        requestDto.setExpetecdMessage(null); /*SET HERE ANY MESSAGE TO MATCHER IN THIS TEST OR SET NULL*/
//
//        whenAnyRequestTest_BasicAuth_RetrieveBadRequest_400(headersDto, requestDto);
//    }

//    public void codexsTester_StatusCode200_BasicAuth_RetrieveOK(HeadersDto headersDto, RequestDto requestDto) throws Exception {
//        String basicAuth = headersDto.getAuthorizationBasic();
//        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
//
//        /*HeadersDto*/
//        headersDto.setAuthorizationBasic(basicAuth);
//        headersDto.setAddtionalName(headersDto.getAddtionalName());
//        headersDto.setAddtionalValue(headersDto.getAddtionalValue());
//        headersDto.setContentType(headersDto.getContentType());
//
//        /*RequestDto*/
//        requestDto.setUri(requestDto.getUri());
//        requestDto.setId(requestDto.getUri());
//        requestDto.setDataRequest(dataRequest.toString());
//        requestDto.setExpectedCode(OK_200);
//        requestDto.setExpetecdMessage(requestDto.getExpetecdMessage());
//
//        execute(requestDto, headersDto);
//    }
//
//    public void codexsTester_StatusCode200_BearerToken_RetrieveOK(HeadersDto headersDto, RequestDto requestDto) throws Exception {
//        String bearerToken = headersDto.getAuthorizationBearer();
//        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
//
//        /*HeadersDto*/
//        headersDto.setAuthorizationBearer(bearerToken);
//        headersDto.setAddtionalName(headersDto.getAddtionalName());
//        headersDto.setAddtionalValue(headersDto.getAddtionalValue());
//        headersDto.setContentType(headersDto.getContentType());
//
//        /*RequestDto*/
//        requestDto.setUri(requestDto.getUri());
//        requestDto.setId(requestDto.getUri());
//        requestDto.setDataRequest(dataRequest.toString());
//        requestDto.setExpectedCode(OK_200);
//        requestDto.setExpetecdMessage(requestDto.getExpetecdMessage());
//
//        execute(requestDto, headersDto);
//    }
//
//    public void codexsTester_StatusCode200_OAuth2_RetrieveOK(HeadersDto headersDto, RequestDto requestDto) throws Exception {
//        Oauth2RequestTokenDto oauth2RequestTokenDto = DataSourceTests.dataSourceOAuth2Token();
//        ResponseEntity<Oauth2ResponseTokenDto> response = getToken(oauth2RequestTokenDto);
//        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
//
//        /*HeadersDto*/
//        headersDto.setAuthorizationBearer(response.getBody().getAccess_token());
//        headersDto.setAddtionalName(headersDto.getAddtionalName());
//        headersDto.setAddtionalValue(headersDto.getAddtionalValue());
//        headersDto.setContentType(headersDto.getContentType());
//
//        /*RequestDto*/
//        requestDto.setUri(requestDto.getUri());
//        requestDto.setId(requestDto.getUri());
//        requestDto.setDataRequest(dataRequest.toString());
//        requestDto.setExpectedCode(OK_200);
//        requestDto.setExpetecdMessage(requestDto.getExpetecdMessage());
//
//        execute(requestDto, headersDto);
//    }
//
//    public void codexsTester_StatusCode200_NoAuth_RetrieveOK(HeadersDto headersDto, RequestDto requestDto) throws Exception {
//        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
//
//        /*HeadersDto*/
//        headersDto.setAddtionalName(headersDto.getAddtionalName());
//        headersDto.setAddtionalValue(headersDto.getAddtionalValue());
//        headersDto.setContentType(headersDto.getContentType());
//
//        /*RequestDto*/
//        requestDto.setUri(requestDto.getUri());
//        requestDto.setId(requestDto.getUri());
//        requestDto.setDataRequest(dataRequest.toString());
//        requestDto.setExpectedCode(OK_200);
//        requestDto.setExpetecdMessage(requestDto.getExpetecdMessage());
//
//        execute(requestDto, headersDto);
//    }
//
//    public void codexsTester_StatusCode200_Specific_RetrieveOK(HeadersDto headersDto, RequestDto requestDto) throws Exception {
//        JSONObject dataRequest = DataSourceTests.dataSourceAddressRequest();
//
//        /*HeadersDto*/
//        headersDto.setAddtionalName(headersDto.getAddtionalName());
//        headersDto.setAddtionalValue(headersDto.getAddtionalValue());
//        headersDto.setContentType(headersDto.getContentType());
//
//        /*RequestDto*/
//        requestDto.setUri(requestDto.getUri());
//        requestDto.setId(requestDto.getUri());
//        requestDto.setDataRequest(dataRequest.toString());
//        requestDto.setExpectedCode(OK_200);
//        requestDto.setExpetecdMessage(requestDto.getExpetecdMessage());
//
//        execute(requestDto, headersDto);
//    }

}
