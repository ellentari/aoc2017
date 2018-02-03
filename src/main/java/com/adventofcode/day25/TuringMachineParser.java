package com.adventofcode.day25;

import com.adventofcode.common.Parsers;

class TuringMachineParser {

    TuringMachine fromBlueprint(String blueprint) {
        String initialState = Parsers.parserFor("Begin in state (\\w)", matcher -> matcher.group(1))
                .from(blueprint).orElseThrow(() -> new IllegalArgumentException("No initial state"));

        int steps = Parsers.parserFor("Perform a diagnostic checksum after (\\d+) steps.", matcher -> Integer.parseInt(matcher.group(1)))
                .from(blueprint).orElseThrow(() -> new IllegalArgumentException("No steps"));




        return new TuringMachine();
    }
}
