package com.adventofcode;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;
import static java.util.stream.Collectors.joining;

import java.util.Collections;
import java.util.List;

public abstract class AdventOfCodePuzzleSolutions<R1, R2> {

    protected void assertPartOne(String input, R1 expectedOutput) {
        assertThat(puzzle(input)).hasPartOneSolution(expectedOutput);
    }

    protected void assertPartOne(List<String> input, R1 expectedOutput) {
        assertThat(puzzle(input)).hasPartOneSolution(expectedOutput);
    }

    protected void assertPartTwo(String input, R2 expectedOutput) {
        assertThat(puzzle(input)).hasPartTwoSolution(expectedOutput);
    }

    protected void assertPartTwo(List<String> input, R2 expectedOutput) {
        assertThat(puzzle(input)).hasPartTwoSolution(expectedOutput);
    }

    protected AdventOfCodePuzzle<R1, R2> puzzle(String input) {
        return puzzle(Collections.singletonList(input));
    }

    protected AdventOfCodePuzzle<R1, R2> puzzle(List<String> input) {
        return puzzle(input.stream().collect(joining()));
    }

}
