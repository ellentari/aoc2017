package com.adventofcode.day5;

import com.adventofcode.AdventOfCodePuzzle;

import java.util.Arrays;

public class Day5 implements AdventOfCodePuzzle<Integer, Integer> {

    private final int[] instructions;

    Day5(int[] input) {
        this.instructions = Arrays.copyOf(input, input.length);
    }

    @Override
    public Integer solvePartOne() {
        int instructions[] = Arrays.copyOf(this.instructions, this.instructions.length);

        int current = 0;
        int count = 0;

        while (current >= 0 && current < instructions.length) {
            current = current + instructions[current]++;
            count++;
        }

        return count;
    }

    @Override
    public Integer solvePartTwo() {
        int instructions[] = Arrays.copyOf(this.instructions, this.instructions.length);

        int current = 0;
        int count = 0;

        while (current >= 0 && current < instructions.length) {
            int curBefore = current;

            int offset = instructions[current];
            current = current + offset;

            if (offset >= 3) {
                instructions[curBefore]--;
            } else {
                instructions[curBefore]++;
            }


            count++;
        }

        return count;
    }
}
