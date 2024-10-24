package com.huntercodexs.codexstester.resource.stdout;

import java.util.List;

public class CodexsStdout {

    private int width = 100;
    private int widthKeyName = 20;
    private int widthTitle = 70;
    private int widthValue = 20;
    private int widthDesc = 48;

    public CodexsStdout() {}

    private static String repeat(String str, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stringBuilder.append(str);
        }
        return String.valueOf(stringBuilder);
    }

    private String format(String data, String ch, int len) {
        int titleLength = data.length();

        if (titleLength == len) return data;

        for (int i = 0; i < len; i++) {
            data += ch;
            if (data.length() == len) {
                break;
            }
        }

        if (data.length() > len) {
            data = data.substring(0, len);
        }

        return data;
    }

    private void drawLine() {
        System.out.println("|"+repeat("-", this.width)+"|");
    }

    private void drawEmptyLine() {
        System.out.println("|"+repeat(" ", this.width)+"|");
    }

    private void drawTitle(String title) {
        System.out.println("| "+format(title, " ", (this.width-2))+" |");
    }

    private void drawItemTitle(String keyname, String value) {
        System.out.println(
                "| # | "+format(keyname, " ", this.widthKeyName)+
                        " | "+format(value, " ", this.widthTitle+1)+
                        " |");
    }

    private void drawItemHeaderFields() {
        System.out.println(
                "|   | "+format("Name", " ", this.widthKeyName)+
                        " | "+format("Value", " ", this.widthValue)+
                        " | "+format("Description", " ", this.widthDesc)+
                        " |");
    }

    private void drawItemLine(String id, String keyname, String value, String description) {
        String index;

        if (id.equals(" ")) {
            index = String.format("%3s", id);
        } else {
            index = String.format("%03d", Integer.parseInt(id));
        }

        System.out.println(
                "|"+index+
                        "| "+format(keyname, " ", this.widthKeyName)+
                        " | "+format(value, " ", this.widthValue)+
                        " | "+format(description, " ", this.widthDesc)+
                        " |");
    }

    public void addWidth(int width) {

        if (width < 4) {
            width = 0;
        }

        if (width % 2 != 0) {
            width -= 1;
        }

        int widthCalc = width/2;
        int widthKeyValue = (int) (double) (widthCalc / 2);

        if (widthCalc % 2 != 0) {
            widthCalc += 1;
        }

        this.width += width;
        this.widthDesc += widthCalc;
        this.widthTitle += (widthCalc+widthKeyValue);
        this.widthKeyName += widthKeyValue;
        this.widthValue += widthKeyValue;

    }

    public void drawHeader(String title) {
        drawLine();
        drawEmptyLine();
        drawTitle(title);
        drawEmptyLine();
    }

    public void drawItemHeader(String keyname, String value) {
        drawLine();
        drawItemTitle(keyname, value);
        drawLine();
        drawItemHeaderFields();
    }

    public void drawItemId(String id, String keyname, String value, String description) {
        if (value == null || value.equals("null")) value = "";
        if (description == null || description.equals("null")) description = "";
        drawLine();
        drawItemLine(id, keyname, value, description);
    }

    public void drawItem(String keyname, String value, String description) {
        if (value == null || value.equals("null")) value = "";
        if (description == null || description.equals("null")) description = "";
        if (!(value.isEmpty() && description.isEmpty())) {
            drawItemLine(" ", keyname, value, description);
        }
    }

    public void nextItem() {
        drawLine();
    }

    public void nextItemHeader() {
        drawEmptyLine();
    }

    public static void matrixPrinter(List<List<String>> matrix, int columnSize) {
        if (columnSize == 1) {
            System.out.println("MATRIX PRINTER SAY: [ERROR] MATRIX IS NOT A MATRIX (3X3)");
            return;
        }

        System.out.println("[MATRIX PRINTER]");
        for (List<String> line : matrix) {
            System.out.print("[");
            int columnCounter = 0;

            for (String column : line) {

                if (columnSize > 1) {
                    if (columnCounter < line.size()-1) {
                        System.out.print(column.substring(0, columnSize) + ", ");
                    } else {
                        System.out.print(column.substring(0, columnSize));
                    }
                } else {
                    if (columnCounter < line.size()-1) {
                        System.out.print(column + ", ");
                    } else {
                        System.out.print(column);
                    }
                }

                columnCounter++;
            }
            System.out.println("]");
        }
    }

    public static void objectMatrixPrinter(Object[][] object, int columnSize) {
        if (columnSize == 1) {
            System.out.println("MATRIX PRINTER SAY: [ERROR] MATRIX IS NOT A MATRIX (3X3)");
            return;
        }

        System.out.println("[MATRIX PRINTER - OBJECT]");
        for (Object[] row : object) {

            int colCtrl = 0;
            System.out.print("[");

            for (Object col : row) {
                if (colCtrl < (columnSize - 1)) {
                    System.out.print(col + ", ");
                } else {
                    System.out.print(col + "");
                }
                colCtrl += 1;
            }

            System.out.print("]\n");
        }

    }

}
