package com.adventofcode.day16;

class Spin implements Move {

    private final int x;

    Spin(int x) {
        this.x = x;
    }

    @Override
    public void doMove(StringBuilder result) {
        for (int i = 0; i < x; i++) {
            result.insert(0, result.charAt(result.length() - 1));
            result.deleteCharAt(result.length() - 1);
        }
    }
}
