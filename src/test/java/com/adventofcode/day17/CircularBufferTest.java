package com.adventofcode.day17;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class CircularBufferTest {

    private static final int STEPS = 3;

    private CircularBuffer circularBuffer = new CircularBuffer(0, 20);

    @Test
    public void stepAndInsert() {
        assertBufferSnapshot("(0)");

        stepAndInsert(STEPS, 1);
        assertBufferSnapshot("0 (1)");

        stepAndInsert(STEPS, 2);
        assertBufferSnapshot("0 (2) 1");

        stepAndInsert(STEPS, 3);
        assertBufferSnapshot("0  2 (3) 1");

        stepAndInsert(STEPS, 4);
        assertBufferSnapshot("0  2 (4) 3  1");

        stepAndInsert(STEPS, 5);
        assertBufferSnapshot("0 (5) 2  4  3  1");

        stepAndInsert(STEPS, 6);
        assertBufferSnapshot("0  5  2  4  3 (6) 1");

        stepAndInsert(STEPS, 7);
        assertBufferSnapshot("0  5 (7) 2  4  3  6  1");

        stepAndInsert(STEPS, 8);
        assertBufferSnapshot("0  5  7  2  4  3 (8) 6  1");

        stepAndInsert(STEPS, 9);
        assertBufferSnapshot("0 (9) 5  7  2  4  3  8  6  1");
    }

    private void stepAndInsert(int steps, int data) {
        circularBuffer.makeStepsAndInsert(steps, data);
    }

    private void assertBufferSnapshot(String snapshot) {
        assertThat(circularBuffer.buildSnapshot().split("\\s+"))
                .as("Unexpected buffer snapshot")
                .isEqualTo(snapshot.split("\\s+"));
    }
}