package com.huntercodexs.codexstester.resource.basic;

import com.huntercodexs.codexstester.resource.quickjson.QuickJson;
import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class CodexsStringHandler {

    private int index = 0;

    public void setIndex(int index) {
        this.index = index;
    }

    public static int stringCounter(String input, String pattern) {

        int counter = 0;

        try {

            while (true) {

                String begin = input.replaceFirst(
                        pattern, "#<" + counter + "#{{{ REPLACED }}}#" + counter + ">#");

                String result = StringUtils
                        .substringBetween(begin, "#<" + counter + "#", "#" + counter + ">#")
                        .trim();

                counter+=1;

                if (result.isEmpty()) break;
                input = begin;

            }

            return counter;

        } catch (Exception ex) {
            System.out.println("Exception during stringExtractor: " + ex.getMessage());
            return counter;
        }

    }

    public static String repeat(String str, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(str);
        }
        return String.valueOf(stringBuilder);
    }

    public static String reverse(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = str.length()-1; i >= 0; i--) {
            stringBuilder.append(str.charAt(i));
        }
        return String.valueOf(stringBuilder);
    }

    public static String ucFirst(String input) {
        if (input == null || input.isEmpty()) return "";
        if (input.matches("^[0-9].*$")) return input;
        if (input.length() == 1) return input.substring(0, 1).toUpperCase();
        String uc = input.substring(0, 1).toUpperCase();
        String rest = input.substring(1);
        return uc+rest;
    }

    public static String queryStringBuilder(Object input) {
        String[] splitter = input.toString().split("},");
        StringBuilder queryBuilder = new StringBuilder();

        for (String s : splitter) {
            String tmp = s.replaceAll("[]}{\\[\"']", "")
                    .replaceAll(", ", "&")
                    .replaceAll(",", "&")
                    .replaceAll(":", "=")
                    .replaceAll("= ", "=")+"&";
            queryBuilder.append(tmp);
        }

        return String.valueOf(queryBuilder).replaceAll("&$", "").trim();
    }

    public static Object getDataFromQueryString(String queryString, String field) {

        String str = (queryString.split(field+"=")[1]);

        if (str.contains("&")) {
            str = str.split("&")[0];
        }

        return str;
    }

    public static String queryStringToJson(String input) {

        String[] splitter = input.split("&");
        QuickJson quickJson = new QuickJson();

        for (String split : splitter) {
            String[] splitter2 = split.split("=");
            quickJson.add(splitter2[0].trim(), splitter2[1].trim());
        }

        return quickJson.json();

    }

    public static String stringToJson(String str) {

        QuickJson quickJson = new QuickJson();
        String strClean = str.replaceAll("([\"{\\[\\]}'/\\\\]+)", "");

        try {
            String[] splitter = strClean.split(",");

            for (String split : splitter) {
                String[] splitter2 = split.split(":");
                quickJson.add(splitter2[0].trim(), splitter2[1].trim());
            }
        } catch (Exception e) {
            try {
                String[] splitter = strClean.split(":");
                quickJson.add(splitter[0].trim(), splitter[1].trim());
            } catch (Exception er) {
                quickJson.add("message", null);
            }
        }

        return quickJson.json();
    }

    public static String sanitizeAsciiCaseSensitive(String input, String letterType) {
        if (letterType == null) letterType = "";
        try {
            if (letterType.endsWith("upper")) {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toUpperCase();
            } else if (letterType.endsWith("lower")) {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
            } else {
                return Normalizer.normalize(input, Normalizer.Form.NFD)
                        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
            }
        } catch (RuntimeException re) {
            throw new RuntimeException(re.getMessage());
        }
    }

    public static String sanitizeAscii(String input) {
        try {
            return Normalizer.normalize(input, Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "");
        } catch (RuntimeException re) {
            throw new RuntimeException(re.getMessage());
        }
    }

    public static String queryExtractor(String input, int begin, int end) {
        if (begin > input.length() || end > input.length()) {
            return input;
        }
        return input.substring(begin, end);
    }

    public String replaceIndexing(
            String input,
            String target,
            String replacement,
            String separator,
            boolean useIndex
    ) {
        int index = this.index;
        String replace;

        if (separator == null) separator = "";

        while (true) {

            if (useIndex) {
                replace = input.replaceFirst(target, replacement+"_"+index+separator);
                index++;
            } else {
                replace = input.replaceFirst(target, replacement+separator);
            }

            if (!replace.contains(target)) {
                break;
            }

            input = replace;

        }

        this.index = index;

        return replace;
    }

    public static String stringExtractor(String input, String clear, String pattern, String replacer, int index) {

        try {

            String begin = input.replaceAll(pattern, "#<" + index + "#" + replacer + "#" + index + ">#");
            String extract = begin.replaceAll(", ", " ");

            return StringUtils
                    .substringBetween(extract, "#<" + index + "#", "#" + index + ">#")
                    .replaceAll(clear + ":", "").trim();

        } catch (Exception ex) {
            System.out.println("Exception during stringExtractor: " + ex.getMessage());
            return "";
        }

    }

    public static String extractByPattern(String input, String field, String useChars) {

        try {

            if (useChars == null) {
                useChars = "";
            }

            String qty = "";
            if (useChars.equals(" ")) {
                qty = "{1,} ";
            }

            String begin = input
                    .replaceAll(
                            "("+field+": ?)([-)(}{\\]\\[@#%_.0-9a-zA-Z"+useChars+"]+)"+qty,
                            "#<1#" + field+":$2" + "#1>#");

            String extract = begin.replaceAll(", ", " ");

            return StringUtils
                    .substringBetween(extract, "#<1#", "#1>#")
                    .replaceAll(field + ":", "")
                    .replaceAll("\"", "")
                    .trim();

        } catch (Exception ex) {
            System.out.println("Exception during alphaFieldPattern: " + ex.getMessage());
            return "";
        }

    }

    public static String stringList(List<String> items, String clear) {

        int i = 0;
        StringBuilder result = new StringBuilder();

        for (String current : items) {

            result.append(current.replaceAll(clear, "").replaceAll(",", ""));

            if (i < items.size()-1) {
                result.append(",");
            }

            i++;
        }

        return String.valueOf(result);
    }

    public static String listExtractor(List<String> items, String detail, String clear, String pattern, String replacer) {

        int i = 0;
        StringBuilder result = new StringBuilder();

        for (String current : items) {

            String item = current
                    .replaceAll(clear, "")
                    .replaceAll(pattern, "#<"+i+"#"+replacer+"#"+i+">#");

            result.append(
                    StringUtils.substringBetween(item, "#<"+i+"#", "#"+i+">#")
                            .replaceAll(detail+":", ""));

            if (i < items.size()-1) {
                result.append(",");
            }

            i++;
        }

        return String.valueOf(result);
    }

    public static List<String> listClear(List<String> items, String replace, String replacement) {
        List<String> result = new ArrayList<>();
        for (String current : items) {
            result.add(current.replaceAll(replace, replacement));
        }
        return result;
    }

    public static String alphaFieldPattern(String input, String field, String useChars) {

        try {

            if (useChars == null) {
                useChars = "";
            }

            String qty = "";
            if (useChars.equals(" ")) {
                qty = "{1,} ";
            }

            String pattern = "("+field+")(:)( ?)([-)(}{\\]\\[/@#%_.0-9a-zA-Z"+useChars+"]+)"+qty;
            String replacement = "#<1#{{{REPLACE}}}:$4#1>#";
            String begin = input.replaceAll(pattern, replacement);
            String extract = begin.replaceAll(", ", " ");

            return StringUtils
                    .substringBetween(extract, "#<1#", "#1>#")
                    .replaceAll("\\{\\{\\{REPLACE}}}:", "")
                    .replaceAll("\"", "")
                    .trim();

        } catch (Exception ex) {
            System.out.println("Exception during alphaFieldPattern: " + ex.getMessage());
            return "";
        }

    }
}
