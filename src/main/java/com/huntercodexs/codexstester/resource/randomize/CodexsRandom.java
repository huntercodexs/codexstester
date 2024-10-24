package com.huntercodexs.codexstester.resource.randomize;

import com.huntercodexs.codexstester.resource.quickjson.QuickJson;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import static com.huntercodexs.codexstester.resource.basic.CodexsTools.md5;
import static com.huntercodexs.codexstester.resource.currency.CodexsCurrency.*;

public class CodexsRandom {

    private static void sleep() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int randomNumber(int digits) {
        Date date = new Date();
        String number = md5(String.valueOf(date.getTime())).replaceAll("[^0-9]", "");
        if (number.startsWith("0")) {
            number = String.valueOf(randomNumber(digits));
        }
        String randomNumber = (number + number).substring(0, digits);
        return Integer.parseInt(randomNumber);
    }

    public static String randomMoney(int digits, String currency) {
        Date date = new Date();
        String number = md5(String.valueOf(date.getTime())).replaceAll("[^0-9]", "");
        if (number.startsWith("0")) {
            number = String.valueOf(randomNumber(digits));
        }

        String value = (number + number).substring(0, digits);

        switch (currency) {
            case "real":
                return currencyFormatReal(value);
            case "euro":
                return currencyFormatEuro(value, false);
            default:
                return currencyFormatDollar(value);
        }
    }

    public static String randomCents(String currency) {
        Date date = new Date();
        String number = md5(String.valueOf(date.getTime())).replaceAll("[^0-9]", "");
        if (number.startsWith("0")) {
            number = String.valueOf(randomNumber(2));
        }

        String value = (number + number).substring(0, 2);

        switch (currency) {
            case "real":
                return "R$ 0,"+String.format("%02d", Integer.parseInt(value));
            case "euro":
                return "0," +String.format("%02d", Integer.parseInt(value))+ " EUR";
            default:
                return "$ 0."+String.format("%02d", Integer.parseInt(value));
        }
    }

    public static String randomCardNumber(String separator) {
        if (separator == null) separator = " ";
        sleep();
        String part1 = String.valueOf(randomNumber(4));
        sleep();
        String part2 = String.valueOf(randomNumber(4));
        sleep();
        String part3 = String.valueOf(randomNumber(4));
        sleep();
        String part4 = String.valueOf(randomNumber(4));
        return part1+separator+part2+separator+part3+separator+part4;
    }

    public static String randomId(String prefix) {
        sleep();
        if (prefix == null) prefix = "";
        return prefix+(randomNumber(6));
    }

    public static String randomGuid() {
        return UUID.randomUUID().toString();
    }

    public static String randomToken(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);
        String alphaNumeric = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < length; i++) {
            stringBuilder.append(alphaNumeric.charAt(secureRandom.nextInt(alphaNumeric.length())));
        }

        return stringBuilder.toString();
    }

    public static String randomJwt() {
        Date date = new Date();
        QuickJson quickJson1 = new QuickJson();
        quickJson1.add("typ", "JWT");
        quickJson1.add("alg", "RS256");

        QuickJson quickJson2 = new QuickJson();
        quickJson2.add("sub", randomGuid());
        quickJson2.add("nbf", date.getTime());
        quickJson2.add("type", "RANDOM");
        quickJson2.add("exp", date.getTime());

        /*TODO: Implement a valid signature*/
        return Base64.getEncoder().encodeToString(quickJson1.json().getBytes(StandardCharsets.UTF_8)) +
                "." +
                Base64.getEncoder().encodeToString(quickJson2.json().getBytes(StandardCharsets.UTF_8)) +
                "." +
                randomToken(64);
    }

    public static String randomHash() {
        sleep();
        Date date = new Date();
        return md5(String.valueOf(date.getTime()));
    }

    public static String randomBinary(int size) {
        sleep();
        Date date = new Date();
        String digits = md5(String.valueOf(date.getTime())).replaceAll("[^01]", "");
        digits += md5(String.valueOf(date.getTime()+8)).replaceAll("[^01]", "");
        digits += md5(String.valueOf(date.getTime()+2)).replaceAll("[^01]", "");
        digits += md5(String.valueOf(date.getTime()+6)).replaceAll("[^01]", "");
        digits += md5(String.valueOf(date.getTime()+4)).replaceAll("[^01]", "");

        if (digits.length() > size) {
            return digits.substring(0, size);
        }

        return digits;
    }

    public static String randomCpf() {
        sleep();
        int cpfNumber = randomNumber(9);
        String[] cpfParts = String.valueOf(cpfNumber).split("(?<=\\G.{" + 3 + "})");

        int cpfDigit;
        int d1 = 0, d2 = 0;
        int digit1, digit2, rest = 0;
        String nDigResult;

        for (int i = 0; i < String.valueOf(cpfNumber).length(); i++) {
            cpfDigit = Integer.parseInt(String.valueOf(String.valueOf(cpfNumber).charAt(i)));
            d1 = d1 + ((11 - (i + 1)) * cpfDigit);
            d2 = d2 + ((12 - (i + 1)) * cpfDigit);
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

        nDigResult = String.valueOf(digit1) + String.valueOf(digit2);

        return cpfParts[0]+"."+cpfParts[1]+"."+cpfParts[2]+"-"+nDigResult;
    }

    public static String randomCnpj() {
        sleep();
        int cnpjInitialNumber = randomNumber(2);
        int cnpjMiddleNumber = randomNumber(6);
        String[] cnpjParts = String.valueOf(cnpjMiddleNumber).split("(?<=\\G.{" + 3 + "})");
        String cnpjThousandInverted = "0001";
        int cnpjDigitNumber = randomNumber(2);
        return cnpjInitialNumber+"."+cnpjParts[0]+"."+cnpjParts[1]+"/"+cnpjThousandInverted+"-"+cnpjDigitNumber;
    }

    public static String randomDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDateTime dateTimeNow = LocalDateTime.now().minusYears(randomNumber(1));
        return dateTimeNow.format(formatter);
    }

    public static String randomDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime dateTimeNow = LocalDateTime.now().minusMinutes(randomNumber(3)).minusYears(randomNumber(1));
        return dateTimeNow.format(formatter);
    }

}
