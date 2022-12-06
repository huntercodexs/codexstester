package codexstester.abstractor.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

public abstract class PropertiesLoaderTests extends AssertionTests {

    protected final Properties internalProp = loadInternalPropsTests();
    protected final Properties externalProp = loadExternalPropsTests();
    protected final Properties unitaryProp = loadUnitaryPropsTests();

    private static Properties loadExternalPropsTests() {
        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile("classpath:"+externalPropertiesFilepath);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

    private static Properties loadInternalPropsTests() {
        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile("classpath:"+internalPropertiesFilepath);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

    private static Properties loadUnitaryPropsTests() {
        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile("classpath:"+unitaryPropertiesFilepath);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

}
