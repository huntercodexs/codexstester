package com.huntercodexs.codexstester.security.dto;

import java.util.List;

public class Oauth2ResponseTokenCheckDto {
    List<String> aud;
    String user_name;
    List<String> scope;
    boolean active;
    long exp;
    List<String> authorities;
    String client_id;

    public Oauth2ResponseTokenCheckDto() {
    }

    public Oauth2ResponseTokenCheckDto(List<String> aud, String user_name, List<String> scope, boolean active, long exp, List<String> authorities, String client_id) {
        this.aud = aud;
        this.user_name = user_name;
        this.scope = scope;
        this.active = active;
        this.exp = exp;
        this.authorities = authorities;
        this.client_id = client_id;
    }

    public List<String> getAud() {
        return aud;
    }

    public void setAud(List<String> aud) {
        this.aud = aud;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public List<String> getScope() {
        return scope;
    }

    public void setScope(List<String> scope) {
        this.scope = scope;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String toString() {
        return "Oauth2ResponseTokenCheckDto(" +
                "aud=" + aud +
                ", user_name=" + user_name +
                ", scope=" + scope +
                ", active=" + active +
                ", exp=" + exp +
                ", authorities=" + authorities +
                ", client_id=" + client_id +
                ")";
    }
}
