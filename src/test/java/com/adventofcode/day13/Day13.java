package com.adventofcode.day13;

import static java.util.stream.Collectors.toList;

import com.adventofcode.AdventOfCodePuzzle;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day13 implements AdventOfCodePuzzle<Integer, Integer> {

    private final List<String> input;

    public Day13(List<String> input) {
        this.input = input;
    }

    @Override
    public Integer solvePartOne() {
        List<Layer> layers = parse();

        return getSeverity(0, layers)[0];
    }

    private List<Layer> parse() {
        return input.stream()
                .map(line -> line.split(": "))
                .map(split -> new Layer(Integer.parseInt(split[0]), new Scanner(Integer.parseInt(split[1]))))
                .collect(toList());
    }


    @Override
    public Integer solvePartTwo() {
        List<Layer> layers = parse();

        int delay = 0;

        while (getSeverity(delay, layers)[1] != 0) {
            delay++;
        }

        return delay;
    }

    private int[] getSeverity(int delay, List<Layer> parse) {
        Queue<Layer> layers = new LinkedList<>(parse);

        int currentLevel = 0;

        int totalSeverity = 0;
        int numberOfHits = 0;

        int moment = delay;

        while (!layers.isEmpty()) {
            Layer peek = layers.peek();

            if (peek.depth == currentLevel) {
                layers.poll();

                int currentScannerPosition = peek.scannerPosition(moment);

                if (currentScannerPosition == 0) {
                    totalSeverity += (peek.depth * peek.scanner.range);
                    numberOfHits++;
                }
            }

            currentLevel++;
            moment++;

        }

        return new int[] {totalSeverity, numberOfHits};
    }


    private static class Layer {

        final int depth;
        final Scanner scanner;

        Layer(int depth, Scanner scanner) {
            this.depth = depth;
            this.scanner = scanner;
        }

        int scannerPosition(int at) {
            return scanner.positionAt(at);
        }
    }

    private static class Scanner {

        final int range;
        final int possiblePositions;

        Scanner(int range) {
            this.range = range;
            this.possiblePositions = range * 2 - 2;
        }

        public int positionAt(int at) {
            int currentPosition = at % possiblePositions;

            if (currentPosition >= range) {
                currentPosition = range - currentPosition / range;
            }

            return currentPosition;
        }
    }
}
