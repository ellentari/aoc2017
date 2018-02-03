package com.adventofcode.day15;

import static java.util.stream.IntStream.generate;

import java.math.BigInteger;
import java.util.function.IntPredicate;

/**
 * To create its next value, a generator will take the previous value it produced,
 * multiply it by a factor of {@link Generator#factor} and then keep the remainder of
 * dividing that resulting product by {@link Generator#modulus}.
 *
 * Then generator will check whether the value meets certain criteria {@link Generator#valueCriteria} before returning it.
 * If not it will generate next using previously produced value (not meeting criteria) as next input.
 *
 * That final remainder is the value it produces next.
 * To calculate generator's first value some initial value is used.
 */
class Generator {

    private final BigInteger factor;
    private final BigInteger modulus;

    private final IntPredicate valueCriteria;

    private int last;

    Generator(int initialValue, int factor, int modulus, IntPredicate valueCriteria) {
        this.factor = BigInteger.valueOf(factor);
        this.modulus = BigInteger.valueOf(modulus);
        this.valueCriteria = valueCriteria;
        this.last = initialValue;
    }

    Generator(int initialValue, int factor, int modulus) {
        this(initialValue, factor, modulus, Values::all);
    }

    int generateNext() {
        int generated = generateNextMeetingCriteria();

        this.last = generated;

        return generated;
    }

    private int generateNextMeetingCriteria() {
        int[] previous = { last };

        return generate(() -> previous[0] = generateNext(previous[0]))
                .filter(valueCriteria)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private int generateNext(int from) {
        return BigInteger.valueOf(from).multiply(factor).mod(modulus).intValue();
    }
}
