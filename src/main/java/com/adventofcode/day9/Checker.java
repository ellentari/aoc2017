package com.adventofcode.day9;

interface Checker {

    boolean appliesTo(State state, char character);

    default Checker and(Checker checker) {
        return (state, character) -> appliesTo(state, character) && checker.appliesTo(state, character);
    }

}
