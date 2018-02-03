package com.adventofcode.day4;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.common.Resources;

import org.junit.Test;

import java.util.List;

public class Day4Solutions extends AdventOfCodePuzzleSolutions<Integer, Integer> {

    private static final String INPUT = "day4.txt";

    @Test
    public void partOneSamples() {
        assertPartOne("aa bb cc dd ee", 1);
        assertPartOne("aa bb cc dd aa", 0);
        assertPartOne("aa bb cc dd aaa", 1);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.lines(INPUT), 383);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo("abcde fghij", 1);
        assertPartTwo("abcde xyz ecdab", 0);
        assertPartTwo("a ab abc abd abf abj", 1);
        assertPartTwo("iiii oiii ooii oooi oooo", 1);
        assertPartTwo("oiii ioii iioi iiio", 0);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.lines(INPUT), 265);
    }

    @Override
    protected AdventOfCodePuzzle<Integer, Integer> puzzle(List<String> input) {
        return new Day4(input);
    }
}
