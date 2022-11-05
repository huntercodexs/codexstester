package com.huntercodexs.codexstester.tests.datasource;

import com.huntercodexs.codexstester.abstractor.dto.Oauth2RequestTokenDto;
import net.minidev.json.JSONObject;

/**
 * SAMPLE DATA SOURCE
 * USE THIS FILE TO CREATE ALL SOURCES TO MAKE THE TESTS
 * */
public class DataSourceTests {


    public static final String samplePort = "33001";
    public static final String sampleEndpointUri = "/huntercodexs/anny-service/api/any-resource";
    public static final String sampleWebhookUrl = "http://your-domain.com/api/1.1/receptor";

    public static Oauth2RequestTokenDto dataSourceOAuth2Token() {
        Oauth2RequestTokenDto oauth2RequestTokenDto = new Oauth2RequestTokenDto();
        oauth2RequestTokenDto.setUrl("PUT HERE THE OAUTH2 URL AUTHORIZATION");
        oauth2RequestTokenDto.setAuth("PUT HERE THE OAUTH2 BASIC AUTHORIZATION");
        oauth2RequestTokenDto.setGrant("PUT HERE THE OAUHT2 GRANT TYPE");
        oauth2RequestTokenDto.setUser("PUT HERE THE OAUTH2 USERNAME");
        oauth2RequestTokenDto.setPass("PUT HERE THE OAUTH2 PASSWORD");
        return oauth2RequestTokenDto;
    }

    public static JSONObject dataSourceAddressRequest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("field1", "123456");
        jsonObject.appendField("field2", "789789");
        jsonObject.appendField("field3", "456456");
        return jsonObject;
    }

}
