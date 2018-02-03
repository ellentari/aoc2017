package com.adventofcode.day6;

import static com.adventofcode.common.ParsingUtils.parseIntArray;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.common.Resources;

import org.junit.Test;

public class Day6Solutions extends AdventOfCodePuzzleSolutions<Integer, Integer> {

    private static final String INPUT = "day6.txt";

    @Test
    public void partOneSamples() {
        assertPartOne("0 2 7 0", 5);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.singleLine(INPUT), 12841);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo("0 2 7 0", 4);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.singleLine(INPUT), 8038);
    }

    @Override
    protected AdventOfCodePuzzle<Integer, Integer> puzzle(String input) {
        return new Day6(parseIntArray(input));
    }
}
