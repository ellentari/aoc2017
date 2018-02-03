package com.adventofcode.day20;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;
import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.common.Resources;
import com.adventofcode.day19.Day19;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class Day20Solutions {

    private static final String INPUT = "day20.txt";

    @Test
    public void partOneSamples() {
        assertPartOne(asList(
                "p=<3,0,0>, v=<2,0,0>, a=<-1,0,0>",
                        "p=<4,0,0>, v=<0,0,0>, a=<-2,0,0>"), 0);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.lines(INPUT), 150);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo(asList(
                "p=<-6,0,0>, v=<3,0,0>, a=<0,0,0>",
                        "p=<-4,0,0>, v=<2,0,0>, a=<0,0,0>",
                        "p=<-2,0,0>, v=<1,0,0>, a=<0,0,0>",
                        "p=<3,0,0>, v=<-1,0,0>, a=<0,0,0>"), 1);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.lines(INPUT), 657);
    }

    private void assertPartOne(List<String> instructions, Integer expectedResult) {
        assertThat(puzzle(instructions)).hasPartOneSolution(expectedResult);
    }

    private void assertPartTwo(List<String> instructions, Integer expectedResult) {
        assertThat(puzzle(instructions)).hasPartTwoSolution(expectedResult);
    }

    private AdventOfCodePuzzle<Integer, Integer> puzzle(List<String> instructions) {
        return new Day20(instructions);
    }
}
