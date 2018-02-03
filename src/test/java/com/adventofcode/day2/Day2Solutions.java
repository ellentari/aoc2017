package com.adventofcode.day2;

import static com.adventofcode.common.ParsingUtils.parseGrid;
import static com.adventofcode.common.Resources.singleLine;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.common.Resources;

import org.junit.Test;

public class Day2Solutions extends AdventOfCodePuzzleSolutions<Integer, Integer> {

    private static final String INPUT = "day2.txt";

    @Test
    public void partOneSamples() {
        assertPartOne("", 0);
        assertPartOne("5 9 2 8\n9 4 7 3\n3 8 6 5", 18);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.singleLine(INPUT), 47136);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo("", 0);
        assertPartTwo("5 9 2 8\n9 4 7 3\n3 8 6 5", 9);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.singleLine(INPUT), 250);
    }

    @Override
    protected AdventOfCodePuzzle<Integer, Integer> puzzle(String input) {
        return new Day2(parseGrid(input));
    }
}
