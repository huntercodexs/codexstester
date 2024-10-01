package codexstester.core.security;

import codexstester.core.dto.Oauth2RequestCheckTokenDto;
import codexstester.core.dto.Oauth2RequestTokenDto;
import codexstester.setup.security.CodexsSecuritySetup;

public abstract class CodexsSecurity extends CodexsSecuritySetup {

    /**
     * DO NOT REMOVE THIS METHOD
     * Change this method function before use it
     * */

    public static Oauth2RequestTokenDto codexsTesterSecurityOAuth2Token(String env) {
        return oauth2Token(env);
    }

    public static Oauth2RequestCheckTokenDto codexsTesterSecurityOAuth2CheckToken(String env, String token) {
        return oauth2CheckToken(env, token);
    }

}
