package com.adventofcode.day9;

import com.adventofcode.common.Pair;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class State {

    private LinkedList<Pair<Character, Sequence>> stack = new LinkedList<>();

    private Map<Sequence, Counter> nestings = new EnumMap<>(Sequence.class);
    private Map<Sequence, Counter> lengths = new EnumMap<>(Sequence.class);

    private List<Pair<Sequence, Integer>> scores = new LinkedList<>();

    private boolean ignoreNext;

    public void ignoreNext(boolean ignoreNext) {
        this.ignoreNext = ignoreNext;
    }

    public boolean isNextIgnored() {
        return ignoreNext;
    }

    public Groups groupsInfo() {
        return new Groups(
                (int) scores.stream().filter(p -> p.getLeft() == Sequence.GROUP).count(),
                scores.stream().filter(p -> p.getLeft() == Sequence.GROUP).mapToInt(Pair::getRight).sum(),
                lengths.getOrDefault(Sequence.GARBAGE, new Counter()).count);
    }

    public void startSequence(char character, Sequence sequence) {
        stack.push(new Pair<>(character, sequence));
    }

    public void pop() {
        stack.pop();
    }

    public void incNesting(Sequence sequence) {
        counter(sequence).inc();
    }

    public void decNesting(Sequence sequence) {
        counter(sequence).dec();
    }

    private Counter counter(Sequence sequence) {
        return nestings.computeIfAbsent(sequence, seq -> new Counter());
    }

    public void flush(Sequence sequence) {
        int score = counter(sequence).get();
        scores.add(new Pair<>(sequence, score));
    }

    public boolean isSequence(Sequence sequence) {
        return !stack.isEmpty() && getCurrentSequence() == sequence;
    }

    public void incLength() {
        length(getCurrentSequence()).inc();
    }

    private Sequence getCurrentSequence() {
        return stack.peek().getRight();
    }

    private Counter length(Sequence sequence) {
        return lengths.computeIfAbsent(sequence, seq -> new Counter());
    }

    public enum Sequence {
        GROUP, GARBAGE
    }

    private static class Counter {

        private int count;

        void inc() {
            count++;
        }

        int get() {
            return count;
        }

        void dec() {
            count--;
        }
    }
}
