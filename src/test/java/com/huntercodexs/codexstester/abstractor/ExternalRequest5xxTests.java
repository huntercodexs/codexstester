package com.huntercodexs.codexstester.abstractor;

import com.huntercodexs.codexstester.abstractor.AbstractExternalRequestTests;
import com.huntercodexs.codexstester.abstractor.dto.HeadersDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2RequestTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2ResponseTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.RequestDto;
import com.huntercodexs.codexstester.tests.datasource.DataSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.springframework.http.ResponseEntity;

public abstract class ExternalRequest5xxTests extends AbstractExternalRequestTests {

    public void isOk5xxTest() {
        System.out.println("Test 5xx is OK !");
        assertionTest("test5xx", "test5xx");
    }

    /**
     * STATUS CODE 500 (INTERNAL_SERVER_ERROR_500) TESTS
     * */

    public void codexsTester_StatusCode500_RetrieveInternalServerError(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(INTERNAL_SERVER_ERROR_500);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 501 (NOT_IMPLEMENTED_501) TESTS
     * */

    public void codexsTester_StatusCode501_RetrieveNotImplemented(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(NOT_IMPLEMENTED_501);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 502 (BAD_GATEWAY_502) TESTS
     * */

    public void codexsTester_StatusCode502_RetrieveBadGateway(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(BAD_GATEWAY_502);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 503 (SERVICE_UNAVAILABLE_503) TESTS
     * */

    public void codexsTester_StatusCode503_RetrieveServiceUnavailable(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(SERVICE_UNAVAILABLE_503);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 504 (GATEWAY_TIMEOUT_504) TESTS
     * */

    public void codexsTester_StatusCode504_RetrieveGatewayTimeout(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(GATEWAY_TIMEOUT_504);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 505 (HTTP_VERSION_NOT_SUPPORTED_505) TESTS
     * */

    public void codexsTester_StatusCode505_RetrieveHttpVersionNotSupported(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(HTTP_VERSION_NOT_SUPPORTED_505);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 506 (VARIANT_ALSO_NEGOTIATES_506) TESTS
     * */

    public void codexsTester_StatusCode506_RetrieveVariantAlsoNegotiates(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(VARIANT_ALSO_NEGOTIATES_506);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 507 (INSUFFICIENT_STORAGE_507) TESTS
     * */

    public void codexsTester_StatusCode507_RetrieveInsuficientStorage(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(INSUFFICIENT_STORAGE_507);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 508 (LOOP_DETECTED_WEBDAV_EN_US_508) TESTS
     * */

    public void codexsTester_StatusCode508_RetrieveLoopDetected(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(LOOP_DETECTED_WEBDAV_EN_US_508);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 510 (NOT_EXTENDED_510) TESTS
     * */

    public void codexsTester_StatusCode510_RetrieveNotExtended(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(NOT_EXTENDED_510);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 511 (NETWORK_AUTHENTICATION_REQUIRED_511) TESTS
     * */

    public void codexsTester_StatusCode511_RetrieveNetworkAuthenticationRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(NETWORK_AUTHENTICATION_REQUIRED_511);
        execute(requestDto, headersDto);
    }

}
