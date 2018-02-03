package com.adventofcode.day14;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.common.Resources;

import org.junit.Test;

public class Day14Solutions extends AdventOfCodePuzzleSolutions<Integer, Integer> {

    @Test
    public void partOneSamples() {
        assertPartOne("flqrgnkx", 8108);
    }

    @Test
    public void partOne() {
        assertPartOne("oundnydw", 8106);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo("flqrgnkx", 1242);
    }

    @Test
    public void partTwo() {
        assertPartTwo("oundnydw", 1164);
    }

    @Override
    protected AdventOfCodePuzzle<Integer, Integer> puzzle(String input) {
        return new Day14(input);
    }
}
