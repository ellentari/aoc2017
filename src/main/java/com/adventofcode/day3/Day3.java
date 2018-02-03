package com.adventofcode.day3;

import com.adventofcode.AdventOfCodePuzzle;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Day3 implements AdventOfCodePuzzle<Integer, Integer> {

    private final int target;

    Day3(int target) {
        this.target = target;

    }

    @Override
    public Integer solvePartOne() {
        int lastNumberInSpiral = calculateLastNumberInSpiralOf(this.target);
        int sideLength = (int) Math.sqrt(lastNumberInSpiral);

        Coordinate end = findGridCoordinatesFor(this.target);
        Coordinate start = new Coordinate(sideLength / 2, sideLength / 2);

        return distance(start, end);
    }

    @Override
    public Integer solvePartTwo() {
        Map<Coordinate, Integer> grid = new HashMap<>();
        grid.put(new Coordinate(0, 0), 1);

        int x = 1;
        int y = 0;
        int lastCalculated = 1;

        int sideLength = 3;

        String direction = "up";

        while (lastCalculated <= target) {
            Integer top = grid.getOrDefault(new Coordinate(x, y + 1), 0);
            Integer bottom = grid.getOrDefault(new Coordinate(x, y - 1), 0);
            Integer left = grid.getOrDefault(new Coordinate(x - 1, y), 0);
            Integer right = grid.getOrDefault(new Coordinate(x + 1, y), 0);
            Integer topRight = grid.getOrDefault(new Coordinate(x + 1, y + 1), 0);
            Integer topLeft = grid.getOrDefault(new Coordinate(x - 1, y + 1), 0);
            Integer bottomRight = grid.getOrDefault(new Coordinate(x + 1, y - 1), 0);
            Integer bottomLeft = grid.getOrDefault(new Coordinate(x - 1, y - 1), 0);

            lastCalculated = top + bottom + left + right + topRight + topLeft + bottomRight + bottomLeft;

            grid.put(new Coordinate(x, y), lastCalculated);

            int maxIndexForSide = sideLength / 2;

            if (direction.equals("up")) {
                if (y == maxIndexForSide) {
                    direction = "left";
                    x--;
                } else {
                    y++;
                }
            } else if (direction.equals("left")) {
                if (x == -maxIndexForSide) {
                    direction = "down";
                    y--;
                } else {
                    x--;
                }
            } else if (direction.equals("down")) {
                if (y == -maxIndexForSide) {
                    direction = "right";
                    x++;
                } else {
                    y--;
                }
            } else {
                if (x == maxIndexForSide) {
                    direction = "up";
                    sideLength += 2;
                }
                x++;
            }
        }

        return lastCalculated;
    }

    private Coordinate findGridCoordinatesFor(int number) {
        if (number == 1) {
            return new Coordinate(0, 0);
        }

        int lastNumberInSpiral = calculateLastNumberInSpiralOf(number);
        int sideLength = (int) Math.sqrt(lastNumberInSpiral);
        int firstNumberInSpiral = (int) Math.pow((sideLength - 2), 2) + 1;

        int side = (number - firstNumberInSpiral) / (sideLength - 1);

        int x = 0;
        int y = 0;

        switch (side) {
            case 0: x = sideLength - 1; y = (sideLength - 2) - (number - firstNumberInSpiral); break;
            case 1: y = 0; x = (sideLength - 1) - (number - getFirstValueOfSide(sideLength, firstNumberInSpiral, side)); break;
            case 2: x = 0; y = number - getFirstValueOfSide(sideLength, firstNumberInSpiral, side); break;
            case 3: y = sideLength - 1; x = number - getFirstValueOfSide(sideLength, firstNumberInSpiral, side); break;
        }

        return new Coordinate(x, y);

    }

    private int getFirstValueOfSide(int sideLength, int firstNumberInSpiral, int side) {
        return firstNumberInSpiral + ((sideLength - 1) * side) - 1;
    }

    private int calculateLastNumberInSpiralOf(int number) {
        double sqrt = Math.sqrt(number);
        int intSqrt = (int) sqrt;

        if (sqrt == intSqrt && intSqrt % 2 != 0) {
            return number;
        } else {
            int nextOddSqrt = intSqrt + (intSqrt % 2 == 0 ? 1 : 2);

            return (int) Math.pow(nextOddSqrt, 2);
        }
    }

    private int distance(Coordinate p, Coordinate q) {
        return Math.abs(p.x - q.x) + Math.abs(p.y - q.y);
    }

    private static class Coordinate {
        final int x;
        final int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Coordinate{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append('}');
            return sb.toString();
        }
    }
}
