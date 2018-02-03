package com.adventofcode.day10;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;
import static com.adventofcode.common.ParsingUtils.parseIntArray;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.common.Resources;

import org.junit.Test;

import java.util.stream.IntStream;

public class Day10Solutions {

    private static final String INPUT = "day10.txt";

    @Test
    public void partOneSamples() {
        assertPartOne(5, "3,4,1,5", 12);
    }

    @Test
    public void partOne() {
        assertPartOne(256, Resources.singleLine(INPUT), 11413);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.singleLine(INPUT), "7adfd64c2a03a4968cf708d1b7fd418d");
    }

    private void assertPartOne(int size, String lengths, int expectedResult) {
        assertThat(puzzle(size, parseIntArray(lengths, ","))).hasPartOneSolution(expectedResult);
    }

    private void assertPartTwo(String lengths, String expectedResult) {
        assertThat(new Day10(lengths)).hasPartTwoSolution(expectedResult);
    }

    private AdventOfCodePuzzle<Integer, String> puzzle(int size, int[] lengths) {
        return new Day10(size, lengths);
    }
}
