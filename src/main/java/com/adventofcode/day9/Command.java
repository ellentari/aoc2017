package com.adventofcode.day9;

interface Command {

    void execute(State state, char character);

    default Command andThen(Command command) {
        return (state, character) -> { execute(state, character); command.execute(state, character); };
    }

}
