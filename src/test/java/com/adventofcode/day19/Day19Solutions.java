package com.adventofcode.day19;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;
import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.common.Resources;
import com.adventofcode.day18.Day18;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class Day19Solutions {

    private static final String INPUT = "day19.txt";

    @Test
    public void partOneSamples() {
        assertPartOne(asList(
                "     |          ",
                "     |  +--+    ",
                "     A  |  C    ",
                " F---|----E|--+ ",
                "     |  |  |  D ",
                "     +B-+  +--+ "), "ABCDEF");
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.lines(INPUT), "EPYDUXANIT");
    }

    @Test
    public void turn() {
        Day19.Turn turnLeft = Day19.turn(asList(
                " | ",
                "-+ "
        ), 1, 1, 'd');

        Assertions.assertThat(turnLeft).isEqualTo(new Day19.Turn(1, 0, 'l'));

        Day19.Turn turnRight = Day19.turn(asList(
                " | ",
                " +-"
        ), 1, 1, 'd');

        Assertions.assertThat(turnRight).isEqualTo(new Day19.Turn(1, 2, 'r'));

        Day19.Turn turnUp = Day19.turn(asList(
                " | ",
                " +-"
        ), 1, 1, 'l');

        Assertions.assertThat(turnUp).isEqualTo(new Day19.Turn(0, 1, 'u'));

        Day19.Turn turnDown = Day19.turn(asList(
                " +-",
                " | "
        ), 0, 1, 'l');

        Assertions.assertThat(turnDown).isEqualTo(new Day19.Turn(1, 1, 'd'));
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo(asList(
                "     |          ",
                "     |  +--+    ",
                "     A  |  C    ",
                " F---|----E|--+ ",
                "     |  |  |  D ",
                "     +B-+  +--+ "), 38);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.lines(INPUT), 17544);
    }

    private void assertPartOne(List<String> instructions, String expectedResult) {
        assertThat(puzzle(instructions)).hasPartOneSolution(expectedResult);
    }

    private void assertPartTwo(List<String> instructions, Integer expectedResult) {
        assertThat(puzzle(instructions)).hasPartTwoSolution(expectedResult);
    }

    private AdventOfCodePuzzle<String, Integer> puzzle(List<String> instructions) {
        return new Day19(instructions);
    }
}
