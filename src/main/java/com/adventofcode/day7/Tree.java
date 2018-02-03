package com.adventofcode.day7;

import java.util.Objects;

public class Tree {

    private Node root;

    public Tree(Node root) {
        this.root = root;
    }

    public static Tree of(Node root) {
        return new Tree(root);
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
}
