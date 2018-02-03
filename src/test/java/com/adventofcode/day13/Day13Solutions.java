package com.adventofcode.day13;

import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.common.Resources;
import com.adventofcode.day12.Day12;

import org.junit.Test;

import java.util.List;

public class Day13Solutions extends AdventOfCodePuzzleSolutions<Integer, Integer> {

    private static final String INPUT = "day13.txt";

    @Test
    public void partOneSamples() {
        assertPartOne(asList("0: 3",
                "1: 2",
                "4: 4",
                "6: 4"), 24);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.lines(INPUT), 2164);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo(asList("0: 3",
                "1: 2",
                "4: 4",
                "6: 4"), 10);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.lines(INPUT), 3861798);
    }

    @Override
    protected AdventOfCodePuzzle<Integer, Integer> puzzle(List<String> input) {
        return new Day13(input);
    }
}
