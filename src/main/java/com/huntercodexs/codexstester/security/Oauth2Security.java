package com.huntercodexs.codexstester.security;

import com.huntercodexs.codexstester.security.dto.Oauth2ResponseTokenCheckDto;
import com.huntercodexs.codexstester.security.dto.Oauth2ResponseTokenDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.HashMap;

public class Oauth2Security {

    private String urlOauth2Token;
    private String urlOauth2CheckToken;
    private String clientId;
    private String secret;
    private String username;
    private String password;
    private String grant;
    private String token;
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
        if (this.secret.isEmpty() || this.clientId.isEmpty()) {
            throw new RuntimeException("[Critical Error] Wrong Instance Basic Auth: " + wrongMessage);
        }
        return "Basic " + base64(this.clientId+":"+this.secret);
    }

    private ResponseEntity<Oauth2ResponseTokenDto> tokenGenerate() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", basicAuth());
        String credentials = "?username="+ this.username+"&password="+ this.password+"&grant_type="+ this.grant;
        HttpEntity<String> httpEntity = new HttpEntity<>(credentials, httpHeaders);

        if (this.addHeader != null && !this.addHeader.isEmpty()) {
            this.addHeader.forEach(httpHeaders::set);
        }

        System.out.println(" ");
        System.out.println("- REQUEST TOKEN IS RUNNING FROM CODEXSTESTER");
        System.out.println("- [POST] " + this.urlOauth2Token);
        System.out.println("- [HTTP] " + httpEntity);

        return restTemplate.postForEntity(
                this.urlOauth2Token + credentials,
                httpEntity,
                Oauth2ResponseTokenDto.class);
    }

    private ResponseEntity<Oauth2ResponseTokenCheckDto> tokenCheck() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", basicAuth());

        if (this.addHeader != null && !this.addHeader.isEmpty()) {
            this.addHeader.forEach(httpHeaders::set);
        }

        String body = "?token="+this.token.replaceFirst("Bearer ", "");
        HttpEntity<String> httpEntity = new HttpEntity<>(body, httpHeaders);

        System.out.println(" ");
        System.out.println("- REQUEST CHECK TOKEN IS RUNNING FROM CODEXSTESTER");
        System.out.println("- [POST] " + this.urlOauth2CheckToken + body);
        System.out.println("- [HTTP] " + httpEntity);

        return restTemplate.postForEntity(
                this.urlOauth2CheckToken + body,
                httpEntity,
                Oauth2ResponseTokenCheckDto.class);
    }

    private void checkTokenInstance(String operation) {
        if (operation.equals("generate")) {

            try {
                if (
                        this.urlOauth2Token.isEmpty() ||
                        this.username.isEmpty() ||
                        this.password.isEmpty() ||
                        this.grant.isEmpty() ||
                        this.secret.isEmpty() ||
                        this.clientId.isEmpty()
                ) {
                    throw new RuntimeException("Wrong instance [token]: " + wrongMessage);
                }
            } catch (Exception ex) {
                throw new RuntimeException("Wrong instance [token][exception]: " + wrongMessage);
            }

        } else {

            try {
                if (this.urlOauth2CheckToken.isEmpty() || this.token.isEmpty()) {
                    throw new RuntimeException("Wrong instance [check]: " + wrongMessage);
                }
            } catch (Exception ex) {
                throw new RuntimeException("Wrong instance [check][exception]: " + wrongMessage);
            }
        }
    }

    public void setUrlOauth2Token(String urlOauth2Token) {
        this.urlOauth2Token = urlOauth2Token;
    }

    public void setUrlOauth2CheckToken(String urlOauth2CheckToken) {
        this.urlOauth2CheckToken = urlOauth2CheckToken;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGrant(String grant) {
        this.grant = grant;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAddHeader(HashMap<String, String> addHeader) {
        this.addHeader = addHeader;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">token</h6>
     *
     * <p style="color: #CDCDCD">Get a token from an OAUTH2 Server</p>
     *
     * @return String (OAuth2 Token)
     * @see <a href="https://github.com/huntercodexs/codexstester">Codexs Tester (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public String token() {
        checkTokenInstance("generate");
        ResponseEntity<Oauth2ResponseTokenDto> response = tokenGenerate();

        System.out.println("- [RESPONSE] " + response.getBody());
        System.out.println(" ");

        if (response.getBody() != null) return response.getBody().getAccess_token();
        return null;
    }

    /**
     * <h6 style="color: #FFFF00; font-size: 11px">check</h6>
     *
     * <p style="color: #CDCDCD">Check a token from an OAUTH2 Server</p>
     *
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/codexstester">Codexs Tester (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public boolean check() {
        checkTokenInstance("check");
        ResponseEntity<Oauth2ResponseTokenCheckDto> response = tokenCheck();

        System.out.println("- [RESPONSE] " + response.getBody());
        System.out.println(" ");

        if (response.getBody() != null) {
            return response.getBody().isActive();
        }
        return false;
    }

}
