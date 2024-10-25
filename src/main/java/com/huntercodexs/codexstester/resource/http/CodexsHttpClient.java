package com.huntercodexs.codexstester.resource.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CodexsHttpClient {

    private String url;
    private String track;
    private Object bodyRequest;
    private Class<?> bodyRequestType;
    private HttpMethod httpMethod;
    private boolean makeLog = false;

    private final List<LinkedHashMap<String, String>> headerList = new ArrayList<>();
    private static final RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders httpHeadersCreate() {
        HttpHeaders httpHeaders = new HttpHeaders();
        for (LinkedHashMap<String, String> header : this.headerList) {
            httpHeaders.add(header.get("header"), header.get("value"));
        }
        return httpHeaders;
    }

    private void log(HttpEntity<?> httpEntity) {
        if (this.makeLog) {
            if (this.track.isEmpty()) {
                this.track = "undefined";
            }
            System.out.println("TRACK ["+this.track+"] HTTP[GET] " + this.url);
            System.out.println("TRACK ["+this.track+"] HttpEntity " + httpEntity);
        }
    }

    private void requestBreak() {
        if (this.bodyRequestType != this.bodyRequest.getClass()) {
            throw new RuntimeException("Wrong parameters: BodyRequest is not a valid ClassName");
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public void setBodyRequest(Object bodyRequest) {
        this.bodyRequest = bodyRequest;
    }

    public void setBodyRequestType(Class<?> bodyRequestType) {
        this.bodyRequestType = bodyRequestType;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setMakeLog(boolean makeLog) {
        this.makeLog = makeLog;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">setHeaderList</p>
     *
     * <p style="color: #CDCDCD">Set the header names and values to HTTP Requests</p>
     *
     * @param headerName (String: The field name)
     * @param headerValue (String: The field value)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void setHeaderList(String headerName, String headerValue) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("header", headerName);
        linkedHashMap.put("value", headerValue);
        this.headerList.add(linkedHashMap);
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">setHttpMethod</p>
     *
     * <p style="color: #CDCDCD">Call the http method setter and get the current instance of
     *      this class where its possible to prepare a specific HTTP REQUEST METHOD and finally
     *      call the request method.
     * </p>
     *
     * @return Help4DevsHttpClientService (this)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public CodexsHttpClient setHttpMethod() {
        return this;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">httpGet</p>
     *
     * <p style="color: #CDCDCD">HTTP METHOD GET</p>
     *
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpGet() {
        this.httpMethod = HttpMethod.GET;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">httpPost</p>
     *
     * <p style="color: #CDCDCD">HTTP METHOD POST</p>
     *
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpPost() {
        this.httpMethod = HttpMethod.POST;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">httpDelete</p>
     *
     * <p style="color: #CDCDCD">HTTP METHOD DELETE</p>
     *
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpDelete() {
        this.httpMethod = HttpMethod.DELETE;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">httpPut</p>
     *
     * <p style="color: #CDCDCD">HTTP METHOD PUT</p>
     *
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpPut() {
        this.httpMethod = HttpMethod.PUT;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">httpPatch</p>
     *
     * <p style="color: #CDCDCD">HTTP METHOD PATCH</p>
     *
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpPatch() {
        this.httpMethod = HttpMethod.PATCH;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">httpOptions</p>
     *
     * <p style="color: #CDCDCD">HTTP METHOD OPTIONS</p>
     *
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpOptions() {
        this.httpMethod = HttpMethod.OPTIONS;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">httpHead</p>
     *
     * <p style="color: #CDCDCD">HTTP METHOD HEAD</p>
     *
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void httpHead() {
        this.httpMethod = HttpMethod.HEAD;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">request</p>
     *
     * <p style="color: #CDCDCD">Execute the HTTP Request previously configured in the current instance</p>
     *
     * @return ResponseEntity
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public ResponseEntity<?> request() {
        requestBreak();
        HttpEntity<?> httpEntity = new HttpEntity<>(this.bodyRequest, httpHeadersCreate());
        log(httpEntity);
        return restTemplate.exchange(this.url, this.httpMethod, httpEntity, this.bodyRequestType);
    }

}
