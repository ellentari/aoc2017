package com.adventofcode.day25;

import org.junit.Test;

import java.util.Optional;

public class TMParsersTest {

    @Test
    public void name() {
        State state = TMParsers.state().from("In state A:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state B.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 0.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state C.").get();
    }
}