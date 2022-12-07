package codexstester.test.unitary;

import codexstester.abstractor.dto.HeadersDto;
import codexstester.setup.bridge.UnitarySampleBridgeTests;
import codexstester.setup.datasource.DataSourceSampleTests;
import net.minidev.json.JSONObject;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.*;

public class UnitarySampleTests extends UnitarySampleBridgeTests {

    @Test
    public void whenSumAnyNumbersTest() {
        int result = DataSourceSampleTests.dataSourceSampleSum(1000, 10);
        codexsTesterAssertInt(result, 1010);
    }

    @Test
    public void whenJsonFormatTypedTests() {
        /*SIMULATE RESPONSE SAMPLE*/
        String string = "value1";
        JSONObject jsonObject = new JSONObject();
        HeadersDto headersDto = new HeadersDto();
        HashMap<Object, Object> hashMap = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> linkedList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("field1", string);
        jsonResponse.put("field2", jsonObject);
        jsonResponse.put("field3", headersDto);
        jsonResponse.put("field4", hashMap);
        jsonResponse.put("field5", arrayList);
        jsonResponse.put("field6", linkedList);
        jsonResponse.put("field7", list);
        jsonResponse.put("field8", linkedHashMap);

        codexsTesterCompareJsonFormat(
                expectedJsonKeys(),
                expectedJsonValues(),
                expectedJsonTyped(),
                jsonResponse,
                true);
    }

    @Test
    public void whenDtoFormatTypedTests() {
        /*SIMULATE RESPONSE SAMPLE*/
        HeadersDto headersDtoResponse = new HeadersDto();
        headersDtoResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDtoResponse.setHttpMethod(HTTP_METHOD_POST);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("name", "value1");
        headersDtoResponse.setBodyParameters(responseMap);

        codexsTesterCompareDtoFormat(
                expectedDtoValues(),
                headersDtoResponse,
                HeadersDto.class,
                true);
    }

    @Test
    public void whenHashMapTypedTests() {
        /*SIMULATE RESPONSE SAMPLE*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", 30);
        jsonObject.put("customer", "yes");

        HashMap<Object, Object> hashMapResponse = new HashMap<>();
        hashMapResponse.put("name", "john smith");
        hashMapResponse.put("info", jsonObject);

        codexsTesterCompareHashMapFormat(
                expectedHashMapKeys(),
                expectedHashMapValues(),
                expectedHashMapTyped(),
                hashMapResponse,
                true);
    }

    @Test
    public void whenArrayListTypedTests() {
        /*SIMULATE RESPONSE SAMPLE*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", 30);
        jsonObject.put("gender", "mens");

        ArrayList<Object> arrayListResponse = new ArrayList<>();
        arrayListResponse.add("john smith");
        arrayListResponse.add(jsonObject);

        codexsTesterCompareArrayListFormat(
                expectedArrayListValues(),
                expectedArrayListTyped(),
                arrayListResponse,
                true);
    }

    @Test
    public void whenALinkedListTypedTests() {
        /*SIMULATE RESPONSE SAMPLE*/
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", 30);
        jsonObject.put("gender", "mens");

        LinkedList<Object> linkedListResponse = new LinkedList<>();
        linkedListResponse.add("john smith");
        linkedListResponse.add(jsonObject);

        codexsTesterCompareLinkedListFormat(
                expectedLinkedListValues(),
                expectedLinkedListTyped(),
                linkedListResponse,
                false);
    }

    @Test
    public void whenListTypedTests() {
        codexsTesterCompareListFormat();
    }

    @Test
    public void whenLinkedHashMapTests() {
        codexsTesterCompareLinkedHashMapFormat();
    }
}
