package com.adventofcode.day17;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;

import com.adventofcode.AdventOfCodePuzzle;

import org.junit.Ignore;
import org.junit.Test;

public class Day17Solutions {

    @Test
    public void partOneSamples() {
        assertPartOne(3, 1, 0);
        assertPartOne(3, 2, 1);
        assertPartOne(3, 3, 1);
        assertPartOne(3, 2017, 638);
    }

    @Test
    public void partOne() {
        assertPartOne(312, 2017, 772);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo(3, 8, 5);
    }

    @Test
    @Ignore("too long")
    public void partTwo() {
        assertPartTwo(312, 50_000_000, 42_729_050);
    }

    private void assertPartOne(int steps, int insertions, int expectedResult) {
        assertThat(puzzle(steps, insertions)).hasPartOneSolution(expectedResult);
    }

    private void assertPartTwo(int steps, int insertions, int expectedResult) {
        assertThat(puzzle(steps, insertions)).hasPartTwoSolution(expectedResult);
    }

    private AdventOfCodePuzzle<Integer, Integer> puzzle(int steps, int insertions) {
        return new Day17(steps, insertions);
    }
}
