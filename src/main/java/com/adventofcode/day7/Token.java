package com.adventofcode.day7;

import java.util.Collections;
import java.util.List;

public class Token {

    private final List<TokenVariable> tokenVariables;

    public Token(List<TokenVariable> tokenVariables) {
        this.tokenVariables = Collections.unmodifiableList(tokenVariables);
    }

    public List<TokenVariable> getTokenVariables() {
        return tokenVariables;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Token{")
                .append("tokenVariables=")
                .append(tokenVariables)
                .append('}');
        return sb.toString();
    }
}
