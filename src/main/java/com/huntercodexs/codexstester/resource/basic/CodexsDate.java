package com.huntercodexs.codexstester.resource.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CodexsDate {

    public static String dateReverse(String inputDate, String separator) {

        if (inputDate == null) return null;

        String[] datetime;
        String date = "";
        String hour = "";
        String day = "";
        String month = "";
        String year = "";
        String format = "en";

        inputDate = inputDate
                .replaceAll("[^0-9/: .-]", " ")
                .replaceAll("[ ]{2,}", " ")
                .trim();

        /*14/07/2023 14:53:25, 14-07-2023 14:53:25*/
        if (inputDate.matches("[0-9]{2}[/-][0-9]{2}[/-][0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2}")) {
            //System.out.println("MATCH 1: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");
            hour = datetime[1];

            day = date.substring(0, 2);
            month = date.substring(2, 4);
            year = date.substring(4, 8);
        }

        /*2023/08/16 16:10:28, 2023-08-16 16:10:28*/
        else if (inputDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}")) {
            //System.out.println("MATCH 2: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");
            hour = datetime[1];

            year = date.substring(0, 4);
            month = date.substring(4, 6);
            day = date.substring(6, 8);
            format = "br";
        }

        /*14/07/2023 14:53:25.333, 14-07-2023 14:53:25.333*/
        else if (inputDate.matches("[0-9]{2}[/-][0-9]{2}[/-][0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})")) {
            //System.out.println("MATCH 3: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");
            hour = datetime[1];

            day = date.substring(0, 2);
            month = date.substring(2, 4);
            year = date.substring(4, 8);
        }

        /*2023/08/16 16:10:28.333, 2023-08-16 16:10:28.333*/
        else if (inputDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})")) {
            //System.out.println("MATCH 4: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");
            hour = datetime[1];

            year = date.substring(0, 4);
            month = date.substring(4, 6);
            day = date.substring(6, 8);
            format = "br";
        }

        /*16/08/2023, 16-08-2023*/
        else if (inputDate.matches("[0-9]{2}[/-][0-9]{2}[/-][0-9]{4}")) {
            //System.out.println("MATCH 5: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");

            day = date.substring(0, 2);
            month = date.substring(2, 4);
            year = date.substring(4, 8);
        }

        /*2023/08/16, 2023-08-16*/
        else if (inputDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2}")) {
            //System.out.println("MATCH 6: " + inputDate);

            datetime = inputDate.split(" ");
            date = datetime[0].replaceAll("[/-]", "");

            year = date.substring(0, 4);
            month = date.substring(4, 6);
            day = date.substring(6, 8);
            format = "br";
        }

        /*invalid datetime*/
        else {
            return "invalid date: " + inputDate;
        }

        if (!hour.equals("")) {
            if (format.equals("en"))
                return year + separator + month + separator + day + " " + hour;
            else
                return day + separator + month + separator + year + " " + hour;
        } else {
            if (format.equals("en"))
                return year + separator + month + separator + day;
            else
                return day + separator + month + separator + year;
        }
    }

    public static boolean dateExpired(String date, int time, String metricType) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime dateTimeNow = LocalDateTime.now();
        String dateTimeFormat = dateTimeNow.format(formatter);
        LocalDateTime dateTimeNowFormatter = LocalDateTime.parse(dateTimeFormat, formatter);
        LocalDateTime dateTimeRef = LocalDateTime.parse(dateReverse(date, "-"), formatter);
        LocalDateTime timeLimit = null;

        switch (metricType) {
            case "nano":
                timeLimit = dateTimeRef.plusNanos(time);
                break;
            case "second":
                timeLimit = dateTimeRef.plusSeconds(time);
                break;
            case "minute":
                timeLimit = dateTimeRef.plusMinutes(time);
                break;
            case "hour":
                timeLimit = dateTimeRef.plusHours(time);
                break;
            case "day":
                timeLimit = dateTimeRef.plusDays(time);
                break;
            case "week":
                timeLimit = dateTimeRef.plusWeeks(time);
                break;
            case "month":
                timeLimit = dateTimeRef.plusMonths(time);
                break;
            case "year":
                timeLimit = dateTimeRef.plusYears(time);
                break;
            default:
                throw new RuntimeException("Invalid period to expiredDate");
        }

        int diffTime = dateTimeNowFormatter.compareTo(timeLimit);

        if (diffTime > 0) {
            System.out.println("Time Expired: [now:"+dateTimeNowFormatter+"] - [limit:"+timeLimit+"]");
            return true;
        }

        return false;
    }

    public static List<Long> dateQuantify(String initialDate, String finalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

        LocalDateTime initialDateRef = LocalDateTime.parse(dateReverse(initialDate, "-"), formatter);
        LocalDateTime finalDateRef = LocalDateTime.parse(dateReverse(finalDate, "-"), formatter);
        LocalDateTime tmpDateTime = LocalDateTime.from(initialDateRef);

        long years = tmpDateTime.until(finalDateRef, ChronoUnit.YEARS);
        tmpDateTime = tmpDateTime.plusYears(years);

        long months = tmpDateTime.until(finalDateRef, ChronoUnit.MONTHS);
        tmpDateTime = tmpDateTime.plusMonths(months);

        long days = tmpDateTime.until(finalDateRef, ChronoUnit.DAYS);
        tmpDateTime = tmpDateTime.plusDays(days);

        long hours = tmpDateTime.until(finalDateRef, ChronoUnit.HOURS);
        tmpDateTime = tmpDateTime.plusHours(hours);

        long minutes = tmpDateTime.until(finalDateRef, ChronoUnit.MINUTES);
        tmpDateTime = tmpDateTime.plusMinutes(minutes);

        long seconds = tmpDateTime.until(finalDateRef, ChronoUnit.SECONDS);
        tmpDateTime = tmpDateTime.plusSeconds(seconds);

        long milliseconds = tmpDateTime.until(finalDateRef, ChronoUnit.MILLIS);

        List<Long> arrayList = new ArrayList<>();
        arrayList.add(years);
        arrayList.add(months);
        arrayList.add(days);
        arrayList.add(hours);
        arrayList.add(minutes);
        arrayList.add(seconds);
        arrayList.add(milliseconds);

        return arrayList;

    }

    public static long quantifyMillisDate(long startDate, long endDate) {
        return endDate - startDate;
    }

    public static long quantifyMillisParamsDate(String start, String end) {

        String[] startDate = start
                .replaceAll("[/-]", " ")
                .replaceAll(":", " ")
                .replaceAll("\\.", " ")
                .split(" ");

        String[] endDate = end
                .replaceAll("[/-]", " ")
                .replaceAll(":", " ")
                .replaceAll("\\.", " ")
                .split(" ");

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.YEAR, Integer.parseInt(startDate[0]));
        startCalendar.set(Calendar.MONTH, Integer.parseInt(startDate[1]));
        startCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(startDate[2]));

        try {
            startCalendar.set(Calendar.HOUR, Integer.parseInt(startDate[3]));
        } catch (RuntimeException re) {
            startCalendar.set(Calendar.HOUR, 0);
        }

        try {
            startCalendar.set(Calendar.MINUTE, Integer.parseInt(startDate[4]));
        } catch (RuntimeException re) {
            startCalendar.set(Calendar.MINUTE, 0);
        }

        try {
            startCalendar.set(Calendar.SECOND, Integer.parseInt(startDate[5]));
        } catch (RuntimeException re) {
            startCalendar.set(Calendar.SECOND, 0);
        }

        try {
            startCalendar.set(Calendar.MILLISECOND, Integer.parseInt(startDate[6]));
        } catch (RuntimeException re) {
            startCalendar.set(Calendar.MILLISECOND, 0);
        }

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.YEAR, Integer.parseInt(endDate[0]));
        endCalendar.set(Calendar.MONTH, Integer.parseInt(endDate[1]));
        endCalendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(endDate[2]));

        try {
            endCalendar.set(Calendar.HOUR, Integer.parseInt(endDate[3]));
        } catch (RuntimeException re) {
            endCalendar.set(Calendar.HOUR, 0);
        }

        try {
            endCalendar.set(Calendar.MINUTE, Integer.parseInt(endDate[4]));
        } catch (RuntimeException re) {
            endCalendar.set(Calendar.MINUTE, 0);
        }

        try {
            endCalendar.set(Calendar.SECOND, Integer.parseInt(endDate[5]));
        } catch (RuntimeException re) {
            endCalendar.set(Calendar.SECOND, 0);
        }

        try {
            endCalendar.set(Calendar.MILLISECOND, Integer.parseInt(endDate[6]));
        } catch (RuntimeException re) {
            endCalendar.set(Calendar.MILLISECOND, 0);
        }

        long duration = endCalendar.getTimeInMillis() - startCalendar.getTimeInMillis();

        return duration;

    }

    public static String localDateFromGmtDate(String gmtDate, String operation, int time) {

        if (!gmtDate.matches("[0-9]{4}[/-][0-9]{2}[/-][0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}(\\.[0-9]{1,3})Z")) {
            return "invalid date format: " + gmtDate;
        }

        DateTimeFormatter formatterDash = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        DateTimeFormatter formatterBar  = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("uuuu-MM-dd");

        String[] saveMillis = gmtDate.replaceAll("Z", "").split("\\.");

        gmtDate = gmtDate
                .replaceAll("[TZ]", " ")
                .trim()
                .replaceAll("\\.[0-9]+$", "");

        LocalDateTime dateTimeRef;

        try {
            dateTimeRef = LocalDateTime.parse(gmtDate, formatterDash);
        } catch (DateTimeParseException re) {
            dateTimeRef = LocalDateTime.parse(gmtDate, formatterBar);
            formatterDate = DateTimeFormatter.ofPattern("uuuu/MM/dd");
        }

        if (operation.equals("-")) {
            String dt = dateTimeRef.minusHours(time).toLocalDate().format(formatterDate);
            LocalTime tm = dateTimeRef.minusHours(time).toLocalTime();
            return dt + " " + tm + "." + saveMillis[1];

        } else if (operation.equals("+")) {
            String dt = dateTimeRef.plusHours(time).toLocalDate().format(formatterDate);
            LocalTime tm = dateTimeRef.plusHours(time).toLocalTime();
            return dt + " " + tm + "." + saveMillis[1];

        } else {
            throw new RuntimeException("Invalid option to localDateFromGmtDate, use: - or +");
        }
    }

    public static String militaryHour(String inputHour) {

        String result = null;
        int militaryHour = 12;
        String[] matches = inputHour.replaceAll("(PM|AM)$", "").split(":");

        if (inputHour.matches("(.*)PM$")) {

            if (!matches[0].equals("12")) {
                militaryHour = Integer.parseInt(matches[0]) + 12;
            }
            result = militaryHour +":"+ matches[1] +":"+ matches[2];

        } else if (inputHour.matches("(.*)AM")) {

            if (matches[0].equals("12")) {
                matches[0] = "00";
            }
            result = matches[0] +":"+ matches[1] +":"+ matches[2];
        }

        return result;
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

    public static Calendar stringToCalendar(String dateString) {

        Calendar cal = Calendar.getInstance();
        Locale localBrazil = new Locale("pt", "BR");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", localBrazil);

        try {
            cal.setTime(sdf.parse(dateString));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return cal;
    }

    public static java.util.Date stringToDate(String dateString) {

        java.util.Date date = null;

        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (Exception e1) {
            try {
                date = new SimpleDateFormat("yyyy/MM/dd").parse(dateString);
            } catch (Exception e2) {
                try {
                    date = new SimpleDateFormat("yyyy.MM.dd").parse(dateString);
                } catch (Exception e3) {
                    throw new RuntimeException(
                            e1.getMessage()+" : "+e2.getMessage()+" : "+e3.getMessage());
                }
            }
        }

        return date;
    }

    public static LocalDateTime stringToLocalDatetime(String dateString, int formatType, Locale locale) {

        String format;

        if (formatType == 1) {
            format = "yyyy-MM-dd hh:mm:ss a";
        } else if (formatType == 2) {
            format = "yyyy/MM/dd hh:mm:ss";
        } else if (formatType == 3) {
            format = "yyyy.MM.dd hh:mm:ss";
        } else if (formatType == 4) {
            format = "yyyy-MMMM-dd HH:mm:ss a";
        } else {
            throw new RuntimeException("Invalid format number");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format).withLocale(locale);

        return LocalDateTime.parse(dateString, formatter);
    }

}
