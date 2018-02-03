package com.adventofcode.day15;

class Judge {

    private final int lowestBitsMask;

    Judge(int lowestBitsToMatch) {
        this.lowestBitsMask = (1 << lowestBitsToMatch) - 1;
    }

    boolean match(int first, int second) {
        return lowestBitsEqual(first, second);
    }

    private boolean lowestBitsEqual(int first, int second) {
        return lowestBitsOf(first) == lowestBitsOf(second);
    }

    private int lowestBitsOf(int value) {
        return value & lowestBitsMask;
    }

}
