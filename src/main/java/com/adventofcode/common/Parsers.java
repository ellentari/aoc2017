package com.adventofcode.common;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;

public class Parsers {

    private Parsers() {
    }

    public static <T> Parser<T> parserFor(String regexp, Function<Matcher, ? extends T> onMatchFound) {
        return new ParserImpl<>(regexp, onMatchFound);
    }

    public static <T> List<T> parse(List<String> input, List<Parser<T>> parsers) {
        return input.stream()
                .map(instruction -> parse(instruction, parsers))
                .collect(toList());
    }

    public static <T> T parse(String input, List<Parser<T>> parsers) {
        return parsers.stream()
                .map(parser -> parser.from(input))
                .filter(Optional::isPresent)
                .findFirst()
                .orElseGet(Optional::empty)
                .orElseThrow(() -> new IllegalArgumentException(input));
    }
}
