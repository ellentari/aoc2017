package com.adventofcode.day18;

import java.util.HashMap;
import java.util.Map;

public class Registers {

    private final Map<Character, Long> registersMap = new HashMap<>();

    public long get(char register) {
        return registersMap.computeIfAbsent(register, reg -> 0L);
    }

    public void set(char register, long value) {
        registersMap.put(register, value);
    }
}
