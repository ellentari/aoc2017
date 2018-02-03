package com.adventofcode.day25;

import com.adventofcode.common.Parser;
import com.adventofcode.common.Parsers;

import java.util.Collections;

class TMParsers {

    private TMParsers() {

    }

    static Parser<State> state() {
        return Parsers.parserFor("In state (?<stateName>\\w):\n" +
                        "(?<definitions>\\s*If.*?)+",
                matcher -> new State(matcher.group("stateName"), Collections.emptyList()));
    }


}
