package com.adventofcode.day7;

import com.adventofcode.day7.model.UnbalancedNode;
import org.junit.Test;

import java.util.Optional;

import static com.adventofcode.day7.TreeBuilderTest.node;
import static com.adventofcode.day7.TreeBuilderTest.tree;
import static org.assertj.core.api.Assertions.assertThat;

public class UnbalancedTreeTest {

    @Test
    public void findUnbalanced() {
        Tree tree = tree(
                node("not_ok_0_0", 0)
                        .addChildren(
                                node("ok_1_0", 8)
                                        .addChildren(
                                                node("ok_2_0", 1),
                                                node("ok_2_1", 1)
                                        ),

                                node("not_ok_1_1", 0)
                                        .addChildren(
                                                node("ok_2_2", 8)
                                                        .addChildren(
                                                                node("ok_3_0", 1),
                                                                node("ok_3_1", 1)
                                                        ),

                                                node("not_ok_2_3", 0)
                                                        .addChildren(
                                                                node("ok_3_2", 2),
                                                                node("ok_3_3", 2)
                                                        ),

                                                node("ok_2_4", 4)
                                                        .addChildren(
                                                                node("ok_3_4", 3),
                                                                node("ok_3_5", 3)
                                                        )
                                        ),

                                node("ok_1_2", 4)
                                        .addChildren(
                                                node("ok_2_5", 3),
                                                node("ok_2_6", 3)
                                        )
                        )
        );

        Optional<UnbalancedNode> unbalancedNode = tree.checkBalance();

        assertThat(unbalancedNode.isPresent()).isTrue();
        assertThat(unbalancedNode.get().getName()).isEqualTo("not_ok_2_3");
        assertThat(unbalancedNode.get().getRequiredWeight()).isEqualTo(6);
    }

}
