package com.huntercodexs.codexstester.security.dto;

import com.huntercodexs.codexstester.security.AuthType;

import java.util.HashMap;

public class JwtAuthRequestDto {

    String url;
    String username;
    String password;
    String bearerToken;
    String basicAuth;
    AuthType authType;
    HashMap<String, String> addHeader;

    public JwtAuthRequestDto() {
    }

    public JwtAuthRequestDto(
            String url,
            String username,
            String password,
            String bearerToken,
            String basicAuth,
            AuthType authType,
            HashMap<String, String> addHeader
    ) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.bearerToken = bearerToken;
        this.basicAuth = basicAuth;
        this.authType = authType;
        this.addHeader = addHeader;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBearerToken() {
        return bearerToken;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public String getBasicAuth() {
        return basicAuth;
    }

    public void setBasicAuth(String basicAuth) {
        this.basicAuth = basicAuth;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public HashMap<String, String> getAddHeader() {
        return addHeader;
    }

    public void setAddHeader(HashMap<String, String> addHeader) {
        this.addHeader = addHeader;
    }

    @Override
    public String toString() {
        return "JwtAuthRequestDto(" +
                "url=" + url +
                ", username=" + username +
                ", password=" + password +
                ", bearerToken=" + bearerToken +
                ", basicAuth=" + basicAuth +
                ", authType=" + authType +
                ", addHeader=" + addHeader +
                ")";
    }
}
