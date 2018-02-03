package com.adventofcode.day6;

class Cycle {

    private final int beginsAfterNOperation;
    private final int length;

    Cycle(int beginsAfterNOperation, int length) {
        this.beginsAfterNOperation = beginsAfterNOperation;
        this.length = length;
    }

    public int getBeginsAfterNOperation() {
        return beginsAfterNOperation;
    }

    public int getLength() {
        return length;
    }
}
