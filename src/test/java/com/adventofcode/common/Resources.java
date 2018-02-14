package com.adventofcode.common;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Resources {

    private Resources() {
    }

    public static String singleLine(String resource) {
        try {
            return IOUtils.toString(classpathResource(resource), UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error reading classpathResource", e);
        }
    }

    public static List<String> lines(String resource) {
        try {
            return IOUtils.readLines(classpathResource(resource), UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error reading classpathResource", e);
        }
    }

    private static InputStream classpathResource(String resource) {
        return Resources.class.getClassLoader().getResourceAsStream(resource);
    }

}
