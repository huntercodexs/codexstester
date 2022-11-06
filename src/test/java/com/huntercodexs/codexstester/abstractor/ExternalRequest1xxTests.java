package com.huntercodexs.codexstester.abstractor;

import com.huntercodexs.codexstester.abstractor.dto.HeadersDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2RequestTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2ResponseTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.RequestDto;
import com.huntercodexs.codexstester.tests.datasource.DataSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.springframework.http.ResponseEntity;

public abstract class ExternalRequest1xxTests extends ExternalRequest2xxTests {

    protected void isOk1xxTest() throws Exception {
        execute(new RequestDto(), new HeadersDto());
        System.out.println("isOk1xxTest is done");
    }

    /**
     * STATUS CODE 100 (CONTINUE_100) TESTS
     * */
    protected void codexsTester_StatusCode100_RetrieveContinue(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(CONTINUE_100);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 101 (SWITCHING_PROTOCOL_101) TESTS
     * */
    protected void codexsTester_StatusCode101_RetrieveSwitchingProtocol(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(SWITCHING_PROTOCOL_101);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 102 (PROCESSING_WEBDAV_EN_US_102) TESTS
     * */
    protected void codexsTester_StatusCode102_RetrieveProcessing(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(PROCESSING_WEBDAV_EN_US_102);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 103 (EARLY_HINTS_103) TESTS
     * */
    protected void codexsTester_StatusCode103_RetrieveEarlyHints(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(EARLY_HINTS_103);
        execute(requestDto, headersDto);
    }

}
