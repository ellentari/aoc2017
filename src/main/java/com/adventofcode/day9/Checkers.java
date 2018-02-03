package com.adventofcode.day9;

class Checkers {

    private Checkers() {
    }

    static Checker isIgnored() {
        return (state, character) -> state.isNextIgnored();
    }

    static Checker isCharacter(char character) {
        return (state, ch) -> ch == character;
    }

    static Checker isGarbage() {
        return (state, ch) -> state.isSequence(State.Sequence.GARBAGE);
    }
}
