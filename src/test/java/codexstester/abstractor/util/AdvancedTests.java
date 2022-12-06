package codexstester.abstractor.util;

import codexstester.setup.properties.FilePropertiesSourceTests;
import net.minidev.json.JSONObject;
import org.junit.Assert;

import static codexstester.abstractor.util.UtilTests.logTerm;
import static codexstester.abstractor.util.UtilTests.logTermTests;

public abstract class AdvancedTests extends FilePropertiesSourceTests {

    public void codexsTesterCompareJsonFormat(
            String[] keysToCompare,
            Object[] valuesToCompare,
            Object[] typingToCompare,
            JSONObject jsonToCompare,
            boolean strictMode
    ) {
        if (keysToCompare.length != valuesToCompare.length || keysToCompare.length != typingToCompare.length || keysToCompare.length != jsonToCompare.size()) {
            logTerm("ERROR ON COMPARE DATA LENGTH", null, true);
            Assert.fail();
        }
        for (int i = 0; i < keysToCompare.length; i++) {

            logTermTests("=> KEY", keysToCompare[i], true);
            logTermTests("VALUE", valuesToCompare[i], false);
            logTermTests("TYPING", typingToCompare[i], false);
            logTermTests("RESPONSE", jsonToCompare.get(keysToCompare[i]), false);

            if (jsonToCompare.get(keysToCompare[i]) == null && typingToCompare[i] == null) {
                logTermTests("OK -> CONTINUE", null, false);
                logTermTests("TYPING", typingToCompare[i], false);
                logTermTests("RESPONSE", jsonToCompare.get(keysToCompare[i]), false);
                continue;
            }

            if (jsonToCompare.get(keysToCompare[i]) == null && typingToCompare[i] != null) {
                logTermTests("ERROR -> FAIL", null, false);
                logTermTests("TYPING", typingToCompare[i], false);
                logTermTests("RESPONSE", jsonToCompare.get(keysToCompare[i]), false);
                Assert.fail();
            }

            if (
                    jsonToCompare.get(keysToCompare[i]).equals(valuesToCompare[i]) &&
                    jsonToCompare.get(keysToCompare[i]).getClass() == typingToCompare[i]
            ) {
                logTermTests("RESULT IS OK", keysToCompare[i], false);
            /*} else if (
                    jsonToCompare.get(keysToCompare[i]).toString().matches(valuesToCompare[i].toString()) &&
                    jsonToCompare.get(keysToCompare[i]).getClass() == typingToCompare[i]
            ) {
                logTermTests("RESULT IS OK [REGEXP]", keysToCompare[i], false);*/
            } else {
                if (typingToCompare[i].toString().contains("interface") && strictMode) {
                    logTermTests("RESULT IS FAIL", keysToCompare[i], false);
                    logTermTests("  ==> EXPECTED TYPING", typingToCompare[i], false);
                    logTermTests("  ==> FOUND TYPING", jsonToCompare.get(keysToCompare[i]).getClass(), false);
                    logTermTests(" Use strictMode = false if you want to ignore it", null, false);
                    Assert.fail();
                } else {
                    logTermTests("**** WARNING ****", keysToCompare[i], false);
                    logTermTests("  ==> EXPECTED TYPING", typingToCompare[i], false);
                    logTermTests("  ==> FOUND TYPING", jsonToCompare.get(keysToCompare[i]).getClass(), false);
                    logTermTests(" Use strictMode = true if you don't want to ignore it", null, false);
                    continue;
                }
            }
        }
        Assert.assertTrue(true);
    }

}
