package com.adventofcode.day8;

import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.common.Resources;

import org.junit.Test;

import java.util.List;

public class Day8Solutions extends AdventOfCodePuzzleSolutions<Integer, Integer> {

    private static final String INPUT = "day8.txt";

    @Test
    public void partOneSamples() {
        assertPartOne(asList(
                "b inc 5 if a > 1",
                "a inc 1 if b < 5",
                "c dec -10 if a >= 1",
                "c inc -20 if c == 10"), 1);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.lines(INPUT), 5221);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo(asList(
                "b inc 5 if a > 1",
                "a inc 1 if b < 5",
                "c dec -10 if a >= 1",
                "c inc -20 if c == 10"), 10);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.lines(INPUT), 7491);
    }

    @Override
    protected AdventOfCodePuzzle<Integer, Integer> puzzle(List<String> input) {
        return new Day8(input);
    }

}
