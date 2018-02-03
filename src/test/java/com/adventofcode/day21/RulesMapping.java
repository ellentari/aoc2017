package com.adventofcode.day21;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RulesMapping {

    private Map<String, String> map = new HashMap<>();

    public void add(String from, String to) {
        map.put(from, to);
    }

    public String map(String value) {
        if (map.containsKey(value)) {
            return map.get(value);
        }

        return tryFlippingAndRotating(value);
    }

    private String tryFlippingAndRotating(String value) {
        char[][] grid = toGrid(value);

        String result = tryFlipped(value, grid);

        if (result != null) {
            return result;
        } else {
            return tryRotating(value);
        }
    }

    private String tryFlipped(String value, char[][] grid) {
        char[][] flipped = flip(grid);

        String flippedPattern = toPattern(flipped);

        if (map.containsKey(flippedPattern)) {
            map.put(value, map.get(flippedPattern));
        }

        return map.get(value);
    }

    private String tryRotating(String value) {
        char[][] grid = toGrid(value);

        char[][] rotated = rotate(grid);
        String rotatedPattern = toPattern(rotated);

        if (!map.containsKey(rotatedPattern)) {

            String result = tryFlipped(value, rotated);
            if (result != null) {
                return result;
            }

            rotated = rotate(rotated);
            rotatedPattern = toPattern(rotated);

            if (!map.containsKey(rotatedPattern)) {

                result = tryFlipped(value, rotated);
                if (result != null) {
                    return result;
                }

                rotated = rotate(rotated);
                rotatedPattern = toPattern(rotated);

                if (map.containsKey(rotatedPattern)) {
                    map.put(value, map.get(rotatedPattern));
                } else {

                    result = tryFlipped(value, rotated);
                    if (result != null) {
                        return result;
                    }
                }

            } else {
                map.put(value, map.get(rotatedPattern));
            }
        } else {
            map.put(value, map.get(rotatedPattern));
        }

        String s = map.get(value);

        if (s == null) {
            throw new IllegalArgumentException(value);
        }

        return s;
    }

    static char[][] rotate(char[][] grid) {
        char[][] rotated = copyOf(grid);

        for (int i = 0; i < grid.length; i++) {
            rotated[0][i] = grid[grid.length - 1 - i][0];
        }

        for (int i = 0; i < grid.length; i++) {
            rotated[i][0] = grid[grid.length - 1][i];
        }

        for (int i = 0; i < grid.length; i++) {
            rotated[grid.length - 1][i] = grid[grid.length - 1 - i][grid.length - 1];
        }

        for (int i = 0; i < grid.length; i++) {
            rotated[i][grid.length - 1] = grid[0][i];
        }

        return rotated;

    }

    private static char[][] copyOf(char[][] grid) {
        char[][] copy = new char[grid.length][];

        for (int i = 0; i < grid.length; i++) {
            copy[i] = Arrays.copyOf(grid[i], grid[i].length);
        }
        return copy;
    }

    private char[][] toGrid(String value) {
        String[] split = value.split("/");

        char[][] grid = new char[split.length][split[0].length()];

        for (int i = 0; i < split.length; i++) {
            for (int j = 0; j < split[i].length(); j++) {
                grid[i][j] = split[i].charAt(j);
            }
        }

        return grid;
    }

    private char[][] flip(char[][] grid) {
        char[][] flipped = copyOf(grid);

        for (char[] aFlipped : flipped) {
            for (int j = 0; j < aFlipped.length / 2; j++) {
                swap(aFlipped, j, aFlipped.length - j - 1);
            }
        }

        return flipped;
    }

    private void swap(char[] chars, int j, int i) {
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    private String toPattern(char[][] grid) {
        StringBuilder pattern = new StringBuilder();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                pattern.append(grid[i][j]);
            }
            pattern.append('/');
        }

        pattern.deleteCharAt(pattern.length() - 1);

        return pattern.toString();
    }
}
