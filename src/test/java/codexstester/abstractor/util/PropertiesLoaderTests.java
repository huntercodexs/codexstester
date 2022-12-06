package codexstester.abstractor.util;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.Properties;

import static codexstester.abstractor.util.UtilTests.readRuntimeFile;

public abstract class PropertiesLoaderTests extends AssertionTests {

    protected final Properties internalProp = loadInternalPropsTests();
    protected final Properties externalProp = loadExternalPropsTests();
    protected final Properties unitaryProp = loadUnitaryPropsTests();

    protected static Properties loadExternalPropsTests() {
        Properties properties = new Properties();
        String externalFileProps = readRuntimeFile();

        try {
            File file = ResourceUtils.getFile("classpath:"+externalFileProps);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

    protected static Properties loadInternalPropsTests() {
        Properties properties = new Properties();
        String internalFileProps = readRuntimeFile();

        try {
            File file = ResourceUtils.getFile("classpath:"+internalFileProps);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

    protected static Properties loadUnitaryPropsTests() {
        Properties properties = new Properties();
        String unitaryFileProps = readRuntimeFile();

        try {
            File file = ResourceUtils.getFile("classpath:"+unitaryFileProps);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

}
