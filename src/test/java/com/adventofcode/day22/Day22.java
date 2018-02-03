package com.adventofcode.day22;

import static com.adventofcode.day22.Day22.State.CLEAN;
import static com.adventofcode.day22.Day22.State.FLAGGED;
import static com.adventofcode.day22.Day22.State.INFECTED;
import static com.adventofcode.day22.Day22.State.WEAKENED;

import com.adventofcode.AdventOfCodePuzzle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class Day22 implements AdventOfCodePuzzle<Integer, Integer> {

    private final int bursts;

    private final List<String> map;

    private final Map<Character, int[]> moves = new HashMap<Character, int[]>() {{
            put('u', new int[]{-1, 0});
            put('d', new int[]{1, 0});
            put('l', new int[]{0, -1});
            put('r', new int[]{0, 1});
    }};

    Day22(int bursts, List<String> map) {
        this.bursts = bursts;
        this.map = map;
    }

    @Override
    public Integer solvePartOne() {
        Grid grid = parseGrid();

        int i = map.size() / 2, j = i;
        char direction = 'u';

        int infectionsCaused = 0;

        for (int n = 0; n < bursts; n++) {

            direction = changeDirection(grid, i, j, direction);

            infectionsCaused += grid.affect(i, j, this::flip) == State.INFECTED ? 1 : 0;

            int[] next = moves.get(direction);

            i += next[0];
            j += next[1];
        }

        return infectionsCaused;
    }

    private Grid parseGrid() {
        Grid grid = new Grid();

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length(); j++) {
                grid.set(i, j, map.get(i).charAt(j) == '#' ? State.INFECTED : State.CLEAN);
            }
        }
        return grid;
    }

    private char changeDirection(Grid grid, int i, int j, char direction) {
        if (grid.get(i, j) == State.INFECTED) {
            return right(direction);
        } else {
            return left(direction);
        }
    }

    private State flip(State state) {
        return state == State.CLEAN ? State.INFECTED : State.CLEAN;
    }

    private State transit(State state) {
        switch (state) {
            case CLEAN: return WEAKENED;
            case WEAKENED: return INFECTED;
            case INFECTED: return FLAGGED;
            case FLAGGED: return CLEAN;
        }

        throw new IllegalArgumentException();
    }

    private char right(char direction) {
        switch (direction) {
            case 'u': return 'r';
            case 'r': return 'd';
            case 'd': return 'l';
            case 'l': return 'u';
        }
        throw new IllegalArgumentException();
    }

    private char left(char direction) {
        switch (direction) {
            case 'u': return 'l';
            case 'l': return 'd';
            case 'd': return 'r';
            case 'r': return 'u';
        }
        throw new IllegalArgumentException();
    }

    private char reverse(char direction) {
        switch (direction) {
            case 'u': return 'd';
            case 'l': return 'r';
            case 'd': return 'u';
            case 'r': return 'l';
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Integer solvePartTwo() {
        Grid grid = parseGrid();

        int i = map.size() / 2, j = i;
        char direction = 'u';

        int infectionsCaused = 0;

        for (int n = 0; n < bursts; n++) {

            direction = changeDirection2(grid, i, j, direction);

            infectionsCaused += grid.affect(i, j, this::transit) == State.INFECTED ? 1 : 0;

            int[] next = moves.get(direction);

            i += next[0];
            j += next[1];
        }

        return infectionsCaused;
    }

    private char changeDirection2(Grid grid, int i, int j, char direction) {
        State current = grid.get(i, j);

        switch (current) {
            case CLEAN: return left(direction);
            case WEAKENED: return direction;
            case INFECTED: return right(direction);
            case FLAGGED: return reverse(direction);
        }

        throw new IllegalArgumentException();
    }



    static class Grid {

        Map<Pair, State> grid = new HashMap<>();

        State affect(int i, int j, Function<State, State> transition) {
            return grid.merge(new Pair(i, j), State.CLEAN, (oldValue, value) -> transition.apply(oldValue));
        }


        void set(int i, int j, State state) {
            grid.put(new Pair(i, j), state);
        }

        State get(int i, int j) {
            return grid.computeIfAbsent(new Pair(i, j), pair -> State.CLEAN);
        }

    }

    enum State {
        INFECTED, CLEAN, FLAGGED, WEAKENED
    }

    static class Pair {
        final int i, j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return i == pair.i &&
                    j == pair.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }
}
