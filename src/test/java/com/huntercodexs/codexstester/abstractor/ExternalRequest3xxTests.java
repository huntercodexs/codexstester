package com.huntercodexs.codexstester.abstractor;

import com.huntercodexs.codexstester.abstractor.dto.HeadersDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2RequestTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2ResponseTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.RequestDto;
import com.huntercodexs.codexstester.tests.datasource.DataSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.springframework.http.ResponseEntity;

public abstract class ExternalRequest3xxTests extends ExternalRequest4xxTests {

    protected void isOk3xxTest() throws Exception {
        execute(new RequestDto(), new HeadersDto());
        System.out.println("isOk3xxTest is done");
    }

    /**
     * STATUS CODE 300 (MULTIPLE_CHOICE_300) TESTS
     * */
    protected void codexsTester_StatusCode300_RetrieveMultipleChoice(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(MULTIPLE_CHOICE_300);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 301 (MOVED_PERMANENTLY_301) TESTS
     * */
    protected void codexsTester_StatusCode301_RetrieveMovedPermanently(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(MOVED_PERMANENTLY_301);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 302 (FOUND_302) TESTS
     * */
    protected void codexsTester_StatusCode302_RetrieveFound(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(FOUND_302);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 303 (SEE_OTHER_303) TESTS
     * */
    protected void codexsTester_StatusCode303_RetrieveSeeOther(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(SEE_OTHER_303);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 304 (NOT_MODIFIED_304) TESTS
     * */
    protected void codexsTester_StatusCode304_RetrieveNotModified(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(NOT_MODIFIED_304);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 305 (USE_PROXY_DEPRECATED_305) TESTS
     * */
    protected void codexsTester_StatusCode305_RetrieveUseProxyDeprecated(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(USE_PROXY_DEPRECATED_305);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 306 (UNUSED_DEPRECATED_306) TESTS
     * */
    protected void codexsTester_StatusCode306_RetrieveUnusedDeprecated(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(UNUSED_DEPRECATED_306);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 307 (TEMPORARY_REDIRECT_307) TESTS
     * */
    protected void codexsTester_StatusCode307_RetrieveTemporaryRedirect(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(TEMPORARY_REDIRECT_307);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 308 (PERMANENT_REDIRECT_308) TESTS
     * */
    protected void codexsTester_StatusCode308_RetrievePermanentRedirect(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(PERMANENT_REDIRECT_308);
        execute(requestDto, headersDto);
    }

}
