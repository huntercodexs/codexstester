package codexstester.abstractor.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

import static codexstester.abstractor.util.CodexsHelperTests.codexsHelperLogTerm;

public abstract class PropertiesLoaderTests extends AssertionTests {

    protected final Properties externalProps = loadExternalPropsTests();
    protected final Properties internalProps = loadInternalPropsTests();
    protected final Properties unitaryProps = loadUnitaryPropsTests();

    private static String fixTarget(String target) {
        if (target == null || target.equals("") || target.equals("/")) {
            return "";
        }
        if (!target.endsWith("/")) {
            return target+"/";
        }
        return target;
    }

    protected static Properties loadExternalPropsTests() {

        codexsHelperLogTerm("LOAD EXTERNAL PROPS", null, true);

        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile("classpath:"+fixTarget(targetTests)+"external.tests.properties");
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

    protected static Properties loadInternalPropsTests() {

        codexsHelperLogTerm("LOAD INTERNAL PROPS", null, true);

        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile("classpath:"+fixTarget(targetTests)+"internal.tests.properties");
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

    protected static Properties loadUnitaryPropsTests() {

        codexsHelperLogTerm("LOAD UNITARY PROPS", null, true);

        Properties properties = new Properties();

        try {
            File file = ResourceUtils.getFile("classpath:"+fixTarget(targetTests)+"unitary.tests.properties");
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

}
