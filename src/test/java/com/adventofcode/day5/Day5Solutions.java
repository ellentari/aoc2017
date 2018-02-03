package com.adventofcode.day5;

import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.common.Resources;

import org.junit.Test;

import java.util.List;

public class Day5Solutions extends AdventOfCodePuzzleSolutions<Integer, Integer> {

    private static final String INPUT = "day5.txt";

    @Test
    public void partOneSamples() {
        assertPartOne(asList("0", "3", "0", "1", "-3"), 5);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.lines(INPUT), 354121);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo(asList("0", "3", "0", "1", "-3"), 10);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.lines(INPUT), 27283023);
    }

    @Override
    protected AdventOfCodePuzzle<Integer, Integer> puzzle(List<String> input) {
        return new Day5(input.stream().mapToInt(Integer::parseInt).toArray());
    }
}
