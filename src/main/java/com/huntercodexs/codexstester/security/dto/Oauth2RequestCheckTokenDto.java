package com.huntercodexs.codexstester.security.dto;

import java.util.HashMap;

public class Oauth2RequestCheckTokenDto {
    String url;
    String authorization;
    String token;
    String additionalName;
    String additionalValue;
    String clientId;
    String secret;
    HashMap<String, String> addHeader;

    public Oauth2RequestCheckTokenDto() {
    }

    public Oauth2RequestCheckTokenDto(
            String url,
            String authorization,
            String token,
            String additionalName,
            String additionalValue,
            String clientId,
            String secret,
            HashMap<String, String> addHeader
    ) {
        this.url = url;
        this.authorization = authorization;
        this.token = token;
        this.additionalName = additionalName;
        this.additionalValue = additionalValue;
        this.clientId = clientId;
        this.secret = secret;
        this.addHeader = addHeader;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAdditionalName() {
        return additionalName;
    }

    public void setAdditionalName(String additionalName) {
        this.additionalName = additionalName;
    }

    public String getAdditionalValue() {
        return additionalValue;
    }

    public void setAdditionalValue(String additionalValue) {
        this.additionalValue = additionalValue;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public HashMap<String, String> getAddHeader() {
        return addHeader;
    }

    public void setAddHeader(HashMap<String, String> addHeader) {
        this.addHeader = addHeader;
    }

    public String toString() {
        return "Oauth2RequestCheckTokenDto(" +
                "url=" + url +
                ", authorization=" + authorization +
                ", token=" + token +
                ", additionalName=" + additionalName +
                ", additionalValue=" + additionalValue +
                ", clientId=" + clientId +
                ", secret=" + secret +
                ", addHeader=" + addHeader +
                ")";
    }
}
