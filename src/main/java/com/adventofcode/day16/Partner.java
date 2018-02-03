package com.adventofcode.day16;

class Partner implements Move {

    private final char first;
    private final char second;

    Partner(char first, char second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void doMove(StringBuilder result) {
        int p1 = result.indexOf(String.valueOf(first));
        int p2 = result.indexOf(String.valueOf(second));

        new Exchange(p1, p2).doMove(result);
    }
}
