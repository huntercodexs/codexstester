package com.huntercodexs.codexstester.datasource;

import com.huntercodexs.codexstester.abstractor.dto.Oauth2RequestTokenDto;
import net.minidev.json.JSONObject;

/**
 * SAMPLE DATA SOURCE
 * Use this file to create all tests source
 * */
public class DataSourceTests {

    public static final String samplePort = "33001";
    public static final String sampleEndpointUri = "/huntercodexs/anny-service/api/any-resource";
    public static final String sampleWebhookUrl = "http://your-domain.com/api/1.1/receptor";

    /**
     * DO NOT REMOVE THIS METHOD
     * Change this method function before use it
     * */
    public static Oauth2RequestTokenDto dataSourceOAuth2Token() {
        Oauth2RequestTokenDto oauth2RequestTokenDto = new Oauth2RequestTokenDto();
        oauth2RequestTokenDto.setUrl("http://localhost:33001/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/token");
        oauth2RequestTokenDto.setAuth("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
        oauth2RequestTokenDto.setGrant("password");
        oauth2RequestTokenDto.setUser("OAUTH2DEMO_USER");
        oauth2RequestTokenDto.setPass("1234567890");
        return oauth2RequestTokenDto;
    }

    /**
     * Change this method function before use it
     * */
    public static JSONObject dataSourceOkRequest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("rulesCode", "XYZ12345");
        jsonObject.appendField("postalCode", "12090002");
        jsonObject.appendField("webhook", "");
        return jsonObject;
    }

    /**
     * Create all data source to make tests below, example: dataSourceSample
     * */
    public static JSONObject dataSourceBadRequest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("rulesCode", 123);
        jsonObject.appendField("postalCode", "12090002");
        return jsonObject;
    }

}
