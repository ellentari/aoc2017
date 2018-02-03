package com.adventofcode.day16;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.common.Parser;
import com.adventofcode.common.Parsers;

import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.regex.Matcher;

/**
 * --- Day 16: Permutation Promenade ---
 *
 * You come upon a very unusual sight; a group of programs
 * here appear to be dancing.
 *
 * There are sixteen programs in total, named a through p.
 * They start by standing in a line: a stands in position 0, b
 * stands in position 1, and so on until p, which stands in position 15.
 *
 * The programs' dance consists of a sequence of dance moves:
 *
 * Spin, written sX, makes X programs move from the end to the front,
 * but maintain their order otherwise. (For example, s3 on abcde produces cdeab).
 * Exchange, written xA/B, makes the programs at positions A and B swap places.
 * Partner, written pA/B, makes the programs named A and B swap places.
 * For example, with only five programs standing in a line (abcde),
 * they could do the following dance:
 *
 * s1, a spin of size 1: eabcd.
 * x3/4, swapping the last two programs: eabdc.
 * pe/b, swapping programs e and b: baedc.
 * After finishing their dance, the programs end up in order baedc.
 *
 * You watch the dance for a while and record their dance moves (your puzzle input).
 * In what order are the programs standing after their dance?
 *
 * --- Part Two ---
 *
 * Now that you're starting to get a feel for the dance moves, you turn your attention
 * to the dance as a whole.
 *
 * Keeping the positions they ended up in from their previous dance, the programs
 * perform it again and again: including the first dance, a total of one billion (1000000000) times.
 *
 * In the example above, their second dance would begin with the order baedc,
 * and use the same dance moves:
 *
 * s1, a spin of size 1: cbaed.
 * x3/4, swapping the last two programs: cbade.
 * pe/b, swapping programs e and b: ceadb.
 * In what order are the programs standing after their billion dances?
 *
 */
public class Day16 implements AdventOfCodePuzzle<String, String> {

    private static final List<Parser<Move>> parsers = asList(
            Parsers.parserFor("s(\\d+)", Day16::spinFrom),
            Parsers.parserFor("x(\\d+)/(\\d+)", Day16::exchangeFrom),
            Parsers.parserFor("p(\\w)/(\\w)", Day16::partnerFrom)
    );

    private final String input;

    private final List<Move> moves;

    private final int times;

    Day16(String input, List<String> moves, int times) {
        this.input = input;
        this.times = times;
        this.moves = moves.stream().map(this::parseMove).collect(toList());
    }

    @Override
    public String solvePartOne() {
        return danceNonStop(1).finalPosition;
    }

    @Override
    public String solvePartTwo() {
        int dancesUntilCycle = danceUntil(times, DanceUntil::cycleEncountered).timesPerformed;

        return danceNonStop(calculateTail(dancesUntilCycle + 1)).finalPosition;
    }

    private DanceStopped danceNonStop(int maxTimes) {
        return danceUntil(maxTimes, DanceUntil::nonStop);
    }

    private DanceStopped danceUntil(int maxTimes, BiPredicate<String, String> stopCondition) {
        StringBuilder result = new StringBuilder(input);

        int i;
        for (i = 0; i < maxTimes; i++) {
            dance(result);

            if (stopCondition.test(input, result.toString())) {
                break;
            }
        }

        return new DanceStopped(i, result.toString());
    }

    private void dance(StringBuilder result) {
        moves.forEach(move -> move.doMove(result));
    }

    private int calculateTail(int cycleLength) {
        int cyclesCount = times / cycleLength;

        return times - cyclesCount * cycleLength;
    }

    private Move parseMove(String move) {
        return parsers.stream()
                .map(parser -> parser.from(move))
                .filter(Optional::isPresent)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(move))
                .orElseThrow(IllegalStateException::new);
    }

    private static Move spinFrom(Matcher matcher) {
        int times = Integer.parseInt(matcher.group(1));

        return new Spin(times);
    }

    private static Move exchangeFrom(Matcher matcher) {
        int first = Integer.parseInt(matcher.group(1));
        int second = Integer.parseInt(matcher.group(2));

        return new Exchange(first, second);
    }

    private static Move partnerFrom(Matcher matcher) {
        char first = matcher.group(1).charAt(0);
        char second = matcher.group(2).charAt(0);

        return new Partner(first, second);
    }

    static class DanceUntil {

        private DanceUntil() {

        }

        static boolean cycleEncountered(String initial, String current) {
            return initial.equals(current);
        }

        static boolean nonStop(String initial, String current) {
            return false;
        }
    }

    static class DanceStopped {

        final int timesPerformed;
        final String finalPosition;

        DanceStopped(int timesPerformed, String finalPosition) {
            this.timesPerformed = timesPerformed;
            this.finalPosition = finalPosition;
        }
    }
}
