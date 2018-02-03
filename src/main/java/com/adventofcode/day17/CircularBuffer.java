package com.adventofcode.day17;

import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

import java.util.HashMap;
import java.util.Map;

class CircularBuffer {

    private final int[] buffer;
    private int size;
    private int currentPosition;

    CircularBuffer(int initialValue, int capacity) {
        buffer = new int[capacity];

        currentPosition = 0;
        buffer[currentPosition] = initialValue;
        size++;

    }

    int getCurrentPosition() {
        return currentPosition;
    }

    int at(int position) {
        return buffer[circularIndexFor(position)];
    }

    void makeStepsAndInsert(int steps, int data) {
        makeSteps(steps);
        insert(data);
    }

    private void makeSteps(int numberFoSteps) {
        currentPosition = circularIndexFor(currentPosition + numberFoSteps);
    }

    private void insert(int data) {
        if (currentPosition + 1 == size) {
            currentPosition++;
            buffer[currentPosition] = data;
        } else {
            currentPosition = circularIndexFor(currentPosition + 1);
            shift(currentPosition);
            buffer[currentPosition] = data;
        }

        size++;
    }

    private void shift(int start) {
        System.arraycopy(buffer, start, buffer, start + 1, size - start);
    }

    private int circularIndexFor(int i) {
        return  i % size;
    }

    String buildSnapshot() {
        return range(0, size)
                .mapToObj(i -> i == currentPosition ? "(" + buffer[i] + ")" : String.valueOf(buffer[i]))
                .collect(joining(" "));
    }
}
