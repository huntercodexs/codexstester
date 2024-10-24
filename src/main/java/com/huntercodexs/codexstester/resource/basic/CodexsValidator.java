package com.huntercodexs.codexstester.resource.basic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodexsValidator {

    private static String phoneRegex(String country) {
        switch (country) {
            case "us":
                return "^[+]?1[0-9]{2}9?[0-9]{8}$";
            case "br":
                return "^[+]?55[0-9]{2}9?[0-9]{8}$";
            default:
                throw new RuntimeException("Invalid Country ID");
        }
    }

    public static boolean cpfValidator(String cpf) {

        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() > 11) return false;

        try {
            Long.parseLong(cpf);
        } catch(NumberFormatException e) {
            return false;
        }

        int cpfDigit;
        int d1 = 0, d2 = 0;
        int digit1, digit2, rest = 0;
        String nDigResult;

        for (int n = 1; n < cpf.length() - 1; n++) {
            cpfDigit = Integer.parseInt(cpf.substring(n - 1, n));
            d1 = d1 + ((11 - n) * cpfDigit);
            d2 = d2 + ((12 - n) * cpfDigit);
        }

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

        String digitVerify = cpf.substring(cpf.length() - 2);
        nDigResult = String.valueOf(digit1) + String.valueOf(digit2);

        return digitVerify.equals(nDigResult);
    }

    public static boolean mailValidator(String email) {
        boolean isValidMail = false;
        if (email != null && !email.isEmpty()) {
            String expression = "^[\\w.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email.trim());
            if (matcher.matches()) {
                isValidMail = true;
            }
        }
        return isValidMail;
    }

    public static boolean phoneValidator(String phoneNumber, String country) {
        boolean isValidPhone = false;

        String expression = phoneRegex(country);

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (matcher.matches()) {
                isValidPhone = true;
            }
        }
        return isValidPhone;
    }

    public static boolean cvvValidator(String cvv) {
        return (cvv.matches("^[0-9]{3}$"));
    }

    public static boolean cardNumberValidator(String cardNumber) {
        return (cardNumber.matches("[0-9]{4}-[0-9]{4}-[0-9]{4}-[0-9]{4}"));
    }

}
