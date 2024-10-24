package com.huntercodexs.codexstester.resource.format;

import com.huntercodexs.codexstester.resource.enumerator.CodexsUfTable;

import java.text.DecimalFormat;

import static com.huntercodexs.codexstester.resource.basic.CodexsStringHandler.repeat;

public class CodexsFormatter {

    public static String numberFormatter(int input, String format) {
        return String.format(format, input);
    }

    public static String stringFormatter(String input, String format) {
        return String.format(format, input);
    }

    public static String fillerFormatter(String input, String fill, String align, int size) {

        if (!align.equals("left") && !align.equals("right")) {
            System.out.println("Error: use left or right to param [align]");
            return null;
        }

        if (size < 0) {
            System.out.println("Error: use size > 0");
            return null;
        }

        String formatted = input;

        int lenValue = input.length();
        int lenFill = size - lenValue;
        String repeat = repeat(fill, lenFill);

        if (align.equals("left")) {
            formatted = input + repeat;
        } else {
            formatted = repeat + input;
        }

        return formatted;
    }

    public static String rgFormatter(String value, String rgUf, boolean rgPrefix) {
        if (value == null || value.isEmpty()) return "";
        if (rgUf == null) rgUf = "";
        if (rgUf.isEmpty()) rgUf = "";
        if (rgUf.length() == 1) rgUf = "";

        rgUf = rgUf.replaceAll("[^A-Z]+", "");

        if (!CodexsUfTable.checkUfExists(rgUf) && !CodexsUfTable.checkRgSspExists(rgUf)) {
            rgUf = "";
        }

        rgUf = rgUf.replaceAll("SSP", "").replaceAll("SP", "");

        if (value.equals("0")) return "";

        if (rgPrefix) {
            return "RG" + value.replaceAll("[^0-9]+", "") + rgUf;
        }

        return value.replaceAll("[^0-9]+", "");
    }

    public static String cpfFormatter(String cpf) {
        return cpf
                .replaceAll("[^0-9]", "")
                .replaceAll("^(\\d{3})(\\d{3})(\\d{3})(\\d{2})$", "$1.$2.$3-$4");
    }

    public static String cnpjFormatter(String cnpj) {
        return cnpj
                .replaceAll("[^0-9]", "")
                .replaceAll("^(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})$", "$1.$2.$3/$4-$5");

    }

    public static String moneyFormatter(Object value, String format) {

        int valueFix = Integer.parseInt(value.toString().replaceAll("[^0-9]", ""));
        DecimalFormat formatter;

        switch (format) {

            case "real":

                formatter = new DecimalFormat("R$ #,##0.00");
                return formatter.format(valueFix)
                        .replaceAll("\\.", "+")
                        .replaceAll(",", ".")
                        .replaceAll("\\+", ",");

            case "dollar":

                formatter = new DecimalFormat("$ #,##0.00");
                return formatter.format(valueFix)
                        .replaceFirst("(.*)(\\.)([0-9]{2})", "$1+$3")
                        .replaceAll("\\.", ",")
                        .replaceAll("\\+", ".");

            case "euro":

                String euroChar = "€"; //1 234,56 €

                formatter = new DecimalFormat("###,###,###,###,##0.00");

                String euroAmount = formatter
                        .format(valueFix)
                        .replaceFirst("(.*)(\\.)([0-9]{2})", "$1+$3")
                        .replaceAll(",", " ")
                        .replaceAll("\\+", ",");

                return euroAmount + " " + euroChar;

            default:
                throw new RuntimeException("[ERROR] Wrong Money Format: " + format);
        }
    }

    public static String dateFormatter(String dateInput, String dateFormat) {

        String separator = "-";
        String replacement = "";
        boolean reverseDate = false;
        boolean cut2Digits = false;
        boolean gmt = false;
        boolean onlyNumbers = false;
        boolean includeHour = false;
        boolean includeMinutes = false;
        boolean includeSeconds = false;
        boolean includeMilliSeconds = false;
        String dateOutput = dateInput.trim();

        /*Formatter*/
        String formatter = dateFormat.trim()
                .replaceAll("[/-]", "([-/])?")
                .replaceAll("[ TZ]", "([ ])?")
                .replaceAll(":", "([:])?")
                .replaceAll("\\.", "([.])?")
                .trim();

        /*Separator*/
        if (dateFormat.contains("/")) {
            separator = "/";
        } else if (dateFormat.contains("-")) {
            separator = "-";
        } else {
            separator = "";
        }

        /*GMT*/
        if (dateOutput.contains("T") && dateOutput.contains("Z")) {
            gmt = true;
            dateOutput = dateOutput.replaceAll("[TZ]", " ").trim();
        }

        /*Only Numbers*/
        if (separator.isEmpty()) {
            onlyNumbers = true;
            dateOutput = dateOutput.replaceAll("[^0-9]+", "");
            formatter = dateFormat.trim();
        }

        /*Default Replacement*/
        replacement = "$1" + separator + "$3" + separator + "$5";

        /*Date*/
        if (dateFormat.startsWith("dd")) {

            formatter = formatter
                    .replaceAll("dd", "([0-9]{2})")
                    .replaceAll("MM", "([0-9]{2})");

            if (
                    (dateFormat.startsWith("dd-MM-yy ") || dateFormat.endsWith("dd-MM-yy")) ||
                    (dateFormat.startsWith("dd/MM/yy ") || dateFormat.endsWith("dd/MM/yy")) ||
                    (dateFormat.startsWith("ddMMyy") && dateFormat.endsWith("ddMMyy")) ||
                    (dateFormat.startsWith("ddMMyyHH") || dateFormat.startsWith("yyMMddHH"))
            ) {
                formatter = formatter.replaceAll("(yy)", "([0-9]{2})");
                cut2Digits = true;
            } else {
                formatter = formatter.replaceAll("(yyyy|yyy)", "([0-9]{4})");
            }

            reverseDate = true;

        } else {

            if (dateFormat.startsWith("yy-") || dateFormat.startsWith("yy/") || dateFormat.startsWith("yyMMdd")) {
                formatter = formatter.replaceAll("(yy)", "([0-9]{2})");
                cut2Digits = true;
            } else {
                formatter = formatter.replaceAll("(yyyy|yyy)", "([0-9]{4})");
            }

            formatter = formatter
                    .replaceAll("MM", "([0-9]{2})")
                    .replaceAll("dd", "([0-9]{2})");

        }

        /*Time*/
        if (dateFormat.contains("HH:mm:ss.ms")) {
            formatter = formatter.replaceAll("ms", "([0-9]{1,3})?");
            replacement = replacement + "$6$7$8$9$10$11$12$13";
            includeMilliSeconds = true;

        } else if (dateFormat.contains("HH:mm:ss")) {
            formatter = formatter.replaceAll("ss", "([0-9]{2})?");
            replacement = replacement + "$6$7$8$9$10$11";
            includeSeconds = true;

        } else if (dateFormat.contains("HH:mm")) {
            formatter = formatter.replaceAll("mm", "([0-9]{2})?");
            replacement = replacement + "$6$7$8";
            includeMinutes = true;

        } else if (dateFormat.contains("HHmmssms")) {
            formatter = formatter.replaceAll("ms", "([0-9]{1,3})?");
            replacement = replacement + "$6$7$8$9$10$11";
            includeMilliSeconds = true;

        } else if (dateFormat.contains("HHmmss")) {
            formatter = formatter.replaceAll("ss", "([0-9]{2})?");
            replacement = replacement + "$6$7$8$9";
            includeSeconds = true;

        } else if (dateFormat.contains("HHmm")) {
            formatter = formatter.replaceAll("mm", "([0-9]{2})?");
            replacement = replacement + "$6$7";
            includeMinutes = true;

        } else if (dateFormat.contains("HH")) {
            formatter = formatter.replaceAll("HH", "([0-9]{2})?");
            replacement = replacement + "$6$7";
            includeHour = true;
        }

        /*Output*/
        if (reverseDate) {

            String dateTmp;
            String hourTmp;

            if (onlyNumbers) {
                dateTmp = dateOutput.substring(0, 8);
                hourTmp = dateOutput.substring(8);
            } else {
                dateTmp = dateOutput.split(" ")[0].replaceAll("[^0-9]", "");
                hourTmp = dateOutput.split(" ")[1];
            }

            String day = dateTmp.substring(6, 8);
            String month = dateTmp.substring(4, 6);
            String year = dateTmp.substring(0, 4);

            if (hourTmp != null &&! hourTmp.isEmpty()) {
                if (!onlyNumbers) {
                    if (includeMilliSeconds) {
                        hourTmp = hourTmp;
                    } else if (includeSeconds) {
                        hourTmp = hourTmp.split("\\.")[0];
                    } else if (includeMinutes) {
                        hourTmp = hourTmp.split("\\.")[0].replaceAll(":[0-9]{2}$", "");
                    } else if (includeHour) {
                        hourTmp = hourTmp.split("\\.")[0].replaceAll(":[0-9]{2}:[0-9]{2}$", "");
                    } else {
                        hourTmp = "";
                    }
                } else {
                    if (includeMilliSeconds) {
                        hourTmp = hourTmp.replaceAll("[^0-9]+", "");
                    } else if (includeSeconds) {
                        hourTmp = hourTmp.substring(0, 6);
                    } else if (includeMinutes) {
                        hourTmp = hourTmp.substring(0, 4);
                    } else if (includeHour) {
                        hourTmp = hourTmp.substring(0, 2);
                    } else {
                        hourTmp = "";
                    }
                }
            }

            if (cut2Digits) {
                year = year.substring(2, 4);
            }

            dateOutput = day+separator+month+separator+year+" "+hourTmp;

        } else if (cut2Digits) {

            String dateTmp;
            String hourTmp;

            if (onlyNumbers) {
                dateTmp = dateOutput.substring(0, 8);
                hourTmp = dateOutput.substring(8);
            } else {
                dateTmp = dateOutput.split(" ")[0].replaceAll("[^0-9]", "");
                hourTmp = dateOutput.split(" ")[1];
            }

            String year = dateTmp.substring(0, 4).substring(2, 4);
            String day = dateTmp.substring(6, 8);
            String month = dateTmp.substring(4, 6);

            if (hourTmp != null &&! hourTmp.isEmpty()) {
                if (!onlyNumbers) {
                    if (includeMilliSeconds) {
                        hourTmp = hourTmp;
                    } else if (includeSeconds) {
                        hourTmp = hourTmp.split("\\.")[0];
                    } else if (includeMinutes) {
                        hourTmp = hourTmp.split("\\.")[0].replaceAll(":[0-9]{2}$", "");
                    } else if (includeHour) {
                        hourTmp = hourTmp.split("\\.")[0].replaceAll(":[0-9]{2}:[0-9]{2}$", "");
                    } else {
                        hourTmp = "";
                    }
                } else {
                    if (includeMilliSeconds) {
                        hourTmp = hourTmp.replaceAll("[^0-9]+", "");
                    } else if (includeSeconds) {
                        hourTmp = hourTmp.substring(0, 6);
                    } else if (includeMinutes) {
                        hourTmp = hourTmp.substring(0, 4);
                    } else if (includeHour) {
                        hourTmp = hourTmp.substring(0, 2);
                    } else {
                        hourTmp = "";
                    }
                }
            }

            dateOutput = year+separator+month+separator+day+" "+hourTmp;

        } else {

            String dateTmp;
            String hourTmp;

            if (onlyNumbers) {
                dateTmp = dateOutput.substring(0, 8);
                hourTmp = dateOutput.substring(8);
            } else {
                dateTmp = dateOutput.split(" ")[0];
                hourTmp = dateOutput.split(" ")[1];
            }

            if (hourTmp != null && !hourTmp.isEmpty()) {
                if (!onlyNumbers) {
                    if (includeMilliSeconds) {
                        hourTmp = hourTmp;
                    } else if (includeSeconds) {
                        hourTmp = hourTmp.split("\\.")[0];
                    } else if (includeMinutes) {
                        hourTmp = hourTmp.split("\\.")[0].replaceAll(":[0-9]{2}$", "");
                    } else if (includeHour) {
                        hourTmp = hourTmp.split("\\.")[0].replaceAll(":[0-9]{2}:[0-9]{2}$", "");
                    } else {
                        hourTmp = "";
                    }
                } else {
                    if (includeMilliSeconds) {
                        hourTmp = hourTmp.replaceAll("[^0-9]+", "");
                    } else if (includeSeconds) {
                        hourTmp = hourTmp.substring(0, 6);
                    } else if (includeMinutes) {
                        hourTmp = hourTmp.substring(0, 4);
                    } else if (includeHour) {
                        hourTmp = hourTmp.substring(0, 2);
                    } else {
                        hourTmp = "";
                    }
                }
            }

            dateOutput = dateTmp.replaceAll("[/-]", separator)+" "+hourTmp;

        }

        dateOutput = dateOutput.replaceAll("^" + formatter + "$", replacement).trim();

        if (gmt && (includeSeconds || includeMilliSeconds)) {
            dateOutput = (dateOutput.replaceAll(" ", "T")+"Z").replaceAll("TZ$", "");
        }

        if (onlyNumbers) {
            dateOutput = dateOutput.replaceAll("[^0-9]", "");
        }

        return dateOutput;

    }

}
