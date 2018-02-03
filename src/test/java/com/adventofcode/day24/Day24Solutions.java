package com.adventofcode.day24;

import static com.adventofcode.common.AdventOfCodePuzzleAssert.assertThat;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.common.Resources;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Day24Solutions {

    private static final String INPUT = "day24.txt";

    @Test
    public void partSimpleCases() {
        assertPartOne(asList("0/1", "1/10"), 12);
        assertPartOne(asList("0/1", "10/1"), 12);
        assertPartOne(asList("0/1", "4/1", "5/1"), 7);
    }

    @Test
    public void partOneSample() {
        assertPartOne(asList(
                "0/2",
                "2/2",
                "2/3",
                "3/4",
                "3/5",
                "0/1",
                "10/1",
                "9/10"), 31);
    }

    @Test
    public void partOne() {
        assertPartOne(Resources.lines(INPUT), 1695);
    }

    @Test
    public void partTwoSamples() {
        assertPartTwo(asList(
                "0/2",
                "2/2",
                "2/3",
                "3/4",
                "3/5",
                "0/1",
                "10/1",
                "9/10"), 19);
    }

    @Test
    public void partTwo() {
        assertPartTwo(Resources.lines(INPUT), 1673);
    }

    private void assertPartOne(List<String> connectors, Integer expectedResult) {
        assertThat(puzzle(connectors))
                .hasPartOneSolution(expectedResult);
    }

    private void assertPartTwo(List<String> connectors, Integer expectedResult) {
        assertThat(puzzle(connectors))
                .hasPartTwoSolution(expectedResult);
    }

    private AdventOfCodePuzzle<Integer, Integer> puzzle(List<String> input) {
        return new Day24(convertToConnectors(input));
    }

    private static List<Connector> convertToConnectors(List<String> input) {
        return input.stream().map(Day24Solutions::toConnector)
                .sorted(Comparator.comparingInt(Connector::getPort1).thenComparing(Comparator.comparingInt(Connector::getPort2)))
                .collect(toList());
    }

    private static Connector toConnector(String s) {
        String[] split = s.split("/");

        int p1 = Integer.parseInt(split[0]);
        int p2 = Integer.parseInt(split[1]);

        return new Connector(UUID.randomUUID().toString(), Math.min(p1, p2), Math.max(p1, p2));
    }
}
