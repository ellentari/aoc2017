package com.adventofcode.day21;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;
import static com.adventofcode.day21.RuleMapping.gridToString;
import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.common.Resources;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class Day21Solutions {

    private static final String INPUT = "day21.txt";

    @Test
    public void partOneSamples() {
        assertPartOne(2, asList(
                "../.# => ##./#../...",
                ".#./..#/### => #..#/..../..../#..#"), 12);
    }

    @Test
    public void split2() {
        char[][] grid = new char[][] {
                {'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h'},
                {'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p'}
        };

        List<String> result = Day21.split(grid, 2);

        Assertions.assertThat(result)
                .contains("ab/ef", "cd/gh", "ij/mn", "kl/op");
    }

    @Test
    public void split3() {
        char[][] grid = new char[][] {
                {'a', 'b', 'c', 'd', 'e', 'f'},
                {'g', 'h', 'i', 'j', 'k', 'l'},
                {'m', 'n', 'o', 'p', 'q', 'r'},
                {'s', 't', 'u', 'v', 'w', 'x'},
                {'y', 'z', 'a', 'b', 'c', 'd'},
                {'e', 'f', 'g', 'h', 'i', 'j'}
        };

        List<String> result = Day21.split(grid, 3);

        Assertions.assertThat(result)
                .contains("abc/ghi/mno", "def/jkl/pqr", "stu/yza/efg", "vwx/bcd/hij");
    }

    @Test
    public void merge() {
        char[][] merged = Day21.merge(2,
                asList("##./#../...", "##./#../...", "##./#../...", "##./#../...")
        );

        String toString = gridToString(merged);

        Assertions.assertThat(toString)
                .isEqualTo(
                        "# # . # # .\n" +
                        "# . . # . .\n" +
                        ". . . . . .\n" +
                        "# # . # # .\n" +
                        "# . . # . .\n" +
                        ". . . . . ."
                );
            }

    @Test
    public void partOne() {
        assertPartOne(5, Resources.lines(INPUT), 171);
    }

    @Test
    public void partTwo() {
        assertPartTwo(18, Resources.lines(INPUT), 2498142);
    }

    private void assertPartOne(int iterations, List<String> instructions, Integer expectedResult) {
        assertThat(puzzle(iterations, instructions)).hasPartOneSolution(expectedResult);
    }

    private void assertPartTwo(int iterations, List<String> instructions, Integer expectedResult) {
        assertThat(puzzle(iterations, instructions)).hasPartTwoSolution(expectedResult);
    }

    private AdventOfCodePuzzle<Integer, Integer> puzzle(int iterations, List<String> instructions) {
        RulesMapping rulesMapping = new RulesMapping();

        for (String instruction : instructions) {
            String[] split = instruction.split(" => ");

            rulesMapping.add(split[0], split[1]);
        }

        return new Day21(iterations, rulesMapping);
    }
}
