package com.adventofcode.day25;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

class State {

    private final String name;
    private final List<Definition> definitions;

    State(String name, List<Definition> definitions) {
        this.name = name;
        this.definitions = definitions;
    }

    String getName() {
        return name;
    }

    void performOn(ITuringMachine turingMachine) {
        definitions.stream()
                .filter(definition -> definition.on.test(turingMachine))
                .findFirst()
                .ifPresent(definition -> definition.action.accept(turingMachine));
    }

    static class Definition {

        final Predicate<ITuringMachine> on;
        final Consumer<ITuringMachine> action;

        private Definition(DefinitionBuilder builder) {
            this.on = builder.on;
            this.action = builder.action;
        }

        static class DefinitionBuilder {

            Predicate<ITuringMachine> on;
            Consumer<ITuringMachine> action;

            private DefinitionBuilder (Predicate<ITuringMachine> predicate) {
                this.on = predicate;
            }

            Definition thenDo(Consumer<ITuringMachine> action) {
                this.action = action;
                return new Definition(this);
            }

            static DefinitionBuilder on(Predicate<ITuringMachine> predicate) {
                return new DefinitionBuilder(predicate);
            }

        }
    }

}
