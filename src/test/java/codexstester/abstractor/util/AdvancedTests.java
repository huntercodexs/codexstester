package codexstester.abstractor.util;

import codexstester.setup.properties.FilePropertiesSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Assert;

import static codexstester.abstractor.util.UtilTests.logTerm;
import static codexstester.abstractor.util.UtilTests.logTermTests;

public abstract class AdvancedTests extends FilePropertiesSourceTests {

    private void strictMessage(boolean flag) {
        if (flag) {
            logTermTests(" Use strictMode = true if you don't want to ignore it", "", false);
        } else {
            logTermTests(" Use strictMode = false if you want to ignore it", "", false);
        }
    }

    private void defaultMessage(Object expectedValue, Object foundValue, Object expectedTyped, Object foundTyped) {
        logTermTests("  ==> EXPECTED VALUE...", expectedValue, false);
        logTermTests("  ==> FOUND VALUE......", foundValue, false);
        logTermTests("  ==> EXPECTED TYPED...", expectedTyped, false);
        logTermTests("  ==> FOUND TYPED......", foundTyped, false);
    }

    public void codexsTesterCompareJsonFormat(
            String[] keysToCompare,
            Object[] valuesToCompare,
            Object[] typedToCompare,
            JSONObject jsonToCompare,
            boolean strictMode
    ) {

        if (keysToCompare.length != valuesToCompare.length || keysToCompare.length != typedToCompare.length || keysToCompare.length != jsonToCompare.size()) {
            logTerm("ERROR ON DATA COMPARE (LENGTH)", null, true);
            Assert.fail();
        }

        for (int i = 0; i < keysToCompare.length; i++) {

            if (jsonToCompare.get(keysToCompare[i]) == null && typedToCompare[i] == null) {
                logTermTests("OK -> CONTINUE", null, false);
                logTermTests("TYPED....", typedToCompare[i], false);
                logTermTests("COMPARE..", jsonToCompare.get(keysToCompare[i]), false);
                continue;
            }

            if (jsonToCompare.get(keysToCompare[i]) == null && typedToCompare[i] != null) {
                logTermTests("ERROR -> FAILURE", null, false);
                logTermTests("TYPED....", typedToCompare[i], false);
                logTermTests("COMPARE..", jsonToCompare.get(keysToCompare[i]), false);
                Assert.fail();
            }

            String expKey = keysToCompare[i];
            String expVal = valuesToCompare[i].toString();
            Object expType = typedToCompare[i];
            String fndVal = jsonToCompare.getAsString(expKey);
            Class<?> fndType = jsonToCompare.get(expKey).getClass();

            logTermTests("=> KEY <=", expKey, true);
            logTermTests("VALUE....", expVal, false);
            logTermTests("TYPED....", expType, false);
            logTermTests("COMPARE..", fndVal, false);

             if (fndVal.equals(expVal) && fndType != expType && expType.toString().contains("interface")) {

                logTermTests("1. RESULT IS [WARNING] [NO-STRICT] [MESS-TYPED.]", expKey, false);
                defaultMessage(expVal, fndVal, expType, fndType);
                strictMessage(true);

            } else if (fndVal.equals(expVal) && fndType != expType && strictMode) {

                logTermTests("1. RESULT IS [FAIL] [STRICT] [CRITICAL-ERROR] [WRONG-TYPED.]", expKey, false);
                defaultMessage(expVal, fndVal, expType, fndType);
                Assert.fail();

            } else if (fndVal.equals(expVal) && fndType == expType) {

                logTermTests("1. RESULT IS [OK]", expKey, false);

            } else if (!fndVal.equals(expVal) && fndType == expType && !strictMode) {

                logTermTests("2. RESULT IS [WARNING] [NO-STRICT] [DIFF-VALUES]", expKey, false);
                defaultMessage(expVal, fndVal, expType, fndType);
                strictMessage(true);

            } else if (!fndVal.equals(expVal) && fndType == expType) {

                logTermTests("2. RESULT IS [FAIL] [STRICT] [DIFF-VALUES]", expKey, false);
                defaultMessage(expVal, fndVal, expType, fndType);
                strictMessage(false);
                Assert.fail();

            } else {

                logTermTests("3. RESULT IS [WARNING] [NO-STRICT] [CRITICAL-ERROR] [WRONG-TYPED.]", expKey, false);
                defaultMessage(expVal, fndVal, expType, fndType);
                Assert.fail();
            }
        }
        Assert.assertTrue(true);
    }

}
