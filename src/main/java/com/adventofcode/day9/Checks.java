package com.adventofcode.day9;

class Checks {

    private Checks() { }

    static Check onNextAfterIgnoreCharacter() {
        return (state, character) -> state.isNextIgnored();
    }

    static Check onCharacter(char character) {
        return (state, ch) -> ch == character;
    }

    static Check within(StreamCharactersReader.SequenceType seqType) {
        return (state, ch) -> state.isCurrentSequence(seqType);
    }

    interface Check {

        boolean appliesTo(StreamCharactersReader.State state, char character);

        default Check and(Check check) {
            return (state, character) -> appliesTo(state, character) && check.appliesTo(state, character);
        }

    }
}
