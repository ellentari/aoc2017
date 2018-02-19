package com.adventofcode.day7.model;

public class UnbalancedNode {

    private final String name;
    private final int requiredWeight;

    public UnbalancedNode(String name, int requiredWeight) {
        this.name = name;
        this.requiredWeight = requiredWeight;
    }

    public String getName() {
        return name;
    }

    public int getRequiredWeight() {
        return requiredWeight;
    }
}
