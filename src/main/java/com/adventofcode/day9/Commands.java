package com.adventofcode.day9;

import com.adventofcode.day9.StreamCharactersReader.SequenceType;

import static com.adventofcode.day9.StreamCharactersReader.SequenceType.GARBAGE;
import static com.adventofcode.day9.StreamCharactersReader.SequenceType.GROUP;

class Commands {

    private Commands() { }

    static Command ignoreCharacter() {
        return (state, character) -> state.resetIgnoreNext();
    }

    static Command ignoreNextCharacter() {
        return (state, character) -> state.ignoreNext();
    }

    static Command startNewGroup() {
        return startSequence(GROUP)
                .andThen(incNestingForGroup());
    }

    static Command startNewGarbage() {
        return startSequence(GARBAGE);
    }

    static Command endGroup() {
        return endCurrentSequence()
                .andThen(recordSequence(GROUP))
                .andThen(decNestingForGroup());
    }

    static Command endGarbage() {
        return endCurrentSequence();
    }

    static Command countSequenceCharacter() {
        return (state, character) -> state.incCurrentSequenceLength();
    }

    private static Command startSequence(SequenceType seqType) {
        return (state, character) -> state.startSequence(seqType);
    }

    private static Command endCurrentSequence() {
        return (state, character) -> state.endCurrentSequence();
    }

    private static Command incNestingForGroup() {
        return (state, character) -> state.incNestingOf(GROUP);
    }

    private static Command decNestingForGroup() {
        return (state, character) -> state.decNestingOf(GROUP);
    }

    private static Command recordSequence(SequenceType seqType) {
        return (state, character) -> state.flush(seqType);
    }

    interface Command {

        void execute(StreamCharactersReader.State state, char currentChar);

        default Command andThen(Command command) {
            return (state, character) -> { execute(state, character); command.execute(state, character); };
        }

    }
}
