package com.huntercodexs.codexstester.resource.file;

import java.io.*;

public class CodexsFileWriter {

    public BufferedWriter bufferedWriter;

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">folderCreate</p>
     *
     * <p style="color: #CDCDCD">Create a folder in the specific path</p>
     *
     * @param path (String: The path to create a target folder)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public boolean folderCreate(String path) {
        try {

            File file = new File(path);

            if (file.mkdirs()) {
                return true;
            }

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] Folder not created: " + ex.getMessage());
        }

        System.out.println("[ERROR] Folder not created: " + path);
        return false;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">fileDelete</p>
     *
     * <p style="color: #CDCDCD">Delete one file in the specific path</p>
     *
     * @param path (String: The path to delete a target file)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public boolean fileDelete(String path) {
        try {

            File file = new File(path);

            if (file.exists()) {
                if (file.delete()) {
                    return true;
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] File not deleted: " + ex.getMessage());
        }

        System.out.println("[ERROR] File not deleted: " + path);
        return false;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">fileMove</p>
     *
     * <p style="color: #CDCDCD">Rename file to specific filename passed in the parameters</p>
     *
     * @param path (String: The path to rename a target file)
     * @param newPath (String: The new path to rename a target file)
     * @return boolean
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public boolean fileMove(String path, String newPath) {
        try {

            File file = new File(path);
            File newFile = new File(newPath);

            if (file.exists()) {
                if (file.renameTo(newFile)) {
                    return true;
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException("[EXCEPTION] File not moved: " + ex.getMessage());
        }

        System.out.println("[ERROR] File not moved: " + path);
        return false;
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">fileCreate</p>
     *
     * <p style="color: #CDCDCD">Create a target file</p>
     *
     * @param filepath (String: The path to create a target file)
     * @throws FileNotFoundException (Exception: File Exception)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void fileCreate(String filepath) throws FileNotFoundException {
        File file = new File(filepath);

        if (file.exists()) {
            if (!file.delete()) {
                System.out.println("ERROR: File Not deleted: " + filepath);
            }
        }

        OutputStream os = new FileOutputStream(filepath, true);
        Writer wr = new OutputStreamWriter(os);
        this.bufferedWriter = new BufferedWriter(wr);
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">fileWrite</p>
     *
     * <p style="color: #CDCDCD">Write in the target file</p>
     *
     * @param data (String: Data to write in the file)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void fileWrite(String data) {
        try {
            this.bufferedWriter.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">fileNewLine</p>
     *
     * <p style="color: #CDCDCD">Append new line in the target file</p>
     *
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void fileNewLine() {
        try {
            this.bufferedWriter.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <p style="color: #FFFF00; font-size: 11px; weight: bold">fileClose</p>
     *
     * <p style="color: #CDCDCD">Close the target file</p>
     *
     * @throws IOException (Exception: File Exception)
     * @see <a href="https://github.com/huntercodexs/help4devs-commons">Help4devs (GitHub)</a>
     * @author huntercodexs (powered by jereelton-devel)
     * */
    public void fileClose() throws IOException {
        this.bufferedWriter.close();
    }

}
