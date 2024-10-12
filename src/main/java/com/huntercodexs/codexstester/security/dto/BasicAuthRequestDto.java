package com.huntercodexs.codexstester.security.dto;

import java.util.HashMap;

public class BasicAuthRequestDto {

    String url;
    String username;
    String password;
    HashMap<String, String> addHeader;

    public BasicAuthRequestDto() {
    }

    public BasicAuthRequestDto(String url, String username, String password, HashMap<String, String> addHeader) {
        this.url = url;
        this.username = username;
        this.password = password;
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

    public HashMap<String, String> getAddHeader() {
        return addHeader;
    }

    public void setAddHeader(HashMap<String, String> addHeader) {
        this.addHeader = addHeader;
    }

    @Override
    public String toString() {
        return "BasicAuthRequestDto(" +
                "url=" + url +
                ", username=" + username +
                ", password=" + password +
                ", addHeader=" + addHeader +
                ")";
    }
}
