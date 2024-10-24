package com.huntercodexs.codexstester.resource.basic;

public class CodexsPath {

    public static String sanitizePath(String path) {
        if (path.matches(".*\\..*$")) {
            return path;
        }
        return path.replaceAll("/$", "") + "/";
    }

    public static String fileExtension(String filepath) {
        if (!filepath.contains(".")) {
            return filepath;
        }

        String[] fileParts = filepath.split("\\.");
        String fileType = fileParts[fileParts.length-1];

        if (fileType.isEmpty()) {
            return filepath;
        }

        return fileType;
    }

}
