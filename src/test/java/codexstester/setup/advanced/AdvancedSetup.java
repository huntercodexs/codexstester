package codexstester.setup.advanced;

import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.resource.quickjson.QuickJson;

import java.util.*;

public abstract class AdvancedSetup {

    /**
     * JSON FORMAT
     */
    public static String[] expectedJsonKeys() {
        return new String[]{"field1", "field2", "field3", "field4", "field5", "field6", "field7", "field8"};
    }

    public static Object[] expectedJsonValues() {
        String string = "value1";
        QuickJson quickJson = new QuickJson();
        HeadersDto headersDto = new HeadersDto();
        HashMap<Object, Object> hashMap = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> linkedList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        return new Object[]{string, quickJson, headersDto, hashMap, arrayList, linkedList, list, linkedHashMap};
    }

    public static Object[] expectedJsonTyped() {
        return new Object[]{
                String.class,
                QuickJson.class,
                HeadersDto.class,
                HashMap.class,
                ArrayList.class,
                LinkedList.class,
                List.class,
                LinkedHashMap.class
        };
    }

    public static Object[][] expectedJsonDataTree() {

        String string = "value1";
        QuickJson quickJson = new QuickJson();
        HeadersDto headersDto = new HeadersDto();
        HashMap<Object, Object> hashMap = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> linkedList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();

        return new Object[][]{
            {"field1", string,        String.class},
            {"field2", quickJson,     QuickJson.class},
            {"field3", headersDto,    HeadersDto.class},
            {"field4", hashMap,       HashMap.class},
            {"field5", arrayList,     ArrayList.class},
            {"field6", linkedList,    LinkedList.class},
            {"field7", list,          List.class},
            {"field8", linkedHashMap, LinkedHashMap.class}
        };
    }

    /**
     * DTO FORMAT
     */
    public static String[] expectedDtoValues() {
        return new String[]{
                "application/json",
                "null",
                "POST",
                "null",
                "null",
                "null",
                "null",
                "null",
                "null",
                "null",
                "null",
                "null",
                "null",
                "null",
                "null",
                "null",
                "null",
                "null",
                "null",
                "{name=value1}"
        };
    }

    public static Object[][] expectedDtoDataTree() {
        return new Object[][]{
                {"contentType", "application/json", String.class},
                {"acceptable", "null", String.class},
                {"httpMethod", "POST", String.class},
                {"statusCode", "null", String.class},
                {"crossOrigin", "null", String.class},
                {"origin", "null", String.class},
                {"hostname", "null", String.class},
                {"ip", "null", String.class},
                {"osName", "null", String.class},
                {"authorizationBasic", "null", String.class},
                {"authorizationBearer", "null", String.class},
                {"apiKeyToken", "null", String.class},
                {"apiKeyAppName", "null", String.class},
                {"apiKeySecret", "null", String.class},
                {"apiKeyValue", "null", String.class},
                {"apiKeyGeneric", "null", String.class},
                {"additionalName", "null", String.class},
                {"additionalValue", "null", String.class},
                {"objectResponse", "null", Class.class},
                {"bodyParameters", "{name=value1}", Map.class}
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
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("customer", "yes");
        return new Object[]{string, quickJson.json()};
    }

    public static Object[] expectedHashMapTyped() {
        return new Object[]{String.class, String.class};
    }

    public static Object[][] expectedHashMapDataTree() {
        return new Object[][]{
            {"name", "john smith", String.class},
            {"info", "{\"age\":30,\"customer\":\"yes\"}", String.class},
        };
    }

    /**
     * ARRAY-LIST FORMAT
     */
    public static Object[] expectedArrayListValues() {
        String string = "john smith";
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("gender", "men");
        return new Object[]{string, quickJson.json()};
    }

    public static Object[] expectedArrayListTyped() {
        return new Object[]{
                String.class, String.class
        };
    }

    public static Object[][] expectedArrayListDataTree() {
        return new Object[][]{
                {0, "john smith", String.class},
                {1, "{\"gender\":\"mens\",\"age\":30}", String.class},
        };
    }

    /**
     * LINKED-LIST FORMAT
     */
    public static Object[] expectedLinkedListValues() {
        String string = "john smith";
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("gender", "mens");
        return new Object[]{string, quickJson};
    }

    public static Object[] expectedLinkedListTyped() {
        return new Object[]{String.class, String.class};
    }

    public static Object[][] expectedLinkedListDataTree() {
        return new Object[][]{
                {0, "john smith", String.class},
                {1, "{\"gender\":\"mens\",\"age\":30}", String.class},
        };
    }

    /**
     * LIST FORMAT
     */
    public static Object[] expectedListValues() {
        String string = "john smith";
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("gender", "mens");
        return new Object[]{string, quickJson};
    }

    public static Object[] expectedListTyped() {
        return new Object[]{String.class, String.class};
    }

    public static Object[][] expectedListDataTree() {
        return new Object[][]{
                {0, "john smith", String.class},
                {1, "{\"gender\":\"mens\",\"age\":30}", String.class},
        };
    }

    /**
     * LINKED-HASH-MAP FORMAT
     */
    public static String[] expectedLinkedHashMapKeys() {
        return new String[]{"name", "info"};
    }

    public static Object[] expectedLinkedHashMapValues() {
        String string = "john smith";
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("customer", "yes");
        return new Object[]{string, quickJson};
    }

    public static Object[] expectedLinkedHashMapTyped() {
        return new Object[]{String.class, QuickJson.class};
    }

    public static Object[][] expectedLinkedHashMapDataTree() {
        return new Object[][]{
                {"name", "john smith", String.class},
                {"info", "{\"age\":30,\"customer\":\"yes\"}", String.class},
        };
    }

}
