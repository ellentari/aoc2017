package com.adventofcode.common;

import static java.util.Arrays.stream;

public class ParsingUtils {

    private ParsingUtils() {

    }

    public static int[][] parseGrid(String input) {
        if (input.isEmpty()) {
            return new int[0][0];
        }
        return stream(input.split("\n"))
                .filter(s -> !s.isEmpty())
                .map(line -> line.split("\\s+"))
                .map(ParsingUtils::parseInts)
                .toArray(int[][]::new);
    }

    public static int[] parseIntArray(String input) {
        return parseIntArray(input, "\\s+");
    }

    public static int[] parseIntArray(String input, String delimiter) {
        return stream(input.split(delimiter))
                .filter(s -> !s.isEmpty())
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int[] parseInts(String[] values) {
        return stream(values).mapToInt(Integer::parseInt).toArray();
    }
}
