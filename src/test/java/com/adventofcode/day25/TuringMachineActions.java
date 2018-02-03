package com.adventofcode.day25;

import java.util.function.Consumer;

public class TuringMachineActions {

    static Consumer<ITuringMachine> writeValue(int value) {
        return iTuringMachine -> iTuringMachine.writeCurrentValue(value);
    }

    static Consumer<ITuringMachine> changeState(String newState) {
        return iTuringMachine -> iTuringMachine.changeState(newState);
    }

    static Consumer<ITuringMachine> move(TuringMachine.Direction direction) {
        return iTuringMachine -> iTuringMachine.move(direction);
    }
}
