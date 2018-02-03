package com.adventofcode.day1;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.common.Resources;

import org.junit.Test;

public class Day1Solutions extends AdventOfCodePuzzleSolutions<Integer, Integer> {

    private static final String INPUT = "day1.txt";

    @Test
    public void partOneSamples() {
        assertPartOne("", 0);
        assertPartOne("1122", 3);
        assertPartOne("1111", 4);
        assertPartOne("1234", 0);
        assertPartOne("91212129", 9);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.singleLine(INPUT), 1141);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo("", 0);
        assertPartTwo("1212", 6);
        assertPartTwo("1221", 0);
        assertPartTwo("123425", 4);
        assertPartTwo("123123", 12);
        assertPartTwo("12131415", 4);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.singleLine(INPUT), 950);
    }

    @Override
    protected AdventOfCodePuzzle<Integer, Integer> puzzle(String input) {
        return new Day1(input);
    }
}
