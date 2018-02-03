package com.adventofcode.day6;

import java.util.HashMap;
import java.util.Map;

class CycleFinder {

    private final Map<MemoryAllocation, Integer> previouslyFound = new HashMap<>();

    private MemoryAllocation lastReAllocation;
    private int reAllocationsCounter;

    CycleFinder(int[] initialMemory) {
        lastReAllocation = new MemoryAllocation(initialMemory);
    }

    boolean foundCycle() {
        return previouslyFound.containsKey(lastReAllocation);
    }

    void nextReallocation() {
        if (foundCycle()) {
            throw new IllegalStateException("Cycle already found");
        }

        doNextReallocation();
    }

    private void doNextReallocation() {
        previouslyFound.put(lastReAllocation, reAllocationsCounter++);

        lastReAllocation = lastReAllocation.reallocate();
    }

    Cycle getFoundCycle() {
        if (!foundCycle()) {
            throw new IllegalStateException("Cycle not yet found");
        }

        return new Cycle(reAllocationsCounter, reAllocationsCounter - previouslyFound.get(lastReAllocation));
    }
}
