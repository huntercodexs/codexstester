package com.huntercodexs.codexstester.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.huntercodexs.codexstester.constant.CodexsConstant.*;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTerm;
import static com.huntercodexs.codexstester.util.CodexsHelper.codexsHelperLogTermTests;

public abstract class CodexsAssertion extends CodexsJsonComparator {

    private static String expected(Object text) {
        return ">> EXPECTED: ["+text+"]";
    }

    private static String received(Object text) {
        return "<< RECEIVED: ["+text+"]";
    }

    private static String methodName(StackTraceElement firstCaller, StackTraceElement lastCaller) {
        String methodName = "! Unknown Method";
        if (firstCaller != null && !firstCaller.getMethodName().isEmpty()) {
            methodName = firstCaller.getMethodName();
        }
        return methodName +" > "+lastCaller.getMethodName();
    }

    protected static void resulted(boolean flag, StackTraceElement methodName) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement currentCaller = stackTrace[2];

        codexsHelperLogTermTests("[RESULT]", "CODEXS TESTER", true);
        if (flag)  codexsHelperLogTermTests("[PASSED]", methodName(methodName, currentCaller), false);
        if (!flag) codexsHelperLogTermTests("[FAILED]", methodName(methodName, currentCaller), false);
    }

    public static void codexsTesterAssertExact(
            String expected,
            String received,
            StackTraceElement methodName
    ) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        if (received.equals(expected)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(expected), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertObject(
            Object expected,
            Object received,
            StackTraceElement methodName
    ) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        if (expected == received) {
            Assert.assertEquals(expected, received);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(expected), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertText(
            String expected,
            String received,
            StackTraceElement methodName
    ) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        if (received.contains(expected)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(expected), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertRegExp(
            String expected,
            String received,
            StackTraceElement methodName
    ) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        if (received.matches(expected)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(expected), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertInt(
            int expected,
            int received,
            StackTraceElement methodName
    ) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        if (expected == received) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(expected), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertBool(
            boolean expected,
            boolean received,
            StackTraceElement methodName
    ) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        if (expected == received) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(expected), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertNotNull(Object received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        try {
            Assert.assertNotNull(received);
            resulted(true, methodCaller);
        } catch (RuntimeException re) {
            codexsHelperLogTerm(expected("NotNull"), received("Null"), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertNull(Object received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        try {
            Assert.assertNull(received);
            resulted(true, methodCaller);
        } catch (RuntimeException re) {
            codexsHelperLogTerm(expected("Null"), received("NotNull"), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertNumber(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        if (StringUtils.isNumeric(received)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected("Numeric"), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertGuid(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_GUID);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertJwtHS256(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_JWT_HS256);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertJwtHS384(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_JWT_HS384);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertJwtHS512(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_JWT_HS512);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertJwtRS256(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_JWT_RS256);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertJwtRS384(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_JWT_RS384);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertJwtRS512(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_JWT_RS512);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertJwtES256(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_JWT_ES256);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertJwtES384(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_JWT_ES384);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertJwtES512(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_JWT_ES512);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertJwtPS256(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_JWT_PS256);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertJwtPS384(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_JWT_PS384);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertJwtPS512(String received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        String pattern = getConstant(CODEXS_JWT_PS512);

        if (received.matches(pattern)) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(pattern), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

    public static void codexsTesterAssertCpf(String cpfNumber, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        if (cpfNumber.length() > 11) {
            resulted(false, methodCaller);
            Assert.fail();
        }

        cpfNumber = cpfNumber.replace(".", "");
        cpfNumber = cpfNumber.replace("-", "");

        try {
            Long.parseLong(cpfNumber);
        } catch(NumberFormatException e) {
            resulted(false, methodCaller);
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

        resulted(true, methodCaller);
        Assert.assertEquals(digitVerify, nDigResult);
    }

    public static void codexsTesterAssertEmail(String emailAddress, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        boolean isValidMail = false;
        if (emailAddress != null && emailAddress.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(emailAddress);
            if (matcher.matches()) {
                isValidMail = true;
            }
        }
        resulted(isValidMail, methodCaller);
        Assert.assertTrue(isValidMail);
    }

    public static void codexsTesterAssertPhone(String phoneNumber, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        boolean isValidPhone = false;
        if (phoneNumber != null && phoneNumber.length() > 0) {
            String expression = "^[+]?[0-9]{13}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches()) {
                isValidPhone = true;
            }
        }
        resulted(isValidPhone, methodCaller);
        Assert.assertTrue(isValidPhone);
    }

    public static void codexsTesterAssertSum(int val1, int val2, int received, StackTraceElement methodName) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement methodCaller = stackTrace[2];

        if (methodName != null) methodCaller = methodName;

        if (val1 + val2 == received) {
            Assert.assertTrue(true);
            resulted(true, methodCaller);
        } else {
            codexsHelperLogTerm(expected(val1+val2), received(received), true);
            resulted(false, methodCaller);
            Assert.fail();
        }
    }

}
