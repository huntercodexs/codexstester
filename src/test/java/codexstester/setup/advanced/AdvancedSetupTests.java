package codexstester.setup.advanced;

import codexstester.abstractor.dto.HeadersDto;
import net.minidev.json.JSONObject;

import java.util.*;

public class AdvancedSetupTests {

    /**
     * JSON FORMAT
     */
    public static String[] expectedJsonKeys() {
        return new String[]{"field1", "field2", "field3", "field4", "field5", "field6", "field7", "field8"};
    }

    public static Object[] expectedJsonValues() {
        String string = "value1";
        JSONObject jsonObject = new JSONObject();
        HeadersDto headersDto = new HeadersDto();
        HashMap<Object, Object> hashMap = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> linkedList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        return new Object[]{string, jsonObject, headersDto, hashMap, arrayList, linkedList, list, linkedHashMap};
    }

    public static Object[] expectedJsonTyped() {
        return new Object[]{
                String.class,
                JSONObject.class,
                HeadersDto.class,
                HashMap.class,
                ArrayList.class,
                LinkedList.class,
                List.class,
                LinkedHashMap.class
        };
    }

    /**
     * DTO FORMAT
     */
    public static String[] expectedDtoValues() {
        return new String[]{
                "contentType=application/json",
                "accepted=null",
                "httpMethod=POST",
                "statusCode=null",
                "crossOrigin=null",
                "origin=null",
                "hostname=null",
                "ip=null",
                "osName=null",
                "authorizationBasic=null",
                "authorizationBearer=null",
                "apiKeyToken=null",
                "apiKeyAppName=null",
                "apiKeySecret=null",
                "apiKeyValue=null",
                "apiKeyGeneric=null",
                "additionalName=null",
                "additionalValue=null",
                "bodyParameters={name=value1}"
        };
    }

    /**
     * HASH-MAP FORMAT
     */
    public static String[] expectedHashMapKeys() {
        return new String[]{"name", "info"};
    }

    public static Object[] expectedHashMapValues() {
        String string = "john smith";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", 30);
        jsonObject.put("customer", "yes");
        return new Object[]{string, jsonObject};
    }

    public static Object[] expectedHashMapTyped() {
        return new Object[]{String.class, JSONObject.class};
    }

    /**
     * ARRAY-LIST FORMAT
     */
    public static Object[] expectedArrayListValues() {
        String string = "john smith";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", 30);
        jsonObject.put("gender", "mens");
        return new Object[]{string, jsonObject};
    }

    public static Object[] expectedArrayListTyped() {
        return new Object[]{String.class, JSONObject.class};
    }

    /**
     * LINKED-LIST FORMAT
     */
    public static Object[] expectedLinkedListValues() {
        String string = "john smith";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", 30);
        jsonObject.put("gender", "mens");
        return new Object[]{string, jsonObject};
    }

    public static Object[] expectedLinkedListTyped() {
        return new Object[]{String.class, JSONObject.class};
    }

    /**
     * LIST FORMAT
     */
    public static Object[] expectedListValues() {
        String string = "john smith";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", 30);
        jsonObject.put("gender", "mens");
        return new Object[]{string, jsonObject};
    }

    public static Object[] expectedListTyped() {
        return new Object[]{String.class, String.class};
    }
}
