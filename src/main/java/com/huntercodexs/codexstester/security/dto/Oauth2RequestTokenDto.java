package com.huntercodexs.codexstester.security.dto;

import java.util.HashMap;

public class Oauth2RequestTokenDto {
    String url;
    String auth;
    String grant;
    String user;
    String pass;
    String clientId;
    String secret;
    HashMap<String, String> addHeader;

    public Oauth2RequestTokenDto() {
    }

    public Oauth2RequestTokenDto(
            String url,
            String auth,
            String grant,
            String user,
            String pass,
            String clientId,
            String secret,
            HashMap<String, String> addHeader
    ) {
        this.url = url;
        this.auth = auth;
        this.grant = grant;
        this.user = user;
        this.pass = pass;
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

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getGrant() {
        return grant;
    }

    public void setGrant(String grant) {
        this.grant = grant;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
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
        return "Oauth2RequestTokenDto(" +
                "url=" + url +
                ", auth=" + auth +
                ", grant=" + grant +
                ", user=" + user +
                ", pass=" + pass +
                ", clientId=" + clientId +
                ", secret=" + secret +
                ", addHeader=" + addHeader +
                ")";
    }
}
