package com.adventofcode.common;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BaseParser<T> implements Parser<T> {

    private final Pattern pattern;

    protected BaseParser(String regexp) {
        this.pattern = Pattern.compile(regexp);
    }

    @Override
    public Optional<T> from(String move) {
        Matcher matcher = pattern.matcher(move);

        if (matcher.find()) {
            return Optional.of(onMatchFound(matcher));
        }

        return Optional.empty();
    }

    protected abstract T onMatchFound(Matcher matcher);
}
