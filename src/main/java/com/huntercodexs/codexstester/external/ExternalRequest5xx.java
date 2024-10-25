package com.huntercodexs.codexstester.external;

import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import com.huntercodexs.codexstester.http.AvailableHttpStatus;

public abstract class ExternalRequest5xx extends AbstractExternalRestTemplate {

    protected ExternalRequest5xx(String target) {
        super(target);
    }

    protected void isOk5xxExternalTest() throws Exception {
        executeExternalTest(new RequestDto(), new HeadersDto());
        System.out.println("isOk5xxExternalTest is done");
    }

    /**
     * Use Status Code 500 (INTERNAL_SERVER_ERROR_500) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterExternal_StatusCode500_RetrieveInternalServerError(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.INTERNAL_SERVER_ERROR_500);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 501 (NOT_IMPLEMENTED_501) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterExternal_StatusCode501_RetrieveNotImplemented(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.NOT_IMPLEMENTED_501);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 502 (BAD_GATEWAY_502) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterExternal_StatusCode502_RetrieveBadGateway(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.BAD_GATEWAY_502);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 503 (SERVICE_UNAVAILABLE_503) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterExternal_StatusCode503_RetrieveServiceUnavailable(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.SERVICE_UNAVAILABLE_503);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 504 (GATEWAY_TIMEOUT_504) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterExternal_StatusCode504_RetrieveGatewayTimeout(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.GATEWAY_TIMEOUT_504);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 505 (HTTP_VERSION_NOT_SUPPORTED_505) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterExternal_StatusCode505_RetrieveHttpVersionNotSupported(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.HTTP_VERSION_NOT_SUPPORTED_505);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 506 (VARIANT_ALSO_NEGOTIATES_506) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterExternal_StatusCode506_RetrieveVariantAlsoNegotiates(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.VARIANT_ALSO_NEGOTIATES_506);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 507 (INSUFFICIENT_STORAGE_507) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterExternal_StatusCode507_RetrieveInsuficientStorage(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.INSUFFICIENT_STORAGE_507);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 508 (LOOP_DETECTED_WEBDAV_EN_US_508) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterExternal_StatusCode508_RetrieveLoopDetected(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.LOOP_DETECTED_WEBDAV_EN_US_508);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 510 (NOT_EXTENDED_510) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterExternal_StatusCode510_RetrieveNotExtended(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.NOT_EXTENDED_510);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * Use Status Code 511 (NETWORK_AUTHENTICATION_REQUIRED_511) for Tests
     *
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     * */
    protected void codexsTesterExternal_StatusCode511_RetrieveNetworkAuthenticationRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.NETWORK_AUTHENTICATION_REQUIRED_511);
        executeExternalTest(requestDto, headersDto);
    }

}
