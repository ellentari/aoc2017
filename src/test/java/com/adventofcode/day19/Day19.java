package com.adventofcode.day19;

import com.adventofcode.AdventOfCodePuzzle;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Day19 implements AdventOfCodePuzzle<String, Integer> {

    private final List<String> lines;

    public Day19(List<String> lines) {
        this.lines = lines;
    }

    @Override
    public String solvePartOne() {
        int i = 0;
        int j = lines.get(i).indexOf('|');

        StringBuilder path = new StringBuilder();


        char currentDirection = 'd';

        while (true) {

            char current = safeGet(lines, i, j);

            if (current == ' ') {
                break;
            }

            if (current == '+') {
                Turn turn = turn(lines, i, j, currentDirection);

                i = turn.iD;
                j = turn.jD;

                currentDirection = turn.newDirection;
            } else {
                if (Character.isLetter(current)) {
                    System.out.print(current);
                    path.append(current);
                }
                switch (currentDirection) {
                    case 'd':
                        i++;
                        break;
                    case 'u':
                        i--;
                        break;
                    case 'l':
                        j--;
                        break;
                    case 'r':
                        j++;
                        break;
                }
            }
        }

        return path.toString();
    }

    public static Turn turn(List<String> lines, int i, int j, char currentDir) {
        if (safeGet(lines, i, j - 1) != ' ' && currentDir != 'r') {
            return new Turn(i, j-1, 'l');
        } else if (safeGet(lines, i, j + 1) != ' ' && currentDir != 'l') {
            return new Turn(i, j+1, 'r');
        } else if (safeGet(lines, i - 1, j) != ' ' && currentDir != 'd') {
            return new Turn(i-1, j, 'u');
        } else if (safeGet(lines, i + 1, j) != ' ' && currentDir != 'u') {
            return new Turn(i+1, j, 'd');
        }

        throw new IllegalArgumentException();
    }

    private static char safeGet(List<String> lines, int i, int j) {
        if (i < 0 || lines.size() <= i) {
            return ' ';
        } else if (j < 0 || lines.get(i).length() <= j) {
            return ' ';
        }

        return lines.get(i).charAt(j);
    }

    @Override
    public Integer solvePartTwo() {
        int i = 0;
        int j = lines.get(i).indexOf('|');

        StringBuilder path = new StringBuilder();

        Set<Turn> turns = new HashSet<>();

        int stepsCount = 0;


        char currentDirection = 'd';

        while (true) {

            char current = safeGet(lines, i, j);

            if (current == ' ') {
                break;
            }

            stepsCount++;
            if (current == '+') {
                Turn turn = turn(lines, i, j, currentDirection);

                i = turn.iD;
                j = turn.jD;

                currentDirection = turn.newDirection;
            } else {
                if (Character.isLetter(current)) {
                    System.out.print(current);
                    path.append(current);
                }
                switch (currentDirection) {
                    case 'd':
                        i++;
                        break;
                    case 'u':
                        i--;
                        break;
                    case 'l':
                        j--;
                        break;
                    case 'r':
                        j++;
                        break;
                }
            }
        }

        return stepsCount;
    }

    static class Turn {

        final int iD;
        final int jD;
        final char newDirection;

        Turn(int iD, int jD, char newDirection) {
            this.iD = iD;
            this.jD = jD;
            this.newDirection = newDirection;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Turn turn = (Turn) o;
            return iD == turn.iD &&
                    jD == turn.jD &&
                    newDirection == turn.newDirection;
        }

        @Override
        public int hashCode() {
            return Objects.hash(iD, jD, newDirection);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Turn{");
            sb.append("iD=").append(iD);
            sb.append(", jD=").append(jD);
            sb.append(", newDirection=").append(newDirection);
            sb.append('}');
            return sb.toString();
        }
    }
}
