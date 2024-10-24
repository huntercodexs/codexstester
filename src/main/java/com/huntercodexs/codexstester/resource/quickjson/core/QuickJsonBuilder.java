package com.huntercodexs.codexstester.resource.quickjson.core;

import com.huntercodexs.codexstester.resource.quickjson.QuickJson;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.huntercodexs.codexstester.resource.quickjson.core.QuickJsonAbstract.*;

public class QuickJsonBuilder {

    private boolean strictMode;
    private final List<Object> arraySave;
    private final List<Object> jsonSave;

    QuickJson qj;
    QuickJsonExtractor qjExtract;

    public QuickJsonBuilder() {
        this.qj = new QuickJson();
        this.qjExtract = new QuickJsonExtractor();
        this.strictMode = false;
        this.arraySave = new ArrayList<>();
        this.jsonSave = new ArrayList<>();
    }

    private String hashMapArrayExtractor(String json) {

        int arrayCounter = 0;
        List<Object> arrayData = new ArrayList<>();

        while (true) {
            arrayCounter += 1;

            if (!json.matches(".*"+HASHMAP_ARRAY_REGEXP+".*")) {
                break;
            }

            arrayData.add(json.replaceFirst(HASHMAP_ARRAY_REGEXP+".*", "$1"));
            json = json.replaceFirst(HASHMAP_ARRAY_REGEXP, ":@ARRAYOBJECT"+arrayCounter+"@");
        }

        for (Object value : arrayData) {
            this.arraySave.add(value
                    .toString()
                    .replaceFirst(".*"+HASHMAP_ARRAY_REGEXP, "$1")
                    .replaceFirst(":", ""));
        }

        return json;
    }

    private String hashMapJsonExtractor(String json) {

        int jsonCounter = 0;
        List<Object> jsonData = new ArrayList<>();

        while (true) {
            jsonCounter += 1;

            if (!json.matches(".*"+HASHMAP_JSON_REGEXP+".*")) {
                break;
            }

            jsonData.add(json.replaceFirst(HASHMAP_JSON_REGEXP+".*", "$1"));
            json = json.replaceFirst(HASHMAP_JSON_REGEXP, ":@JSONOBJECT"+jsonCounter+"@");
        }

        for (Object value : jsonData) {
            this.jsonSave.add(value
                    .toString()
                    .replaceFirst(".*"+HASHMAP_JSON_REGEXP, "$1")
                    .replaceFirst(":", ""));
        }

        return json;
    }

    private HashMap<Object, Object> hashMapExtractor(Object jsonObj) {

        String json = String.valueOf(jsonObj);
        json = this.hashMapArrayExtractor(json);
        json = this.hashMapJsonExtractor(json);
        HashMap<Object, Object> hashMap = new HashMap<>();

        int jsonCounter = 1;
        int arrayCounter = 1;

        String[] jsonFields = json
                .replaceFirst("\\{", "")
                .replaceFirst("}", "")
                .split(",");

		for (String jsonField : jsonFields) {

			String[] keyValue = jsonField.split(":");

			String jf = keyValue[0]
					.trim()
					.replaceFirst("^\"", "")
					.replaceFirst("\"$", "");

			String jv = keyValue[1]
					.trim()
					.replaceFirst("^\"", "")
					.replaceFirst("\"$", "");

			if (jv.contains("@JSONOBJECT" + jsonCounter + "@")) {
				jv = jv.replaceFirst(jv, String.valueOf(jsonSave.get(jsonCounter - 1)));
				jsonCounter += 1;

			} else if (jv.contains("@ARRAYOBJECT" + arrayCounter + "@")) {
				jv = jv.replaceFirst(jv, String.valueOf(arraySave.get(arrayCounter - 1)));
				arrayCounter += 1;
			}

			hashMap.put(jf, jv);
		}

        return hashMap;
    }

    public void setStrictMode(boolean mode) {
        this.strictMode = mode;
    }

    @SuppressWarnings(value = {"unchecked"})
    public <T> T build(Object jsonData, Class<T> classT) {

        Field[] fields = classT.getDeclaredFields();

        try {

            Object instanceClass = Class.forName(classT.getName()).getDeclaredConstructor().newInstance();

            for (Field field : fields) {

                String currentField = field.getName();
                Object fieldValue = this.qjExtract.smartExtraction(jsonData, currentField);

                if (fieldValue == null && this.strictMode) {
                    throw new RuntimeException("Invalid data to mapper, field not found: " + currentField);
                }

                Field field1 = classT.getField(currentField);
                String typeF = field1.getType().toString();

                field1.setAccessible(true);

                switch (typeF) {
                    case "class java.lang.Object":
                        field1.set(instanceClass, fieldValue);
                        break;

                    case "class java.lang.Integer":
                        field1.set(instanceClass, Integer.parseInt(String.valueOf(fieldValue)));
                        break;

                    case "class java.lang.String":
                        field1.set(instanceClass, String.valueOf(fieldValue));
                        break;

                    case "interface java.util.List":

                        if (fieldValue != null) {

                            String[] arr = fieldValue.toString()
                                    .replaceFirst("^\\[", "")
                                    .replaceFirst("]$", "")
                                    .split(",");

                            field1.set(instanceClass, Arrays.asList(arr));

                        } else {
                            field1.set(instanceClass, null);
                        }

                        break;

                    case "class java.util.HashMap":

                        if (fieldValue != null) {
                            field1.set(instanceClass, hashMapExtractor(fieldValue));
                        } else {
                            field1.set(instanceClass, null);
                        }
                        break;

                }
            }

            return (T) instanceClass;

        } catch (
                IllegalAccessException |
                NoSuchFieldException |
                ClassNotFoundException |
                InstantiationException |
                InvocationTargetException |
                NoSuchMethodException e
        ) {
            throw new RuntimeException(e);
        }

	}

    public Object build(Object object) {

        Class<?> classT = object.getClass();
        Field[] fields = classT.getDeclaredFields();

        String[] packageSplit = classT.getName().split("\\.");
        String className = packageSplit[packageSplit.length-1];

        String dataJson = object.toString()
                .replaceFirst(className+"\\(", "{")
                .replaceFirst("\\)$", "}")
                .replaceAll("("+FIELD+")=", "\"$1\":")
                .replaceAll("(\""+FIELD+"\"):("+STRINGED+")", "$1:\"$2\"");

		try {

		    for (Field field : fields) {

                Field field1 = classT.getField(field.getName());
                String fieldName = field1.getName();

                Object content = this.qjExtract.smartExtraction(dataJson, fieldName);

                this.qj.add(fieldName, content);

            }

            return this.qj.json();

		} catch (NoSuchFieldException e) {
			throw new RuntimeException("Critical Error: " + e.getMessage());
		}
    }

}
