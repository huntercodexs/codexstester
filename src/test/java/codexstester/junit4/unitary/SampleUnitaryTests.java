package codexstester.junit4.unitary;

import codexstester.setup.bridge.SampleBridgeTest;
import codexstester.setup.datasource.SampleDataSource;
import com.huntercodexs.codexstester.dto.HeadersDto;
import com.huntercodexs.codexstester.resource.quickjson.QuickJson;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.*;

import static codexstester.setup.advanced.AdvancedSetup.*;
import static codexstester.setup.datasource.SampleDataSource.quickJsonDataSource;
import static com.huntercodexs.codexstester.resource.file.CodexsFileReader.awaitFileContent;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperReadFile;
import static com.huntercodexs.codexstester.util.CodexsJsonParser.codexsTesterJsonRefactor;
import static com.huntercodexs.codexstester.util.CodexsJsonParser.codexsTesterParseOrgJsonObject;

public class SampleUnitaryTests extends SampleBridgeTest {

    @Test
    public void propsTest() {
        System.out.println(unitaryProps);
    }

    @Test
    public void whenSumAnyNumbersTest() {
        int result = SampleDataSource.dataSourceSampleSum(1000, 10);
        codexsTesterAssertInt(result, 1010, null);
    }

    @Test
    public void quickJsonTest() {
        String result = quickJsonDataSource();
        codexsTesterAssertExact("{\"name\":\"Jereelton\",\"age\":39}", result, null);
    }

    @Test
    public void whenOrgJsonFormatTypedWithDataTreeTests() throws Exception {

        /*SIMULATE RESPONSE SAMPLE*/
        String string = "value1";
        QuickJson quickJson = new QuickJson();
        HeadersDto headersDto = new HeadersDto();
        HashMap<Object, Object> hashMap = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> linkedList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();

        org.json.JSONObject orgJsonResponseSimulate = new org.json.JSONObject();
        orgJsonResponseSimulate.put("field1", string);
        orgJsonResponseSimulate.put("field2", quickJson);
        orgJsonResponseSimulate.put("field3", headersDto);
        orgJsonResponseSimulate.put("field4", hashMap);
        orgJsonResponseSimulate.put("field5", arrayList);
        orgJsonResponseSimulate.put("field6", linkedList);
        orgJsonResponseSimulate.put("field7", list);
        orgJsonResponseSimulate.put("field8", linkedHashMap);

        codexsTesterCompareJsonFormat(
                expectedJsonDataTree(),
                orgJsonResponseSimulate,
                true,
                "none",
                true);

    }

    @Test
    public void whenQuickJsonFormatTypedWithDataTreeTests() throws Exception {

        /*SIMULATE RESPONSE SAMPLE*/
        String string = "value1";
        QuickJson quickJson = new QuickJson();
        HeadersDto headersDto = new HeadersDto();
        HashMap<Object, Object> hashMap = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> linkedList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();

        QuickJson quickJsonResponseSimulate = new QuickJson();
        quickJsonResponseSimulate.add("field1", string);
        quickJsonResponseSimulate.add("field2", quickJson);
        quickJsonResponseSimulate.add("field3", headersDto);
        quickJsonResponseSimulate.add("field4", hashMap);
        quickJsonResponseSimulate.add("field5", arrayList);
        quickJsonResponseSimulate.add("field6", linkedList);
        quickJsonResponseSimulate.add("field7", list);
        quickJsonResponseSimulate.add("field8", linkedHashMap);

        codexsTesterCompareJsonFormat(
                expectedJsonDataTree(),
                quickJsonResponseSimulate,
                true,
                "none",
                true);
    }

    @Test
    public void whenOrgJsonFormatTypedTests() throws Exception {

        /*SIMULATE RESPONSE SAMPLE*/
        String string = "value1";
        QuickJson quickJson = new QuickJson();
        HeadersDto headersDto = new HeadersDto();
        HashMap<Object, Object> hashMap = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> linkedList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();

        org.json.JSONObject orgJsonResponseSimulate = new org.json.JSONObject();
        orgJsonResponseSimulate.put("field1", string);
        orgJsonResponseSimulate.put("field2", quickJson);
        orgJsonResponseSimulate.put("field3", headersDto);
        orgJsonResponseSimulate.put("field4", hashMap);
        orgJsonResponseSimulate.put("field5", arrayList);
        orgJsonResponseSimulate.put("field6", linkedList);
        orgJsonResponseSimulate.put("field7", list);
        orgJsonResponseSimulate.put("field8", linkedHashMap);

        codexsTesterCompareJsonFormat(
                expectedJsonKeys(),
                expectedJsonValues(),
                expectedJsonTyped(),
                orgJsonResponseSimulate,
                true,
                "none",
                true);
    }

    @Test
    public void whenQuickJsonFormatTypedTests() throws Exception {

        /*SIMULATE RESPONSE SAMPLE*/
        String string = "value1";
        QuickJson quickJson = new QuickJson();
        HeadersDto headersDto = new HeadersDto();
        HashMap<Object, Object> hashMap = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> linkedList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();

        QuickJson quickJsonResponseSimulate = new QuickJson();
        quickJsonResponseSimulate.add("field1", string);
        quickJsonResponseSimulate.add("field2", quickJson);
        quickJsonResponseSimulate.add("field3", headersDto);
        quickJsonResponseSimulate.add("field4", hashMap);
        quickJsonResponseSimulate.add("field5", arrayList);
        quickJsonResponseSimulate.add("field6", linkedList);
        quickJsonResponseSimulate.add("field7", list);
        quickJsonResponseSimulate.add("field8", linkedHashMap);

        codexsTesterCompareJsonFormat(
                expectedJsonKeys(),
                expectedJsonValues(),
                expectedJsonTyped(),
                quickJsonResponseSimulate,
                true,
                "none",
                true);
    }

    @Test
    public void whenDtoFormatTypedTests() {

        /*SIMULATE RESPONSE SAMPLE*/
        HeadersDto dtoToCompare = new HeadersDto();
        dtoToCompare.setContentType(MediaType.APPLICATION_JSON_VALUE);
        dtoToCompare.setHttpMethod(HTTP_METHOD_POST);

        Map<String, String> mapResponseSimulate = new HashMap<>();
        mapResponseSimulate.put("name", "value1");
        dtoToCompare.setBodyParameters(mapResponseSimulate);

        codexsTesterCompareDtoFormat(
                expectedDtoValues(),
                dtoToCompare,
                HeadersDto.class,
                true);
    }

    @Test
    public void whenDtoFormatTypedWithDataTreeTests() {

        /*SIMULATE RESPONSE SAMPLE*/
        HeadersDto headersDtoResponse = new HeadersDto();
        headersDtoResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        headersDtoResponse.setHttpMethod(HTTP_METHOD_POST);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("name", "value1");
        headersDtoResponse.setBodyParameters(responseMap);

        codexsTesterCompareDtoFormat(
                expectedDtoDataTree(),
                headersDtoResponse,
                HeadersDto.class,
                true);
    }

    @Test
    public void whenListTypedTests() {

        /*SIMULATE RESPONSE SAMPLE*/
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("gender", "mens");

        List<String> listResponse = new ArrayList<>();
        listResponse.add("john smith");
        listResponse.add(quickJson.toString());

        codexsTesterCompareListFormat(
                expectedListValues(),
                expectedListTyped(),
                listResponse,
                true);
    }

    @Test
    public void whenListTypedWithDataTreeTests() {
        /*SIMULATE RESPONSE SAMPLE*/
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("gender", "mens");

        List<String> listResponse = new ArrayList<>();
        listResponse.add("john smith");
        listResponse.add(quickJson.json());

        codexsTesterCompareListFormat(
                expectedListDataTree(),
                listResponse,
                true);
    }

    @Test
    public void whenArrayListTypedTests() {

        /*SIMULATE RESPONSE SAMPLE*/
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("gender", "men");

        ArrayList<Object> arrayListResponse = new ArrayList<>();
        arrayListResponse.add("john smith");
        arrayListResponse.add(quickJson.json());

        codexsTesterCompareArrayListFormat(
                expectedArrayListValues(),
                expectedArrayListTyped(),
                arrayListResponse,
                true);
    }

    @Test
    public void whenArrayListTypedWithDataTreeTests() {

        /*SIMULATE RESPONSE SAMPLE*/
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("gender", "mens");

        ArrayList<Object> arrayListResponse = new ArrayList<>();
        arrayListResponse.add("john smith");
        arrayListResponse.add(quickJson.json());

        codexsTesterCompareArrayListFormat(
                expectedArrayListDataTree(),
                arrayListResponse,
                true);
    }

    @Test
    public void whenHashMapTypedTests() {

        /*SIMULATE RESPONSE SAMPLE*/
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("customer", "yes");

        HashMap<Object, Object> hashMapResponse = new HashMap<>();
        hashMapResponse.put("name", "john smith");
        hashMapResponse.put("info", quickJson.json());

        codexsTesterCompareHashMapFormat(
                expectedHashMapKeys(),
                expectedHashMapValues(),
                expectedHashMapTyped(),
                hashMapResponse,
                true);
    }

    @Test
    public void whenHashMapTypedWithDataTreeTests() {

        /*SIMULATE RESPONSE SAMPLE*/
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("customer", "yes");

        HashMap<Object, Object> hashMapResponse = new HashMap<>();
        hashMapResponse.put("name", "john smith");
        hashMapResponse.put("info", quickJson.json());

        codexsTesterCompareHashMapFormat(
                expectedHashMapDataTree(),
                hashMapResponse,
                true);
    }

    @Test
    public void whenALinkedListTypedTests() {

        /*SIMULATE RESPONSE SAMPLE*/
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("gender", "mens");

        LinkedList<Object> linkedListResponse = new LinkedList<>();
        linkedListResponse.add("john smith");
        linkedListResponse.add(quickJson.json());

        codexsTesterCompareLinkedListFormat(
                expectedLinkedListValues(),
                expectedLinkedListTyped(),
                linkedListResponse,
                false);
    }

    @Test
    public void whenALinkedListTypedWithDataTreeTests() {

        /*SIMULATE RESPONSE SAMPLE*/
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("gender", "mens");

        LinkedList<Object> linkedListResponse = new LinkedList<>();
        linkedListResponse.add("john smith");
        linkedListResponse.add(quickJson.json());

        codexsTesterCompareLinkedListFormat(
                expectedLinkedListDataTree(),
                linkedListResponse,
                true);
    }

    @Test
    public void whenLinkedHashMapTests() {
        /*SIMULATE RESPONSE SAMPLE*/
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("customer", "yes");

        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("name", "john smith");
        linkedHashMap.put("info", quickJson);

        codexsTesterCompareLinkedHashMapFormat(
                expectedLinkedHashMapKeys(),
                expectedLinkedHashMapValues(),
                expectedLinkedHashMapTyped(),
                linkedHashMap,
                true);
    }

    @Test
    public void whenLinkedHashMapWithDataTreeTests() {
        /*SIMULATE RESPONSE SAMPLE*/
        QuickJson quickJson = new QuickJson();
        quickJson.add("age", 30);
        quickJson.add("customer", "yes");

        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("name", "john smith");
        linkedHashMap.put("info", quickJson.json());

        codexsTesterCompareLinkedHashMapFormat(
                expectedLinkedHashMapDataTree(),
                linkedHashMap,
                true);
    }

    @Test
    public void orgJson1Test() throws Exception {
        org.json.JSONObject json1 = new org.json.JSONObject();
        json1.put("name", "John");
        json1.put("age", "34");
        json1.put("mail", "john@testmail.com");
        json1.put("site", "http://www.johnsmith.com");
        List<String> interest = new ArrayList<>();
        interest.add("Soccer");
        interest.add("Run");
        interest.add("Cycle");
        interest.add("Films");
        json1.put("interest", interest);
        List<String> strings = new ArrayList<>();
        strings.add(String.valueOf(1));
        strings.add("2");
        strings.add("3");
        strings.add("4");
        json1.put("strings", strings);
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1893);
        numbers.add(293);
        numbers.add(39);
        numbers.add(3);
        json1.put("numbers", numbers);
        List<String> strings2 = new ArrayList<>();
        strings2.add("1, 2,3, 4, 5 ,6, 123 345 987 345");
        json1.put("strings2", strings2);
        List<String> stringNumbers = new ArrayList<>();
        stringNumbers.add("n1");
        stringNumbers.add("x2");
        stringNumbers.add("e3");
        stringNumbers.add("m4");
        json1.put("stringNumbers", stringNumbers);

        Object object1 = json1;
        System.out.println("JSON 1");
        System.out.println(json1);
        String string1 = json1.toString();
        System.out.println("OBJECT 1");
        System.out.println(object1);
        System.out.println("STRING 1");
        System.out.println(string1);

        codexsTesterParseOrgJsonObject(string1, true);
        codexsTesterJsonRefactor("complex", object1, true);
    }

    @Test
    public void orgJson2Test() throws Exception {
        org.json.JSONObject json1 = new org.json.JSONObject();
        json1.put("name", "John");
        json1.put("age", "34");
        json1.put("mail", "john@testmail.com");
        json1.put("site", "http://www.johnsmith.com");
        List<String> interest = new ArrayList<>();
        interest.add("Soccer");
        interest.add("Run");
        interest.add("Cycle");
        interest.add("Films");
        json1.put("interest", interest);
        List<String> strings = new ArrayList<>();
        strings.add(String.valueOf(1));
        strings.add("2");
        strings.add("3");
        strings.add("4");
        json1.put("strings", strings);
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1893);
        numbers.add(293);
        numbers.add(39);
        numbers.add(3);
        json1.put("numbers", numbers);
        List<String> strings2 = new ArrayList<>();
        strings2.add("1, 2,3, 4, 5 ,6");
        json1.put("strings2", strings2);
        List<String> stringNumbers = new ArrayList<>();
        stringNumbers.add("n1");
        stringNumbers.add("x2");
        stringNumbers.add("e3");
        stringNumbers.add("m4");
        json1.put("stringNumbers", stringNumbers);

        org.json.JSONObject json2 = new org.json.JSONObject();
        json2.put("person", json1);
        json2.put("customer", "yes");
        String[] roles1 = new String[]{"ROLE1", "ROLE2", "ROLE3"};
        String[] roles2 = new String[]{"ROLE1", "ROLE2", "ROLE3"};
        String[] roles3 = new String[]{"ROLE1", "ROLE2", "ROLE3"};
        json2.put("roles1", Arrays.toString(roles1));
        json2.put("roles2", Arrays.toString(roles2));
        json2.put("roles3", "["+Arrays.toString(roles3)+"]");
        json2.put("profileLink", "https://www.profile.com/john-smith?level=test&customer=yes");
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("data1", null);
//        hashMap.put("data2", "My Data 1");
//        hashMap.put("value", 1000);
//        //hashMap.put("object", new Object()+", {\"name\":\"john smith\"}");
//        hashMap.put("object", new Object());
//        json2.put("product", hashMap);
        Object object2 = json2;
        String string2 = json2.toString();

        System.out.println("JSON 2");
        System.out.println(json2);
        System.out.println("OBJECT 2");
        System.out.println(object2);
        System.out.println("STRING 2");
        System.out.println(string2);

        codexsTesterParseOrgJsonObject(string2, true);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        codexsTesterParseOrgJsonObject(object2, true);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");

        codexsTesterJsonRefactor("complex", object2, true);
    }
    
    @Test
    public void readFileTest() {
        String result = codexsHelperReadFile("./src/test/resources/junit4/sample/unitary.tests.properties");
        System.out.println(result);
    }

    @Test
    public void awaitFileContentTest() throws Exception {
        /*NOTE: Edit the file ./src/test/resources/help4devs/file.txt and press [Ctrl+S] button*/
        String code = awaitFileContent(
                "./src/test/resources/junit4/file.txt",
                "[0-9]{6}",
                1500000000);
        codexsTesterAssertRegExp("[0-9]{6}", code, null);
    }

}
