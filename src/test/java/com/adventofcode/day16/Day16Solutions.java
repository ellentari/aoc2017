package com.adventofcode.day16;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.common.Resources;

import org.junit.Test;

import java.util.List;

public class Day16Solutions {

    private static final String INPUT = "day16.txt";

    @Test
    public void partOneSamples() {
        assertPartOne("abcde", singletonList("s1"), "eabcd");
        assertPartOne("eabcd", singletonList("x3/4"), "eabdc");
        assertPartOne("eabdc", singletonList("pe/b"), "baedc");
        assertPartOne("abcde", asList("s1", "x3/4", "pe/b"), "baedc");
    }

    @Test
    public void partOne() {
        assertPartOne("abcdefghijklmnop", asList(Resources.singleLine(INPUT).split(",")), "kpbodeajhlicngmf");
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo("abcde", 2, asList("s1", "x3/4", "pe/b"), "ceadb");
        assertPartTwo("abcdefghijklmnop", 223,
                asList(Resources.singleLine(INPUT).split(",")), "bhdakljmfocgpeni");
    }

    @Test
    public void partTwo() {
        assertPartTwo("abcdefghijklmnop", 1_000_000_000,
                asList(Resources.singleLine(INPUT).split(",")), "ahgpjdkcbfmneloi");
    }

    private void assertPartOne(String input, List<String> moves, String expectedResult) {
        assertThat(puzzle(input, moves, 1)).hasPartOneSolution(expectedResult);
    }

    private void assertPartTwo(String input, int times, List<String> moves, String expectedResult) {
        assertThat(puzzle(input, moves, times)).hasPartTwoSolution(expectedResult);
    }

    private AdventOfCodePuzzle<String, String> puzzle(String input, List<String> moves, int times) {
        return new Day16(input, moves, times);
    }
}
