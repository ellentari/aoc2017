package com.adventofcode.day23;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.common.Resources;

import org.junit.Test;

import java.util.List;

public class Day23Solutions {

    private static final String INPUT = "day23.txt";

    @Test
    public void partOne() {
        assertPartOne(Resources.lines(INPUT), 5929);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.lines(INPUT).subList(0, 1), 907);
    }

    private void assertPartOne(List<String> instructions, int expectedResult) {
        assertThat(puzzle(instructions)).hasPartOneSolution(expectedResult);
    }

    private void assertPartTwo(List<String> instructions, int expectedResult) {
        assertThat(puzzle(instructions)).hasPartTwoSolution(expectedResult);
    }

    private AdventOfCodePuzzle<Integer, Integer> puzzle(List<String> instructions) {
        return new Day23(instructions);
    }
}
