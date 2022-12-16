package codexstester.abstractor.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static codexstester.abstractor.util.CodexsHelperTests.codexsHelperLogTermTests;

public abstract class AssertionTests extends AdvancedTests {

    protected static void resulted(boolean flag) {
        if (flag) codexsHelperLogTermTests("CODEXS TESTER FINISHED: ", "PASSED", true);
        if (!flag) codexsHelperLogTermTests("CODEXS TESTER FINISHED: ", "FAILED", true);
    }

    protected void codexsTesterAssertExact(String ref, String text) {
        if (text.equals(ref)) {
            Assert.assertEquals(1, 1);
            resulted(true);
        } else {
            resulted(false);
            Assert.assertEquals(1, 0);
        }
    }

    protected void codexsTesterAssertObject(Object obj1, Object obj2) {
        try {
            Assert.assertEquals(obj1, obj2);
            resulted(true);
        } catch (RuntimeException re) {
            resulted(false);
        }
    }

    protected void codexsTesterAssertText(String ref, String text) {
        if (text.contains(ref)) {
            Assert.assertEquals(1, 1);
            resulted(true);
        } else {
            resulted(false);
            Assert.assertEquals(1, 0);
        }
    }

    protected void codexsTesterAssertRegExp(String regExp, String text) {
        if (text.matches(regExp)) {
            Assert.assertEquals(1, 1);
            resulted(true);
        } else {
            resulted(false);
            Assert.assertEquals(1, 0);
        }
    }

    protected void codexsTesterAssertInt(int num1, int num2) {
        if (num1 == num2) {
            Assert.assertEquals(1, 1);
            resulted(true);
        } else {
            resulted(false);
            Assert.assertEquals(1, 0);
        }
    }

    protected void codexsTesterAssertBool(boolean val, boolean flag) {
        if (flag) {
            Assert.assertTrue(val);
            resulted(true);
        } else {
            resulted(false);
            Assert.assertFalse(val);
        }
    }

    protected void codexsTesterAssertNotNull(Object obj) {
        try {
            Assert.assertNotNull(obj);
            resulted(true);
        } catch (RuntimeException re) {
            resulted(false);
        }
    }

    protected void codexsTesterAssertNull(Object obj) {
        try {
            Assert.assertNull(obj);
            resulted(true);
        } catch (RuntimeException re) {
            resulted(false);
        }
    }

    protected void codexsTesterAssertNumber(String number) {
        if (StringUtils.isNumeric(number)) {
            Assert.assertEquals(1, 1);
            resulted(true);
        } else {
            resulted(false);
            Assert.assertEquals(1, 0);
        }
    }

    protected void codexsTesterAssertCpf(String cpf) {

        if (cpf.length() > 11) {
            resulted(false);
            Assert.fail();
        }

        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        try {
            Long.parseLong(cpf);
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

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            cpfDigit = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();
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

        String digitVerify = cpf.substring(cpf.length() - 2, cpf.length());

        nDigResult = String.valueOf(digit1) + String.valueOf(digit2);

        resulted(true);
        Assert.assertEquals(digitVerify, nDigResult);
    }

    protected void codexsTesterAssertEmail(String email) {
        boolean isValidMail = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
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

    protected void codexsTesterAssertSum(int a, int b, int c) {
        if (a + b == c) {
            Assert.assertTrue(true);
            resulted(true);
        } else {
            resulted(false);
        }
    }

}
