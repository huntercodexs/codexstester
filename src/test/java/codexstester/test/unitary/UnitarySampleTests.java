package codexstester.test.unitary;

import codexstester.abstractor.dto.HeadersDto;
import codexstester.setup.bridge.UnitarySampleBridgeTests;
import codexstester.setup.datasource.DataSourceSampleTests;
import net.minidev.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class UnitarySampleTests extends UnitarySampleBridgeTests {

    @Test
    public void whenSumAnyNumbersTest() {
        int result = DataSourceSampleTests.dataSourceSampleSum(1000, 10);
        codexsTesterAssertInt(result, 1010);
    }

    @Test
    public void whenTypingTests() {
        /*REQUEST SAMPLE*/
        String string = "value1";
        JSONObject jsonObject = new JSONObject();
        HeadersDto headersDto = new HeadersDto();
        HashMap<Object, Object> hashMap = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> linkedList = new LinkedList<>();
        List<String> list = new ArrayList<>();

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("field1", string);
        jsonResponse.put("field2", jsonObject);
        jsonResponse.put("field3", headersDto);
        jsonResponse.put("field4", hashMap);
        jsonResponse.put("field5", arrayList);
        jsonResponse.put("field6", linkedList);
        jsonResponse.put("field7", list);

        codexsTesterCompareJsonFormat(
                expectedJsonKeys(),
                expectedJsonValues(),
                expectedJsonTyping(),
                jsonResponse,
                false);
    }
}
