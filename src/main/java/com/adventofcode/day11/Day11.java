package com.adventofcode.day11;

import com.adventofcode.AdventOfCodePuzzle;

import java.util.List;

/**
 * --- Day 11: Hex Ed ---
 *
 * Crossing the bridge, you've barely reached the other side of the stream
 * when a program comes up to you, clearly in distress. "It's my child process,"
 * she says, "he's gotten lost in an infinite grid!"
 *
 * Fortunately for her, you have plenty of experience with infinite grids.
 *
 * Unfortunately for you, it's a hex grid.
 *
 * The hexagons ("hexes") in this grid are aligned such that adjacent hexes can
 * be found to the north, northeast, southeast, south, southwest, and northwest:
 *
 * \ n  /
 * nw +--+ ne
 * /    \
 * -+      +-
 * \    /
 * sw +--+ se
 * / s  \
 * You have the path the child process took. Starting where he started, you need
 * to determine the fewest number of steps required to reach him. (A "step" means
 * to move from the hex you are in to any adjacent hex.)
 *
 * For example:
 *
 * ne,ne,ne is 3 steps away.
 * ne,ne,sw,sw is 0 steps away (back where you started).
 * ne,ne,s,s is 2 steps away (se,se).
 * se,sw,se,sw,sw is 3 steps away (s,s,sw).
 *
 * --- Part Two ---
 *
 * How many steps away is the furthest he ever got from his starting position?
 */
public class Day11 implements AdventOfCodePuzzle<Integer, Integer> {

    private final List<String> path;

    Day11(List<String> path) {
        this.path = path;
    }

    @Override
    public Integer solvePartOne() {
        CubeCoordinate start = CubeCoordinate.from(0, 0, 0);
        CubeCoordinate end = followPath(start);

        return start.distanceTo(end);
    }

    private CubeCoordinate followPath(CubeCoordinate start) {
        CubeCoordinate current = start;

        for (String direction : path) {
            current = current.stepTo(direction);
        }

        return current;
    }

    @Override
    public Integer solvePartTwo() {
        return findFurthestDistance(CubeCoordinate.from(0, 0, 0));
    }

    private int findFurthestDistance(CubeCoordinate start) {
        int furthestDistance = 0;
        CubeCoordinate current = start;

        for (String direction : path) {
            current = current.stepTo(direction);

            int distance = start.distanceTo(current);

            if (furthestDistance < distance) {
                furthestDistance = distance;
            }
        }

        return furthestDistance;
    }

    private static class CubeCoordinate {

        final int y;
        final int x;
        final int z;

        private CubeCoordinate(int y, int x, int z) {
            this.y = y;
            this.x = x;
            this.z = z;
        }

        int distanceTo(CubeCoordinate to) {
            return (Math.abs(this.x - to.x) + Math.abs(this.y - to.y) + Math.abs(this.z - to.z)) / 2;
        }

        CubeCoordinate stepTo(String direction) {
            switch (direction) {
                case "n" : return CubeCoordinate.from(this.y + 1, this.x, this.z - 1);
                case "ne" : return CubeCoordinate.from(this.y, this.x + 1, this.z - 1);
                case "se" : return CubeCoordinate.from(this.y - 1, this.x + 1, this.z);
                case "s" : return CubeCoordinate.from(this.y - 1, this.x, this.z + 1);
                case "sw" : return CubeCoordinate.from(this.y, this.x - 1, this.z + 1);
                case "nw" : return CubeCoordinate.from(this.y + 1, this.x - 1, this.z);
                default: throw new IllegalArgumentException(direction);
            }
        }

        static CubeCoordinate from(int y, int x, int z) {
            return new CubeCoordinate(y, x, z);
        }
    }
}
