package com.adventofcode.day21;

import static java.util.stream.Collectors.toList;

import com.adventofcode.AdventOfCodePuzzle;

import java.util.ArrayList;
import java.util.List;

public class Day21 implements AdventOfCodePuzzle<Integer, Integer> {

    private final int iterations;
    private final RulesMapping rulesMapping;

    Day21(int iterations, RulesMapping rulesMapping) {
        this.iterations = iterations;
        this.rulesMapping = rulesMapping;
    }

    @Override
    public Integer solvePartOne() {
        char[][] grid = new char[][] {
                {'.', '#', '.'},
                {'.', '.', '#'},
                {'#', '#', '#'}
        };

        for (int i = 0; i < iterations; i++) {
            List<String> split;

            int n;
            if (grid.length % 2 == 0) {
                n = 2;
                split = split(grid, 2);
            } else if (grid.length % 3 == 0) {
                n = 3;
                split = split(grid, 3);
            } else {
                throw new IllegalStateException();
            }

            List<String> transformed = split.stream().map(rulesMapping::map).collect(toList());

            grid = merge(grid.length / n, transformed);
        }

        return count(grid, '#');
    }

    private int count(char[][] grid, char ch) {
        int count = 0;

        for (char[] row : grid) {
            for (char element : row) {
                if (element == ch) {
                    count++;
                }
            }
        }
        return count;
    }

    static char[][] merge(int n, List<String> splits) {
        List<String[]> splited = splits.stream().map(s -> s.split("/")).collect(toList());

        int length = splited.get(0)[0].length();
        int newSize = n * length;

        char[][] merged = new char[newSize][newSize];

        int startJ = 0;


        for (int i = 0; i < splited.size(); i++) {
            String[] split = splited.get(i);

            int ii = i / n * length;
            int jj = startJ;

            for (int j = 0; j < split.length; j++) {

                String row = split[j];

                for (int k = 0; k < row.length(); k++) {
                    merged[ii][jj++] = row.charAt(k);
                }

                ii++;
                jj = startJ;
            }

            startJ = (startJ + length) % newSize;
        }

        return merged;
    }

    static List<String> split(char[][] grid, int n) {
        List<String> result = new ArrayList<>();

        for (int k = 0; k < grid.length / n; k++) {
            for (int kj = 0; kj < grid.length / n; kj++) {
                StringBuilder current = new StringBuilder();

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        current.append(grid[k * n + i][kj * n + j]);
                    }
                    current.append('/');
                }

                current.deleteCharAt(current.length() - 1);

                result.add(current.toString());
            }
        }

        return result;
    }


    @Override
    public Integer solvePartTwo() {
        return solvePartOne();
    }

}
