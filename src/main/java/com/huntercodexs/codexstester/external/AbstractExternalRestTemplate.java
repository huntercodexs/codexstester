package com.huntercodexs.codexstester.external;

import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.dto.RequestDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTerm;

public abstract class AbstractExternalRestTemplate extends AbstractExternalRequest {

    protected AbstractExternalRestTemplate(String target) {
        super(target);
    }

    protected ResponseEntity<?> codexsTesterExternalDispatcher(RequestDto requestDto, HeadersDto headersDto) {

        try {
            int code = requestDto.getExpectedCode();
        } catch (RuntimeException re) {
            String err = "Missing HTTP-STATUS from RequestDto: use setExpectedCode";
            throw new RuntimeException(err);
        }

        String uri = externalUriBaseTest;
        String method = headersDto.getHttpMethod();

        if (requestDto.getUri() != null && !requestDto.getUri().equals("")) uri = requestDto.getUri();
        if (requestDto.getId() != null && !requestDto.getId().equals("")) uri = uri +"/"+ requestDto.getId();

        String url = externalUrlBaseTest
                .replaceFirst("/$", "") +"/"+
                uri.replaceFirst("^/", "");

        if (requestDto.getUrl() != null && !requestDto.getUrl().equals("")) {
            url = requestDto.getUrl()
                    .replaceFirst("/$", "") +"/"+
                    uri.replaceFirst("^/", "");
        }

        if (externalUrlQueryParameters != null && !externalUrlQueryParameters.equals("")) {
            url = url + "?" + externalUrlQueryParameters;
        }

        HttpEntity<?> httpEntity = new HttpEntity<>(requestDto.getDataRequest(), externalBuilderHeaders(requestDto, headersDto));

        codexsHelperLogTerm("EXTERNAL DISPATCHER REQUEST URL IS", url, true);
        codexsHelperLogTerm("HTTP METHOD IS", method, true);

        Class<?> objResponse = headersDto.getObjectResponse();

        if (objResponse == null) {
            objResponse = String.class;
        }

        ResponseEntity<?> response = null;

        codexsHelperLogTerm("DATA REQUEST", httpEntity, true);

        try {

            switch (method) {
                case HTTP_METHOD_GET:
                    try {
                        codexsHelperLogTerm("TRY GET", url, true);
                        response = genericRestTemplate.exchange(url, HttpMethod.GET, httpEntity, objResponse);
                    } catch (HttpClientErrorException | HttpServerErrorException he) {
                        codexsHelperLogTerm("TRY GET [EXCEPTION]", url, true);
                        return ResponseEntity.status(he.getRawStatusCode()).body(he.getResponseBodyAsString());
                    } catch (Exception ex) {
                        codexsHelperLogTerm("TRY GET [EXCEPTION] [KILL]", url, true);
                        throw new RuntimeException(ex.getMessage());
                    }
                    break;
                case HTTP_METHOD_POST:
                    try {
                        codexsHelperLogTerm("TRY POST", url, true);
                        response = genericRestTemplate.postForEntity(url, httpEntity, objResponse);
                    } catch (HttpClientErrorException | HttpServerErrorException he) {
                        codexsHelperLogTerm("TRY POST [EXCEPTION]", url, true);
                        return ResponseEntity.status(he.getRawStatusCode()).body(he.getResponseBodyAsString());
                    } catch (Exception ex) {
                        codexsHelperLogTerm("TRY POST [EXCEPTION] [KILL]", url, true);
                        throw new RuntimeException(ex.getMessage());
                    }
                    break;
                case HTTP_METHOD_DELETE:
                    try {
                        codexsHelperLogTerm("TRY DELETE", url, true);
                        response = genericRestTemplate.exchange(url, HttpMethod.DELETE, httpEntity, objResponse);
                    } catch (HttpClientErrorException | HttpServerErrorException he) {
                        codexsHelperLogTerm("TRY DELETE [EXCEPTION]", url, true);
                        return ResponseEntity.status(he.getRawStatusCode()).body(he.getResponseBodyAsString());
                    } catch (Exception ex) {
                        codexsHelperLogTerm("TRY DELETE [EXCEPTION] [KILL]", url, true);
                        throw new RuntimeException(ex.getMessage());
                    }
                    break;
                case HTTP_METHOD_PUT:
                    try {
                        codexsHelperLogTerm("TRY PUT", url, true);
                        response = genericRestTemplate.exchange(url, HttpMethod.PUT, httpEntity, objResponse);
                    } catch (HttpClientErrorException | HttpServerErrorException he) {
                        codexsHelperLogTerm("TRY PUT [EXCEPTION]", url, true);
                        return ResponseEntity.status(he.getRawStatusCode()).body(he.getResponseBodyAsString());
                    } catch (Exception ex) {
                        codexsHelperLogTerm("TRY PUT [EXCEPTION] [KILL]", url, true);
                        throw new RuntimeException(ex.getMessage());
                    }
                    break;
                case HTTP_METHOD_PATCH:
                    genericRestTemplate.setRequestFactory(externalHttpClientFactory());
                    try {
                        codexsHelperLogTerm("TRY PATCH", url, true);
                        response = genericRestTemplate.exchange(url, HttpMethod.PATCH, httpEntity, objResponse);
                    } catch (HttpClientErrorException | HttpServerErrorException he) {
                        codexsHelperLogTerm("TRY PATCH [EXCEPTION]", url, true);
                        return ResponseEntity.status(he.getRawStatusCode()).body(he.getResponseBodyAsString());
                    } catch (Exception ex) {
                        codexsHelperLogTerm("TRY PATCH [EXCEPTION] [KILL]", url, true);
                        throw new RuntimeException(ex.getMessage());
                    }
                    break;
                case HTTP_METHOD_HEAD:
                    try {
                        codexsHelperLogTerm("TRY HEAD", url, true);
                        response = genericRestTemplate.exchange(url, HttpMethod.HEAD, httpEntity, objResponse);
                    } catch (HttpClientErrorException | HttpServerErrorException he) {
                        codexsHelperLogTerm("TRY HEAD [EXCEPTION]", url, true);
                        return ResponseEntity.status(he.getRawStatusCode()).body(he.getResponseBodyAsString());
                    } catch (Exception ex) {
                        codexsHelperLogTerm("TRY HEAD [EXCEPTION] [KILL]", url, true);
                        throw new RuntimeException(ex.getMessage());
                    }
                    break;
                case HTTP_METHOD_OPTIONS:
                    try {
                        codexsHelperLogTerm("TRY OPTIONS", url, true);
                        response = genericRestTemplate.exchange(url, HttpMethod.OPTIONS, httpEntity, objResponse);
                    } catch (HttpClientErrorException | HttpServerErrorException he) {
                        codexsHelperLogTerm("TRY OPTIONS [EXCEPTION]", url, true);
                        return ResponseEntity.status(he.getRawStatusCode()).body(he.getResponseBodyAsString());
                    } catch (Exception ex) {
                        codexsHelperLogTerm("TRY OPTIONS [EXCEPTION] [KILL]", url, true);
                        throw new RuntimeException(ex.getMessage());
                    }
                    break;
                default:
                    throw new RuntimeException("INVALID HTTP METHOD: " + method);
            }

            codexsHelperLogTerm("EXTERNAL DISPATCHER RESPONSE IS", response, true);

            if (response.getStatusCodeValue() != requestDto.getExpectedCode()) {
                codexsHelperLogTerm("EXTERNAL DISPATCHER ERROR [INVALID STATUS CODE]", response.getStatusCodeValue(), true);
                codexsTesterAssertInt(response.getStatusCodeValue(), requestDto.getExpectedCode());
            }

            return response;

        } catch (HttpClientErrorException ex) {

            codexsHelperLogTerm("EXCEPTION[HEADER] HttpClientErrorException: ", ex.getResponseHeaders(), true);
            codexsHelperLogTerm("EXCEPTION[BODY] HttpClientErrorException: ", ex.getResponseBodyAsString(), true);
            codexsHelperLogTerm("EXCEPTION[MESSAGE] HttpClientErrorException: ", ex.getMessage(), true);

        } catch (HttpServerErrorException se) {

            codexsHelperLogTerm("EXCEPTION[BODY] HttpServerErrorException: ", se.getResponseBodyAsString(), true);
            codexsHelperLogTerm("EXCEPTION[MESSAGE] HttpServerErrorException: ", se.getMessage(), true);

        } catch (RuntimeException re) {
            codexsHelperLogTerm("EXCEPTION[MESSAGE] RuntimeException: ", re.getMessage(), true);
        }

        return null;
    }

}