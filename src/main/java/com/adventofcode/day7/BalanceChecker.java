package com.adventofcode.day7;

import com.adventofcode.day7.model.BalanceCheck;
import com.adventofcode.day7.model.UnbalancedNode;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

class BalanceChecker {

    private final Tree.Node node;

    BalanceChecker(Tree.Node node) {
        this.node = node;
    }

    BalanceCheck check() {
        Map<String, BalanceCheck> childrenBalanceChecks = node.getChildren().stream()
                .collect(toMap(Tree.Node::getName, Tree.Node::checkBalance));

        return findUnbalancedNodeIn(childrenBalanceChecks.values())
                .orElseGet(() -> checkBalance(childrenBalanceChecks));
    }

    private Optional<BalanceCheck> findUnbalancedNodeIn(Collection<BalanceCheck> balanceChecks) {
        return balanceChecks.stream()
                .filter(check -> check.getUnbalancedNode().isPresent()).findAny();
    }

    private BalanceCheck checkBalance(Map<String, BalanceCheck> childrenBalanceChecks) {
        if (childrenBalanceChecks.isEmpty()) {
            return BalanceCheck.balanced(node.getWeight());
        }

        WeightsChecker weightsChecker = new WeightsChecker(node, childrenBalanceChecks);

        WeightsChecker.WeightsState weightsState = weightsChecker.determineWeightsState();

        switch (weightsState) {
            case ALL_WEIGHTS_SAME:
                return BalanceCheck.balanced(node.getWeight() + weightsChecker.getTotalWeight());

            case ONE_WEIGHT_DIFFERS:
                return BalanceCheck.unbalanced(weightsChecker.getUnbalancedNode());

            default:
                throw new IllegalStateException("Only one node is supposed to be unbalanced, but was: "
                        + weightsChecker.distinctWeightsCount());
        }
    }

    private static class WeightsChecker {

        private final Tree.Node node;

        private final Map<String, Integer> weights;
        private final Map<Integer, Long> weightsFrequencies;

        private WeightsChecker(Tree.Node node, Map<String, BalanceCheck> balanceChecks) {
            this.node = node;

            this.weights = balanceChecks.entrySet().stream()
                    .collect(toMap(Map.Entry::getKey, e -> e.getValue().getTotalWeight()));

            this.weightsFrequencies = this.weights.values().stream()
                    .collect(groupingBy(Function.identity(), counting()));
        }

        WeightsState determineWeightsState() {
            if (distinctWeightsCount() == 1) {
                return WeightsState.ALL_WEIGHTS_SAME;
            } else if (distinctWeightsCount() == 2) {
                return WeightsState.ONE_WEIGHT_DIFFERS;
            } else {
                return WeightsState.MORE_THAN_ONE_WEIGHT_DIFFER;
            }
        }

        int getTotalWeight() {
            return weights.values().stream().mapToInt(i -> i).sum();
        }

        int distinctWeightsCount() {
            return weightsFrequencies.size();
        }

        UnbalancedNode getUnbalancedNode() {
            WeightDiff weightDiff = getWeightDiff();

            String unbalancedName = weights.entrySet().stream()
                    .filter(e -> e.getValue() == weightDiff.actual)
                    .map(Map.Entry::getKey)
                    .findFirst().orElseThrow(() -> new IllegalStateException("no way3"));

            Tree.Node unbalancedNode = node.getChild(unbalancedName)
                    .orElseThrow(() -> new IllegalStateException("Child should have been found: " + unbalancedName));

            return new UnbalancedNode(unbalancedName, unbalancedNode.getWeight() + weightDiff.diff);
        }

        WeightDiff getWeightDiff() {
            int actualWeight = getWeightByFrequency(frequency -> frequency == 1L);
            int supposedWeight = getWeightByFrequency(frequency -> frequency > 1L);

            return new WeightDiff(supposedWeight, actualWeight);
        }

        private int getWeightByFrequency(Predicate<Long> frequencyPredicate) {
            return weightsFrequencies.entrySet().stream()
                    .filter(e -> frequencyPredicate.test(e.getValue()))
                    .mapToInt(Map.Entry::getKey)
                    .findFirst().orElseThrow(() -> new IllegalStateException("Weight with frequency should be present"));
        }

        enum WeightsState {
            ALL_WEIGHTS_SAME, ONE_WEIGHT_DIFFERS, MORE_THAN_ONE_WEIGHT_DIFFER
        }

        static class WeightDiff {
            final int required;
            final int actual;
            final int diff;

            WeightDiff(int required, int actual) {
                this.required = required;
                this.actual = actual;
                this.diff = required - actual;
            }
        }
    }
}
