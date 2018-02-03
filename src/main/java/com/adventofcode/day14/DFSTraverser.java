package com.adventofcode.day14;

import java.util.function.IntPredicate;

class DFSTraverser {

    final int[][] grid;

    DFSTraverser(int[][] grid) {
        this.grid = grid;
    }

    int regionsCount(int regionValue) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (isToVisit(i, j, visited, isRegion(regionValue))) {
                    dfs(i, j, visited, isRegion(regionValue));
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int i, int j, boolean[][] visited, IntPredicate needsToBeVisited) {
        visited[i][j] = true;

        int[] iDiffs = {-1, 0, 0, 1};
        int[] jDiffs = {0, -1, 1, 0};

        for (int k = 0; k < iDiffs.length; k++) {
            int nextI = i + iDiffs[k];
            int nextJ = j + jDiffs[k];

            if (isSafe(nextI, nextJ) && isToVisit(nextI, nextJ, visited, needsToBeVisited)) {
                dfs(nextI, nextJ, visited, needsToBeVisited);
            }
        }
    }

    private boolean isSafe(int i, int j) {
        return withinBounds(i, grid.length) && withinBounds(j, grid[i].length);
    }

    private boolean withinBounds(int index, int upperLimit) {
        return index >= 0 && index < upperLimit;
    }

    private boolean isToVisit(int i, int j, boolean[][] visited, IntPredicate needsToBeVisited) {
        return !visited[i][j] && needsToBeVisited.test(grid[i][j]);
    }

    private static IntPredicate isRegion(int regionValue) {
        return value -> value == regionValue;
    }
}
