package com.adventofcode.day7;

public class TokenVariable {

    private final String variable;
    private final Object value;

    TokenVariable(String variable, Object value) {
        this.variable = variable;
        this.value = value;
    }

    public String getVariable() {
        return variable;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TokenVariable{");
        sb.append("variable='").append(variable).append('\'');
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
