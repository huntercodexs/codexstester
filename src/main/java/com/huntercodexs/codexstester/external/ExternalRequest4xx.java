package com.huntercodexs.codexstester.external;

import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import com.huntercodexs.codexstester.http.AvailableHttpStatus;

public abstract class ExternalRequest4xx extends ExternalRequest5xx {

    protected ExternalRequest4xx(String target) {
        super(target);
    }

    protected void isOk4xxExternalTest() throws Exception {
        executeExternalTest(new RequestDto(), new HeadersDto());
        System.out.println("isOk4xxExternalTest is done");
    }

    /**
     * STATUS CODE 400 (BAD-REQUEST) TESTS
     * */

    protected void codexsTesterExternal_StatusCode400_RetrieveBadRequest(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.BAD_REQUEST_400);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 401 (UNAUTHORIZED) TESTS
     * */

    protected void codexsTesterExternal_StatusCode401_RetrieveUnauthorized(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.UNAUTHORIZED_401);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 402 (PAYMENT-REQUIRED) TESTS
     * */

    protected void codexsTesterExternal_StatusCode402_RetrievePaymentRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.PAYMENT_REQUIRED_EXPERIMENTAL_402);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 403 (FORBIDDEN) TESTS
     * */

    protected void codexsTesterExternal_StatusCode403_RetrieveForbidden(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.FORBIDDEN_403);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 404 (NOT-FOUND) TESTS
     * */

    protected void codexsTesterExternal_StatusCode404_RetrieveNotFound(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.NOT_FOUND_404);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 405 (METHOD-NOT-ALLOWED) TESTS
     * */

    protected void codexsTesterExternal_StatusCode405_RetrieveMethodNotAllowed(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.METHOD_NOT_ALLOWED_405);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 406 (NOT-ACCEPTABLE) TESTS
     * */

    protected void codexsTesterExternal_StatusCode406_RetrieveNotAcceptable(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.NOT_ACCEPTABLE_406);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 407 (PROXY-AUTHENTICATION-REQUIRED) TESTS
     * */

    protected void codexsTesterExternal_StatusCode407_RetrieveProxyAuthenticationRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.PROXY_AUTHENTICATION_REQUIRED_407);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 408 (REQUEST-TIMEOUT) TESTS
     * */

    protected void codexsTesterExternal_StatusCode408_RetrieveRequestTimeout(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.REQUEST_TIMEOUT_408);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 409 (CONFLICT) TESTS
     * */

    protected void codexsTesterExternal_StatusCode409_RetrieveConflict(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.CONFLICT_409);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 410 (GONE) TESTS
     * */

    protected void codexsTesterExternal_StatusCode410_RetrieveGone(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.GONE_410);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 411 (LENGTH_REQUIRED_411) TESTS
     * */

    protected void codexsTesterExternal_StatusCode411_RetrieveLengthRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.LENGTH_REQUIRED_411);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 412 (PRECONDITION_FAILED_412) TESTS
     * */

    protected void codexsTesterExternal_StatusCode412_RetrievePreconditionFailed(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.PRECONDITION_FAILED_412);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 413 (PAYLOAD_TOO_LARGE_413) TESTS
     * */

    protected void codexsTesterExternal_StatusCode413_RetrievePayloadTooLarge(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.PAYLOAD_TOO_LARGE_413);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 414 (URI_TOO_LONG_414) TESTS
     * */

    protected void codexsTesterExternal_StatusCode414_RetrieveUriTooLong(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.URI_TOO_LONG_414);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 415 (UNSUPPORTED_MEDIA_TYPE_415) TESTS
     * */

    protected void codexsTesterExternal_StatusCode415_RetrieveUnsupportedMediaType(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.UNSUPPORTED_MEDIA_TYPE_415);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 416 (REQUESTED_RANGE_NOT_SATISFIABLE_416) TESTS
     * */

    protected void codexsTesterExternal_StatusCode416_RetrieveRequestedRangeNotSatisfiable(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE_416);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 417 (EXPECTATION_FAILED_417) TESTS
     * */

    protected void codexsTesterExternal_StatusCode417_RetrieveExpectationFailed(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.EXPECTATION_FAILED_417);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 418 (IM_A_TEAPOT_418) TESTS
     * */

    protected void codexsTesterExternal_StatusCode418_RetrieveImATeapotLengthRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.IM_A_TEAPOT_418);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 421 (MISDIRECTED_REQUEST_421) TESTS
     * */

    protected void codexsTesterExternal_StatusCode421_RetrieveMisDirectedRequest(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.MISDIRECTED_REQUEST_421);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 422 (UNPROCESSABLE_ENTITY_WEBDAV_EN_US_422) TESTS
     * */

    protected void codexsTesterExternal_StatusCode422_RetrieveUnprocessableEntity(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.UNPROCESSABLE_ENTITY_WEBDAV_EN_US_422);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 423 (LOCKED_WEBDAV_EN_US_423) TESTS
     * */

    protected void codexsTesterExternal_StatusCode423_RetrieveLocked(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.LOCKED_WEBDAV_EN_US_423);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 424 (FAILED_DEPENDENCY_WEBDAV_EN_US_424) TESTS
     * */

    protected void codexsTesterExternal_StatusCode424_RetrieveFailedDependency(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.FAILED_DEPENDENCY_WEBDAV_EN_US_424);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 425 (TOO_EARLY_425) TESTS
     * */

    protected void codexsTesterExternal_StatusCode425_RetrieveTooEarly(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.TOO_EARLY_425);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 426 (UPGRADE_REQUIRED_426) TESTS
     * */

    protected void codexsTesterExternal_StatusCode426_RetrieveUpgradeRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.UPGRADE_REQUIRED_426);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 428 (PRECONDITION_REQUIRED_428) TESTS
     * */

    protected void codexsTesterExternal_StatusCode428_RetrievePreConditionRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.PRECONDITION_REQUIRED_428);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 429 (TOO_MANY_REQUESTS_429) TESTS
     * */

    protected void codexsTesterExternal_StatusCode429_RetrieveTooManyRequest(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.TOO_MANY_REQUESTS_429);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 431 (REQUEST_HEADER_FIELDS_TOO_LARGE_431) TESTS
     * */

    protected void codexsTesterExternal_StatusCode431_RetrieveRequestHeaderFieldsTooLarge(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.REQUEST_HEADER_FIELDS_TOO_LARGE_431);
        executeExternalTest(requestDto, headersDto);
    }

    /**
     * STATUS CODE 451 (UNAVAILABLE_FOR_LEGAL_REASONS_451) TESTS
     * */

    protected void codexsTesterExternal_StatusCode451_RetrieveUnavailableForLegalReasons(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(AvailableHttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS_451);
        executeExternalTest(requestDto, headersDto);
    }
}