package com.adventofcode.day25;

import java.util.function.Predicate;

class ITuringMachinePredicates {

    static Predicate<ITuringMachine> isCurrentValue(int value) {
        return iTuringMachine -> iTuringMachine.getCurrentValue() == value;
    }

}
