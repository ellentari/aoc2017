package com.adventofcode.day15;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.day14.Day14;
import com.adventofcode.day15.Day15.GeneratorInput;

import org.junit.Ignore;
import org.junit.Test;

public class Day15Solutions {

    @Test
    public void partOneSamplesFast() {
        assertPartOne(65, 8921, 5, 1);
    }

    @Test
    @Ignore("too long")
    public void partOneSamplesSlow() {
        assertPartOne(65, 8921, 40_000_000, 588);
    }

    @Test
    @Ignore("too long")
    public void partOne() {
        assertPartOne(699, 124, 40_000_000, 600);
    }

    @Test
    public void partTwoSamplesFast() {
        assertPartTwo(65, 8921, 1056, 1);
    }

    @Test
    @Ignore("too long")
    public void partTwoSampleSlow() {
        assertPartTwo(65, 8921, 5_000_000, 309);
    }

    @Test
    @Ignore("too long")
    public void partTwo() {
        assertPartTwo(699, 124, 5_000_000, 313);
    }

    private void assertPartOne(int aStart, int bStart, int times, int expectedResult) {
        assertThat(puzzle(aStart, bStart, times)).hasPartOneSolution(expectedResult);
    }

    private void assertPartTwo(int aStart, int bStart, int times, int expectedResult) {
        assertThat(puzzle(aStart, bStart, times)).hasPartTwoSolution(expectedResult);
    }

    private AdventOfCodePuzzle<Integer, Integer> puzzle(int aStart, int bStart, int times) {
        return new Day15(
                new GeneratorInput(aStart, 16807),
                new GeneratorInput(bStart, 48271),
                16,
                times
        );
    }
}
