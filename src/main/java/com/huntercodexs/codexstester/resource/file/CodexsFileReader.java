package com.huntercodexs.codexstester.resource.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CodexsFileReader {

    public static String awaitFileContent(String filepath, String regex, int timeout) throws Exception {

        String content = " ";
        int counter = 0;

        while (!content.matches(regex)) {

            FileReader openFile = open(filepath);
            BufferedReader bufferFile = buffer(openFile);

            try {
                counter++;
                content = reader(bufferFile);

                if (content.matches(regex)) {
                    break;
                }

                if (counter > timeout && timeout > 0) {
                    close(openFile, filepath);
                    throw new RuntimeException("Time Exception to get content: timeout !");
                }

            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            close(openFile,filepath);
        }

        return content;
    }

    private static FileReader open(String filepath) {

        FileReader activateFile;

        try {
            activateFile = new FileReader(filepath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return activateFile;
    }

    private static BufferedReader buffer(FileReader activateFile) {
        return new BufferedReader(activateFile);
    }

    private static String reader(BufferedReader readActivateFile) throws InterruptedException {
        String lineFile;

        try {
            Thread.sleep(1000);
            lineFile = readActivateFile.readLine();
            if (lineFile == null || lineFile.isEmpty()) {
                return "";
            }
        } catch (IOException e) {
            return "";
        }

        return lineFile;
    }

    private static void close(FileReader activateFile, String filepath) throws IOException, InterruptedException {
        activateFile.close();
        Thread.sleep(2000);
    }

}
