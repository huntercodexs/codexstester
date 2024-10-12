package codexstester.setup.security;

import com.huntercodexs.codexstester.dto.JwtResponseDto;
import com.huntercodexs.codexstester.security.AuthType;
import com.huntercodexs.codexstester.security.CodexsSecurity;
import com.huntercodexs.codexstester.security.CodexsSecuritySetup;
import com.huntercodexs.codexstester.security.dto.BasicAuthRequestDto;
import com.huntercodexs.codexstester.security.dto.JwtAuthRequestDto;
import com.huntercodexs.codexstester.security.dto.Oauth2RequestCheckTokenDto;
import com.huntercodexs.codexstester.security.dto.Oauth2RequestTokenDto;

import java.util.Base64;
import java.util.HashMap;

public class SecuritySetup implements CodexsSecuritySetup {

    private boolean rsa;

    /**
     * SECURITY SETTINGS (Change it as needed)
     * WARNING: DO NOT REMOVE THESE METHODS JUST CHANGE IT
     **/

    @Override
    public Oauth2RequestTokenDto oauth2Token(String env) {

        HashMap<String, String> header = new HashMap<>();
        Oauth2RequestTokenDto oauth2RequestTokenDto = new Oauth2RequestTokenDto();

        switch (env) {
            case "local":
                /*Sample 1*/
                /*oauth2RequestTokenDto.setUrl("http://localhost:33100/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
                oauth2RequestTokenDto.setGrant("password");
                oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                oauth2RequestTokenDto.setPass("1234567890");
                oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestTokenDto.setClientId("arch_demo_client_1");
                oauth2RequestTokenDto.setSecret("11111111-2222-3333-4444-555555555555");

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                oauth2RequestTokenDto.setAddHeader(header);*/

                /*Sample 2 - Without additional header*/
                oauth2RequestTokenDto.setUrl("http://localhost:33100/huntercodexs/server/api/rest/oauth/v1/oauth/token");
                oauth2RequestTokenDto.setAuth("Basic Y2xpZW50X2lkOmNiZmNjNzRiLTA3Y2QtNGFiYi05MDZiLWFiZGRkOGZhMWJlYw==");
                oauth2RequestTokenDto.setGrant("password");
                oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                oauth2RequestTokenDto.setPass("1234567890");
                oauth2RequestTokenDto.setClientId("client_id");
                oauth2RequestTokenDto.setSecret("cbfcc74b-07cd-4abb-906b-abddd8fa1bec");

                return oauth2RequestTokenDto;
            case "dev":
                oauth2RequestTokenDto.setUrl("http://192.168.0.174:33001/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
                oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestTokenDto.setGrant("password");
                oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                oauth2RequestTokenDto.setPass("1234567890");
                oauth2RequestTokenDto.setClientId("arch_demo_client_1");
                oauth2RequestTokenDto.setSecret("11111111-2222-3333-4444-555555555555");

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                oauth2RequestTokenDto.setAddHeader(header);

                return oauth2RequestTokenDto;
            case "test":
            case "homolog":
                oauth2RequestTokenDto.setUrl("http://homolog.huntercodexs.com/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
                oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestTokenDto.setGrant("password");
                oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                oauth2RequestTokenDto.setPass("1234567890");
                oauth2RequestTokenDto.setClientId("arch_demo_client_1");
                oauth2RequestTokenDto.setSecret("11111111-2222-3333-4444-555555555555");

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                oauth2RequestTokenDto.setAddHeader(header);

                return oauth2RequestTokenDto;
            case "prod":
                oauth2RequestTokenDto.setUrl("http://api.huntercodexs.com/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
                oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestTokenDto.setGrant("password");
                oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
                oauth2RequestTokenDto.setPass("1234567890");
                oauth2RequestTokenDto.setClientId("arch_demo_client_1");
                oauth2RequestTokenDto.setSecret("11111111-2222-3333-4444-555555555555");

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                oauth2RequestTokenDto.setAddHeader(header);

                return oauth2RequestTokenDto;
        }
        return null;
    }

    @Override
    public Oauth2RequestCheckTokenDto oauth2CheckToken(String env, String token) {

        HashMap<String, String> header = new HashMap<>();
        Oauth2RequestCheckTokenDto oauth2RequestCheckTokenDto = new Oauth2RequestCheckTokenDto();

        switch (env) {
            case "local":
                /*Sample 1*/
                /*oauth2RequestCheckTokenDto.setUrl("http://localhost:33001/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
                oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestCheckTokenDto.setClientId("arch_demo_client_1");
                oauth2RequestCheckTokenDto.setSecret("11111111-2222-3333-4444-555555555555");
                oauth2RequestCheckTokenDto.setToken(token);

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                oauth2RequestCheckTokenDto.setAddHeader(header);*/

                /*Sample 2 - Without additional header*/
                oauth2RequestCheckTokenDto.setUrl("http://localhost:33100/huntercodexs/server/api/rest/oauth/v1/oauth/check_token");
                oauth2RequestCheckTokenDto.setAuthorization("Basic Y2xpZW50X2lkOmNiZmNjNzRiLTA3Y2QtNGFiYi05MDZiLWFiZGRkOGZhMWJlYw==");
                oauth2RequestCheckTokenDto.setClientId("client_id");
                oauth2RequestCheckTokenDto.setSecret("cbfcc74b-07cd-4abb-906b-abddd8fa1bec");
                oauth2RequestCheckTokenDto.setToken(token);

                return oauth2RequestCheckTokenDto;
            case "dev":
                oauth2RequestCheckTokenDto.setUrl("http://192.168.0.174:33001/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
                oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestCheckTokenDto.setToken(token);
                oauth2RequestCheckTokenDto.setClientId("arch_demo_client_1");
                oauth2RequestCheckTokenDto.setSecret("11111111-2222-3333-4444-555555555555");

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                oauth2RequestCheckTokenDto.setAddHeader(header);

                return oauth2RequestCheckTokenDto;
            case "test":
            case "homolog":
                oauth2RequestCheckTokenDto.setUrl("http://homolog.huntercodexs.com/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
                oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestCheckTokenDto.setToken(token);
                oauth2RequestCheckTokenDto.setClientId("arch_demo_client_1");
                oauth2RequestCheckTokenDto.setSecret("11111111-2222-3333-4444-555555555555");

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                oauth2RequestCheckTokenDto.setAddHeader(header);

                return oauth2RequestCheckTokenDto;
            case "prod":
                oauth2RequestCheckTokenDto.setUrl("http://api.huntercodexs.com/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
                oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
                oauth2RequestCheckTokenDto.setToken(token);
                oauth2RequestCheckTokenDto.setClientId("arch_demo_client_1");
                oauth2RequestCheckTokenDto.setSecret("11111111-2222-3333-4444-555555555555");

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                oauth2RequestCheckTokenDto.setAddHeader(header);

                return oauth2RequestCheckTokenDto;
        }

        return null;
    }

    @Override
    public BasicAuthRequestDto basicAuth(String env) {

        HashMap<String, String> header;
        BasicAuthRequestDto basicAuthRequestDto = new BasicAuthRequestDto();

        switch (env) {
            case "local":

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                basicAuthRequestDto.setAddHeader(header);

                basicAuthRequestDto.setUrl("http://localhost:35000/api/auth/basic");
                basicAuthRequestDto.setUsername("username");
                basicAuthRequestDto.setPassword("password");
                basicAuthRequestDto.setAddHeader(header);
                return basicAuthRequestDto;

            case "dev":

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                basicAuthRequestDto.setAddHeader(header);

                basicAuthRequestDto.setUrl("http://192.168.0.174:35000/api/auth/basic");
                basicAuthRequestDto.setUsername("username");
                basicAuthRequestDto.setPassword("password");
                basicAuthRequestDto.setAddHeader(header);
                return basicAuthRequestDto;

            case "test":
            case "homolog":

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                basicAuthRequestDto.setAddHeader(header);

                basicAuthRequestDto.setUrl("http://homolog.huntercodexs.com/api/auth/basic");
                basicAuthRequestDto.setUsername("username");
                basicAuthRequestDto.setPassword("password");
                basicAuthRequestDto.setAddHeader(header);
                return basicAuthRequestDto;

            case "prod":

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                basicAuthRequestDto.setAddHeader(header);

                basicAuthRequestDto.setUrl("http://api.huntercodexs.com/api/auth/basic");
                basicAuthRequestDto.setUsername("username");
                basicAuthRequestDto.setPassword("password");
                basicAuthRequestDto.setAddHeader(header);
                return basicAuthRequestDto;

        }

        return basicAuthRequestDto;
    }

    @Override
    public JwtAuthRequestDto jwtAuth(String env) {

        HashMap<String, String> header;
        JwtAuthRequestDto jwtAuthRequestDto = new JwtAuthRequestDto();

        switch (env) {
            case "local":

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                jwtAuthRequestDto.setAddHeader(header);

                // RS512, RSA
                if (this.rsa) {
                    jwtAuthRequestDto.setUrl("http://localhost:35000/api/auth/jwt-assign");
                } else {
                    // HS512 (NOT RSA)
                    jwtAuthRequestDto.setUrl("http://localhost:35000/api/auth/jwt");
                }

                jwtAuthRequestDto.setUsername("username");
                jwtAuthRequestDto.setPassword("password");
                jwtAuthRequestDto.setAuthType(AuthType.NONE);
                jwtAuthRequestDto.setAuthType(AuthType.BASIC);
                jwtAuthRequestDto.setAuthType(AuthType.BEARER);
                jwtAuthRequestDto.setAuthType(AuthType.USERNAME_PASSWORD);
                jwtAuthRequestDto.setBearerToken("");
                jwtAuthRequestDto.setBasicAuth("");
                jwtAuthRequestDto.setAddHeader(header);
                return jwtAuthRequestDto;

            case "dev":

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                jwtAuthRequestDto.setAddHeader(header);

                jwtAuthRequestDto.setUrl("http://192.168.0.174:35000/api/auth/jwt");
                jwtAuthRequestDto.setUsername("username");
                jwtAuthRequestDto.setPassword("password");
                jwtAuthRequestDto.setAuthType(AuthType.NONE);
                jwtAuthRequestDto.setAuthType(AuthType.BASIC);
                jwtAuthRequestDto.setAuthType(AuthType.BEARER);
                jwtAuthRequestDto.setAuthType(AuthType.USERNAME_PASSWORD);
                jwtAuthRequestDto.setBearerToken("");
                jwtAuthRequestDto.setBasicAuth("");
                jwtAuthRequestDto.setAddHeader(header);
                return jwtAuthRequestDto;

            case "test":
            case "homolog":

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                jwtAuthRequestDto.setAddHeader(header);

                jwtAuthRequestDto.setUrl("http://homolog.huntercodexs.com/api/auth/jwt");
                jwtAuthRequestDto.setUsername("username");
                jwtAuthRequestDto.setPassword("password");
                jwtAuthRequestDto.setAuthType(AuthType.NONE);
                jwtAuthRequestDto.setAuthType(AuthType.BASIC);
                jwtAuthRequestDto.setAuthType(AuthType.BEARER);
                jwtAuthRequestDto.setAuthType(AuthType.USERNAME_PASSWORD);
                jwtAuthRequestDto.setBearerToken("");
                jwtAuthRequestDto.setBasicAuth("");
                jwtAuthRequestDto.setAddHeader(header);
                return jwtAuthRequestDto;

            case "prod":

                header = new HashMap<>();
                header.put("Access-Code", "XYZ-123");
                jwtAuthRequestDto.setAddHeader(header);

                jwtAuthRequestDto.setUrl("http://api.huntercodexs.com/api/auth/jwt");
                jwtAuthRequestDto.setUsername("username");
                jwtAuthRequestDto.setPassword("password");
                jwtAuthRequestDto.setAuthType(AuthType.NONE);
                jwtAuthRequestDto.setAuthType(AuthType.BASIC);
                jwtAuthRequestDto.setAuthType(AuthType.BEARER);
                jwtAuthRequestDto.setAuthType(AuthType.USERNAME_PASSWORD);
                jwtAuthRequestDto.setBearerToken("");
                jwtAuthRequestDto.setBasicAuth("");
                jwtAuthRequestDto.setAddHeader(header);
                return jwtAuthRequestDto;

        }

        return jwtAuthRequestDto;
    }

    public String oauth2Authorization(String env) {
        CodexsSecurity codexsSecurity = new CodexsSecurity(oauth2Token(env));
        return codexsSecurity.token();
    }

    public String basicAuthorization(String env) {
        BasicAuthRequestDto basicAuthRequestDto = basicAuth(env);

        String login = basicAuthRequestDto.getUsername()+":"+basicAuthRequestDto.getPassword();
        byte[] inputBytes = login.getBytes();
        byte[] base64InputBytes = Base64.getEncoder().encode(inputBytes);

        return new String(base64InputBytes);
    }

    public String jwtAuthorization(String env, boolean rsa) {
        this.rsa = rsa;
        JwtAuthRequestDto jwtAuthRequestDto = jwtAuth(env);
        CodexsSecurity codexsSecurity = new CodexsSecurity(jwtAuthRequestDto);

        JwtResponseDto response = codexsSecurity.jwtAuth(JwtResponseDto.class);
        return response.getJwt();
    }

}
