package com.huntercodexs.codexstester.dto;

import java.util.HashMap;

public class RequestDto {
    String url;
    String uri;
    String id;
    Object dataRequest;
    String expectedMessage;
    int expectedCode;
    HashMap<String, String> addHeader;

    public RequestDto() {
    }

    public RequestDto(
            String url,
            String uri,
            String id,
            Object dataRequest,
            String expectedMessage,
            int expectedCode,
            HashMap<String, String> addHeader
    ) {
        this.url = url;
        this.uri = uri;
        this.id = id;
        this.dataRequest = dataRequest;
        this.expectedMessage = expectedMessage;
        this.expectedCode = expectedCode;
        this.addHeader = addHeader;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getDataRequest() {
        return dataRequest;
    }

    public void setDataRequest(Object dataRequest) {
        this.dataRequest = dataRequest;
    }

    public String getExpectedMessage() {
        return expectedMessage;
    }

    public void setExpectedMessage(String expectedMessage) {
        this.expectedMessage = expectedMessage;
    }

    public int getExpectedCode() {
        return expectedCode;
    }

    public void setExpectedCode(int expectedCode) {
        this.expectedCode = expectedCode;
    }

    public HashMap<String, String> getAddHeader() {
        return addHeader;
    }

    public void setAddHeader(HashMap<String, String> addHeader) {
        this.addHeader = addHeader;
    }

    public String toString() {
        return "RequestDto(" +
                "url=" + url +
                ", uri=" + uri +
                ", id=" + id +
                ", dataRequest=" + dataRequest +
                ", expectedMessage=" + expectedMessage +
                ", expectedCode=" + expectedCode +
                ", addHeader=" + addHeader +
                ")";
    }
}
