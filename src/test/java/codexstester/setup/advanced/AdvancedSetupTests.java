package codexstester.setup.advanced;

import codexstester.abstractor.dto.HeadersDto;
import net.minidev.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AdvancedSetupTests {

    public static String[] expectedJsonKeys() {
        return new String[]{"field1", "field2", "field3", "field4", "field5", "field6", "field7"};
    }

    public static Object[] expectedJsonValues() {
        String string = "value1";
        JSONObject jsonObject = new JSONObject();
        HeadersDto headersDto = new HeadersDto();
        HashMap<Object, Object> hashMap = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> linkedList = new LinkedList<>();
        List<String> list = new ArrayList<>();
        return new Object[]{string, jsonObject, headersDto, hashMap, arrayList, linkedList, list};
    }

    public static Object[] expectedJsonTyped() {
        return new Object[]{
                String.class,
                JSONObject.class,
                HeadersDto.class,
                HashMap.class,
                ArrayList.class,
                LinkedList.class,
                List.class
        };
    }
}
