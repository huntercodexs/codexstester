package com.huntercodexs.codexstester.security;

import com.huntercodexs.codexstester.security.dto.BasicAuthRequestDto;
import com.huntercodexs.codexstester.security.dto.JwtAuthRequestDto;
import com.huntercodexs.codexstester.security.dto.Oauth2RequestCheckTokenDto;
import com.huntercodexs.codexstester.security.dto.Oauth2RequestTokenDto;

public class CodexsSecurity {

    Oauth2Security oauth2Security;
    Oauth2RequestTokenDto requestTokenDto;

    BasicAuthSecurity basicAuthSecurity;
    BasicAuthRequestDto basicAuthRequestDto;

    JwtAuthSecurity jwtAuthSecurity;
    JwtAuthRequestDto jwtAuthRequestDto;

    public CodexsSecurity() {
    }

    public CodexsSecurity(Oauth2RequestTokenDto requestTokenDto) {
        this.requestTokenDto = requestTokenDto;
        this.oauth2Security = new Oauth2Security();
    }

    public CodexsSecurity(BasicAuthRequestDto basicAuthRequestDto) {
        this.basicAuthRequestDto = basicAuthRequestDto;
        this.basicAuthSecurity = new BasicAuthSecurity();
    }

    public CodexsSecurity(JwtAuthRequestDto jwtAuthRequestDto) {
        this.jwtAuthRequestDto = jwtAuthRequestDto;
        this.jwtAuthSecurity = new JwtAuthSecurity();
    }

    public String token() {
        oauth2Security.setUrlOauth2Token(this.requestTokenDto.getUrl());
        oauth2Security.setUsername(this.requestTokenDto.getUser());
        oauth2Security.setPassword(this.requestTokenDto.getPass());
        oauth2Security.setGrant(this.requestTokenDto.getGrant());
        oauth2Security.setClientId(this.requestTokenDto.getClientId());
        oauth2Security.setSecret(this.requestTokenDto.getSecret());
        oauth2Security.setAddHeader(this.requestTokenDto.getAddHeader());
        return oauth2Security.token();
    }

    public boolean checkToken(Oauth2RequestCheckTokenDto request) {
        oauth2Security.setUrlOauth2CheckToken(request.getUrl());
        oauth2Security.setToken(request.getToken());
        oauth2Security.setClientId(request.getClientId());
        oauth2Security.setSecret(request.getSecret());
        oauth2Security.setAddHeader(request.getAddHeader());
        return oauth2Security.check();
    }

    public Object basicAuth() {
        basicAuthSecurity.setUrlBasic(this.basicAuthRequestDto.getUrl());
        basicAuthSecurity.setUsername(this.basicAuthRequestDto.getUsername());
        basicAuthSecurity.setPassword(this.basicAuthRequestDto.getPassword());
        basicAuthSecurity.setAddHeader(this.basicAuthRequestDto.getAddHeader());
       return basicAuthSecurity.basic();
    }

    public <T> T jwtAuth(Class<T> objectResponse) {
        jwtAuthSecurity.setUrlJwt(this.jwtAuthRequestDto.getUrl());
        jwtAuthSecurity.setUsername(this.jwtAuthRequestDto.getUsername());
        jwtAuthSecurity.setPassword(this.jwtAuthRequestDto.getPassword());
        jwtAuthSecurity.setBasicAuth(this.jwtAuthRequestDto.getBasicAuth());
        jwtAuthSecurity.setBearerToken(this.jwtAuthRequestDto.getBearerToken());
        jwtAuthSecurity.setAuthType(this.jwtAuthRequestDto.getAuthType());
        jwtAuthSecurity.setAddHeader(this.jwtAuthRequestDto.getAddHeader());
        return jwtAuthSecurity.jwt(objectResponse);
    }

}
