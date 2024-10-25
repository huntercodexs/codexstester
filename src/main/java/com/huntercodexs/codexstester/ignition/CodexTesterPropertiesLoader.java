package com.huntercodexs.codexstester.ignition;

import com.huntercodexs.codexstester.util.CodexsAssertion;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Properties;

public abstract class CodexTesterPropertiesLoader extends CodexsAssertion {

    protected Properties externalProps;
    protected Properties internalProps;
    protected Properties unitaryProps;

    protected CodexTesterPropertiesLoader(String target) {
        externalProps = loadExternalPropsTests(target);
        internalProps = loadInternalPropsTests(target);
        unitaryProps = loadUnitaryPropsTests(target);
    }

    private static String fixTarget(String target) {
        if (target == null || target.equals("") || target.equals("/")) {
            return "";
        }
        if (!target.endsWith("/")) {
            return target+"/";
        }
        return target;
    }

    private static String propertiesPath(String type, String target) {
        return "classpath:"+fixTarget(target)+type+".tests.properties";
    }

    private static void exceptionFileNotFound(String error) {
        String message = "[EXCEPTION]: File Not Found";
        message += "\n---------------------------------------------------------------------\n";
        message += error;
        message += "\n---------------------------------------------------------------------\n";
        throw new RuntimeException(message);
    }

    protected static Properties loadExternalPropsTests(String target) {

        Properties properties = new Properties();
        String propFile = propertiesPath("external", target);

        try {
            File file = ResourceUtils.getFile(propFile);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            exceptionFileNotFound(ioe.getMessage());
        }

        return properties;
    }

    protected static Properties loadInternalPropsTests(String target) {

        Properties properties = new Properties();
        String propFile = propertiesPath("internal", target);

        try {
            File file = ResourceUtils.getFile(propFile);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            exceptionFileNotFound(ioe.getMessage());
        }

        return properties;
    }

    protected static Properties loadUnitaryPropsTests(String target) {

        Properties properties = new Properties();
        String propFile = propertiesPath("unitary", target);

        try {
            File file = ResourceUtils.getFile(propFile);
            InputStream in = Files.newInputStream(file.toPath());
            properties.load(in);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            exceptionFileNotFound(ioe.getMessage());
        }

        return properties;
    }

}
