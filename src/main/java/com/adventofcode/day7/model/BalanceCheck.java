package com.adventofcode.day7.model;

import java.util.Optional;

public class BalanceCheck {

    private final int totalWeight;
    private final UnbalancedNode unbalancedNode;

    private BalanceCheck(int totalWeight, UnbalancedNode unbalancedNode) {
        this.totalWeight = totalWeight;
        this.unbalancedNode = unbalancedNode;
    }

    public Optional<UnbalancedNode> getUnbalancedNode() {
        return Optional.ofNullable(unbalancedNode);
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public static BalanceCheck balanced(int totalWeight) {
        return new BalanceCheck(totalWeight, null);
    }

    public static BalanceCheck unbalanced(UnbalancedNode unbalancedNode) {
        return new BalanceCheck(0, unbalancedNode);
    }
}
