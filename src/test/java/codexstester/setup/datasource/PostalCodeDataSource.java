package codexstester.setup.datasource;

import codexstester.core.security.CodexsSecurity;
import com.huntercodexs.postalcode.database.entity.PostalCodeEntity;
import com.huntercodexs.postalcode.dto.PostalCodeRequestDto;
import com.huntercodexs.postalcode.dto.PostalCodeResponseDto;
import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;

/**
 * SAMPLE DATA SOURCE
 * Use this file to create all tests source
 * */
public class PostalCodeDataSource extends CodexsSecurity {

    /**
     * DEFAULT ATTRIBUTES
     * Change it as needed
     * */

    public static final boolean ignoreOAuth2Tests = true;
    public static final String postalCodePort = "33001";
    public static final String postalCodeEndpointUri = "/huntercodexs/anny-service/api/any-resource";
    public static final String postalCodeWebhookUrl = "http://your-domain.com/api/1.1/receptor";
    public static final String postalCodeOauth2Token = "d4cd86a0-809a-40aa-a590-ef68873dcd7b";

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
        jsonObject.appendField("rulesCode", null);
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
        return "This is a expected postalCode response";
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
