package com.huntercodexs.codexstester.abstractor;

import com.huntercodexs.codexstester.abstractor.dto.HeadersDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2RequestTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2ResponseTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.RequestDto;
import com.huntercodexs.codexstester.tests.datasource.DataSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.springframework.http.ResponseEntity;

public abstract class ExternalRequest4xxTests extends ExternalRequest5xxTests {

    public void isOk4xxTest() {
        System.out.println("Test 4xx is OK !");
        assertionTest("test4xx", "test4xx");
    }

    /**
     * STATUS CODE 400 (BAD-REQUEST) TESTS
     * */

    public void codexsTester_StatusCode400_RetrieveBadRequest(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(BAD_REQUEST_400);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 401 (UNAUTHORIZED) TESTS
     * */

    public void codexsTester_StatusCode401_RetrieveUnauthorized(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(UNAUTHORIZED_401);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 402 (PAYMENT-REQUIRED) TESTS
     * */

    public void codexsTester_StatusCode402_RetrievePaymentRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(PAYMENT_REQUIRED_EXPERIMENTAL_402);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 403 (FORBIDDEN) TESTS
     * */

    public void codexsTester_StatusCode403_RetrieveForbidden(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(FORBIDDEN_403);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 404 (NOT-FOUND) TESTS
     * */

    public void codexsTester_StatusCode404_RetrieveNotFound(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(NOT_FOUND_404);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 405 (METHOD-NOT-ALLOWED) TESTS
     * */

    public void codexsTester_StatusCode405_RetrieveMethodNotAllowed(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(METHOD_NOT_ALLOWED_405);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 406 (NOT-ACCEPTABLE) TESTS
     * */

    public void codexsTester_StatusCode406_RetrieveNotAcceptable(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(NOT_ACCEPTABLE_406);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 407 (PROXY-AUTHENTICATION-REQUIRED) TESTS
     * */

    public void codexsTester_StatusCode407_RetrieveProxyAuthenticationRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(PROXY_AUTHENTICATION_REQUIRED_407);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 408 (REQUEST-TIMEOUT) TESTS
     * */

    public void codexsTester_StatusCode408_RetrieveRequestTimeout(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(REQUEST_TIMEOUT_408);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 409 (CONFLICT) TESTS
     * */

    public void codexsTester_StatusCode409_RetrieveConflict(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(CONFLICT_409);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 410 (GONE) TESTS
     * */

    public void codexsTester_StatusCode410_RetrieveGone(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(GONE_410);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 411 (LENGTH_REQUIRED_411) TESTS
     * */

    public void codexsTester_StatusCode411_RetrieveLengthRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(LENGTH_REQUIRED_411);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 412 (PRECONDITION_FAILED_412) TESTS
     * */

    public void codexsTester_StatusCode412_RetrievePreconditionFailed(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(PRECONDITION_FAILED_412);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 413 (PAYLOAD_TOO_LARGE_413) TESTS
     * */

    public void codexsTester_StatusCode413_RetrievePayloadTooLarge(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(PAYLOAD_TOO_LARGE_413);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 414 (URI_TOO_LONG_414) TESTS
     * */

    public void codexsTester_StatusCode414_RetrieveUriTooLong(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(URI_TOO_LONG_414);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 415 (UNSUPPORTED_MEDIA_TYPE_415) TESTS
     * */

    public void codexsTester_StatusCode415_RetrieveUnsupportedMediaType(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(UNSUPPORTED_MEDIA_TYPE_415);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 416 (REQUESTED_RANGE_NOT_SATISFIABLE_416) TESTS
     * */

    public void codexsTester_StatusCode416_RetrieveRequestedRangeNotSatisfiable(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(REQUESTED_RANGE_NOT_SATISFIABLE_416);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 417 (EXPECTATION_FAILED_417) TESTS
     * */

    public void codexsTester_StatusCode417_RetrieveExpectationFailed(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(EXPECTATION_FAILED_417);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 418 (IM_A_TEAPOT_418) TESTS
     * */

    public void codexsTester_StatusCode418_RetrieveImATeapotLengthRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(IM_A_TEAPOT_418);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 421 (MISDIRECTED_REQUEST_421) TESTS
     * */

    public void codexsTester_StatusCode421_RetrieveMisDirectedRequest(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(MISDIRECTED_REQUEST_421);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 422 (UNPROCESSABLE_ENTITY_WEBDAV_EN_US_422) TESTS
     * */

    public void codexsTester_StatusCode422_RetrieveUnprocessableEntity(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(UNPROCESSABLE_ENTITY_WEBDAV_EN_US_422);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 423 (LOCKED_WEBDAV_EN_US_423) TESTS
     * */

    public void codexsTester_StatusCode423_RetrieveLocked(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(LOCKED_WEBDAV_EN_US_423);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 424 (FAILED_DEPENDENCY_WEBDAV_EN_US_424) TESTS
     * */

    public void codexsTester_StatusCode424_RetrieveFailedDependency(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(FAILED_DEPENDENCY_WEBDAV_EN_US_424);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 425 (TOO_EARLY_425) TESTS
     * */

    public void codexsTester_StatusCode425_RetrieveTooEarly(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(TOO_EARLY_425);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 426 (UPGRADE_REQUIRED_426) TESTS
     * */

    public void codexsTester_StatusCode426_RetrieveUpgradeRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(UPGRADE_REQUIRED_426);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 428 (PRECONDITION_REQUIRED_428) TESTS
     * */

    public void codexsTester_StatusCode428_RetrievePreConditionRequired(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(PRECONDITION_REQUIRED_428);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 429 (TOO_MANY_REQUESTS_429) TESTS
     * */

    public void codexsTester_StatusCode429_RetrieveTooManyRequest(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(TOO_MANY_REQUESTS_429);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 431 (REQUEST_HEADER_FIELDS_TOO_LARGE_431) TESTS
     * */

    public void codexsTester_StatusCode431_RetrieveRequestHeaderFieldsTooLarge(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(REQUEST_HEADER_FIELDS_TOO_LARGE_431);
        execute(requestDto, headersDto);
    }

    /**
     * STATUS CODE 451 (UNAVAILABLE_FOR_LEGAL_REASONS_451) TESTS
     * */

    public void codexsTester_StatusCode451_RetrieveUnavailableForLegalReasons(HeadersDto headersDto, RequestDto requestDto) throws Exception {
        requestDto.setExpectedCode(UNAVAILABLE_FOR_LEGAL_REASONS_451);
        execute(requestDto, headersDto);
    }
}
