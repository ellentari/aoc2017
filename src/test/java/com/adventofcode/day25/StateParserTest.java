package com.adventofcode.day25;

import static com.adventofcode.day25.State.Definition.DefinitionBuilder.on;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import com.adventofcode.common.Parsers;

import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StateParserTest {

    @Test
    public void stateName() {
        String instruction = "In state A:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state B.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 0.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state B.";

        State state = parseStateDefinitionFrom(instruction);

        assertThat(state.getName()).isEqualTo("A");
    }

    @Test
    public void case0() {
        State state = parseStateDefinitionFrom("In state A:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state B.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 0.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state B.");

        TuringMachineStub turingMachine = new TuringMachineStub();
        turingMachine.setCurrentValue(0);

        state.performOn(turingMachine);

        assertThat(turingMachine.getCurrentValue()).isEqualTo(1);
        assertThat(turingMachine.getDirectionMoved()).isEqualTo(TuringMachine.Direction.RIGHT);
        assertThat(turingMachine.getState()).isEqualTo("B");
    }

    @Test
    public void case1() {
        State state = parseStateDefinitionFrom(
                "In state A:\n" +
                "  If the current value is 0:\n" +
                "    - Write the value 1.\n" +
                "    - Move one slot to the right.\n" +
                "    - Continue with state B.\n" +
                "  If the current value is 1:\n" +
                "    - Write the value 0.\n" +
                "    - Move one slot to the left.\n" +
                "    - Continue with state C.");

        TuringMachineStub turingMachine = new TuringMachineStub();
        turingMachine.setCurrentValue(1);

        state.performOn(turingMachine);

        assertThat(turingMachine.getCurrentValue()).isEqualTo(0);
        assertThat(turingMachine.getDirectionMoved()).isEqualTo(TuringMachine.Direction.LEFT);
        assertThat(turingMachine.getState()).isEqualTo("C");
    }

    private State parseStateDefinitionFrom(String instruction) {
        String stateName = Parsers.parserFor("In state (\\w):", matcher -> matcher.group(1))
                .from(instruction).orElseThrow(IllegalStateException::new);

        Predicate<ITuringMachine> predicate = Parsers.parserFor("If the current value is (\\d+):",
                matcher -> ITuringMachinePredicates.isCurrentValue(Integer.parseInt(matcher.group(1))))
                .from(instruction).orElseThrow(IllegalStateException::new);

        Consumer<ITuringMachine> writeValue = Parsers.parserFor("Write the value (\\d+).",
                matcher -> TuringMachineActions.writeValue(Integer.parseInt(matcher.group(1))))
                .from(instruction).orElseThrow(IllegalStateException::new);

        Consumer<ITuringMachine> move = Parsers.parserFor("Move one slot to the (\\w+)",
                matcher -> TuringMachineActions.move(TuringMachine.Direction.from(matcher.group(1))))
                .from(instruction).orElseThrow(IllegalStateException::new);

        Consumer<ITuringMachine> changeState = Parsers.parserFor("Continue with state (\\w).",
                matcher -> TuringMachineActions.changeState(matcher.group(1)))
                .from(instruction).orElseThrow(IllegalStateException::new);





        return new State(stateName, asList(
                on(predicate).thenDo(writeValue.andThen(move).andThen(changeState))
        ));
    }



    static class TuringMachineStub implements ITuringMachine {

        private int currentValue;

        private String state;

        private TuringMachine.Direction directionMoved;

        void setCurrentValue(int currentValue) {
            this.currentValue = currentValue;
        }

        @Override
        public int getCurrentValue() {
            return currentValue;
        }

        @Override
        public void changeState(String name) {
            this.state = name;
        }

        @Override
        public void move(TuringMachine.Direction direction) {
            directionMoved = direction;
        }

        @Override
        public void writeCurrentValue(int value) {
            currentValue = value;
        }

        TuringMachine.Direction getDirectionMoved() {
            return directionMoved;
        }

        String getState() {
            return state;
        }
    }
}
