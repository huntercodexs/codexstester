package com.huntercodexs.codexstester.resource.basic;

public class CodexsPath {

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">sanitizePath</p>
     *
     * <p style="color: #CDCDCD">Sanitize any path as you need</p>
     *
     * @param path (String)
     * @return String (Path sanitized)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public static String sanitizePath(String path) {
        if (path.matches(".*\\..*$")) {
            return path;
        }
        return path.replaceAll("/$", "") + "/";
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">fileExtension</p>
     *
     * <p style="color: #CDCDCD">Get the file extension from one string</p>
     *
     * @param filepath (String)
     * @return String (Extension File)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
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
