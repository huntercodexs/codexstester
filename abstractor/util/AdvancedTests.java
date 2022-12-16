package codexstester.abstractor.util;

import codexstester.setup.properties.FilePropertiesSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.*;

import static codexstester.abstractor.util.AssertionTests.resulted;
import static codexstester.abstractor.util.CodexsHelperTests.codexsHelperLogTermTests;

public abstract class AdvancedTests extends FilePropertiesSourceTests {

    private void strictMessage(boolean flag) {
        if (flag) {
            codexsHelperLogTermTests(" Use strictMode = true if you don't want to ignore it", "", false);
        } else {
            codexsHelperLogTermTests(" Use strictMode = false if you want to ignore it", "", false);
        }
    }

    private void defaultMessage(
            Object expectedValue,
            Object foundValue,
            Object expectedTyped,
            Object foundTyped,
            Object expectedName,
            Object foundName
    ) {
        codexsHelperLogTermTests("  ==> EXPECTED NAME....", expectedName, false);
        codexsHelperLogTermTests("  ==> FOUND NAME.......", foundName, false);
        codexsHelperLogTermTests("  ==> EXPECTED TYPED...", expectedTyped, false);
        codexsHelperLogTermTests("  ==> FOUND TYPED......", foundTyped, false);
        codexsHelperLogTermTests("  ==> EXPECTED VALUE...", expectedValue, false);
        codexsHelperLogTermTests("  ==> FOUND VALUE......", foundValue, false);
    }

    private String extractFields(Object[][] expectedDataTree, int index) {
        StringBuilder fields = new StringBuilder();
        for (Object[] objects : expectedDataTree) {
            fields.append(objects[index]);
            fields.append(", ");
        }
        return "["+fields.toString().trim().replaceFirst(",$", "")+"]";
    }

    public void codexsTesterCompareJsonFormat(
            Object[][] expectedJsonDataTree,
            JSONObject jsonCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("JSON-KEYS...............", extractFields(expectedJsonDataTree, 0), false);
        codexsHelperLogTermTests("JSON-VALUES.............", extractFields(expectedJsonDataTree, 1), false);
        codexsHelperLogTermTests("JSON-TYPED..............", extractFields(expectedJsonDataTree, 2), false);
        codexsHelperLogTermTests("JSON-COMPARE............", jsonCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", jsonCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!jsonCompare.getClass().toString().contains("JSONObject")) {
            codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-JSON-TYPED)", "", true);
            resulted(false);
            Assert.fail();
        }

        int arraySize = 0;

        for (int i = 0; i < expectedJsonDataTree.length; i++) {

            if (i == 0) {
                arraySize = expectedJsonDataTree[i].length;
            }

            if (i > 0) {
                if (arraySize != expectedJsonDataTree[i].length) {
                    codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-LENGTH)", expectedJsonDataTree[i], true);
                    resulted(false);
                    Assert.fail();
                }
            }

            if (jsonCompare.get(expectedJsonDataTree[i][0]) == null && expectedJsonDataTree[i][2] == null) {
                codexsHelperLogTermTests("OK -> CONTINUE", null, false);
                codexsHelperLogTermTests("TYPED....", expectedJsonDataTree[i][2], false);
                codexsHelperLogTermTests("COMPARE..", jsonCompare.get(expectedJsonDataTree[i][0]), false);
                continue;
            }

            if (jsonCompare.get(expectedJsonDataTree[i][0]) == null && expectedJsonDataTree[i][2] != null) {
                codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", expectedJsonDataTree[i][0], false);
                codexsHelperLogTermTests("EXPECTED..", expectedJsonDataTree[i][2], false);
                codexsHelperLogTermTests("RECEIVED..", null, false);
                resulted(false);
                Assert.fail();
            }

            String expKey = expectedJsonDataTree[i][0].toString();
            Object fndKey = null;
            String expVal = expectedJsonDataTree[i][1].toString();
            String fndVal = jsonCompare.getAsString(expKey);
            Object expType = expectedJsonDataTree[i][2];
            Class<?> fndType = jsonCompare.get(expKey).getClass();

            codexsHelperLogTermTests("=> KEY <=", expKey, true);
            codexsHelperLogTermTests("TYPED....", expType, false);
            codexsHelperLogTermTests("EXPECTED.", expVal, false);
            codexsHelperLogTermTests("COMPARE..", fndVal, false);

            if (fndVal.equals(expVal) && fndType != expType && expType.toString().contains("interface") && strictMode) {

                codexsHelperLogTermTests("> RESULT IS [WARNING] [WRONG-TYPED]", expKey, true);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");

            } else if (fndVal.equals(expVal) && fndType != expType && strictMode) {

                codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED]", expKey, true);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
                resulted(false);
                Assert.fail();

            } else if (fndVal.equals(expVal) && fndType == expType) {

                codexsHelperLogTermTests("> RESULT IS [OK]", expKey, false);

            } else if (!expVal.equals(fndVal) && fndType == expType && !strictMode) {

                codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [DIFF-VALUES]", expKey, true);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
                strictMessage(true);

            } else if (!expVal.equals(fndVal) && fndType == expType) {

                codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-VALUES]", expKey, true);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
                strictMessage(false);
                resulted(false);
                Assert.fail();

            } else {

                if (strictMode) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED]", expKey, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
                    resulted(false);
                    Assert.fail();
                } else {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [WRONG-TYPED]", expKey, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
                }
            }
        }
        resulted(true);
        Assert.assertTrue(true);

    }

    public void codexsTesterCompareJsonFormat(
            String[] jsonKeys,
            Object[] jsonValues,
            Object[] jsonTyped,
            JSONObject jsonCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("JSON-KEYS...............", Arrays.toString(jsonKeys), false);
        codexsHelperLogTermTests("JSON-VALUES.............", Arrays.toString(jsonValues), false);
        codexsHelperLogTermTests("JSON-TYPED..............", Arrays.toString(jsonTyped), false);
        codexsHelperLogTermTests("JSON-COMPARE............", jsonCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", jsonCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!jsonCompare.getClass().toString().contains("JSONObject")) {
            codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-JSON-TYPED)", "", true);
            resulted(false);
            Assert.fail();
        }

        if (jsonKeys.length != jsonValues.length || jsonKeys.length != jsonTyped.length || jsonKeys.length != jsonCompare.size()) {
            codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-LENGTH)", "", true);
            resulted(false);
            Assert.fail();
        }

        for (int i = 0; i < jsonKeys.length; i++) {

            if (jsonCompare.get(jsonKeys[i]) == null && jsonTyped[i] == null) {
                codexsHelperLogTermTests("OK -> CONTINUE", null, false);
                codexsHelperLogTermTests("TYPED....", jsonTyped[i], false);
                codexsHelperLogTermTests("COMPARE..", jsonCompare.get(jsonKeys[i]), false);
                continue;
            }

            if (jsonCompare.get(jsonKeys[i]) == null && jsonTyped[i] != null) {
                codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", jsonKeys[i], false);
                codexsHelperLogTermTests("EXPECTED..", jsonTyped[i], false);
                codexsHelperLogTermTests("RECEIVED..", null, false);
                resulted(false);
                Assert.fail();
            }

            String expKey = jsonKeys[i];
            Object fndKey = null;
            String expVal = jsonValues[i].toString();
            String fndVal = jsonCompare.getAsString(expKey);
            Object expType = jsonTyped[i];
            Class<?> fndType = jsonCompare.get(expKey).getClass();

            codexsHelperLogTermTests("=> KEY <=", expKey, true);
            codexsHelperLogTermTests("TYPED....", expType, false);
            codexsHelperLogTermTests("EXPECTED.", expVal, false);
            codexsHelperLogTermTests("COMPARE..", fndVal, false);

            if (fndVal.equals(expVal) && fndType != expType && expType.toString().contains("interface") && strictMode) {

                codexsHelperLogTermTests("> RESULT IS [WARNING] [WRONG-TYPED]", expKey, true);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");

            } else if (fndVal.equals(expVal) && fndType != expType && strictMode) {

                codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED]", expKey, true);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
                resulted(false);
                Assert.fail();

            } else if (fndVal.equals(expVal) && fndType == expType) {

                codexsHelperLogTermTests("> RESULT IS [OK]", expKey, false);

            } else if (!expVal.equals(fndVal) && fndType == expType && !strictMode) {

                codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [DIFF-VALUES]", expKey, true);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
                strictMessage(true);

            } else if (!expVal.equals(fndVal) && fndType == expType) {

                codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-VALUES]", expKey, true);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
                strictMessage(false);
                resulted(false);
                Assert.fail();

            } else {

                 if (strictMode) {
                     codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED]", expKey, true);
                     defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
                     resulted(false);
                     Assert.fail();
                 } else {
                     codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [WRONG-TYPED]", expKey, true);
                     defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
                 }
             }
        }
        resulted(true);
        Assert.assertTrue(true);
    }

    public void codexsTesterCompareDtoFormat(
            Object[][] expectedDtoDataTree,
            Object dtoCompare,
            Class<?> dtoClass,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("=========== SUMMARY =========", "", true);
        codexsHelperLogTermTests("FIELDS.......................", extractFields(expectedDtoDataTree, 0), false);
        codexsHelperLogTermTests("VALUES.......................", extractFields(expectedDtoDataTree, 1), false);
        codexsHelperLogTermTests("TYPES........................", extractFields(expectedDtoDataTree, 2), false);
        codexsHelperLogTermTests("CLASS........................", dtoClass, false);
        codexsHelperLogTermTests("DTO-CLASS-FIELDS.............", Arrays.toString(dtoClass.getDeclaredFields()), false);
        codexsHelperLogTermTests("DTO-CLASS-FIELDS-LENGTH......", dtoClass.getDeclaredFields().length, false);
        codexsHelperLogTermTests("DTO-COMPARE..................", dtoCompare, false);
        codexsHelperLogTermTests("DTO-COMPARE-GET-CLASS........", dtoCompare.getClass(), false);
        codexsHelperLogTermTests("DTO-COMPARE-TO-STRING........", dtoCompare.toString(), false);
        codexsHelperLogTermTests("DTO-COMPARE-FIELDS...........", Arrays.toString(dtoCompare.getClass().getFields()), false);
        codexsHelperLogTermTests("DTO-COMPARE-DECLARED-FIELDS..", Arrays.toString(dtoCompare.getClass().getDeclaredFields()), false);
        codexsHelperLogTermTests("DTO-COMPARE-LENGTH...........", dtoCompare.getClass().getDeclaredFields().length, false);
        codexsHelperLogTermTests("STRICT-MODE..................", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME..............", dtoCompare.getClass().toString(), false);
        codexsHelperLogTermTests("=========== COMPARE =========", "", true);

        if (dtoClass != dtoCompare.getClass() && strictMode) {
            codexsHelperLogTermTests("**** ERROR ****", "", true);
            codexsHelperLogTermTests("ERROR ON DTO DATA COMPARE (WRONG-DTO-CLASS)", "", false);
            codexsHelperLogTermTests("  ==> EXPECTED...: ", dtoClass, false);
            codexsHelperLogTermTests("  ==> RECEIVED...: ", dtoCompare.getClass(), false);
            resulted(false);
            Assert.fail();
        }

        int sizeClass = dtoClass.getDeclaredFields().length;
        int sizeCompare = dtoCompare.getClass().getDeclaredFields().length;
        int sizeDataTree = expectedDtoDataTree.length;

        if (sizeClass != sizeCompare || sizeClass != sizeDataTree) {
            codexsHelperLogTermTests("**** ERROR ****", "", true);
            codexsHelperLogTermTests("ERROR ON DTO DATA COMPARE (WRONG-DTO-CLASS-LENGTH)", "", false);
            codexsHelperLogTermTests("  ==> CLASS..................", dtoClass, false);
            codexsHelperLogTermTests("  ==> COMPARE................", dtoCompare.getClass(), false);
            codexsHelperLogTermTests("  ==> EXPECTED-SIZE-CLASS....", sizeClass, false);
            codexsHelperLogTermTests("  ==> RECEIVED-DTO-COMPARE...", sizeCompare, false);
            codexsHelperLogTermTests("  ==> RECEIVED-DATAGRID......", sizeDataTree, false);
            resulted(false);
            Assert.fail();
        }

        Field[] fieldsCompare = dtoCompare.getClass().getDeclaredFields();
        String[] arrayCompare = dtoCompare.toString()
                .replaceFirst("[0-9a-zA-Z]+\\(", "")
                .replaceFirst("\\)$", "")
                .replaceAll(" ", "").split(",");

        for (int i = 0; i < expectedDtoDataTree.length; i++) {

            String expName = expectedDtoDataTree[i][0].toString();
            String fndName = fieldsCompare[i].getName();
            String expVal = expectedDtoDataTree[i][1].toString();
            String fndVal = arrayCompare[i];
            Class<?> expType = (Class<?>) expectedDtoDataTree[i][2];
            Class<?> fndType = fieldsCompare[i].getType();

            codexsHelperLogTermTests("=> NAME <=", expName, true);
            codexsHelperLogTermTests("TYPED.....", expType, false);
            codexsHelperLogTermTests("EXPECTED..", expVal, false);
            codexsHelperLogTermTests("COMPARE...", fndVal, false);

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    resulted(false);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    resulted(false);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    continue;
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    continue;
                }

            }

            codexsHelperLogTermTests("> RESULT IS [OK]", expName, false);

        }
        resulted(true);
        Assert.assertTrue(true);

    }

    public void codexsTesterCompareDtoFormat(
            String[] dtoValues,
            Object dtoCompare,
            Class<?> dtoClass,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("VALUES..................", Arrays.toString(dtoValues), false);
        codexsHelperLogTermTests("CLASS...................", dtoClass, false);
        codexsHelperLogTermTests("CLASS-FIELDS............", Arrays.toString(dtoClass.getDeclaredFields()), false);
        codexsHelperLogTermTests("CLASS-FIELDS-LENGTH.....", dtoClass.getDeclaredFields().length, false);
        codexsHelperLogTermTests("COMPARE.................", dtoCompare, false);
        codexsHelperLogTermTests("COMPARE-GET-CLASS.......", dtoCompare.getClass(), false);
        codexsHelperLogTermTests("COMPARE-TO-STRING.......", dtoCompare.toString(), false);
        codexsHelperLogTermTests("COMPARE-FIELDS..........", Arrays.toString(dtoCompare.getClass().getFields()), false);
        codexsHelperLogTermTests("COMPARE-DECLARED-FIELDS.", Arrays.toString(dtoCompare.getClass().getDeclaredFields()), false);
        codexsHelperLogTermTests("COMPARE-LENGTH..........", dtoCompare.getClass().getDeclaredFields().length, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", dtoCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (dtoClass != dtoCompare.getClass() && strictMode) {
            codexsHelperLogTermTests("**** ERROR ****", "", true);
            codexsHelperLogTermTests("ERROR ON DTO DATA COMPARE (WRONG-DTO-CLASS)", "", false);
            codexsHelperLogTermTests("  ==> EXPECTED...: ", dtoClass, false);
            codexsHelperLogTermTests("  ==> RECEIVED...: ", dtoCompare.getClass(), false);
            resulted(false);
            Assert.fail();
        }

        int sizeClass = dtoClass.getDeclaredFields().length;
        int sizeCompare = dtoCompare.getClass().getDeclaredFields().length;

        if (sizeClass != sizeCompare) {
            codexsHelperLogTermTests("**** ERROR ****", "", true);
            codexsHelperLogTermTests("ERROR ON DTO DATA COMPARE (WRONG-DTO-CLASS-LENGTH)", "", false);
            codexsHelperLogTermTests("  ==> CLASS......", dtoClass, false);
            codexsHelperLogTermTests("  ==> COMPARE....", dtoCompare.getClass(), false);
            codexsHelperLogTermTests("  ==> EXPECTED...", sizeClass, false);
            codexsHelperLogTermTests("  ==> RECEIVED...", sizeCompare, false);
            resulted(false);
            Assert.fail();
        }

        Field[] fieldsClass = dtoClass.getDeclaredFields();
        Field[] fieldsCompare = dtoCompare.getClass().getDeclaredFields();

        String[] arrayCompare = dtoCompare.toString()
                .replaceFirst("[0-9a-zA-Z]+\\(", "")
                .replaceFirst("\\)$", "")
                .replaceAll(" ", "").split(",");

        for (int i = 0; i < fieldsClass.length; i++) {

            String expVal = dtoValues[i];
            String fndVal = arrayCompare[i];
            Class<?> expType = fieldsClass[i].getType();
            Class<?> fndType = fieldsCompare[i].getType();
            String expName = fieldsClass[i].getName();
            String fndName = fieldsCompare[i].getName();

            codexsHelperLogTermTests("=> NAME <=", expName, true);
            codexsHelperLogTermTests("TYPED.....", expType, false);
            codexsHelperLogTermTests("EXPECTED..", expVal, false);
            codexsHelperLogTermTests("COMPARE...", fndVal, false);

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    resulted(false);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    resulted(false);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    continue;
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    continue;
                }

            }

            codexsHelperLogTermTests("> RESULT IS [OK]", expName, false);

        }
        resulted(true);
        Assert.assertTrue(true);
    }

    public void codexsTesterCompareHashMapFormat(
            Object[][] expectedHashMapDataTree,
            HashMap<Object, Object> hashMapCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("HASH-MAP-KEYS...........", extractFields(expectedHashMapDataTree, 0), false);
        codexsHelperLogTermTests("HASH-MAP-VALUES.........", extractFields(expectedHashMapDataTree, 1), false);
        codexsHelperLogTermTests("HASH-MAP-TYPED..........", extractFields(expectedHashMapDataTree, 2), false);
        codexsHelperLogTermTests("HASH-MAP-COMPARE........", hashMapCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", hashMapCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!hashMapCompare.getClass().toString().contains("HashMap")) {
            codexsHelperLogTermTests("ERROR ON HASH-MAP DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
            resulted(false);
            Assert.fail();
        }

        int arraySize = 0;

        for (int i = 0; i < expectedHashMapDataTree.length; i++) {

            if (i == 0) {
                arraySize = expectedHashMapDataTree[i].length;
            }

            if (i > 0) {
                if (arraySize != expectedHashMapDataTree[i].length) {
                    codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-LENGTH)", expectedHashMapDataTree[i], true);
                    resulted(false);
                    Assert.fail();
                }
            }

            if (hashMapCompare.get(expectedHashMapDataTree[i][0]) == null && expectedHashMapDataTree[i][2] == null) {
                codexsHelperLogTermTests("OK -> CONTINUE", null, false);
                codexsHelperLogTermTests("TYPED....", expectedHashMapDataTree[i][2], false);
                codexsHelperLogTermTests("COMPARE..", hashMapCompare.get(expectedHashMapDataTree[i][0]), false);
                continue;
            }

            if (!hashMapCompare.containsKey(expectedHashMapDataTree[i][0]) || hashMapCompare.get(expectedHashMapDataTree[i][0]) == null && expectedHashMapDataTree[i][2] != null) {
                codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [MISSING-KEY]", expectedHashMapDataTree[i][0], true);
                codexsHelperLogTermTests("EXPECTED....", expectedHashMapDataTree[i][0], false);
                codexsHelperLogTermTests("RECEIVED....", null, false);
                resulted(false);
                Assert.fail();
            }

            String expName = expectedHashMapDataTree[i][0].toString();
            String fndName = expectedHashMapDataTree[i][0].toString();
            String expVal = expectedHashMapDataTree[i][1].toString();
            String fndVal = hashMapCompare.get(expectedHashMapDataTree[i][0]).toString();
            Object expType = expectedHashMapDataTree[i][2];
            Class<?> fndType = hashMapCompare.get(expectedHashMapDataTree[i][0]).getClass();

            codexsHelperLogTermTests("=> NAME <=", expName, true);
            codexsHelperLogTermTests("TYPED....", expType, false);
            codexsHelperLogTermTests("EXPECTED.", expVal, false);
            codexsHelperLogTermTests("COMPARE..", fndVal, false);

            if (expVal.equals(fndVal) && expType != fndType && expType.toString().contains("interface")) {
                codexsHelperLogTermTests("> RESULT IS [WARNING] [WRONG-TYPED]", expName, true);
                defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    resulted(false);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    resulted(false);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    continue;
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    continue;
                }

            }
            codexsHelperLogTermTests("> RESULT IS [OK]", expName, false);
        }
        resulted(true);
        Assert.assertTrue(true);
    }

    public void codexsTesterCompareHashMapFormat(
            String[] hashMapKeys,
            Object[] hashMapValues,
            Object[] hashMapTyped,
            HashMap<Object, Object> hashMapCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("HASH-MAP-KEYS...........", Arrays.toString(hashMapKeys), false);
        codexsHelperLogTermTests("HASH-MAP-VALUES.........", Arrays.toString(hashMapValues), false);
        codexsHelperLogTermTests("HASH-MAP-TYPED..........", Arrays.toString(hashMapTyped), false);
        codexsHelperLogTermTests("HASH-MAP-COMPARE........", hashMapCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", hashMapCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!hashMapCompare.getClass().toString().contains("HashMap")) {
            codexsHelperLogTermTests("ERROR ON HASH-MAP DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
            resulted(false);
            Assert.fail();
        }

        if (hashMapKeys.length != hashMapValues.length || hashMapKeys.length != hashMapTyped.length || hashMapKeys.length != hashMapCompare.size()) {
            codexsHelperLogTermTests("ERROR ON HASH-MAP DATA COMPARE (WRONG-LENGTH)", "", true);
            resulted(false);
            Assert.fail();
        }

        for (int i = 0; i < hashMapKeys.length; i++) {

            if (hashMapCompare.get(hashMapKeys[i]) == null && hashMapTyped[i] == null) {
                codexsHelperLogTermTests("OK -> CONTINUE", null, false);
                codexsHelperLogTermTests("TYPED....", hashMapTyped[i], false);
                codexsHelperLogTermTests("COMPARE..", hashMapCompare.get(hashMapKeys[i]), false);
                continue;
            }

            if (!hashMapCompare.containsKey(hashMapKeys[i]) || hashMapCompare.get(hashMapKeys[i]) == null && hashMapTyped[i] != null) {
                codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [MISSING-KEY]", hashMapKeys[i], true);
                codexsHelperLogTermTests("EXPECTED....", hashMapKeys[i], false);
                codexsHelperLogTermTests("RECEIVED....", null, false);
                resulted(false);
                Assert.fail();
            }

            String expName = hashMapKeys[i];
            String fndName = hashMapKeys[i];
            String expVal = hashMapValues[i].toString();
            String fndVal = hashMapCompare.get(hashMapKeys[i]).toString();
            Object expType = hashMapTyped[i];
            Class<?> fndType = hashMapCompare.get(hashMapKeys[i]).getClass();

            codexsHelperLogTermTests("=> NAME <=", expName, true);
            codexsHelperLogTermTests("TYPED....", expType, false);
            codexsHelperLogTermTests("EXPECTED.", expVal, false);
            codexsHelperLogTermTests("COMPARE..", fndVal, false);

            if (expVal.equals(fndVal) && expType != fndType && expType.toString().contains("interface")) {
                codexsHelperLogTermTests("> RESULT IS [WARNING] [WRONG-TYPED]", expName, true);
                defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    resulted(false);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    resulted(false);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    continue;
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    continue;
                }

            }
            codexsHelperLogTermTests("> RESULT IS [OK]", expName, false);
        }
        resulted(true);
        Assert.assertTrue(true);
    }

    public void codexsTesterCompareArrayListFormat(
            Object[][] expectedArrayListDataTree,
            ArrayList<Object> arrayListCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("ARRAY-LIST-KEYS.........", extractFields(expectedArrayListDataTree, 0), false);
        codexsHelperLogTermTests("ARRAY-LIST-VALUES.......", extractFields(expectedArrayListDataTree, 1), false);
        codexsHelperLogTermTests("ARRAY-LIST-TYPED........", extractFields(expectedArrayListDataTree, 2), false);
        codexsHelperLogTermTests("ARRAY-LIST-COMPARE......", arrayListCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", arrayListCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!arrayListCompare.getClass().toString().contains("ArrayList")) {
            codexsHelperLogTermTests("ERROR ON ARRAY-LIST DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
            resulted(false);
            Assert.fail();
        }

        int arraySize = 0;

        for (int i = 0; i < expectedArrayListDataTree.length; i++) {

            if (i == 0) {
                arraySize = expectedArrayListDataTree[i].length;
            }

            if (i > 0) {
                if (arraySize != expectedArrayListDataTree[i].length) {
                    codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-LENGTH)", expectedArrayListDataTree[i], true);
                    resulted(false);
                    Assert.fail();
                }
            }

            if (arrayListCompare.get((Integer) expectedArrayListDataTree[i][0]) == null && expectedArrayListDataTree[i][2] == null) {
                codexsHelperLogTermTests("OK -> CONTINUE", null, false);
                codexsHelperLogTermTests("TYPED....", null, false);
                codexsHelperLogTermTests("COMPARE..", null, false);
                continue;
            }

            if (arrayListCompare.get((Integer) expectedArrayListDataTree[i][0]) == null && expectedArrayListDataTree[i][2] != null) {
                codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", "INDEX-"+i, true);
                codexsHelperLogTermTests("EXPECTED....", expectedArrayListDataTree[i][2], false);
                codexsHelperLogTermTests("RECEIVED....", null, false);
                resulted(false);
                Assert.fail();
            }

            String expVal = expectedArrayListDataTree[i][1].toString();
            String fndVal = arrayListCompare.get((Integer) expectedArrayListDataTree[i][0]).toString();
            Object expType = expectedArrayListDataTree[i][2];
            Class<?> fndType = arrayListCompare.get(i).getClass();

            codexsHelperLogTermTests("=> INDEX <=", i, true);
            codexsHelperLogTermTests("TYPED....", expType, false);
            codexsHelperLogTermTests("EXPECTED.", expVal, false);
            codexsHelperLogTermTests("COMPARE..", fndVal, false);

            if (expVal.equals(fndVal) && expType != fndType && expType.toString().contains("interface")) {
                codexsHelperLogTermTests("> RESULT IS [WARNING] [WRONG-TYPED]", expVal, true);
                defaultMessage(expVal, fndVal, expType, fndType, null, null);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(true);
                    continue;
                }

            }
            codexsHelperLogTermTests("> RESULT IS [OK]", "INDEX-"+i, false);
        }
        resulted(true);
        Assert.assertTrue(true);

    }

    public void codexsTesterCompareArrayListFormat(
            Object[] arrayListValues,
            Object[] arrayListTyped,
            ArrayList<Object> arrayListCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("ARRAY-LIST-VALUES.......", Arrays.toString(arrayListValues), false);
        codexsHelperLogTermTests("ARRAY-LIST-TYPED........", Arrays.toString(arrayListTyped), false);
        codexsHelperLogTermTests("ARRAY-LIST-COMPARE......", arrayListCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", arrayListCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!arrayListCompare.getClass().toString().contains("ArrayList")) {
            codexsHelperLogTermTests("ERROR ON ARRAY-LIST DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
            resulted(false);
            Assert.fail();
        }

        if (arrayListValues.length != arrayListTyped.length || arrayListValues.length != arrayListCompare.size()) {
            codexsHelperLogTermTests("ERROR ON ARRAY-LIST DATA COMPARE (WRONG-LENGTH)", "", true);
            resulted(false);
            Assert.fail();
        }

        for (int i = 0; i < arrayListValues.length; i++) {

            if (arrayListCompare.get(i) == null && arrayListTyped[i] == null) {
                codexsHelperLogTermTests("OK -> CONTINUE", null, false);
                codexsHelperLogTermTests("TYPED....", arrayListTyped[i], false);
                codexsHelperLogTermTests("COMPARE..", arrayListCompare.get(i), false);
                continue;
            }

            if (arrayListCompare.get(i) == null && arrayListTyped[i] != null) {
                codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", "INDEX-"+i, true);
                codexsHelperLogTermTests("EXPECTED....", arrayListTyped[i], false);
                codexsHelperLogTermTests("RECEIVED....", null, false);
                resulted(false);
                Assert.fail();
            }

            String expVal = arrayListValues[i].toString();
            String fndVal = arrayListCompare.get(i).toString();
            Object expType = arrayListTyped[i];
            Class<?> fndType = arrayListCompare.get(i).getClass();

            codexsHelperLogTermTests("=> INDEX <=", i, true);
            codexsHelperLogTermTests("TYPED....", expType, false);
            codexsHelperLogTermTests("EXPECTED.", expVal, false);
            codexsHelperLogTermTests("COMPARE..", fndVal, false);

            if (expVal.equals(fndVal) && expType != fndType && expType.toString().contains("interface")) {
                codexsHelperLogTermTests("> RESULT IS [WARNING] [WRONG-TYPED]", expVal, true);
                defaultMessage(expVal, fndVal, expType, fndType, null, null);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(true);
                    continue;
                }

            }
            codexsHelperLogTermTests("> RESULT IS [OK]", "INDEX-"+i, false);
        }
        resulted(true);
        Assert.assertTrue(true);

    }

    public void codexsTesterCompareLinkedListFormat(
            Object[][] expectedLinkedListDataTree,
            LinkedList<Object> linkedListCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("LINKED-LIST-KEYS........", extractFields(expectedLinkedListDataTree, 0), false);
        codexsHelperLogTermTests("LINKED-LIST-VALUES......", extractFields(expectedLinkedListDataTree, 1), false);
        codexsHelperLogTermTests("LINKED-LIST-TYPED.......", extractFields(expectedLinkedListDataTree, 2), false);
        codexsHelperLogTermTests("LINKED-LIST-COMPARE.....", linkedListCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", linkedListCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!linkedListCompare.getClass().toString().contains("LinkedList")) {
            codexsHelperLogTermTests("ERROR ON LINKED-LIST DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
            resulted(false);
            Assert.fail();
        }

        int arraySize = 0;

        for (int i = 0; i < expectedLinkedListDataTree.length; i++) {

            if (i == 0) {
                arraySize = expectedLinkedListDataTree[i].length;
            }

            if (i > 0) {
                if (arraySize != expectedLinkedListDataTree[i].length) {
                    codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-LENGTH)", expectedLinkedListDataTree[i], true);
                    resulted(false);
                    Assert.fail();
                }
            }

            if (linkedListCompare.get((Integer) expectedLinkedListDataTree[i][0]) == null && expectedLinkedListDataTree[i][2] == null) {
                codexsHelperLogTermTests("OK -> CONTINUE", null, false);
                codexsHelperLogTermTests("TYPED....", null, false);
                codexsHelperLogTermTests("COMPARE..", null, false);
                continue;
            }

            if (linkedListCompare.get((Integer) expectedLinkedListDataTree[i][0]) == null && expectedLinkedListDataTree[i][2] != null) {
                codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", "INDEX-"+i, true);
                codexsHelperLogTermTests("EXPECTED....", expectedLinkedListDataTree[i][2], false);
                codexsHelperLogTermTests("RECEIVED....", null, false);
                resulted(false);
                Assert.fail();
            }

            String expVal = expectedLinkedListDataTree[i][1].toString();
            String fndVal = linkedListCompare.get((Integer) expectedLinkedListDataTree[i][0]).toString();
            Object expType = expectedLinkedListDataTree[i][2];
            Class<?> fndType = linkedListCompare.get(i).getClass();

            codexsHelperLogTermTests("=> INDEX <=", i, true);
            codexsHelperLogTermTests("TYPED....", expType, false);
            codexsHelperLogTermTests("EXPECTED.", expVal, false);
            codexsHelperLogTermTests("COMPARE..", fndVal, false);

            if (expVal.equals(fndVal) && expType != fndType && expType.toString().contains("interface")) {
                codexsHelperLogTermTests("> RESULT IS [WARNING] [WRONG-TYPED]", expVal, true);
                defaultMessage(expVal, fndVal, expType, fndType, null, null);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(true);
                    continue;
                }

            }
            codexsHelperLogTermTests("> RESULT IS [OK]", "INDEX-"+i, false);
        }
        resulted(true);
        Assert.assertTrue(true);

    }

    public void codexsTesterCompareLinkedListFormat(
            Object[] linkedListValues,
            Object[] linkedListTyped,
            LinkedList<Object> linkedListCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("LINKED-LIST-VALUES......", Arrays.toString(linkedListValues), false);
        codexsHelperLogTermTests("LINKED-LIST-TYPED.......", Arrays.toString(linkedListTyped), false);
        codexsHelperLogTermTests("LINKED-LIST-COMPARE.....", linkedListCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", linkedListCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!linkedListCompare.getClass().toString().contains("LinkedList")) {
            codexsHelperLogTermTests("ERROR ON LINKED-LIST DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
            resulted(false);
            Assert.fail();
        }

        if (linkedListValues.length != linkedListTyped.length || linkedListValues.length != linkedListCompare.size()) {
            codexsHelperLogTermTests("ERROR ON LINKED-LIST DATA COMPARE (WRONG-LENGTH)", "", true);
            resulted(false);
            Assert.fail();
        }

        for (int i = 0; i < linkedListValues.length; i++) {

            if (linkedListCompare.get(i) == null && linkedListTyped[i] == null) {
                codexsHelperLogTermTests("OK -> CONTINUE", null, false);
                codexsHelperLogTermTests("TYPED....", linkedListTyped[i], false);
                codexsHelperLogTermTests("COMPARE..", linkedListCompare.get(i), false);
                continue;
            }

            if (linkedListCompare.get(i) == null && linkedListTyped[i] != null) {
                codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", "INDEX-"+i, true);
                codexsHelperLogTermTests("EXPECTED....", linkedListTyped[i], false);
                codexsHelperLogTermTests("RECEIVED....", null, false);
                resulted(false);
                Assert.fail();
            }

            String expVal = linkedListValues[i].toString();
            String fndVal = linkedListCompare.get(i).toString();
            Object expType = linkedListTyped[i];
            Class<?> fndType = linkedListCompare.get(i).getClass();

            codexsHelperLogTermTests("=> INDEX <=", i, true);
            codexsHelperLogTermTests("TYPED....", expType, false);
            codexsHelperLogTermTests("EXPECTED.", expVal, false);
            codexsHelperLogTermTests("COMPARE..", fndVal, false);

            if (expVal.equals(fndVal) && expType != fndType && expType.toString().contains("interface")) {
                codexsHelperLogTermTests("> RESULT IS [WARNING] [WRONG-TYPED]", expVal, true);
                defaultMessage(expVal, fndVal, expType, fndType, null, null);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(true);
                    continue;
                }

            }
            codexsHelperLogTermTests("> RESULT IS [OK]", "INDEX-"+i, false);
        }
        resulted(true);
        Assert.assertTrue(true);

    }

    public void codexsTesterCompareListFormat(
            Object[][] expectedListDataTree,
            List<String> listCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("LIST-KEYS...............", extractFields(expectedListDataTree, 0), false);
        codexsHelperLogTermTests("LIST-VALUES.............", extractFields(expectedListDataTree, 1), false);
        codexsHelperLogTermTests("LIST-TYPED..............", extractFields(expectedListDataTree, 2), false);
        codexsHelperLogTermTests("LIST-COMPARE............", listCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", listCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!listCompare.getClass().toString().contains("ArrayList") && !listCompare.getClass().toString().contains("List")) {
            codexsHelperLogTermTests("ERROR ON LIST<I> DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
            resulted(false);
            Assert.fail();
        }

        int arraySize = 0;

        for (int i = 0; i < expectedListDataTree.length; i++) {

            if (i == 0) {
                arraySize = expectedListDataTree[i].length;
            }

            if (i > 0) {
                if (arraySize != expectedListDataTree[i].length) {
                    codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-LENGTH)", expectedListDataTree[i], true);
                    resulted(false);
                    Assert.fail();
                }
            }

            if (listCompare.get((Integer) expectedListDataTree[i][0]) == null && expectedListDataTree[i][2] == null) {
                codexsHelperLogTermTests("OK -> CONTINUE", null, false);
                codexsHelperLogTermTests("TYPED....", null, false);
                codexsHelperLogTermTests("COMPARE..", null, false);
                continue;
            }

            if (listCompare.get((Integer) expectedListDataTree[i][0]) == null && expectedListDataTree[i][2] != null) {
                codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", "INDEX-"+i, true);
                codexsHelperLogTermTests("EXPECTED....", expectedListDataTree[i][2], false);
                codexsHelperLogTermTests("RECEIVED....", null, false);
                resulted(false);
                Assert.fail();
            }

            String expVal = expectedListDataTree[i][1].toString();
            String fndVal = listCompare.get((Integer) expectedListDataTree[i][0]);
            Object expType = expectedListDataTree[i][2];
            Class<?> fndType = listCompare.get(i).getClass();

            codexsHelperLogTermTests("=> INDEX <=", i, true);
            codexsHelperLogTermTests("TYPED....", expType, false);
            codexsHelperLogTermTests("EXPECTED.", expVal, false);
            codexsHelperLogTermTests("COMPARE..", fndVal, false);

            if (expVal.equals(fndVal) && expType != fndType && expType.toString().contains("interface")) {
                codexsHelperLogTermTests("> RESULT IS [WARNING] [WRONG-TYPED]", expVal, true);
                defaultMessage(expVal, fndVal, expType, fndType, null, null);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(true);
                    continue;
                }

            }
            codexsHelperLogTermTests("> RESULT IS [OK]", "INDEX-"+i, false);
        }
        resulted(true);
        Assert.assertTrue(true);

    }

    public void codexsTesterCompareListFormat(
            Object[] listValues,
            Object[] listTyped,
            List<String> listCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("LIST-VALUES.............", Arrays.toString(listValues), false);
        codexsHelperLogTermTests("LIST-TYPED..............", Arrays.toString(listTyped), false);
        codexsHelperLogTermTests("LIST-COMPARE............", listCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", listCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!listCompare.getClass().toString().contains("ArrayList") && !listCompare.getClass().toString().contains("List")) {
            codexsHelperLogTermTests("ERROR ON LIST<I> DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
            resulted(false);
            Assert.fail();
        }

        if (listValues.length != listTyped.length || listValues.length != listCompare.size()) {
            codexsHelperLogTermTests("ERROR ON LIST<I> DATA COMPARE (WRONG-LENGTH)", "", true);
            resulted(false);
            Assert.fail();
        }

        for (int i = 0; i < listValues.length; i++) {

            if (listCompare.get(i) == null && listTyped[i] == null) {
                codexsHelperLogTermTests("OK -> CONTINUE", null, false);
                codexsHelperLogTermTests("TYPED....", listTyped[i], false);
                codexsHelperLogTermTests("COMPARE..", listCompare.get(i), false);
                continue;
            }

            if (listCompare.get(i) == null && listTyped[i] != null) {
                codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [WRONG-TYPED]", "INDEX-"+i, true);
                codexsHelperLogTermTests("EXPECTED....", listTyped[i], false);
                codexsHelperLogTermTests("RECEIVED....", null, false);
                resulted(false);
                Assert.fail();
            }

            String expVal = listValues[i].toString();
            String fndVal = listCompare.get(i);
            Object expType = listTyped[i];
            Class<?> fndType = listCompare.get(i).getClass();

            codexsHelperLogTermTests("=> INDEX <=", i, true);
            codexsHelperLogTermTests("TYPED....", expType, false);
            codexsHelperLogTermTests("EXPECTED.", expVal, false);
            codexsHelperLogTermTests("COMPARE..", fndVal, false);

            if (expVal.equals(fndVal) && expType != fndType && expType.toString().contains("interface")) {
                codexsHelperLogTermTests("> RESULT IS [WARNING] [WRONG-TYPED]", expVal, true);
                defaultMessage(expVal, fndVal, expType, fndType, null, null);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(true);
                    continue;
                }

            }
            codexsHelperLogTermTests("> RESULT IS [OK]", "INDEX-"+i, false);
        }
        resulted(true);
        Assert.assertTrue(true);

    }

    public void codexsTesterCompareLinkedHashMapFormat(
            Object[][] expectedLinkedHashMapDataTree,
            LinkedHashMap<Object, Object> linkedHashMapCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("LINKED-HASH-MAP-KEYS....", extractFields(expectedLinkedHashMapDataTree, 0), false);
        codexsHelperLogTermTests("LINKED-HASH-MAP-VALUES..", extractFields(expectedLinkedHashMapDataTree, 1), false);
        codexsHelperLogTermTests("LINKED-HASH-MAP-TYPED...", extractFields(expectedLinkedHashMapDataTree, 2), false);
        codexsHelperLogTermTests("LINKED-HASH-MAP-COMPARE.", linkedHashMapCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", linkedHashMapCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!linkedHashMapCompare.getClass().toString().contains("LinkedHashMap")) {
            codexsHelperLogTermTests("ERROR ON LINKED-HASH-MAP DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
            resulted(false);
            Assert.fail();
        }

        int arraySize = 0;

        for (int i = 0; i < expectedLinkedHashMapDataTree.length; i++) {

            if (i == 0) {
                arraySize = expectedLinkedHashMapDataTree[i].length;
            }

            if (i > 0) {
                if (arraySize != expectedLinkedHashMapDataTree[i].length) {
                    codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-LENGTH)", expectedLinkedHashMapDataTree[i], true);
                    resulted(false);
                    Assert.fail();
                }
            }

            if (linkedHashMapCompare.get(expectedLinkedHashMapDataTree[i][0]) == null && expectedLinkedHashMapDataTree[i][2] == null) {
                codexsHelperLogTermTests("OK -> CONTINUE", null, false);
                codexsHelperLogTermTests("TYPED....", null, false);
                codexsHelperLogTermTests("COMPARE..", null, false);
                continue;
            }

            if (
                    !linkedHashMapCompare.containsKey(expectedLinkedHashMapDataTree[i][0]) ||
                    linkedHashMapCompare.get(expectedLinkedHashMapDataTree[i][0]) == null &&
                    expectedLinkedHashMapDataTree[i][2] != null
            ) {
                codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [MISSING-KEY]", expectedLinkedHashMapDataTree[i][0], true);
                codexsHelperLogTermTests("EXPECTED....", expectedLinkedHashMapDataTree[i][0], false);
                codexsHelperLogTermTests("RECEIVED....", null, false);
                resulted(false);
                Assert.fail();
            }

            String expName = expectedLinkedHashMapDataTree[i][0].toString();
            String fndName = expectedLinkedHashMapDataTree[i][0].toString();
            String expVal = expectedLinkedHashMapDataTree[i][1].toString();
            String fndVal = linkedHashMapCompare.get(expectedLinkedHashMapDataTree[i][0]).toString();
            Object expType = expectedLinkedHashMapDataTree[i][2];
            Class<?> fndType = linkedHashMapCompare.get(expectedLinkedHashMapDataTree[i][0]).getClass();

            codexsHelperLogTermTests("=> NAME <=", expName, true);
            codexsHelperLogTermTests("TYPED....", expType, false);
            codexsHelperLogTermTests("EXPECTED.", expVal, false);
            codexsHelperLogTermTests("COMPARE..", fndVal, false);

            if (expVal.equals(fndVal) && expType != fndType && expType.toString().contains("interface")) {
                codexsHelperLogTermTests("> RESULT IS [WARNING] [WRONG-TYPED]", expName, true);
                defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    resulted(false);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    resulted(false);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    continue;
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    continue;
                }

            }
            codexsHelperLogTermTests("> RESULT IS [OK]", expName, false);
        }
        resulted(true);
        Assert.assertTrue(true);

    }

    public void codexsTesterCompareLinkedHashMapFormat(
            String[] linkedHashMapKeys,
            Object[] linkedHashMapValues,
            Object[] linkedHashMapTyped,
            LinkedHashMap<Object, Object> linkedHashMapCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("LINKED-HASH-MAP-KEYS....", Arrays.toString(linkedHashMapKeys), false);
        codexsHelperLogTermTests("LINKED-HASH-MAP-VALUES..", Arrays.toString(linkedHashMapValues), false);
        codexsHelperLogTermTests("LINKED-HASH-MAP-TYPED...", Arrays.toString(linkedHashMapTyped), false);
        codexsHelperLogTermTests("LINKED-HASH-MAP-COMPARE.", linkedHashMapCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", linkedHashMapCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!linkedHashMapCompare.getClass().toString().contains("LinkedHashMap")) {
            codexsHelperLogTermTests("ERROR ON LINKED-HASH-MAP DATA COMPARE (WRONG-CLASS-TYPED)", "", true);
            resulted(false);
            Assert.fail();
        }

        if (linkedHashMapKeys.length != linkedHashMapValues.length || linkedHashMapKeys.length != linkedHashMapTyped.length || linkedHashMapKeys.length != linkedHashMapCompare.size()) {
            codexsHelperLogTermTests("ERROR ON LINKED-HASH-MAP DATA COMPARE (WRONG-LENGTH)", "", true);
            resulted(false);
            Assert.fail();
        }

        for (int i = 0; i < linkedHashMapKeys.length; i++) {

            if (linkedHashMapCompare.get(linkedHashMapKeys[i]) == null && linkedHashMapTyped[i] == null) {
                codexsHelperLogTermTests("OK -> CONTINUE", null, false);
                codexsHelperLogTermTests("TYPED....", linkedHashMapTyped[i], false);
                codexsHelperLogTermTests("COMPARE..", linkedHashMapCompare.get(linkedHashMapKeys[i]), false);
                continue;
            }

            if (!linkedHashMapCompare.containsKey(linkedHashMapKeys[i]) || linkedHashMapCompare.get(linkedHashMapKeys[i]) == null && linkedHashMapTyped[i] != null) {
                codexsHelperLogTermTests("> RESULT IS [FAIL] [CRITICAL] [MISSING-KEY]", linkedHashMapKeys[i], true);
                codexsHelperLogTermTests("EXPECTED....", linkedHashMapKeys[i], false);
                codexsHelperLogTermTests("RECEIVED....", null, false);
                resulted(false);
                Assert.fail();
            }

            String expName = linkedHashMapKeys[i];
            String fndName = linkedHashMapKeys[i];
            String expVal = linkedHashMapValues[i].toString();
            String fndVal = linkedHashMapCompare.get(linkedHashMapKeys[i]).toString();
            Object expType = linkedHashMapTyped[i];
            Class<?> fndType = linkedHashMapCompare.get(linkedHashMapKeys[i]).getClass();

            codexsHelperLogTermTests("=> NAME <=", expName, true);
            codexsHelperLogTermTests("TYPED....", expType, false);
            codexsHelperLogTermTests("EXPECTED.", expVal, false);
            codexsHelperLogTermTests("COMPARE..", fndVal, false);

            if (expVal.equals(fndVal) && expType != fndType && expType.toString().contains("interface")) {
                codexsHelperLogTermTests("> RESULT IS [WARNING] [WRONG-TYPED]", expName, true);
                defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    resulted(false);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    resulted(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    resulted(false);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    continue;
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    continue;
                }

            }
            codexsHelperLogTermTests("> RESULT IS [OK]", expName, false);
        }
        resulted(true);
        Assert.assertTrue(true);

    }

}
