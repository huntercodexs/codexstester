package com.huntercodexs.codexstester.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.huntercodexs.codexstester.constant.CodexsConstant.*;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTerm;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTermTests;

public abstract class CodexsAssertion extends CodexsJsonComparator {

    private static String methodName(StackTraceElement caller, StackTraceElement element) {
        String methodName = "! Unknown Method";
        if (caller != null && !caller.getMethodName().isEmpty()) {
            methodName = caller.getMethodName();
        }
        return methodName +" > "+element.getMethodName();
    }

    protected static void resulted(boolean flag, StackTraceElement caller) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        codexsHelperLogTermTests("[RESULT]", "CODEXS TESTER", true);
        if (flag)  codexsHelperLogTermTests("[PASSED]", methodName(caller, element), false);
        if (!flag) codexsHelperLogTermTests("[FAILED]", methodName(caller, element), false);
    }

    protected void codexsTesterAssertExact(String expected, String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (received.equals(expected)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertObject(Object expected, Object received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (expected == received) {
            Assert.assertEquals(expected, received);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertText(String expected, String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (received.contains(expected)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertRegExp(String expected, String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (received.matches(expected)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertInt(int expected, int received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (expected == received) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertBool(boolean expected, boolean received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (expected == received) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: "+expected, "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertNotNull(Object received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        try {
            Assert.assertNotNull(received);
            resulted(true, element);
        } catch (RuntimeException re) {
            codexsHelperLogTerm(">> EXPECTED: NotNull", "<< RECEIVED: Null", true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertNull(Object received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        try {
            Assert.assertNull(received);
            resulted(true, element);
        } catch (RuntimeException re) {
            codexsHelperLogTerm(">> EXPECTED: Null", "<< RECEIVED: NotNull", true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertNumber(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (StringUtils.isNumeric(received)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: Numeric", "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertGuid(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_GUID);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertJwtHS256(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_JWT_HS256);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertJwtHS384(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_JWT_HS384);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertJwtHS512(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_JWT_HS512);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertJwtRS256(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_JWT_RS256);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertJwtRS384(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_JWT_RS384);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertJwtRS512(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_JWT_RS512);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertJwtES256(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_JWT_ES256);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertJwtES384(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_JWT_ES384);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertJwtES512(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_JWT_ES512);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertJwtPS256(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_JWT_PS256);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertJwtPS384(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_JWT_PS384);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertJwtPS512(String received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        String pattern = getConstant(CODEXS_JWT_PS512);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(
                    ">> EXPECTED: " + pattern,
                    "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

    protected void codexsTesterAssertCpf(String cpfNumber) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];


        if (cpfNumber.length() > 11) {
            resulted(false, element);
            Assert.fail();
        }

        cpfNumber = cpfNumber.replace(".", "");
        cpfNumber = cpfNumber.replace("-", "");

        try {
            Long.parseLong(cpfNumber);
        } catch(NumberFormatException e) {
            resulted(false, element);
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

        resulted(true, element);
        Assert.assertEquals(digitVerify, nDigResult);
    }

    protected void codexsTesterAssertEmail(String emailAddress) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        boolean isValidMail = false;
        if (emailAddress != null && emailAddress.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailAddress);
            if (matcher.matches()) {
                isValidMail = true;
            }
        }
        resulted(isValidMail, element);
        Assert.assertTrue(isValidMail);
    }

    protected void codexsTesterAssertPhone(String phoneNumber) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        boolean isValidPhone = false;
        if (phoneNumber != null && phoneNumber.length() > 0) {
            String expression = "^[+]?[0-9]{13}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches()) {
                isValidPhone = true;
            }
        }
        resulted(isValidPhone, element);
        Assert.assertTrue(isValidPhone);
    }

    protected void codexsTesterAssertSum(int val1, int val2, int received) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement element = stackTrace[2];

        if (val1 + val2 == received) {
            Assert.assertTrue(true);
            resulted(true, element);
        } else {
            codexsHelperLogTerm(">> EXPECTED: " + (val1+val2), "<< RECEIVED: " + received, true);
            resulted(false, element);
            Assert.fail();
        }
    }

}
