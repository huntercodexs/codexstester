package com.huntercodexs.codexstester.resource.web;

public abstract class CodexsWebElements {

    public static String inputElement(String id) {
        return "//input[@id='"+id+"']";
    }

    public static String textElement(String id) {
        return  "//textarea[@id='"+id+"']";
    }

    public static String buttonElement(String id) {
        return  "//button[@id='"+id+"']";
    }

    public static String aElement(String text) {
        return "//a[text()='"+text+"']";
    }

    public static String divElement(String id) {
        return "//div[@id='"+id+"']";
    }

    public static String pTextElement(String text) {
        return "//p[text()='"+text+"']";
    }

    public static String pElement(String id) {
        return "//p[@id='"+id+"']";
    }
}
