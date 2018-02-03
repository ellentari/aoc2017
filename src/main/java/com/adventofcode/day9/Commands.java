package com.adventofcode.day9;

class Commands {

    private Commands() {

    }

    static Command ignore() {
        return (state, character) -> state.ignoreNext(false);
    }

    static Command ignoreNextCharacter() {
        return (state, character) -> state.ignoreNext(true);
    }

    private static Command startSequence(State.Sequence sequence) {
        return (state, character) -> state.startSequence(character, sequence);
    }

    private static Command endSequence() {
        return (state, character) -> state.pop();
    }

    private static Command incNesting() {
        return (state, character) -> state.incNesting(State.Sequence.GROUP);
    }

    private static Command decNesting() {
        return (state, character) -> state.decNesting(State.Sequence.GROUP);
    }

    static Command startNewGroup() {
        return startSequence(State.Sequence.GROUP).andThen(incNesting());
    }

    static Command startNewGarbage() {
        return startSequence(State.Sequence.GARBAGE);
    }

    static Command endGroup() {
        return endSequence().andThen(recordSequence(State.Sequence.GROUP).andThen(decNesting()));
    }

    static Command endGarbage() {
        return endSequence();
    }

    private static Command recordSequence(State.Sequence sequence) {
        return (state, character) -> state.flush(sequence);
    }

    static Command recordSequenceCharacter() {
        return (state, character) -> state.incLength();
    }
}
