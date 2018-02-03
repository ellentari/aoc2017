package com.adventofcode.day25;

interface ITuringMachine {

    void changeState(String name);

    int getCurrentValue();

    void writeCurrentValue(int value);

    void move(TuringMachine.Direction direction);

}
