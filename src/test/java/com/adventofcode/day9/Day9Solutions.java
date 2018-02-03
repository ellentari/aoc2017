package com.adventofcode.day9;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.common.Resources;

import org.junit.Test;

public class Day9Solutions extends AdventOfCodePuzzleSolutions<Integer, Integer> {

    private static final String INPUT = "day9.txt";

    @Test
    public void partOneSamples() {
        assertPartOne("{}", 1);
        assertPartOne("{{{}}}", 6);
        assertPartOne("{{},{}}", 5);
        assertPartOne("{{{},{},{{}}}}", 16);
        assertPartOne("{<a>,<a>,<a>,<a>}", 1);
        assertPartOne("{{<ab>},{<ab>},{<ab>},{<ab>}}", 9);
        assertPartOne("{{<!!>},{<!!>},{<!!>},{<!!>}}", 9);
        assertPartOne("{{<a!>},{<a!>},{<a!>},{<ab>}}", 3);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.singleLine(INPUT), 16021);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo("<>", 0);
        assertPartTwo("<random characters>", 17);
        assertPartTwo("<<<<>", 3);
        assertPartTwo("<{!>}>", 2);
        assertPartTwo("<!!>", 0);
        assertPartTwo("<!!!>>", 0);
        assertPartTwo("<{o\"i!a,<{i<a>", 10);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.singleLine(INPUT), 7685);
    }

    @Override
    protected AdventOfCodePuzzle<Integer, Integer> puzzle(String input) {
        return new Day9(input);
    }
}
