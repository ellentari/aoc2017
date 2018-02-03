package com.adventofcode.day3;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;

import org.junit.Test;

public class Day3Solutions extends AdventOfCodePuzzleSolutions<Integer, Integer> {

    @Test
    public void partOneSamples() {
        assertPartOne("1", 0);
        assertPartOne("12", 3);
        assertPartOne("23", 2);
        assertPartOne("15", 2);
        assertPartOne("19", 2);
        assertPartOne("1024", 31);
    }

    @Test
    public void partOne() {
        assertPartOne("265149", 438);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo("1", 2);
        assertPartTwo("26", 54);
    }

    @Test
    public void partTwo() {
        assertPartTwo("265149", 266330);
    }

    @Override
    protected AdventOfCodePuzzle<Integer, Integer> puzzle(String input) {
        return new Day3(Integer.parseInt(input));
    }
}
