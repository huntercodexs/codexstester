package com.huntercodexs.codexstester.resource.basic;

public class CodexsBase {

    private static String calculateResult(String[] metrics, String scale) {
        if (metrics[1].length() >= 2) {
            metrics[1] = metrics[1].substring(0, 2);
        } else {
            metrics[1] = String.valueOf(metrics[1].charAt(0));
        }
        return metrics[0]+"."+metrics[1]+scale;
    }

    public static String calculateBytes(long bytesLength) {
        if (bytesLength >= 1 && bytesLength < 1024) {
            if (bytesLength == 1) {
                return bytesLength + "b";
            } else {
                return bytesLength + "b";
            }
        }
        return "Is not possible to calculate the memory => ["+bytesLength+"b]";
    }

    public static String calculateKilobytes(long bytesLength) {
        if (bytesLength >= 1024 && bytesLength < 1024000) {
            String size = String.valueOf(((float) bytesLength) / 1024);
            return calculateResult(size.split("\\."), "KB");
        }
        return calculateBytes(bytesLength);
    }

    public static String calculateMegabytes(long bytesLength) {
        if (bytesLength >= 1024000 && bytesLength < 1024000000) {
            float kilobytes = (((float) bytesLength) / 1024);
            float megabytes = (kilobytes / 1024);
            String size = String.valueOf(megabytes);
            return calculateResult(size.split("\\."), "MB");
        }
        return calculateKilobytes(bytesLength);
    }

    public static String calculateGigabytes(long bytesLength) {
        if (bytesLength >= 1024000000 && bytesLength < 1024000000000L) {
            float kilobytes = (((float) bytesLength) / 1024);
            float megabytes = (kilobytes / 1024);
            float gigabytes = (megabytes / 1024);
            String size = String.valueOf(gigabytes);
            return calculateResult(size.split("\\."), "GB");
        }
        return calculateMegabytes(bytesLength);
    }

    public static String calculateMemory(long bytesLength) {
        return calculateGigabytes(bytesLength);
    }

}
