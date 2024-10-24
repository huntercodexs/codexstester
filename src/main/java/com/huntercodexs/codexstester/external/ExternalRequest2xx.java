package com.huntercodexs.codexstester.external;

import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import com.huntercodexs.codexstester.http.AvailableHttpStatus;

public abstract class ExternalRequest2xx extends ExternalRequest3xx {

    protected ExternalRequest2xx(String target) {
        super(target);
    }

    protected void isOk2xxExternalTest() throws Exception {
        executeExternalTest(new RequestDto(), new HeadersDto());
        System.out.println("isOk2xxExternalTest is done");
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 200 (OK_200) for Tests
     * */
    protected void codexsTesterExternal_StatusCode200_RetrieveOK(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.OK_200);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 201 (CREATED_201) for Tests
     * */
    protected void codexsTesterExternal_StatusCode201_RetrieveCreated(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.CREATED_201);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 202 (ACCEPTED_202) for Tests
     * */
    protected void codexsTesterExternal_StatusCode202_RetrieveAccepted(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.ACCEPTED_202);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 203 (NON_AUTHORITATIVE_INFORMATION_203) for Tests
     * */
    protected void codexsTesterExternal_StatusCode203_RetrieveNonAuthoritativeInformation(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.NON_AUTHORITATIVE_INFORMATION_203);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 204 (NO_CONTENT_204) for Tests
     * */
    protected void codexsTesterExternal_StatusCode204_RetrieveNoContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.NO_CONTENT_204);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 205 (RESET_CONTENT_205) for Tests
     * */
    protected void codexsTesterExternal_StatusCode205_RetrieveResetContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.RESET_CONTENT_205);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 206 (PARTIAL_CONTENT_206) for Tests
     * */
    protected void codexsTesterExternal_StatusCode206_RetrievePartialContent(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.PARTIAL_CONTENT_206);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 207 (MULT_STATUS_WEBDAV_EN_US_207) for Tests
     * */
    protected void codexsTesterExternal_StatusCode207_RetrieveMultStatusWebdav(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.MULTI_STATUS_WEBDAV_EN_US_207);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 208 (MULTI_STATUS_WEBDAV_EN_US_208) for Tests
     * */
    protected void codexsTesterExternal_StatusCode208_RetrieveMultiStatus(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.MULTI_STATUS_WEBDAV_EN_US_208);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * @param headersDto (Object: HeadersDto)
     * @param requestDto (Object: RequestDto)
     * @throws Exception (Exception: generic exception)
     * @author huntercodexs (powered by jereelton-devel)
     *  Use Status Code 226 (IM_USED_HTTP_DELTA_ENCODING_226) for Tests
     * */
    protected void codexsTesterExternal_StatusCode226_RetrieveImUsedHttpDeltaEncoding(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.IM_USED_HTTP_DELTA_ENCODING_226);
        executeExternalTest(requestDto, headersDto);
    }

}
