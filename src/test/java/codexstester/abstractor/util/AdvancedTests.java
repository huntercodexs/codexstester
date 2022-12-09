package codexstester.abstractor.util;

import codexstester.setup.properties.FilePropertiesSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.*;

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

    public void codexsTesterCompareJsonFormat(
            String[] jsonKeys,
            Object[] jsonValues,
            Object[] jsonTyped,
            JSONObject jsonCompare,
            boolean strictMode
    ) {

        codexsHelperLogTermTests("======== SUMMARY =======", "", true);
        codexsHelperLogTermTests("JSON-KEYS...............", jsonKeys, false);
        codexsHelperLogTermTests("JSON-VALUES.............", jsonValues, false);
        codexsHelperLogTermTests("JSON-TYPED..............", jsonTyped, false);
        codexsHelperLogTermTests("JSON-COMPARE............", jsonCompare, false);
        codexsHelperLogTermTests("STRICT-MODE.............", strictMode, false);
        codexsHelperLogTermTests("CLASS-TYPE-NAME.........", jsonCompare.getClass().toString(), false);
        codexsHelperLogTermTests("======== COMPARE =======", "", true);

        if (!jsonCompare.getClass().toString().contains("JSONObject")) {
            codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-JSON-TYPED)", "", true);
            Assert.fail();
        }

        if (jsonKeys.length != jsonValues.length || jsonKeys.length != jsonTyped.length || jsonKeys.length != jsonCompare.size()) {
            codexsHelperLogTermTests("ERROR ON JSON DATA COMPARE (WRONG-LENGTH)", "", true);
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

             if (fndVal.equals(expVal) && fndType != expType && expType.toString().contains("interface")) {

                codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [MESS-TYPED]", expKey, true);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
                strictMessage(true);

            } else if (fndVal.equals(expVal) && fndType != expType && strictMode) {

                codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED]", expKey, true);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
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
                Assert.fail();

            } else {

                codexsHelperLogTermTests("> RESULT IS [WARNING] [NO-STRICT] [CRITICAL-ERROR] [WRONG-TYPED]", expKey, true);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, "IGNORED");
                Assert.fail();
            }
        }
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
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
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
            Assert.fail();
        }

        if (hashMapKeys.length != hashMapValues.length || hashMapKeys.length != hashMapTyped.length || hashMapKeys.length != hashMapCompare.size()) {
            codexsHelperLogTermTests("ERROR ON HASH-MAP DATA COMPARE (WRONG-LENGTH)", "", true);
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
                codexsHelperLogTermTests("> RESULT IS [WARNING] [MESS-TYPED]", expName, true);
                defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
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
            Assert.fail();
        }

        if (arrayListValues.length != arrayListTyped.length || arrayListValues.length != arrayListCompare.size()) {
            codexsHelperLogTermTests("ERROR ON ARRAY-LIST DATA COMPARE (WRONG-LENGTH)", "", true);
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
                codexsHelperLogTermTests("> RESULT IS [WARNING] [MESS-TYPED]", expVal, true);
                defaultMessage(expVal, fndVal, expType, fndType, null, null);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
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
            Assert.fail();
        }

        if (linkedListValues.length != linkedListTyped.length || linkedListValues.length != linkedListCompare.size()) {
            codexsHelperLogTermTests("ERROR ON LINKED-LIST DATA COMPARE (WRONG-LENGTH)", "", true);
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
                codexsHelperLogTermTests("> RESULT IS [WARNING] [MESS-TYPED]", expVal, true);
                defaultMessage(expVal, fndVal, expType, fndType, null, null);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
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
            Assert.fail();
        }

        if (listValues.length != listTyped.length || listValues.length != listCompare.size()) {
            codexsHelperLogTermTests("ERROR ON LIST<I> DATA COMPARE (WRONG-LENGTH)", "", true);
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
                codexsHelperLogTermTests("> RESULT IS [WARNING] [MESS-TYPED]", expVal, true);
                defaultMessage(expVal, fndVal, expType, fndType, null, null);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
                    strictMessage(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", "INDEX-"+i, true);
                    defaultMessage(expVal, fndVal, expType, fndType, null, null);
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
            Assert.fail();
        }

        if (linkedHashMapKeys.length != linkedHashMapValues.length || linkedHashMapKeys.length != linkedHashMapTyped.length || linkedHashMapKeys.length != linkedHashMapCompare.size()) {
            codexsHelperLogTermTests("ERROR ON LINKED-HASH-MAP DATA COMPARE (WRONG-LENGTH)", "", true);
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
                codexsHelperLogTermTests("> RESULT IS [WARNING] [MESS-TYPED]", expName, true);
                defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                strictMessage(true);
                continue;
            }

            if (strictMode) {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    codexsHelperLogTermTests("> RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
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
        Assert.assertTrue(true);

    }

}
