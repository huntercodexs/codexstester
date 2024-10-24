package com.huntercodexs.codexstester.external;

import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import com.huntercodexs.codexstester.http.AvailableHttpStatus;

public abstract class ExternalRequest3xx extends ExternalRequest4xx {

    protected ExternalRequest3xx(String target) {
        super(target);
    }

    protected void isOk3xxExternalTest() throws Exception {
        executeExternalTest(new RequestDto(), new HeadersDto());
        System.out.println("isOk3xxExternalTest is done");
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 300 (MULTIPLE_CHOICE_300) for Tests
     * */
    protected void codexsTesterExternal_StatusCode300_RetrieveMultipleChoice(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.MULTIPLE_CHOICE_300);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 301 (MOVED_PERMANENTLY_301) for Tests
     * */
    protected void codexsTesterExternal_StatusCode301_RetrieveMovedPermanently(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.MOVED_PERMANENTLY_301);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 302 (FOUND_302) for Tests
     * */
    protected void codexsTesterExternal_StatusCode302_RetrieveFound(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.FOUND_302);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 303 (SEE_OTHER_303) for Tests
     * */
    protected void codexsTesterExternal_StatusCode303_RetrieveSeeOther(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.SEE_OTHER_303);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 304 (NOT_MODIFIED_304) for Tests
     * */
    protected void codexsTesterExternal_StatusCode304_RetrieveNotModified(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.NOT_MODIFIED_304);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 305 (USE_PROXY_DEPRECATED_305) for Tests
     * */
    protected void codexsTesterExternal_StatusCode305_RetrieveUseProxyDeprecated(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.USE_PROXY_DEPRECATED_305);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 306 (UNUSED_DEPRECATED_306) for Tests
     * */
    protected void codexsTesterExternal_StatusCode306_RetrieveUnusedDeprecated(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.UNUSED_DEPRECATED_306);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 307 (TEMPORARY_REDIRECT_307) for Tests
     * */
    protected void codexsTesterExternal_StatusCode307_RetrieveTemporaryRedirect(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.TEMPORARY_REDIRECT_307);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 308 (PERMANENT_REDIRECT_308) for Tests
     * */
    protected void codexsTesterExternal_StatusCode308_RetrievePermanentRedirect(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.PERMANENT_REDIRECT_308);
        executeExternalTest(requestDto, headersDto);
    }

}
