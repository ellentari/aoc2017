package com.adventofcode.common;

import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ParserImpl<T> implements Parser<T> {

    private final Pattern pattern;
    private final Function<Matcher, ? extends T> onMatchFound;

    ParserImpl(String regexp, Function<Matcher, ? extends T> onMatchFound) {
        this.pattern = Pattern.compile(regexp);
        this.onMatchFound = onMatchFound;
    }

    @Override
    public Optional<T> from(String input) {
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return Optional.of(onMatchFound.apply(matcher));
        }

        return Optional.empty();
    }



}
