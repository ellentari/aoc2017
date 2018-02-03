package com.adventofcode.day23;

import static java.util.Arrays.asList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.day18.Day18;
import com.adventofcode.day18.InstructionsParsers;
import com.adventofcode.day18.Program;
import com.adventofcode.day18.Registers;
import com.adventofcode.day18.SimpleInstructionsExecutor;

import java.util.List;

/**
 * --- Day 23: Coprocessor Conflagration ---
 *
 * You decide to head directly to the CPU and fix the printer from there. As you get close,
 * you find an experimental coprocessor doing so much work that the local programs are
 * afraid it will halt and catch fire. This would cause serious issues for the rest of
 * the computer, so you head in and see what you can do.
 *
 * The code it's running seems to be a variant of the kind you saw recently on that tablet.
 * The general functionality seems very similar, but some of the instructions are different:
 *
 * set X Y sets register X to the value of Y.
 * sub X Y decreases register X by the value of Y.
 * mul X Y sets register X to the result of multiplying the value contained in register
 * X by the value of Y.
 * jnz X Y jumps with an offset of the value of Y, but only if the value of X is not zero.
 * (An offset of 2 skips the next instruction, an offset of -1 jumps to the previous
 * instruction, and so on.)
 * Only the instructions listed above are used. The eight registers here, named a through
 * h, all start at 0.
 *
 * The coprocessor is currently set to some kind of debug mode, which allows for testing,
 * but prevents it from doing any meaningful work.
 *
 * If you run the program (your puzzle input), how many times is the mul instruction invoked?
 *
 * --- Part Two ---
 *
 * Now, it's time to fix the problem.
 *
 * The debug mode switch is wired directly to register a. You flip the switch,
 * which makes register a now start at 1 when the program is executed.
 *
 * Immediately, the coprocessor begins to overheat. Whoever wrote this program obviously
 * didn't choose a very efficient implementation. You'll need to optimize the program
 * if it has any hope of completing before Santa needs that printer working.
 *
 * The coprocessor's ultimate goal is to determine the final value left in register h
 * once the program completes. Technically, if it had that... it wouldn't even need
 * to run the program.
 *
 * After setting register a to 1, if the program were to run to completion, what value
 * would be left in register h?
 *
 */
class Day23 implements AdventOfCodePuzzle<Integer, Integer> {

    private final List<String> input;

    Day23(List<String> input) {
        this.input = input;
    }

    @Override
    public Integer solvePartOne() {
        int[] multiplicationsCount = {0};

        Program program = new SimpleInstructionsExecutor(new Registers(), Day18.parseInputInstructions(input, asList(
                InstructionsParsers.setRegister(),
                InstructionsParsers.decRegister(),
                InstructionsParsers.mulRegister(() -> multiplicationsCount[0]++),
                InstructionsParsers.jump("jnz", value -> value != 0)
        )));

        while (!program.isTerminated()) {
            program.executeNext();
        }

        return multiplicationsCount[0];
    }

    @Override
    public Integer solvePartTwo() {
        return optimized(valueFrom(input.get(0)));
    }

    private int valueFrom(String instruction) {
        return Integer.parseInt(instruction.split(" ")[2]);
    }

    private int optimized(int initialB) {
        int start = initialB * 100 + 100_000;
        int end = start + 17_000;

        int compositeCount = 0;

        for (int b = start; b <= end; b += 17) {
            if (!isPrime(b)) {
                compositeCount++;
            }
        }

        return compositeCount;
    }

    private boolean isPrime(int number) {
        double sqrt = Math.sqrt(number);

        for (int i = 2; i <= sqrt; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
