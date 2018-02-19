package com.adventofcode.day7;

import com.adventofcode.day7.model.BalanceCheck;
import com.adventofcode.day7.model.UnbalancedNode;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.asList;

public class Tree {

    private final Node root;

    Tree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree tree = (Tree) o;
        return Objects.equals(root, tree.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(root);
    }

    @Override
    public String toString() {
        return "Tree{" + "root=" + root + '}';
    }

    public Optional<UnbalancedNode> checkBalance() {
        return root.checkBalance().getUnbalancedNode();
    }

    public static class Node {

        private final String name;
        private final int weight;

        private final Set<Node> children = new LinkedHashSet<>();

        Node(String name, int weight) {
            this.name = name;
            this.weight = weight;
        }

        String getName() {
            return name;
        }

        int getWeight() {
            return weight;
        }

        Node addChild(Node child) {
            children.add(child);
            return this;
        }

        Node addChildren(Node... children) {
            this.children.addAll(asList(children));
            return this;
        }

        Set<Node> getChildren() {
            return children;
        }

        Optional<Tree.Node> getChild(String name) {
            return children.stream().filter(child -> name.equals(child.getName())).findFirst();
        }

        boolean isLeaf() {
            return children.isEmpty();
        }

        BalanceCheck checkBalance() {
            return new BalanceChecker(this)
                    .check();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return weight == node.weight &&
                    Objects.equals(name, node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, weight);
        }

        @Override
        public String toString() {
            return "Node{" + "name='" + name + '\'' +
                    ", weight=" + weight +
                    ", children=" + children +
                    '}';
        }
    }

}
