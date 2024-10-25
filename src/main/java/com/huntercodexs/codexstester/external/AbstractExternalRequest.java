package com.huntercodexs.codexstester.external;

import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import com.huntercodexs.codexstester.internal.InternalRequest1Xx;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTerm;

public abstract class AbstractExternalRequest extends InternalRequest1Xx {

    protected AbstractExternalRequest(String target) {
        super(target);
    }

    void executeExternalTest(RequestDto requestDto, HeadersDto headersDto) throws Exception {

        /*Fix a bug when getHttpMethod is null*/
        try {
            String method = headersDto.getHttpMethod();
            if (method == null) headersDto.setHttpMethod(HTTP_METHOD_TESTER);
        } catch (Exception ex) {
            headersDto.setHttpMethod(HTTP_METHOD_TESTER);
        }

        switch (headersDto.getHttpMethod()) {
            case HTTP_METHOD_GET:
                assertResultFromRequestByHttpGet(requestDto, headersDto);
                break;
            case HTTP_METHOD_POST:
                assertResultFromRequestByHttpPost(requestDto, headersDto);
                break;
            case HTTP_METHOD_PUT:
                assertResultFromRequestByHttpPut(requestDto, headersDto);
                break;
            case HTTP_METHOD_DELETE:
                assertResultFromRequestByHttpDelete(requestDto, headersDto);
                break;
            case HTTP_METHOD_PATCH:
                assertResultFromRequestByHttpPatch(requestDto, headersDto);
                break;
            case HTTP_METHOD_HEAD:
                assertResultFromRequestByHttpHead(requestDto, headersDto);
                break;
            case HTTP_METHOD_OPTIONS:
                assertResultFromRequestByHttpOptions(requestDto, headersDto);
                break;
            default:
                assertResultExternalTest();
        }
    }

    private void dispatcher(RequestDto requestDto, HeadersDto headersDto, String method) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String uri = externalUriBaseTest;

        if (requestDto.getUri() != null && !requestDto.getUri().equals("")) uri = requestDto.getUri();
        if (requestDto.getId() != null && !requestDto.getId().equals("")) uri = uri +"/"+ requestDto.getId();

        String url = externalUrlBaseTest + uri;

        if (requestDto.getUrl() != null && !requestDto.getUrl().equals("")) {
            url = requestDto.getUrl() + uri;
        }

        if (externalUrlQueryParameters != null && !externalUrlQueryParameters.equals("")) {
            url = url + "?" + externalUrlQueryParameters;
        }

        HttpEntity<?> httpEntity = new HttpEntity<>(requestDto.getDataRequest(), externalBuilderHeaders(requestDto, headersDto));

        codexsHelperLogTerm("EXTERNAL REQUEST URL IS", url, true);
        codexsHelperLogTerm("HTTP METHOD IS", method, true);

        try {

            ResponseEntity<?> response = null;
            Class<?> responseType = Object.class;

            if (requestDto.getExpectedMessage() != null && !requestDto.getExpectedMessage().equals("")) {
                responseType = String.class;
            }

            switch (method) {
                case HTTP_METHOD_GET:
                    codexsHelperLogTerm("SEND REQUEST BY exchange GET ["+responseType+"]", url, true);
                    response = genericRestTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType);
                    break;
                case HTTP_METHOD_POST:
                    codexsHelperLogTerm("SEND REQUEST BY exchange POST ["+responseType+"]", url, true);
                    response = genericRestTemplate.postForEntity(url, httpEntity, responseType);
                    break;
                case HTTP_METHOD_DELETE:
                    codexsHelperLogTerm("SEND REQUEST BY exchange DELETE ["+responseType+"]", url, true);
                    response = genericRestTemplate.exchange(url, HttpMethod.DELETE, httpEntity, responseType);
                    break;
                case HTTP_METHOD_PUT:
                    codexsHelperLogTerm("SEND REQUEST BY exchange PUT ["+responseType+"]", url, true);
                    response = genericRestTemplate.exchange(url, HttpMethod.PUT, httpEntity, responseType);
                    break;
                case HTTP_METHOD_PATCH:
                    genericRestTemplate.setRequestFactory(externalHttpClientFactory());
                    codexsHelperLogTerm("SEND REQUEST BY exchange PATCH ["+responseType+"]", url, true);
                    response = genericRestTemplate.exchange(url, HttpMethod.PATCH, httpEntity, responseType);
                    break;
                case HTTP_METHOD_HEAD:
                    codexsHelperLogTerm("SEND REQUEST BY exchange HEAD ["+responseType+"]", url, true);
                    response = genericRestTemplate.exchange(url, HttpMethod.HEAD, httpEntity, responseType);
                    break;
                case HTTP_METHOD_OPTIONS:
                    codexsHelperLogTerm("SEND REQUEST BY exchange OPTIONS ["+responseType+"]", url, true);
                    response = genericRestTemplate.exchange(url, HttpMethod.OPTIONS, httpEntity, responseType);
                    break;
                default:
                    throw new RuntimeException("INVALID HTTP METHOD: " + method);
            }

            codexsHelperLogTerm("EXTERNAL RESPONSE IS", response, true);
            codexsHelperLogTerm("EXTERNAL STATUS CODE IS", response.getStatusCode(), true);

            codexsTesterAssertInt(
                    requestDto.getExpectedCode(),
                    response.getStatusCodeValue(),
                    requestDto.getMethodCallerName());

            if (requestDto.getExpectedMessage() != null && !requestDto.getExpectedMessage().equals("")) {
                codexsHelperLogTerm("RESPONSE[BODY] MATCH", response.getBody(), true);
                codexsTesterAssertObject(
                        requestDto.getExpectedMessage(),
                        response.getBody(),
                        requestDto.getMethodCallerName());
                resulted(true, element);
            }

        } catch (HttpClientErrorException ex) {

            codexsHelperLogTerm("HttpClientErrorException[CODE]:", ex.getStatusCode(), true);
            codexsHelperLogTerm("HttpClientErrorException[BODY]:", ex.getResponseBodyAsString(), true);
            codexsHelperLogTerm("HttpClientErrorException[MESSAGE]:", ex.getMessage(), true);

            codexsTesterAssertInt(
                    requestDto.getExpectedCode(),
                    ex.getRawStatusCode(),
                    requestDto.getMethodCallerName());

            if (requestDto.getExpectedMessage() != null && !requestDto.getExpectedMessage().equals("")) {
                if (!ex.getResponseBodyAsString().equals("")) {
                    codexsTesterAssertExact(
                            requestDto.getExpectedMessage(),
                            ex.getResponseBodyAsString(),
                            requestDto.getMethodCallerName());
                } else {

                    String warn = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
                    warn += "IS NOT WAS POSSIBLE COMPARE THE RECEIVED RESPONSE BECAUSE IT IS EMPTY\n";
                    warn += "IF REQUIRED ALSO COMPARE THE RESPONSE, YOU CAN BE USE THE ADVANCED TESTS\n";
                    warn += "PLEASE, FOR MORE DETAILS GIVE A LOOK IN THE DOCUMENTATION ON GITHUB (README.md)\n";
                    warn += "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
                    codexsHelperLogTerm("!!! W A R N I N G !!!", warn, false);

                    codexsTesterAssertBool(true, true, requestDto.getMethodCallerName());
                    resulted(true, element);

                }
            }

        } catch (HttpServerErrorException se) {

            codexsHelperLogTerm("HttpServerErrorException[CODE]:", se.getStatusCode(), true);
            codexsHelperLogTerm("HttpServerErrorException[BODY]:", se.getResponseBodyAsString(), true);
            codexsHelperLogTerm("HttpServerErrorException[MESSAGE]:", se.getMessage(), true);

            codexsTesterAssertInt(
                    requestDto.getExpectedCode(),
                    se.getRawStatusCode(),
                    requestDto.getMethodCallerName());

            if (requestDto.getExpectedMessage() != null && !requestDto.getExpectedMessage().equals("")) {
                if (!se.getResponseBodyAsString().equals("")) {

                    codexsTesterAssertExact(
                            requestDto.getExpectedMessage(),
                            se.getResponseBodyAsString(),
                            requestDto.getMethodCallerName());

                } else {

                    String warn = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
                    warn += "IS NOT WAS POSSIBLE COMPARE THE RECEIVED RESPONSE BECAUSE IT IS EMPTY\n";
                    warn += "IF REQUIRED ALSO COMPARE THE RESPONSE, YOU CAN BE USE THE ADVANCED TESTS\n";
                    warn += "PLEASE, FOR MORE DETAILS GIVE A LOOK IN THE DOCUMENTATION ON GITHUB (README.md)\n";
                    warn += "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";
                    codexsHelperLogTerm("!!! W A R N I N G !!!", warn, false);

                    codexsTesterAssertBool(true, true, requestDto.getMethodCallerName());
                    resulted(true, element);
                }
            }

        } catch (RuntimeException re) {
            codexsHelperLogTerm("RuntimeException[MESSAGE]:", re.getMessage(), true);
            resulted(false, element);
            codexsTesterAssertBool(true, false, requestDto.getMethodCallerName());
        }
    }

    /**
     * <p>Using Http GET with Rest Template</p>
     *
     * @param requestDto (Object: RequestDto)
     * @param headersDto (Object: HeadersDto)
     * @author huntercodexs (powered by jereelton-devel)
     * @throws Exception (Exception: generic exception)
     */
    private void assertResultFromRequestByHttpGet(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_GET);
    }

    /**
     * <p>Using Http POST with Rest Template</p>
     *
     * @param requestDto (Object: RequestDto)
     * @param headersDto (Object: HeadersDto)
     * @author huntercodexs (powered by jereelton-devel)
     * @throws Exception (Exception: generic exception)
     */
    private void assertResultFromRequestByHttpPost(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_POST);
    }

    /**
     * <p>Using Http DELETE with Rest Template</p>
     *
     * @param requestDto (Object: RequestDto)
     * @param headersDto (Object: HeadersDto)
     * @author huntercodexs (powered by jereelton-devel)
     * @throws Exception (Exception: generic exception)
     */
    private void assertResultFromRequestByHttpDelete(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_DELETE);
    }

    /**
     * <p>Using Http PUT with Rest Template</p>
     *
     * @param requestDto (Object: RequestDto)
     * @param headersDto (Object: HeadersDto)
     * @author huntercodexs (powered by jereelton-devel)
     * @throws Exception (Exception: generic exception)
     */
    private void assertResultFromRequestByHttpPut(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_PUT);
    }

    /**
     * <p>Using Http PATCH with Rest Template</p>
     *
     * @param requestDto (Object: RequestDto)
     * @param headersDto (Object: HeadersDto)
     * @author huntercodexs (powered by jereelton-devel)
     * @throws Exception (Exception: generic exception)
     */
    private void assertResultFromRequestByHttpPatch(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_PATCH);
    }

    /**
     * <p>Using Http HEAD with Rest Template</p>
     *
     * @param requestDto (Object: RequestDto)
     * @param headersDto (Object: HeadersDto)
     * @author huntercodexs (powered by jereelton-devel)
     * @throws Exception (Exception: generic exception)
     */
    private void assertResultFromRequestByHttpHead(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_HEAD);
    }

    /**
     * <p>Using Http OPTIONS with Rest Template</p>
     *
     * @param requestDto (Object: RequestDto)
     * @param headersDto (Object: HeadersDto)
     * @author huntercodexs (powered by jereelton-devel)
     * @throws Exception (Exception: generic exception)
     */
    private void assertResultFromRequestByHttpOptions(RequestDto requestDto, HeadersDto headersDto) throws Exception {
        dispatcher(requestDto, headersDto, HTTP_METHOD_OPTIONS);
    }

    private void assertResultExternalTest() {
        System.out.println("CODEXS TESTER IS RUNNING IN EXTERNAL MODE");
    }

}