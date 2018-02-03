package com.adventofcode.day7;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Node {

    private final String name;
    private final int weight;

    private Node parent;
    private Set<Node> children = new LinkedHashSet<>();

    private Node(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public static Node of(String name, int weight) {
        return new Node(name, weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return weight == node.weight &&
                Objects.equals(name, node.name) &&
                Objects.equals(children, node.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, weight);
    }
}
