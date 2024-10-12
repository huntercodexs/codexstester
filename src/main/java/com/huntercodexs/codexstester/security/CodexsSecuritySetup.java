package com.huntercodexs.codexstester.security;

import com.huntercodexs.codexstester.security.dto.BasicAuthRequestDto;
import com.huntercodexs.codexstester.security.dto.JwtAuthRequestDto;
import com.huntercodexs.codexstester.security.dto.Oauth2RequestCheckTokenDto;
import com.huntercodexs.codexstester.security.dto.Oauth2RequestTokenDto;

public interface CodexsSecuritySetup {

    /**
     * <p>
     * The expected data are: (Oauth2RequestTokenDto)
     * </p>
     *
     * <blockquote><pre>
     * Url("http://localhost:33100/service-authorizator/api/rest/oauth/v1/oauth/token");
     * Authorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
     * Grant("password");
     * Username("OAUTH2DEMO_USER");
     * Password("1234567890");
     * ClientId("client_id");
     * Secret("abfcc74b-07cd-425b-906b-abbcd8fa1bec");
     * AddHeader(HashMap&lt;String, String&gt;)
     * </pre></blockquote>
     *
     * @param env (String)
     * @return Oauth2RequestTokenDto
     * */
    Oauth2RequestTokenDto oauth2Token(String env);

    /**
     * <p>
     * The expected data are: (Oauth2RequestCheckTokenDto)
     * </p>
     *
     * <blockquote><pre>
     * Url("http://localhost:33001/service-authorizator/api/rest/oauth/v1/oauth/check_token");
     * Authorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
     * Token("d3462419-2a45-47b7-bcf2-f576f706d3e9");
     * ClientId("client_id");
     * AddHeader(HashMap&lt;String, String&gt;)
     * </pre></blockquote>
     *
     * @param env (String)
     * @return Oauth2RequestTokenDto
     * */
    Oauth2RequestCheckTokenDto oauth2CheckToken(String env, String token);

    /**
     * <p>
     * The expected data are: (BasicAuthRequestDto)
     * </p>
     *
     * <blockquote><pre>
     * Url("http://localhost:35000/api/auth/basic");
     * Username("Username");
     * Password("1234567890");
     * AddHeader(HashMap&lt;String, String&gt;)
     * </pre></blockquote>
     *
     * @param env (String)
     * @return Oauth2RequestTokenDto
     * */
    BasicAuthRequestDto basicAuth(String env);

    /**
     * <p>
     * The expected data are: (JwtAuthRequestDto)
     * </p>
     *
     * <blockquote><pre>
     * Url("http://localhost:35000/api/auth/jwt");
     * Username("Username");
     * Password("1234567890");
     * BearerToken("d3462419-2a45-47b7-bcf2-f576f706d3e9");
     * BasicAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xO...");
     * AuthType(NONE, BASIC, BEARER, USERNAME_PASSWORD);
     * AddHeader(HashMap&lt;String, String&gt;)
     * </pre></blockquote>
     *
     * @param env (String)
     * @return Oauth2RequestTokenDto
     * */
    JwtAuthRequestDto jwtAuth(String env);
}
