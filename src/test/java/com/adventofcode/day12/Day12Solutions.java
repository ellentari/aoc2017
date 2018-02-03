package com.adventofcode.day12;

import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.common.Resources;
import com.adventofcode.day11.Day11;

import org.junit.Test;

import java.util.List;

public class Day12Solutions extends AdventOfCodePuzzleSolutions<Integer, Integer> {

    private static final String INPUT = "day12.txt";

    @Test
    public void partOneSamples() {
        assertPartOne(asList("0 <-> 2",
                "1 <-> 1",
                "2 <-> 0, 3, 4",
                "3 <-> 2, 4",
                "4 <-> 2, 3, 6",
                "5 <-> 6",
                "6 <-> 4, 5"), 6);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.lines(INPUT), 145);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo(asList("0 <-> 2",
                "1 <-> 1",
                "2 <-> 0, 3, 4",
                "3 <-> 2, 4",
                "4 <-> 2, 3, 6",
                "5 <-> 6",
                "6 <-> 4, 5"), 2);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.lines(INPUT), 207);
    }

    @Override
    protected AdventOfCodePuzzle<Integer, Integer> puzzle(List<String> input) {
        return new Day12(0, input);
    }
}
