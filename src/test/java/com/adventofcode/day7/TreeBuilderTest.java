package com.adventofcode.day7;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class TreeBuilderTest {

    private TreeBuilder treeBuilder = new TreeBuilder();

    @Test
    public void onlyRoot() {
        List<String> treeDefinition = singletonList(
                "root (1)"
        );

        Tree tree = treeBuilder.buildFrom(treeDefinition);

        assertThat(tree)
                .isEqualToComparingFieldByField(
                        tree(node("root", 1))
                );
    }



    @Test
    public void withChildrenOneLevel() {
        List<String> treeDefinition = asList(
                "root (1) -> child_1_1, child_1_2",
                "child_1_1 (2)",
                "child_1_2 (3)"
        );

        Tree tree = treeBuilder.buildFrom(treeDefinition);

        assertThat(tree)
                .isEqualToComparingFieldByField(
                        tree(
                                node("root", 1).addChildren(
                                        node("child_1_1", 2),
                                        node("child_1_2", 3)
                                )
                        )
                );
    }

    @Test
    public void withChildrenTwoLevel() {
        List<String> treeDefinition = asList(
                "root (1) -> child_1_1, child_1_2",
                "child_1_1 (2) -> child_2_1",
                "child_1_2 (3) -> child_2_2",
                "child_2_1 (4)",
                "child_2_2 (5)"
        );

        Tree tree = treeBuilder.buildFrom(treeDefinition);

        assertThat(tree)
                .isEqualToComparingFieldByField(
                        tree(
                                node("root", 1)
                                        .addChild(
                                                node("child_1_1", 2)
                                                        .addChild(node("child_2_1", 4))
                                        )
                                        .addChild(
                                                node("child_1_2", 3)
                                                        .addChild(node("child_2_2", 5))
                                        )
                        )
                );
    }



    /**
     *  *              gyxo
     *             /
     *       ugml - ebii
     *      /      \
     *      |       jptl
     *      |
     *      |         pbga
     *      /        /
     * tknk --- padx - havc
     *      \        \
     *      |         qoyq
     *      |
     *      |        ktlj
     *      \      /
     *       fwft - cntj
     *            \
     *             xhth
     */
    @Test
    public void treeFromSample() {
        List<String> treeDefinition = asList(
                "pbga (66)",
                "xhth (57)",
                "ebii (61)",
                "havc (66)",
                "ktlj (57)",
                "fwft (72) -> ktlj, cntj, xhth",
                "qoyq (66)",
                "padx (45) -> pbga, havc, qoyq",
                "tknk (41) -> ugml, padx, fwft",
                "jptl (61)",
                "ugml (68) -> gyxo, ebii, jptl",
                "gyxo (61)",
                "cntj (57)"
        );

        Tree tree = treeBuilder.buildFrom(treeDefinition);

        assertThat(tree)
                .isEqualToComparingFieldByField(
                        tree(
                                node("tknk", 41).addChildren(
                                        node("ugml", 68).addChildren(
                                                node("gyxo", 61),
                                                node("ebii", 61),
                                                node("jptl", 61)
                                        ),
                                        node("padx", 45).addChildren(
                                                node("pbga", 66),
                                                node("havc", 66),
                                                node("qoyq", 66)
                                        ),
                                        node("fwft", 72).addChildren(
                                                node("ktlj", 57),
                                                node("cntj", 57),
                                                node("xhth", 57)
                                        )
                                )
                        )
                );
    }

    static Tree tree(Tree.Node root) {
        return new Tree(root);
    }

    static Tree.Node node(String name, int weight) {
        return new Tree.Node(name, weight);
    }
}
