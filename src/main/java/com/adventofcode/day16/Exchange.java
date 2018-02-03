package com.adventofcode.day16;

class Exchange implements Move {

    private final int first;
    private final int second;

    Exchange(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void doMove(StringBuilder result) {
        char tmp = result.charAt(first);
        result.setCharAt(first, result.charAt(second));
        result.setCharAt(second, tmp);
    }
}
