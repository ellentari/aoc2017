package com.adventofcode.common;

import com.adventofcode.AdventOfCodePuzzle;

import org.assertj.core.api.AbstractAssert;

import java.util.Objects;

public class AdventOfCodePuzzleAssert<R1, R2> extends AbstractAssert<AdventOfCodePuzzleAssert<R1, R2>, AdventOfCodePuzzle<R1, R2>> {

    private AdventOfCodePuzzleAssert(AdventOfCodePuzzle<R1, R2> actual) {
        super(actual, AdventOfCodePuzzleAssert.class);
    }

    public static <T1, T2> AdventOfCodePuzzleAssert<T1, T2> assertThat(AdventOfCodePuzzle<T1, T2> actual) {
        return new AdventOfCodePuzzleAssert<>(actual);
    }

    public AdventOfCodePuzzleAssert hasPartOneSolution(R1 solution) {
        isNotNull();

        R1 actualPartOneSolution = actual.solvePartOne();

        if (!Objects.equals(actualPartOneSolution, solution)) {
            failWithMessage("Expected part one solution to be <%s> but was <%s>", solution, actualPartOneSolution);
        }

        return this;
    }

    public AdventOfCodePuzzleAssert hasPartTwoSolution(R2 solution) {
        isNotNull();

        R2 actualPartTwoSolution = actual.solvePartTwo();

        if (!Objects.equals(actualPartTwoSolution, solution)) {
            failWithMessage("Expected part two solution to be <%s> but was <%s>", solution, actualPartTwoSolution);
        }

        return this;
    }


}
