package com.adventofcode.day11;

import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.common.Resources;

import org.junit.Test;

public class Day11Solutions extends AdventOfCodePuzzleSolutions<Integer, Integer> {

    private static final String INPUT = "day11.txt";

    @Test
    public void partOneSamples() {
        assertPartOne("ne,ne,ne", 3);
        assertPartOne("ne,ne,sw,sw", 0);
        assertPartOne("ne,ne,s,s", 2);
        assertPartOne("se,sw,se,sw,sw", 3);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.singleLine(INPUT), 747);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.singleLine(INPUT), 1544);
    }

    @Override
    protected AdventOfCodePuzzle<Integer, Integer> puzzle(String input) {
        return new Day11(asList(input.split(",")));
    }
}
