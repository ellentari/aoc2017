package com.adventofcode.day22;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;
import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.common.Resources;

import org.junit.Test;

import java.util.List;

public class Day22Solutions {

    private static final String INPUT = "day22.txt";

    @Test
    public void partOneSamples() {
        assertPartOne(7, asList(
                "..#",
                "#..",
                "..."), 5);

        assertPartOne(70, asList(
                "..#",
                "#..",
                "..."), 41);

        assertPartOne(10000, asList(
                "..#",
                "#..",
                "..."), 5587);
    }

    @Test
    public void partOne() {
        assertPartOne(10000, Resources.lines(INPUT), 5447);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo(100, asList(
                "..#",
                "#..",
                "..."), 26);

        assertPartTwo(10_000_000, asList(
                "..#",
                "#..",
                "..."), 2_511_944);
    }

    @Test
    public void partTwo() {
        assertPartTwo(10_000_000, Resources.lines(INPUT), 2_511_705);
    }

    private void assertPartOne(int bursts, List<String> instructions, Integer expectedResult) {
        assertThat(puzzle(bursts, instructions)).hasPartOneSolution(expectedResult);
    }

    private void assertPartTwo(int bursts, List<String> instructions, Integer expectedResult) {
        assertThat(puzzle(bursts, instructions)).hasPartTwoSolution(expectedResult);
    }

    private AdventOfCodePuzzle<Integer, Integer> puzzle(int bursts, List<String> map) {
        return new Day22(bursts, map);
    }
}
