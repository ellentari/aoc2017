package com.adventofcode.day9;

import java.util.LinkedHashMap;
import java.util.Map;

class StreamReader {

    private Map<Checker, Command> commands = new LinkedHashMap<>();

    private State state = new State();

    void bind(Checker checker, Command command) {
        commands.put(checker, command);
    }

    void nextCharacter(char character) {
        commands.entrySet().stream()
                .filter(e -> e.getKey().appliesTo(state, character))
                .findFirst()
                .ifPresent(e -> {
                    e.getValue().execute(state, character);
                });

    }

    Groups groupsInfo() {
        return state.groupsInfo();
    }

}
