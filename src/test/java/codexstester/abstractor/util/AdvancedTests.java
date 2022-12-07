package codexstester.abstractor.util;

import codexstester.setup.properties.FilePropertiesSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.util.Arrays;

import static codexstester.abstractor.util.UtilTests.logTermTests;

public abstract class AdvancedTests extends FilePropertiesSourceTests {

    private void strictMessage(boolean flag) {
        if (flag) {
            logTermTests(" Use strictMode = true if you don't want to ignore it", "", false);
        } else {
            logTermTests(" Use strictMode = false if you want to ignore it", "", false);
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
        logTermTests("  ==> EXPECTED NAME....", expectedName, false);
        logTermTests("  ==> FOUND NAME.......", foundName, false);
        logTermTests("  ==> EXPECTED TYPED...", expectedTyped, false);
        logTermTests("  ==> FOUND TYPED......", foundTyped, false);
        logTermTests("  ==> EXPECTED VALUE...", expectedValue, false);
        logTermTests("  ==> FOUND VALUE......", foundValue, false);
    }

    public void codexsTesterCompareJsonFormat(
            String[] jsonKeys,
            Object[] jsonValues,
            Object[] jsonTyped,
            JSONObject jsonCompare,
            boolean strictMode
    ) {

        if (!jsonCompare.getClass().toString().contains("JSONObject")) {
            logTermTests("ERROR ON JSON DATA COMPARE (WRONG-JSON-TYPED)", "", true);
            Assert.fail();
        }

        if (jsonKeys.length != jsonValues.length || jsonKeys.length != jsonTyped.length || jsonKeys.length != jsonCompare.size()) {
            logTermTests("ERROR ON JSON DATA COMPARE (LENGTH)", "", true);
            Assert.fail();
        }

        for (int i = 0; i < jsonKeys.length; i++) {

            if (jsonCompare.get(jsonKeys[i]) == null && jsonTyped[i] == null) {
                logTermTests("OK -> CONTINUE", null, false);
                logTermTests("TYPED....", jsonTyped[i], false);
                logTermTests("COMPARE..", jsonCompare.get(jsonKeys[i]), false);
                continue;
            }

            if (jsonCompare.get(jsonKeys[i]) == null && jsonTyped[i] != null) {
                logTermTests("ERROR -> FAILURE", null, false);
                logTermTests("TYPED....", jsonTyped[i], false);
                logTermTests("COMPARE..", jsonCompare.get(jsonKeys[i]), false);
                Assert.fail();
            }

            String expKey = jsonKeys[i];
            String expVal = jsonValues[i].toString();
            Object expType = jsonTyped[i];
            Object fndKey = null;
            String fndVal = jsonCompare.getAsString(expKey);
            Class<?> fndType = jsonCompare.get(expKey).getClass();

            logTermTests("=> KEY <=", expKey, true);
            logTermTests("VALUE....", expVal, false);
            logTermTests("TYPED....", expType, false);
            logTermTests("COMPARE..", fndVal, false);

             if (fndVal.equals(expVal) && fndType != expType && expType.toString().contains("interface")) {

                logTermTests("1. RESULT IS [WARNING] [NO-STRICT] [MESS-TYPED.]", expKey, false);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, null);
                strictMessage(true);

            } else if (fndVal.equals(expVal) && fndType != expType && strictMode) {

                logTermTests("1. RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED.]", expKey, false);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, null);
                Assert.fail();

            } else if (fndVal.equals(expVal) && fndType == expType) {

                logTermTests("1. RESULT IS [OK]", expKey, false);

            } else if (!fndVal.equals(expVal) && fndType == expType && !strictMode) {

                logTermTests("2. RESULT IS [WARNING] [NO-STRICT] [DIFF-VALUES]", expKey, false);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, null);
                strictMessage(true);

            } else if (!fndVal.equals(expVal) && fndType == expType) {

                logTermTests("2. RESULT IS [FAIL] [STRICT] [DIFF-VALUES]", expKey, false);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, null);
                strictMessage(false);
                Assert.fail();

            } else {

                logTermTests("3. RESULT IS [WARNING] [NO-STRICT] [CRITICAL-ERROR] [WRONG-TYPED.]", expKey, false);
                defaultMessage(expVal, fndVal, expType, fndType, expKey, null);
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

        logTermTests("======== SUMMARY =======", "", true);
        logTermTests("VALUES..................", Arrays.toString(dtoValues), false);
        logTermTests("CLASS...................", dtoClass, false);
        logTermTests("CLASS-FIELDS............", Arrays.toString(dtoClass.getDeclaredFields()), false);
        logTermTests("CLASS-FIELDS-LENGTH.....", dtoClass.getDeclaredFields().length, false);
        logTermTests("COMPARE.................", dtoCompare, false);
        logTermTests("COMPARE-GET-CLASS.......", dtoCompare.getClass(), false);
        logTermTests("COMPARE-TO-STRING.......", dtoCompare.toString(), false);
        logTermTests("COMPARE-FIELDS..........", Arrays.toString(dtoCompare.getClass().getFields()), false);
        logTermTests("COMPARE-DECLARED-FIELDS.", Arrays.toString(dtoCompare.getClass().getDeclaredFields()), false);
        logTermTests("COMPARE-LENGTH..........", dtoCompare.getClass().getDeclaredFields().length, false);
        logTermTests("======== COMPARE =======", "", true);

        if (dtoClass != dtoCompare.getClass() && strictMode) {
            logTermTests("**** ERROR ****", "", true);
            logTermTests("ERROR ON DTO DATA COMPARE (WRONG-DTO-CLASS)", "", false);
            logTermTests("  ==> EXPECTED...: ", dtoClass, false);
            logTermTests("  ==> RECEIVED...: ", dtoCompare.getClass(), false);
            Assert.fail();
        }

        int sizeClass = dtoClass.getDeclaredFields().length;
        int sizeCompare = dtoCompare.getClass().getDeclaredFields().length;

        if (sizeClass != sizeCompare) {
            logTermTests("**** ERROR ****", "", true);
            logTermTests("ERROR ON DTO DATA COMPARE (WRONG-DTO-CLASS-LENGTH)", "", false);
            logTermTests("  ==> CLASS......", dtoClass, false);
            logTermTests("  ==> COMPARE....", dtoCompare.getClass(), false);
            logTermTests("  ==> EXPECTED...", sizeClass, false);
            logTermTests("  ==> RECEIVED...", sizeCompare, false);
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

            logTermTests("=> NAME <=", expName, true);
            logTermTests("TYPED.....", expType, false);
            logTermTests("VALUE.....", expVal, false);
            logTermTests("COMPARE...", fndVal, false);

            if (strictMode) {

                if (expType != fndType) {
                    logTermTests("1. RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    logTermTests("2. RESULT IS [FAIL] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    Assert.fail();
                }

                if (!expVal.equals(fndVal)) {
                    logTermTests("3. RESULT IS [FAIL] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(true);
                    Assert.fail();
                }

            } else {

                if (expType != fndType) {
                    logTermTests("4. RESULT IS [FAIL] [STRICT] [DIFF-TYPED]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    Assert.fail();
                }

                if (!expName.equals(fndName)) {
                    logTermTests("1. RESULT IS [WARNING] [STRICT] [WRONG-NAME]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    continue;
                }

                if (!expVal.equals(fndVal)) {
                    logTermTests("2. RESULT IS [WARNING] [STRICT] [WRONG-VALUE]", expName, true);
                    defaultMessage(expVal, fndVal, expType, fndType, expName, fndName);
                    strictMessage(false);
                    continue;
                }

            }

            logTermTests("1. RESULT IS [OK]", expName, false);

        }
        Assert.assertTrue(true);
    }

    public void codexsTesterCompareHashMapFormat() {}

    public void codexsTesterCompareArrayListFormat() {}

    public void codexsTesterCompareLinkedListFormat() {}

    public void codexsTesterCompareListFormat() {}

}
