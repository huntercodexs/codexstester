package com.huntercodexs.codexstester.internal;

import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import com.huntercodexs.codexstester.http.AvailableHttpStatus;

public abstract class InternalRequest3Xx extends InternalRequest4Xx {

    protected InternalRequest3Xx(String target) {
        super(target);
    }

    protected void isOk3xxInternalTest() throws Exception {
        executeInternalTest(new RequestDto(), new HeadersDto());
        System.out.println("isOk3xxInternalTest is done");
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 300 (MULTIPLE_CHOICE_300) for tests
     * */
    protected void codexsTesterInternal_StatusCode300_RetrieveMultipleChoice(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.MULTIPLE_CHOICE_300);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 301 (MOVED_PERMANENTLY_301) for tests
     * */
    protected void codexsTesterInternal_StatusCode301_RetrieveMovedPermanently(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.MOVED_PERMANENTLY_301);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 302 (FOUND_302) for tests
     * */
    protected void codexsTesterInternal_StatusCode302_RetrieveFound(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.FOUND_302);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 303 (SEE_OTHER_303) for tests
     * */
    protected void codexsTesterInternal_StatusCode303_RetrieveSeeOther(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.SEE_OTHER_303);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 304 (NOT_MODIFIED_304) for tests
     * */
    protected void codexsTesterInternal_StatusCode304_RetrieveNotModified(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.NOT_MODIFIED_304);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 305 (USE_PROXY_DEPRECATED_305) for tests
     * */
    protected void codexsTesterInternal_StatusCode305_RetrieveUseProxyDeprecated(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.USE_PROXY_DEPRECATED_305);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 306 (UNUSED_DEPRECATED_306) for tests
     * */
    protected void codexsTesterInternal_StatusCode306_RetrieveUnusedDeprecated(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.UNUSED_DEPRECATED_306);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 307 (TEMPORARY_REDIRECT_307) for tests
     * */
    protected void codexsTesterInternal_StatusCode307_RetrieveTemporaryRedirect(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.TEMPORARY_REDIRECT_307);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 308 (PERMANENT_REDIRECT_308) for tests
     * */
    protected void codexsTesterInternal_StatusCode308_RetrievePermanentRedirect(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.PERMANENT_REDIRECT_308);
        executeInternalTest(requestDto, headersDto);
    }

}
