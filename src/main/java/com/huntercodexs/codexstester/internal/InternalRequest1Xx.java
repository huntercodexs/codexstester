package com.huntercodexs.codexstester.internal;

import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import com.huntercodexs.codexstester.http.AvailableHttpStatus;

public abstract class InternalRequest1Xx extends InternalRequest2Xx {

    protected InternalRequest1Xx(String target) {
        super(target);
    }

    protected void isOk1xxInternalTest() throws Exception {
        executeInternalTest(new RequestDto(), new HeadersDto());
        System.out.println("isOk1xxInternalTest is done");
    }

    /**
     * Use Status Code 100 (CONTINUE_100) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterInternal_StatusCode100_RetrieveContinue(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.CONTINUE_100);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 101 (SWITCHING_PROTOCOL_101) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterInternal_StatusCode101_RetrieveSwitchingProtocol(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.SWITCHING_PROTOCOL_101);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 102 (PROCESSING_WEBDAV_EN_US_102) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterInternal_StatusCode102_RetrieveProcessing(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.PROCESSING_WEBDAV_EN_US_102);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 103 (EARLY_HINTS_103) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterInternal_StatusCode103_RetrieveEarlyHints(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.EARLY_HINTS_103);
        executeInternalTest(requestDto, headersDto);
    }

}
