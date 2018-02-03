package com.adventofcode.common;

import com.adventofcode.AdventOfCodePuzzle;

import org.assertj.core.api.SoftAssertions;

public class AdventOfCodeAssertions extends SoftAssertions {

    public <R1, R2> AdventOfCodePuzzleAssert<R1, R2> assertThat(AdventOfCodePuzzle<R1, R2> actual) {
        return proxy(AdventOfCodePuzzleAssert.class, AdventOfCodePuzzle.class, actual);
    }
}
