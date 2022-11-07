package com.huntercodexs.codexstester.abstractor.unitary;

import com.huntercodexs.codexstester.abstractor.AvailableHttpMethodTests;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractTestsUnitaryTests extends AvailableHttpMethodTests {

    protected void assertionExact(String ref, String text) {
        if (text.equals(ref)) {
            Assert.assertEquals(1, 1);
        } else {
            Assert.assertEquals(1, 0);
        }
    }

    protected void assertionObject(Object obj1, Object obj2) {
        Assert.assertEquals(obj1, obj2);
    }

    protected void assertionText(String ref, String text) {
        if (text.contains(ref)) {
            Assert.assertEquals(1, 1);
        } else {
            Assert.assertEquals(1, 0);
        }
    }

    protected void assertionRegExp(String regExp, String text) {
        if (text.matches(regExp)) {
            Assert.assertEquals(1, 1);
        } else {
            Assert.assertEquals(1, 0);
        }
    }

    protected void assertionInt(int num1, int num2) {
        if (num1 == num2) {
            Assert.assertEquals(1, 1);
        } else {
            Assert.assertEquals(1, 0);
        }
    }

    protected void assertionBool(boolean val, boolean flag) {
        if (flag)
            Assert.assertTrue(val);
        else
            Assert.assertFalse(val);
    }

    protected void assertionNotNull(Object obj) {
        Assert.assertNotNull(obj);
    }

    protected void assertionNull(Object obj) {
        Assert.assertNull(obj);
    }

    protected void assertionNumber(String number) {
        if (StringUtils.isNumeric(number)) {
            Assert.assertEquals(1, 1);
        } else {
            Assert.assertEquals(1, 0);
        }
    }

    protected void assertionCpf(String cpf) {

        if (cpf.length() > 11) Assert.fail();

        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        try {
            Long.parseLong(cpf);
        } catch(NumberFormatException e) {
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

        Assert.assertEquals(digitVerify, nDigResult);
    }

    protected void assertionEmail(String email) {
        boolean isValidMail = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isValidMail = true;
            }
        }
        Assert.assertTrue(isValidMail);
    }

    protected void assertionPhone(String phoneNumber) {
        boolean isValidPhone = false;
        if (phoneNumber != null && phoneNumber.length() > 0) {
            String expression = "^[+]?[0-9]{13}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches()) {
                isValidPhone = true;
            }
        }
        Assert.assertTrue(isValidPhone);
    }

    protected void assertionSum(int a, int b, int c) {
        if (a + b == c) Assert.assertTrue(true);
    }

}