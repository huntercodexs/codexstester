package com.huntercodexs.codexstester.security;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;

public class BasicAuthSecurity {

    private String urlBasic;
    private String username;
    private String password;
    HashMap<String, String> addHeader;

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

    private ResponseEntity<String> dispatcher() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", basicAuth());
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        if (this.addHeader != null && !this.addHeader.isEmpty()) {
            this.addHeader.forEach(httpHeaders::set);
        }

        System.out.println(" ");
        System.out.println("- REQUEST BASIC IS RUNNING FROM CODEXSTESTER");
        System.out.println("- [POST] " + this.urlBasic);
        System.out.println("- [HTTP] " + httpEntity);

        return restTemplate.postForEntity(this.urlBasic, httpEntity, String.class);
    }

    public void setUrlBasic(String urlBasic) {
        this.urlBasic = urlBasic;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddHeader(HashMap<String, String> addHeader) {
        this.addHeader = addHeader;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">basic</p>
     *
     * <p style="color: #CDCDCD">Make the Authentication using BASIC AUTH</p>
     *
     * @return Object (Basic Auth Data)
     * @see <a href="https://github.com/huntercodexs/codexstester">Codexs Tester (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public Object basic() {
        ResponseEntity<String> response = dispatcher();

        System.out.println("- [RESPONSE] " + response.getBody());
        System.out.println(" ");

        if (response.getBody() != null) return response.getBody();
        return null;
    }

}
