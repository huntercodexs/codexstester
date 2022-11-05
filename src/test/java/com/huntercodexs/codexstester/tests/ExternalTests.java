package com.huntercodexs.codexstester.tests;

import com.huntercodexs.codexstester.abstractor.dto.HeadersDto;
import com.huntercodexs.codexstester.abstractor.dto.RequestDto;
import com.huntercodexs.codexstester.tests.datasource.DataSourceTests;
import com.huntercodexs.codexstester.tests.external.ExternalRequestTests;
import net.minidev.json.JSONObject;
import org.junit.Test;

public class ExternalTests extends ExternalRequestTests {

    @Test
    public void sampleTest_400() throws Exception {
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

        postTest_400_DEV(headersDto, requestDto);
    }

}
