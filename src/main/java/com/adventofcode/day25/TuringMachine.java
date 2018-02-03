package com.adventofcode.day25;

import static com.adventofcode.day25.TuringMachine.Direction.LEFT;
import static java.util.Arrays.stream;

import java.util.LinkedList;

/**
 * Begin in state A.
 * Perform a diagnostic checksum after 12173597 steps.
 *
 * In state A:
 * If the current value is 0:
 * - Write the value 1.
 * - Move one slot to the right.
 * - Continue with state B.
 * If the current value is 1:
 * - Write the value 0.
 * - Move one slot to the left.
 * - Continue with state C.
 *
 * In state B:
 * If the current value is 0:
 * - Write the value 1.
 * - Move one slot to the left.
 * - Continue with state A.
 * If the current value is 1:
 * - Write the value 1.
 * - Move one slot to the right.
 * - Continue with state D.
 *
 * In state C:
 * If the current value is 0:
 * - Write the value 1.
 * - Move one slot to the right.
 * - Continue with state A.
 * If the current value is 1:
 * - Write the value 0.
 * - Move one slot to the left.
 * - Continue with state E.
 *
 * In state D:
 * If the current value is 0:
 * - Write the value 1.
 * - Move one slot to the right.
 * - Continue with state A.
 * If the current value is 1:
 * - Write the value 0.
 * - Move one slot to the right.
 * - Continue with state B.
 *
 * In state E:
 * If the current value is 0:
 * - Write the value 1.
 * - Move one slot to the left.
 * - Continue with state F.
 * If the current value is 1:
 * - Write the value 1.
 * - Move one slot to the left.
 * - Continue with state C.
 *
 * In state F:
 * If the current value is 0:
 * - Write the value 1.
 * - Move one slot to the right.
 * - Continue with state D.
 * If the current value is 1:
 * - Write the value 1.
 * - Move one slot to the right.
 * - Continue with state A.
 */
class TuringMachine {

    private final LinkedList<Integer> tape = new LinkedList<>();

    private State state = State.A;

    private int current = 0;

    TuringMachine() {
        tape.add(0);
    }

    private void setState(State state) {
        this.state = state;
    }

    private int getCurrentValue() {
        return tape.get(current);
    }

    private void writeValue(int value) {
        tape.set(current, value);
    }

    private void move(Direction direction) {
        current += direction.getDelta();
        ensureTapeSize();
    }

    private void ensureTapeSize() {
        if (current == tape.size()) {
            tape.add(0);
        } else if (current < 0){
            current = 0;
            tape.addFirst(0);
        }
    }

    void execute() {
        state.execute(this);
    }

    public long getDiagnosticsCheckSum() {
        return tape.stream().filter(i -> i == 1).count();
    }

    private enum State {

        A {
            @Override
            void execute(TuringMachine turingMachine) {
                if (turingMachine.getCurrentValue() == 0) {
                    turingMachine.writeValue(1);
                    turingMachine.move(Direction.RIGHT);
                    turingMachine.setState(TuringMachine.State.B);
                } else {
                    turingMachine.writeValue(0);
                    turingMachine.move(LEFT);
                    turingMachine.setState(TuringMachine.State.C);
                }
            }
        },

        B {
            @Override
            void execute(TuringMachine turingMachine) {
                if (turingMachine.getCurrentValue() == 0) {
                    turingMachine.writeValue(1);
                    turingMachine.move(LEFT);
                    turingMachine.setState(A);
                } else {
                    turingMachine.writeValue(1);
                    turingMachine.move(Direction.RIGHT);
                    turingMachine.setState(D);
                }
            }
        },

        C {
            @Override
            void execute(TuringMachine turingMachine) {
                if (turingMachine.getCurrentValue() == 0) {
                    turingMachine.writeValue(1);
                    turingMachine.move(Direction.RIGHT);
                    turingMachine.setState(A);
                } else {
                    turingMachine.writeValue(0);
                    turingMachine.move(LEFT);
                    turingMachine.setState(E);
                }
            }
        },

        D {
            @Override
            void execute(TuringMachine turingMachine) {
                if (turingMachine.getCurrentValue() == 0) {
                    turingMachine.writeValue(1);
                    turingMachine.move(Direction.RIGHT);
                    turingMachine.setState(A);
                } else {
                    turingMachine.writeValue(0);
                    turingMachine.move(Direction.RIGHT);
                    turingMachine.setState(B);
                }
            }
        },

        E {
            @Override
            void execute(TuringMachine turingMachine) {
                if (turingMachine.getCurrentValue() == 0) {
                    turingMachine.writeValue(1);
                    turingMachine.move(LEFT);
                    turingMachine.setState(F);
                } else {
                    turingMachine.writeValue(1);
                    turingMachine.move(LEFT);
                    turingMachine.setState(C);
                }
            }
        },

        F {
            @Override
            void execute(TuringMachine turingMachine) {
                if (turingMachine.getCurrentValue() == 0) {
                    turingMachine.writeValue(1);
                    turingMachine.move(Direction.RIGHT);
                    turingMachine.setState(D);
                } else {
                    turingMachine.writeValue(1);
                    turingMachine.move(Direction.RIGHT);
                    turingMachine.setState(A);
                }
            }
        };

        abstract void execute(TuringMachine turingMachine);
    }

    enum Direction {

        RIGHT(1), LEFT(-1);

        private final int delta;

        Direction(int delta) {
            this.delta = delta;
        }

        public int getDelta() {
            return delta;
        }

        public static Direction from(String direction) {
            return stream(values()).filter(value -> value.name().equalsIgnoreCase(direction))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }
}
