package com.adventofcode.day15;

import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import java.util.function.IntPredicate;

public class GeneratorTest {

    private static final int MOD = 2147483647;

    @Test
    public void generatorA() {
        assertGeneratedValues(generator(65, 16807),
                1092455, 1181022009, 245556042, 1744312007, 1352636452);
    }

    @Test
    public void generatorA_withCondition() {
        assertGeneratedValues(generator(65, 16807, Values::multipleOf4),
                1352636452, 1992081072, 530830436, 1980017072, 740335192);
    }

    @Test
    public void generatorB() {
        assertGeneratedValues(generator(8921, 48271),
                430625591, 1233683848, 1431495498, 137874439, 285222916);
    }

    @Test
    public void generatorB_withCondition() {
        assertGeneratedValues(generator(8921, 48271, Values::multipleOf8),
                1233683848, 862516352, 1159784568, 1616057672, 412269392);
    }

    private Generator generator(int initialValue, int factor) {
        return new Generator(initialValue, factor, MOD);
    }

    private Generator generator(int initialValue, int factor, IntPredicate condition) {
        return new Generator(initialValue, factor, MOD, condition);
    }

    private void assertGeneratedValues(Generator generator, int... expected) {
        int[] generated = range(0, expected.length)
                .map(i -> generator.generateNext())
                .toArray();

        assertThat(generated).containsExactly(expected);
    }
}