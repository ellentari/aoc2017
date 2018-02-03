package com.adventofcode.day8;

import static java.util.Optional.ofNullable;

import com.adventofcode.AdventOfCodePuzzle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 8: I Heard You Like Registers ---
 *
 * You receive a signal directly from the CPU.
 * Because of your recent assistance with jump instructions,
 * it would like you to compute the result of a series of unusual register instructions.
 *
 * Each instruction consists of several parts: the register to modify,
 * whether to increase or decrease that register's value,
 * the amount by which to increase or decrease it, and a condition.
 * If the condition fails, skip the instruction without modifying the register.
 * The registers all start at 0. The instructions look like this:
 *
 * b inc 5 if a > 1
 * a inc 1 if b < 5
 * c dec -10 if a >= 1
 * c inc -20 if c == 10
 * These instructions would be processed as follows:
 *
 * Because a starts at 0, it is not greater than 1, and so b is not modified.
 * a is increased by 1 (to 1) because b is less than 5 (it is 0).
 * c is decreased by -10 (to 10) because a is now greater than or equal to 1 (it is 1).
 * c is increased by -20 (to -10) because c is equal to 10.
 * After this process, the largest value in any register is 1.
 *
 * You might also encounter <= (less than or equal to) or != (not equal to).
 * However, the CPU doesn't have the bandwidth to tell you what all
 * the registers are named, and leaves that to you to determine.
 *
 * What is the largest value in any register after completing the
 * instructions in your puzzle input?
 *
 * --- Part Two ---
 *
 * To be safe, the CPU also needs to know the highest value held in
 * any register during this process so that it can decide how much
 * memory to allocate to these operations. For example,
 * in the above instructions, the highest value ever held was 10
 * (in register c after the third instruction was evaluated).
 *
 */
public class Day8 implements AdventOfCodePuzzle<Integer, Integer> {

    private final List<String> instructions;

    Day8(List<String> instructions) {
        this.instructions = instructions;
    }

    @Override
    public Integer solvePartOne() {
        Map<String, Integer> registers = new HashMap<>();

        for (String instruction : instructions) {
            String[] tokens = instruction.split("\\s+");

            String registerToModify = tokens[0];
            String howToModify = tokens[1];
            int byHowMany = Integer.parseInt(tokens[2]);

            String ifRegister = tokens[4];
            String ifOpertator = tokens[5];
            int operand = Integer.parseInt(tokens[6]);


            if (checkCondition(registers.computeIfAbsent(ifRegister, register -> 0), ifOpertator, operand)) {
                registers.compute(registerToModify, (register, value) -> compute(ofNullable(value).orElse(0), howToModify, byHowMany));
            }
        }

        return registers.values().stream().mapToInt(i -> i).max().orElse(-1);
    }

    private int compute(int registerValue, String operator, int operand) {
        switch (operator) {
            case "inc" : return registerValue + operand;
            case "dec" : return registerValue - operand;
            default: throw new IllegalArgumentException(operator);
        }
    }

    private boolean checkCondition(int registerValue, String operator, int operand) {
        switch (operator) {
            case ">" : return registerValue > operand;
            case "<" : return registerValue < operand;
            case ">=" : return registerValue >= operand;
            case "<=" : return registerValue <= operand;
            case "==" : return registerValue == operand;
            case "!=" : return registerValue != operand;
            default: throw new IllegalArgumentException(operator);
        }
    }

    @Override
    public Integer solvePartTwo() {
        Map<String, Integer> registers = new HashMap<>();

        int maxValue = Integer.MIN_VALUE;

        for (String instruction : instructions) {
            String[] tokens = instruction.split("\\s+");

            String firstRegister = tokens[0];
            String firstOperator = tokens[1];
            int firstOperand = Integer.parseInt(tokens[2]);

            String secondRegister = tokens[4];
            String secondOperator = tokens[5];
            int secondOperand = Integer.parseInt(tokens[6]);


            if (checkCondition(registers.computeIfAbsent(secondRegister, register -> 0), secondOperator, secondOperand)) {
                maxValue = Math.max(maxValue, registers.compute(firstRegister, (register, value) -> compute(ofNullable(value).orElse(0), firstOperator, firstOperand)));
            }
        }

        return maxValue;
    }
}
