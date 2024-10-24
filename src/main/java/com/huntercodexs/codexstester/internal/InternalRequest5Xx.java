package com.huntercodexs.codexstester.internal;

import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import com.huntercodexs.codexstester.http.AvailableHttpStatus;

public abstract class InternalRequest5Xx extends AbstractInternalMockMvc {

    protected InternalRequest5Xx(String target) {
        super(target);
    }

    protected void isOk5xxInternalTest() throws Exception {
        executeInternalTest(new RequestDto(), new HeadersDto());
        System.out.println("isOk5xxInternalTest is done");
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 500 (INTERNAL_SERVER_ERROR_500) for Tests
     * */
    protected void codexsTesterInternal_StatusCode500_RetrieveInternalServerError(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.INTERNAL_SERVER_ERROR_500);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 501 (NOT_IMPLEMENTED_501) for Tests
     * */
    protected void codexsTesterInternal_StatusCode501_RetrieveNotImplemented(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.NOT_IMPLEMENTED_501);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 502 (BAD_GATEWAY_502) for Tests
     * */
    protected void codexsTesterInternal_StatusCode502_RetrieveBadGateway(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.BAD_GATEWAY_502);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 503 (SERVICE_UNAVAILABLE_503) for Tests
     * */
    protected void codexsTesterInternal_StatusCode503_RetrieveServiceUnavailable(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.SERVICE_UNAVAILABLE_503);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 504 (GATEWAY_TIMEOUT_504) for Tests
     * */
    protected void codexsTesterInternal_StatusCode504_RetrieveGatewayTimeout(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.GATEWAY_TIMEOUT_504);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 505 (HTTP_VERSION_NOT_SUPPORTED_505) for Tests
     * */
    protected void codexsTesterInternal_StatusCode505_RetrieveHttpVersionNotSupported(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.HTTP_VERSION_NOT_SUPPORTED_505);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 506 (VARIANT_ALSO_NEGOTIATES_506) for Tests
     * */
    protected void codexsTesterInternal_StatusCode506_RetrieveVariantAlsoNegotiates(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.VARIANT_ALSO_NEGOTIATES_506);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 507 (INSUFFICIENT_STORAGE_507) for Tests
     * */
    protected void codexsTesterInternal_StatusCode507_RetrieveInsuficientStorage(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.INSUFFICIENT_STORAGE_507);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 508 (LOOP_DETECTED_WEBDAV_EN_US_508) for Tests
     * */
    protected void codexsTesterInternal_StatusCode508_RetrieveLoopDetected(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.LOOP_DETECTED_WEBDAV_EN_US_508);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 510 (NOT_EXTENDED_510) for Tests
     * */
    protected void codexsTesterInternal_StatusCode510_RetrieveNotExtended(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.NOT_EXTENDED_510);
        executeInternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 511 (NETWORK_AUTHENTICATION_REQUIRED_511) for Tests
     * */
    protected void codexsTesterInternal_StatusCode511_RetrieveNetworkAuthenticationRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.NETWORK_AUTHENTICATION_REQUIRED_511);
        executeInternalTest(requestDto, headersDto);
    }

}
