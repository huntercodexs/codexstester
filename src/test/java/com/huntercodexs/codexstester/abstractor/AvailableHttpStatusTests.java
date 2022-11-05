package com.huntercodexs.codexstester.abstractor;

public abstract class AvailableHttpStatusTests {

    /*INFORMATION RESPONSE*/

    protected static final int CONTINUE_100 = 100;
    protected static final int SWITCHING_PROTOCOL_101 = 101;
    protected static final int PROCESSING_WEBDAV_EN_US_102 = 102;
    protected static final int EARLY_HINTS_103 = 103;

    /*SUCCESS RESPONSE*/

    protected static final int OK_200 = 200;
    protected static final int CREATED_201 = 201;
    protected static final int ACCEPTED_202 = 202;
    protected static final int NON_AUTHORITATIVE_INFORMATION_203 = 203;
    protected static final int NO_CONTENT_204 = 204;
    protected static final int RESET_CONTENT_205 = 205;
    protected static final int PARTIAL_CONTENT_206 = 206;
    protected static final int MULT_STATUS_WEBDAV_EN_US_207 = 207;
    protected static final int MULTI_STATUS_WEBDAV_EN_US_208 = 208;
    protected static final int IM_USED_HTTP_DELTA_ENCODING_226 = 226;
    protected static final int MULTIPLE_CHOICE_300 = 300;
    protected static final int MOVED_PERMANENTLY_301 = 301;
    protected static final int FOUND_302 = 302;
    protected static final int SEE_OTHER_303 = 303;
    protected static final int NOT_MODIFIED_304 = 304;
    protected static final int USE_PROXY_DEPRECATED_305 = 305;
    protected static final int UNUSED_DEPRECATED_306 = 306;
    protected static final int TEMPORARY_REDIRECT_307 = 307;
    protected static final int PERMANENT_REDIRECT_308 = 308;

    /*CLIENT ERRORS RESPONSE*/

    protected static final int BAD_REQUEST_400 = 400;
    protected static final int UNAUTHORIZED_401 = 401;
    protected static final int PAYMENT_REQUIRED_EXPERIMENTAL_402 = 402;
    protected static final int FORBIDDEN_403 = 403;
    protected static final int NOT_FOUND_404 = 404;
    protected static final int METHOD_NOT_ALLOWED_405 = 405;
    protected static final int NOT_ACCEPTABLE_406 = 406;
    protected static final int PROXY_AUTHENTICATION_REQUIRED_407 = 407;
    protected static final int REQUEST_TIMEOUT_408 = 408;
    protected static final int CONFLICT_409 = 409;
    protected static final int GONE_410 = 410;
    protected static final int LENGTH_REQUIRED_411 = 411;
    protected static final int PRECONDITION_FAILED_412 = 412;
    protected static final int PAYLOAD_TOO_LARGE_413 = 413;
    protected static final int URI_TOO_LONG_414 = 414;
    protected static final int UNSUPPORTED_MEDIA_TYPE_415 = 415;
    protected static final int REQUESTED_RANGE_NOT_SATISFIABLE_416 = 416;
    protected static final int EXPECTATION_FAILED_417 = 417;
    protected static final int IM_A_TEAPOT_418 = 418;
    protected static final int MISDIRECTED_REQUEST_421 = 421;
    protected static final int UNPROCESSABLE_ENTITY_WEBDAV_EN_US_422 = 422;
    protected static final int LOCKED_WEBDAV_EN_US_423 = 423;
    protected static final int FAILED_DEPENDENCY_WEBDAV_EN_US_424 = 424;
    protected static final int TOO_EARLY_425 = 425;
    protected static final int UPGRADE_REQUIRED_426 = 426;
    protected static final int PRECONDITION_REQUIRED_428 = 428;
    protected static final int TOO_MANY_REQUESTS_429 = 429;
    protected static final int REQUEST_HEADER_FIELDS_TOO_LARGE_431 = 431;
    protected static final int UNAVAILABLE_FOR_LEGAL_REASONS_451 = 451;

    /*SERVER ERRORS RESPONSE*/

    protected static final int INTERNAL_SERVER_ERROR_500 = 500;
    protected static final int NOT_IMPLEMENTED_501 = 501;
    protected static final int BAD_GATEWAY_502 = 502;
    protected static final int SERVICE_UNAVAILABLE_503 = 503;
    protected static final int GATEWAY_TIMEOUT_504 = 504;
    protected static final int HTTP_VERSION_NOT_SUPPORTED_505 = 505;
    protected static final int VARIANT_ALSO_NEGOTIATES_506 = 506;
    protected static final int INSUFFICIENT_STORAGE_507 = 507;
    protected static final int LOOP_DETECTED_WEBDAV_EN_US_508 = 508;
    protected static final int NOT_EXTENDED_510 = 510;
    protected static final int NETWORK_AUTHENTICATION_REQUIRED_511 = 511;

}
