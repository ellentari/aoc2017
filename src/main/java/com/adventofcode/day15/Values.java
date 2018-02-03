package com.adventofcode.day15;

public class Values {

    private Values() {

    }

    static boolean all(int value) {
        return true;
    }

    static boolean multipleOf4(int value) {
        return isMultipleOf(value, 4);
    }

    static boolean multipleOf8(int value) {
        return isMultipleOf(value, 8);
    }

    private static boolean isMultipleOf(int value, int of) {
        return value % of == 0;
    }
}
