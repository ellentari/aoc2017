package com.adventofcode.day15;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class JudgeTest {

    private Judge judge = new Judge(16);

    @Test
    public void different() {
        assertNotMatch(judge, 1092455, 430625591);
        assertNotMatch(judge, 1181022009, 1233683848);
        assertNotMatch(judge, 1744312007, 137874439);
        assertNotMatch(judge, 1352636452, 285222916);
    }

    @Test
    public void match() {
        assertMatch(judge, 245556042, 1431495498);
    }

    private void assertNotMatch(Judge judge, int first, int second) {
        assertMatchResult(judge, first, second, false);
    }

    private void assertMatch(Judge judge, int first, int second) {
        assertMatchResult(judge, first, second, true);
    }

    private void assertMatchResult(Judge judge, int first, int second, boolean expectedResult) {
        assertThat(judge.match(first, second)).isEqualTo(expectedResult);
    }
}
