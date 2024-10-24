package com.huntercodexs.codexstester.resource.quickjson.core;

import static com.huntercodexs.codexstester.resource.quickjson.core.QuickJsonAbstract.*;

public class QuickJsonExtractor {

    private int position;
    private int length;
    private int total;

    private int jsonOpenCounter = 0;
    private int arrayOpenCounter = 0;

    private String json = "";
    private String ch4r = "";
    private String prevChar = "";
    private StringBuilder result = new StringBuilder();

    public QuickJsonExtractor() {
    }

    private void reset() {
        this.json = "";
        this.ch4r = "";
        this.prevChar = "";
        this.jsonOpenCounter = 0;
        this.arrayOpenCounter = 0;
        this.result = new StringBuilder();
    }

    private boolean jsonOk(String json, String field) {
        return json != null && !json.isEmpty() && json.matches("^.*(\"" + field + "\":).*$");
    }

    private boolean fieldOk(String field) {
        return field != null && !field.isEmpty();
    }

    private String jsonFilter(String json, String field) {
        return json
                .replaceAll("\n", "")
                .replaceAll("\r", "")
                .replaceAll("\t", "")

                .replaceAll("\", ([0-9])", "\",$1")
                .replaceAll("([0-9]), \"", "$1,\"")

                .replaceAll("\", \"", "\",\"")
                .replaceAll("\", \\[", "\",[")
                .replaceAll("\", \\{", "\",{")

                .replaceAll("\": \"", "\":\"")
                .replaceAll("\": \\[", "\":[")
                .replaceAll("\": \\{", "\":{")

                .replaceAll("], \"", "],\"")
                .replaceAll("], \\[", "],[")
                .replaceAll("], \\{", "],{")

                .replaceAll("}, \"", "},\"")
                .replaceAll("}, \\[", "},[")
                .replaceAll("}, \\{", "},{")

                .replaceAll(JSON_FIELD_REGEXP[0].replaceFirst(TARGET, field), JSON_FIELD_REGEXP[1]);
    }

    private void arrayExtract() {

        for (int i = this.total; i < this.json.length(); i++) {

            this.ch4r = String.valueOf(json.charAt(i));

            if (this.ch4r.equals("[") && !this.prevChar.equals("\\")) {
                this.arrayOpenCounter += 1;
            } else if (this.ch4r.equals("]") && !this.prevChar.equals("\\")) {
                this.arrayOpenCounter -= 1;
            }

            if (this.arrayOpenCounter == 0) {
                this.result.append(this.json.charAt(i));
                break;
            }

            this.result.append(this.json.charAt(i));
            this.prevChar = this.ch4r;

        }

        if (this.arrayOpenCounter > 0) {
            throw new RuntimeException("Critical Error - Invalid Array: " + this.result);
        }

    }

    private void jsonExtract() {

        for (int i = this.total; i < this.json.length(); i++) {

            this.ch4r = String.valueOf(this.json.charAt(i));

            if (this.ch4r.equals("{") && !this.prevChar.equals("\\")) {
                this.jsonOpenCounter += 1;
            } else if (this.ch4r.equals("}") && !this.prevChar.equals("\\")) {
                this.jsonOpenCounter -= 1;
            }

            if (this.jsonOpenCounter == 0) {
                this.result.append(this.json.charAt(i));
                break;
            }

            this.result.append(this.json.charAt(i));
            this.prevChar = this.ch4r;

        }

        if (this.arrayOpenCounter > 0) {
            throw new RuntimeException("Critical Error - Invalid JSON: " + this.result);
        }

    }

    private void strExtract() {

        for (int i = this.total; i < this.json.length(); i++) {

            this.ch4r = String.valueOf(this.json.charAt(i));

            if (i > this.total && this.ch4r.equals("\"") && !this.prevChar.equals("\\")) {
                break;
            }

            if (!this.ch4r.equals("\"") && !this.ch4r.equals("\\") || this.prevChar.equals("\\")) {
                this.result.append(this.json.charAt(i));
            }

            this.prevChar = this.ch4r;

        }

    }

    private void intExtract() {

        for (int i = this.total; i < this.json.length(); i++) {

            this.ch4r = String.valueOf(this.json.charAt(i));

            if (!this.ch4r.matches("^[0-9]$")) {
                break;
            }

            this.result.append(this.json.charAt(i));

        }

    }

    public Object standardExtraction(Object jsonObj, String field) {

        String[] split;
        String json = String.valueOf(jsonObj);

        String subArray1Extract = json
                .replaceFirst(SUB_ARRAY1_REGEXP[0].replaceFirst(TARGET, field), SUB_ARRAY1_REGEXP[1]);

        split = subArray1Extract.split("\\{@EXTRACT}");

        if (split.length > 1 && !split[1].isEmpty()) {
            return split[1];
        }

        String subArrayExtract = json
                .replaceFirst(SUB_ARRAY2_REGEXP[0].replaceFirst(TARGET, field), SUB_ARRAY2_REGEXP[1]);

        split = subArrayExtract.split("\\{@EXTRACT}");

        if (split.length > 1 && !split[1].isEmpty()) {
            return split[1];
        }

        String jsonExtract = json
                .replaceFirst(JSON_REGEXP[0].replaceFirst(TARGET, field), JSON_REGEXP[1]);

        split = jsonExtract.split("\\{@EXTRACT}");

        if (split.length > 1 && !split[1].isEmpty()) {
            return split[1].replaceFirst("(},.*)", "}");
        }

        String arrayExtract = json
                .replaceFirst(ARRAY_REGEXP[0].replaceFirst(TARGET, field), ARRAY_REGEXP[1]);

        split = arrayExtract.split("\\{@EXTRACT}");

        if (split.length > 1 && !split[1].isEmpty()) {
            return split[1];
        }

        String dataExtract = json.replaceFirst(STR_REGEXP[0].replaceFirst(TARGET, field), STR_REGEXP[1]);

        split = dataExtract.split("\\{@EXTRACT}");

        if (split.length > 1) {
            return split[1]
                    .replaceFirst("}$", "")
                    .replaceFirst("^\\{", "")
                    .replaceFirst("(\".*)", "")
                    .trim();
        }

        return null;

    }

    public Object smartExtraction(Object jsonObj, String field) {

        this.reset();

        String json = String.valueOf(jsonObj);

        if (!jsonOk(json, field) || !fieldOk(field)) return "";

        this.json = jsonFilter(json, field);
        this.position = this.json.indexOf("\""+field+"\":");
        this.length = ("\""+field+"\":").length();

        /*
         * When not is primary field in the JSON or String, for example:
         * Suppose that you are trying to retrieve one field called "name", and the JSON String is:
         *
         * {"person":{"name":"Mary Viz"}}
         *
         * According to the string above the field "name" is not a primary field, so you need to get
         * the value from "person" field firstly and so get the "name" inside the value from "person"
         */
        if (this.position > 1 && String.valueOf(this.json.charAt(this.position -1)).equals("{")) {
            this.position = this.json.indexOf(",\""+field+"\":");
            this.length = (",\""+field+"\":").length();
        }

        this.total = this.position + this.length;
        this.ch4r = String.valueOf(this.json.charAt(this.total));

        //Array
        if (this.ch4r.equals("[")) {
            arrayExtract();
        }
        //JSON
        else if (this.ch4r.equals("{")) {
            jsonExtract();
        }
        //String
        else if (this.ch4r.equals("\"")) {
            strExtract();
        }
        //Integer
        else if (this.ch4r.matches("^[0-9]$")) {
            intExtract();
        }

        return String.valueOf(this.result);
    }

}
