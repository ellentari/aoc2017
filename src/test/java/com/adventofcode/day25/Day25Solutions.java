package com.adventofcode.day25;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;
import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.common.Resources;

import org.junit.Test;

import java.util.List;

public class Day25Solutions {
    
    private static final String INPUT = "day25.txt";

    @Test
    public void partOneSample() {
        assertPartOne("Begin in state A.\n" +
                "Perform a diagnostic checksum after 6 steps.\n" +
                "\n" +
                "In state A:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state B.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 0.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state B.\n" +
                "\n" +
                "In state B:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state A.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state A.", 6, 3);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.singleLine(INPUT), 12_173_597, 2870);
    }

    private void assertPartOne(String blueprint, int steps, long expectedResult) {
        assertThat(puzzle(blueprint, steps))
                .hasPartOneSolution(expectedResult);
    }

    private AdventOfCodePuzzle<Long, Integer> puzzle(String blueprint, int steps) {
        return new Day25(blueprint, steps);
    }

}
