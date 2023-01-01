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

    private static String fixTarget(Object[][] target) {
        if (target.length == 0) throw new RuntimeException("PROPERTIES FILE ERROR: Is Empty");

        for (Object[] current : target) {
            String currentTarget = current[0].toString();
            String stateTarget = current[1].toString();

            if (stateTarget.equals("true")) {
                if (currentTarget == null || currentTarget.equals("") || currentTarget.equals("/")) {
                    return "";
                }
                if (!currentTarget.endsWith("/")) {
                    return currentTarget+"/";
                }
                return currentTarget;
            }
        }
        throw new RuntimeException("PROPERTIES FILE ERROR: Is Null");
    }

    private static String propertiesPath(String type) {
        return "classpath:"+fixTarget(targetTests)+type+".tests.properties";
    }

    protected static Properties loadExternalPropsTests() {

        Properties properties = new Properties();
        String propFile = propertiesPath("external");
        codexsHelperLogTerm("LOAD EXTERNAL PROPS", propFile, true);

        try {
            File file = ResourceUtils.getFile(propFile);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

    protected static Properties loadInternalPropsTests() {

        Properties properties = new Properties();
        String propFile = propertiesPath("internal");
        codexsHelperLogTerm("LOAD INTERNAL PROPS", propFile, true);

        try {
            File file = ResourceUtils.getFile(propFile);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

    protected static Properties loadUnitaryPropsTests() {

        Properties properties = new Properties();
        String propFile = propertiesPath("unitary");
        codexsHelperLogTerm("LOAD UNITARY PROPS", propFile, true);

        try {
            File file = ResourceUtils.getFile(propFile);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return properties;
    }

}
