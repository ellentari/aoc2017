package com.adventofcode.day18;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;
import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.common.Resources;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class Day18Solutions {

    private static final String INPUT = "day18.txt";

    @Test
    public void partOneSamples() {
        assertPartOne(asList("set a 1",
                "add a 2",
                "mul a a",
                "mod a 5",
                "snd a",
                "set a 0",
                "rcv a",
                "jgz a -1",
                "set a 1",
                "jgz a -2"), 4L);


        assertPartOne(Collections.singletonList("jgz 1 -2"), null);
        assertPartOne(Collections.singletonList("jgz 1 2"), null);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.lines(INPUT), 2951L);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo(asList("snd 1",
                "snd 2",
                "snd p",
                "rcv a",
                "rcv b",
                "rcv c",
                "rcv d"), 3);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.lines(INPUT), 7366);
    }

    private void assertPartOne(List<String> instructions, Long expectedResult) {
        assertThat(puzzle(instructions)).hasPartOneSolution(expectedResult);
    }

    private void assertPartTwo(List<String> instructions, Integer expectedResult) {
        assertThat(puzzle(instructions)).hasPartTwoSolution(expectedResult);
    }

    private AdventOfCodePuzzle<Long, Integer> puzzle(List<String> instructions) {
        return new Day18(instructions);
    }
}
