package com.adventofcode.day9;

import com.adventofcode.day9.Checks.Check;
import com.adventofcode.day9.Commands.Command;

import java.util.Deque;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.adventofcode.day9.StreamCharactersReader.SequenceType.GROUP;
import static java.util.Collections.emptyList;

class StreamCharactersReader {

    private final Map<Check, Command> bindings = new LinkedHashMap<>();

    private final State state = new State();

    private StreamCharactersReader(Map<Check, Command> bindings) {
        this.bindings.putAll(bindings);
    }

    void accept(char character) {
        bindings.entrySet().stream()
                .filter(binding -> binding.getKey().appliesTo(state, character))
                .findFirst()

                .ifPresent(binding ->
                    binding.getValue().execute(state, character)
                );
    }

    GroupsStats getGroupsStats() {
        return state.getGroupsStats();
    }

    static Builder streamReader() {
        return new Builder();
    }

    enum SequenceType {
        GROUP, GARBAGE
    }

    static class Builder {

        private final Map<Check, Command> bindings = new LinkedHashMap<>();

        Builder bind(Check check, Command toCommand) {
            bindings.put(check, toCommand);
            return this;
        }

        StreamCharactersReader build() {
            return new StreamCharactersReader(bindings);
        }
    }

    static class State {

        private final Deque<SequenceType> sequenceStack = new LinkedList<>();

        private final Map<SequenceType, Integer> nestings = new EnumMap<>(SequenceType.class);
        private final Map<SequenceType, Integer> charsCounts = new EnumMap<>(SequenceType.class);

        private final Map<SequenceType, List<Integer>> scores = new EnumMap<>(SequenceType.class);

        private boolean ignoreNext;

        void ignoreNext() {
            this.ignoreNext = true;
        }

        void resetIgnoreNext() {
            this.ignoreNext = false;
        }

        boolean isNextIgnored() {
            return ignoreNext;
        }

        GroupsStats getGroupsStats() {
            return new GroupsStats(getGroupsScore(), getGarbageCharactersCount());
        }

        void startSequence(SequenceType seqType) {
            sequenceStack.push(seqType);
        }

        void endCurrentSequence() {
            sequenceStack.pop();
        }

        void incNestingOf(SequenceType seqType) {
            nestings.merge(seqType, 1, (oldVal, newVal) -> oldVal + 1);
        }

        void decNestingOf(SequenceType seqType) {
            nestings.computeIfPresent(seqType, (key, value) -> value - 1);
        }

        void flush(SequenceType seqType) {
            scores.computeIfAbsent(seqType, s -> new LinkedList<>())
                    .add(nestings.get(seqType));
        }

        boolean isCurrentSequence(SequenceType seqType) {
            return !sequenceStack.isEmpty() && getCurrentSequence() == seqType;
        }

        void incCurrentSequenceLength() {
            charsCounts.merge(getCurrentSequence(), 1, (oldVal, newVal) -> oldVal + 1);
        }

        private int getGroupsScore() {
            return scores.getOrDefault(GROUP, emptyList()).stream()
                    .mapToInt(i -> i)
                    .sum();
        }

        private int getGarbageCharactersCount() {
            return charsCounts.getOrDefault(SequenceType.GARBAGE, 0);
        }

        private SequenceType getCurrentSequence() {
            return sequenceStack.peek();
        }
    }
}
