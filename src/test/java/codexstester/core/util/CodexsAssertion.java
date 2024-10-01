package codexstester.core.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static codexstester.core.util.CodexsHelper.codexsHelperLogTerm;
import static codexstester.core.util.CodexsHelper.codexsHelperLogTermTests;

public abstract class CodexsAssertion extends CodexsAdvanced {

    protected static void resulted(boolean flag) {
        if (flag) codexsHelperLogTermTests("CODEXS TESTER FINISHED", "PASSED", true);
        if (!flag) codexsHelperLogTermTests("CODEXS TESTER FINISHED", "FAILED", true);
    }

    protected void codexsTesterAssertExact(String expected, String received) {
        if (received.equals(expected)) {
            Assert.assertTrue(true);
            resulted(true);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + received, true);
            resulted(false);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertObject(Object expected, Object received) {
        if (expected == received) {
            Assert.assertEquals(expected, received);
            resulted(true);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + received, true);
            resulted(false);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertText(String expected, String received) {
        if (received.contains(expected)) {
            Assert.assertTrue(true);
            resulted(true);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + received, true);
            resulted(false);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertRegExp(String expected, String received) {
        if (received.matches(expected)) {
            Assert.assertTrue(true);
            resulted(true);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + received, true);
            resulted(false);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertInt(int expected, int received) {
        if (expected == received) {
            Assert.assertTrue(true);
            resulted(true);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + received, true);
            resulted(false);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertBool(boolean expected, boolean received) {
        if (expected == received) {
            Assert.assertTrue(true);
            resulted(true);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + received, true);
            resulted(false);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertNotNull(Object received) {
        try {
            Assert.assertNotNull(received);
            resulted(true);
        } catch (RuntimeException re) {
            codexsHelperLogTerm(">> EXPECTED: NotNull", "<< RECEIVED: Null", true);
            resulted(false);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertNull(Object received) {
        try {
            Assert.assertNull(received);
            resulted(true);
        } catch (RuntimeException re) {
            codexsHelperLogTerm(">> EXPECTED: Null", "<< RECEIVED: NotNull", true);
            resulted(false);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertNumber(String received) {
        if (StringUtils.isNumeric(received)) {
            Assert.assertTrue(true);
            resulted(true);
        } else {
            codexsHelperLogTerm(">> EXPECTED: Numeric", "<< RECEIVED: " + received, true);
            resulted(false);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertCpf(String cpfNumber) {

        if (cpfNumber.length() > 11) {
            resulted(false);
            Assert.fail();
        }

        cpfNumber = cpfNumber.replace(".", "");
        cpfNumber = cpfNumber.replace("-", "");

        try {
            Long.parseLong(cpfNumber);
        } catch(NumberFormatException e) {
            resulted(false);
            Assert.fail();
        }

        int d1, d2;
        int digit1, digit2, rest;
        int cpfDigit;
        String nDigResult;

        d1 = d2 = 0;
        digit1 = digit2 = rest = 0;

        for (int nCount = 1; nCount < cpfNumber.length() - 1; nCount++) {
            cpfDigit = Integer.valueOf(cpfNumber.substring(nCount - 1, nCount)).intValue();
            d1 = d1 + (11 - nCount) * cpfDigit;
            d2 = d2 + (12 - nCount) * cpfDigit;
        };

        rest = (d1 % 11);

        if (rest < 2)
            digit1 = 0;
        else
            digit1 = 11 - rest;

        d2 += 2 * digit1;

        rest = (d2 % 11);

        if (rest < 2)
            digit2 = 0;
        else
            digit2 = 11 - rest;

        String digitVerify = cpfNumber.substring(cpfNumber.length() - 2, cpfNumber.length());

        nDigResult = String.valueOf(digit1) + String.valueOf(digit2);

        resulted(true);
        Assert.assertEquals(digitVerify, nDigResult);
    }

    protected void codexsTesterAssertEmail(String emailAddress) {
        boolean isValidMail = false;
        if (emailAddress != null && emailAddress.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailAddress);
            if (matcher.matches()) {
                isValidMail = true;
            }
        }
        resulted(isValidMail);
        Assert.assertTrue(isValidMail);
    }

    protected void codexsTesterAssertPhone(String phoneNumber) {
        boolean isValidPhone = false;
        if (phoneNumber != null && phoneNumber.length() > 0) {
            String expression = "^[+]?[0-9]{13}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches()) {
                isValidPhone = true;
            }
        }
        resulted(isValidPhone);
        Assert.assertTrue(isValidPhone);
    }

    protected void codexsTesterAssertSum(int val1, int val2, int received) {
        if (val1 + val2 == received) {
            Assert.assertTrue(true);
            resulted(true);
        } else {
            codexsHelperLogTerm(">> EXPECTED: " + (val1+val2), "<< RECEIVED: " + received, true);
            resulted(false);
            Assert.fail();
        }
    }

}
