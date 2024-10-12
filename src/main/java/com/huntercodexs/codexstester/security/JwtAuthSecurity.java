package com.huntercodexs.codexstester.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;

public class JwtAuthSecurity {

    private String urlJwt;
    private String username;
    private String password;
    private String bearerToken;
    private String basicAuth;
    private AuthType authType;
    private HashMap<String, String> addHeader;

    private static final RestTemplate restTemplate = new RestTemplate();

    private static final String wrongMessage =
            "Make sure you have instanced the class and set up the properties correctly";

    private static String base64(String input) {
        byte[] inputBytes = input.getBytes();
        byte[] base64InputBytes = Base64.getEncoder().encode(inputBytes);
        return new String(base64InputBytes);
    }

    private String basicAuth() {
        if (this.username.isEmpty() || this.password.isEmpty()) {
            throw new RuntimeException("[Critical Error] Wrong Instance Basic Auth: " + wrongMessage);
        }
        return "Basic " + base64(this.username+":"+this.password);
    }

    private <T> T dispatcher(Class<T> objectResponse) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        if (!this.authType.name().equals(AuthType.NONE.name())) {
            if (this.authType.name().equals(AuthType.BASIC.name())) {
                httpHeaders.set("Authorization", this.basicAuth);

            } else if (this.authType.name().equals(AuthType.BEARER.name())) {
                httpHeaders.set("Authorization", "Bearer " + this.bearerToken);

            } else if (this.authType.name().equals(AuthType.USERNAME_PASSWORD.name())) {
                httpHeaders.set("Authorization", basicAuth());

            }
        }

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        if (this.addHeader != null && !this.addHeader.isEmpty()) {
            this.addHeader.forEach(httpHeaders::set);
        }

        System.out.println(" ");
        System.out.println("- REQUEST JWT IS RUNNING FROM CODEXSTESTER");
        System.out.println("- [POST] " + this.urlJwt);
        System.out.println("- [HTTP] " + httpEntity);

        return restTemplate.postForEntity(this.urlJwt, httpEntity, objectResponse).getBody();
    }

    public void setUrlJwt(String urlJwt) {
        this.urlJwt = urlJwt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBearerToken(String bearerToken) {
        this.bearerToken = bearerToken;
    }

    public void setBasicAuth(String basicAuth) {
        this.basicAuth = basicAuth;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public void setAddHeader(HashMap<String, String> addHeader) {
        this.addHeader = addHeader;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">jwt</h6>
     *
     * <p style="color: #CDCDCD">Get the JWT Token</p>
     *
     * @return Object (Basic Auth Data)
     * @see <a href="https://github.com/huntercodexs/codexstester">Codexs Tester (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public <T> T jwt(Class<T> objectResponse) {
        T response = dispatcher(objectResponse);

        System.out.println("- [RESPONSE] " + response);
        System.out.println(" ");

        return response;
    }

}
