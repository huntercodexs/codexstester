package com.huntercodexs.codexstester.setup.datasource;

import com.huntercodexs.codexstester.abstractor.dto.Oauth2RequestCheckTokenDto;
import com.huntercodexs.codexstester.abstractor.dto.Oauth2RequestTokenDto;
import com.huntercodexs.codexstester.postalcode.database.model.PostalCodeEntity;
import com.huntercodexs.codexstester.postalcode.dto.PostalCodeRequestDto;
import com.huntercodexs.codexstester.postalcode.dto.PostalCodeResponseDto;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;

/**
 * SAMPLE DATA SOURCE
 * Use this file to create all tests source
 * */
public class DataSourceTests {

    public static final boolean skipOAuth2Tests = true;
    public static final String samplePort = "33001";
    public static final String sampleEndpointUri = "/huntercodexs/anny-service/api/any-resource";
    public static final String sampleWebhookUrl = "http://your-domain.com/api/1.1/receptor";
    public static final String sampleOauth2Token = "d4cd86a0-809a-40aa-a590-ef68873dcd7b";

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

    public static Oauth2RequestCheckTokenDto dataSourceOAuth2CheckToken(String token) {
        Oauth2RequestCheckTokenDto oauth2RequestCheckTokenDto = new Oauth2RequestCheckTokenDto();
        oauth2RequestCheckTokenDto.setUrl("http://localhost:32943/huntercodexs/arch-demo/service-authorizator/api/rest/oauth/v1/oauth/check_token");
        oauth2RequestCheckTokenDto.setAuthorization("Basic YXJjaF9kZW1vX2NsaWVudF8xOjExMTExMTExLTIyMjItMzMzMy00NDQ0LTU1NTU1NTU1NTU1NQ==");
        oauth2RequestCheckTokenDto.setToken(token);
        return oauth2RequestCheckTokenDto;
    }

    /**
     * SAMPLES
     * Expected Request To Match
     * */

    public static JSONObject dataSourceOkRequest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("rulesCode", "XYZ12345");
        jsonObject.appendField("postalCode", "12090002");
        jsonObject.appendField("webhook", "");
        return jsonObject;
    }

    public static JSONObject dataSourceBadRequest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("rulesCode", 123);
        jsonObject.appendField("postalCode", "12090002");
        return jsonObject;
    }

    public static PostalCodeResponseDto dataSourceMapperFinalResponseDto() {
        return new PostalCodeResponseDto();
    }

    public static PostalCodeRequestDto dataSourcePostalCodeRequestDto() {
        PostalCodeRequestDto postalCodeRequestDto = new PostalCodeRequestDto();
        postalCodeRequestDto.setRulesCode("XYZ12345");
        postalCodeRequestDto.setPostalCode("12090002");
        postalCodeRequestDto.setWebhook("");
        return postalCodeRequestDto;
    }

    public static PostalCodeEntity dataSourcePostalCodeEntityEmpty() {
        PostalCodeEntity postalCodeEntity = new PostalCodeEntity();
        postalCodeEntity.setCep("");
        postalCodeEntity.setLogradouro("");
        postalCodeEntity.setComplemento("");
        postalCodeEntity.setBairro("");
        postalCodeEntity.setLocalidade("");
        postalCodeEntity.setUf("");
        postalCodeEntity.setIbge("");
        postalCodeEntity.setGia("");
        postalCodeEntity.setDdd("");
        postalCodeEntity.setSiafi("");
        return postalCodeEntity;
    }

    public static PostalCodeEntity dataSourcePostalCodeEntityFill() {
        PostalCodeEntity addressEntity = new PostalCodeEntity();
        addressEntity.setCep("12090000");
        addressEntity.setLogradouro("Avenida Dom Pedro I");
        addressEntity.setComplemento("de 2612/2613 a 3634/3635");
        addressEntity.setBairro("Campos Elíseos");
        addressEntity.setLocalidade("Taubaté");
        addressEntity.setUf("SP");
        addressEntity.setIbge("3554102");
        addressEntity.setGia("6889");
        addressEntity.setDdd("12");
        addressEntity.setSiafi("7183");
        return addressEntity;
    }

    /**
     * SAMPLES
     * Expected Response To Match
     * */

    public static ResponseEntity<PostalCodeResponseDto> dataSourcePostalCodeEntityResponse() {
        PostalCodeResponseDto postalCodeResponseDto = new PostalCodeResponseDto();
        postalCodeResponseDto.setCep("12099999");
        postalCodeResponseDto.setLogradouro("Avenida Test");
        postalCodeResponseDto.setComplemento("Complement Test");
        postalCodeResponseDto.setBairro("Bairro Test");
        postalCodeResponseDto.setLocalidade("Localidade Test");
        postalCodeResponseDto.setUf("TT");
        postalCodeResponseDto.setIbge("1234567");
        postalCodeResponseDto.setGia("1234");
        postalCodeResponseDto.setDdd("12");
        postalCodeResponseDto.setSiafi("1234");
        return ResponseEntity.ok().body(postalCodeResponseDto);
    }

    public static String dataSourceSampleResponseString() {
        return "This is a expected sample response";
    }

    public static String dataSourceSampleResponseJSONString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.appendField("setFieldName1", "setFieldValue1");
        jsonObject.appendField("setFieldName2", "setFieldValue2");
        jsonObject.appendField("setFieldName3", "setFieldValue3");
        jsonObject.appendField("setFieldName4", "setFieldValue4");
        /*Add more fields if needed*/
        return jsonObject.toJSONString();
    }

}
