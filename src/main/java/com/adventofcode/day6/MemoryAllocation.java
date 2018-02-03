package com.adventofcode.day6;

import java.util.Arrays;

class MemoryAllocation {

    private final int[] memory;

    MemoryAllocation(int[] memory) {
        this.memory = Arrays.copyOf(memory, memory.length);
    }

    MemoryAllocation reallocate() {
        return reallocate(findIndexToReallocateFrom());
    }

    private MemoryAllocation reallocate(int from) {
        int[] redistributed = Arrays.copyOf(memory, memory.length);

        int toRedistribute = redistributed[from];
        redistributed[from] = 0;

        int index = (from + 1) % redistributed.length;

        while (toRedistribute > 0) {
            toRedistribute--;
            redistributed[index]++;
            index = (index + 1) % redistributed.length;
        }

        return new MemoryAllocation(redistributed);
    }

    private int findIndexToReallocateFrom() {
        int indexWithMaxValue = 0;
        int maxValue = memory[0];

        for (int i = 0; i < memory.length; i++) {
            if (memory[i] > maxValue) {
                maxValue = memory[i];
                indexWithMaxValue = i;
            }
        }

        return indexWithMaxValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemoryAllocation that = (MemoryAllocation) o;
        return Arrays.equals(memory, that.memory);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(memory);
    }
}
