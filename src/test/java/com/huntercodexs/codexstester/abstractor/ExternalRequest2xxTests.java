package com.huntercodexs.codexstester.abstractor;

import com.huntercodexs.codexstester.abstractor.dto.HeadersDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2RequestTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2ResponseTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.RequestDto;
import com.huntercodexs.codexstester.tests.datasource.DataSourceTests;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;

public abstract class ExternalRequest2xxTests extends ExternalRequest3xxTests {

    public void isOk2xxTest() {
        System.out.println("Test 2xx is OK !");
        assertionTest("test2xx", "test2xx");
    }

    /**
     * STATUS CODE 200 (OK_200) TESTS
     * */
    public void codexsTester_StatusCode200_RetrieveOK(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(OK_200);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 201 (CREATED_201) TESTS
     * */
    public void codexsTester_StatusCode201_RetrieveCreated(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(CREATED_201);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 202 (ACCEPTED_202) TESTS
     * */
    public void codexsTester_StatusCode202_RetrieveAccepted(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(ACCEPTED_202);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 203 (NON_AUTHORITATIVE_INFORMATION_203) TESTS
     * */
    public void codexsTester_StatusCode203_RetrieveNonAuthoritativeInformation(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(NON_AUTHORITATIVE_INFORMATION_203);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 204 (NO_CONTENT_204) TESTS
     * */
    public void codexsTester_StatusCode204_RetrieveNoContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(NO_CONTENT_204);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 205 (RESET_CONTENT_205) TESTS
     * */
    public void codexsTester_StatusCode205_RetrieveResetContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(RESET_CONTENT_205);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 206 (PARTIAL_CONTENT_206) TESTS
     * */
    public void codexsTester_StatusCode206_RetrievePartialContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(PARTIAL_CONTENT_206);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 207 (MULT_STATUS_WEBDAV_EN_US_207) TESTS
     * */
    public void codexsTester_StatusCode207_RetrieveMultStatusWebdav(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(MULTI_STATUS_WEBDAV_EN_US_207);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 208 (MULTI_STATUS_WEBDAV_EN_US_208) TESTS
     * */
    public void codexsTester_StatusCode208_RetrieveMultiStatus(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(MULTI_STATUS_WEBDAV_EN_US_208);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 226 (IM_USED_HTTP_DELTA_ENCODING_226) TESTS
     * */
    public void codexsTester_StatusCode226_RetrieveImUsedHttpDeltaEncoding(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(IM_USED_HTTP_DELTA_ENCODING_226);
        execute(requestDto, headersDto);
    }

}
