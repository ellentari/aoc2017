package com.adventofcode.day7;

import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.AdventOfCodePuzzleSolutions;
import com.adventofcode.common.Resources;

import org.junit.Test;

import java.util.List;

public class Day7Solutions extends AdventOfCodePuzzleSolutions<String, Integer> {

    private static final String INPUT = "day7.txt";

    @Test
    public void partOneSamples() {
        assertPartOne("pbga (66)", "pbga");
        assertPartOne(asList(
                "pbga (66)",
                "xhth (57)",
                "ebii (61)",
                "havc (66)",
                "ktlj (57)",
                "fwft (72) -> ktlj, cntj, xhth",
                "qoyq (66)",
                "padx (45) -> pbga, havc, qoyq",
                "tknk (41) -> ugml, padx, fwft",
                "jptl (61)",
                "ugml (68) -> gyxo, ebii, jptl",
                "gyxo (61)",
                "cntj (57)"), "tknk");
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.lines(INPUT), "eugwuhl");
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo(asList(
                "pbga (66)",
                "xhth (57)",
                "ebii (61)",
                "havc (66)",
                "ktlj (57)",
                "fwft (72) -> ktlj, cntj, xhth",
                "qoyq (66)",
                "padx (45) -> pbga, havc, qoyq",
                "tknk (41) -> ugml, padx, fwft",
                "jptl (61)",
                "ugml (68) -> gyxo, ebii, jptl",
                "gyxo (61)",
                "cntj (57)"), 60);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.lines(INPUT), 420);
    }

    @Override
    protected AdventOfCodePuzzle<String, Integer> puzzle(List<String> input) {
        return new Day7(input);
    }
}
