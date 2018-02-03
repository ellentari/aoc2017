package com.adventofcode.day21;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class RuleMapping {

    RulesMapping mapping = new RulesMapping();

    @Test
    public void simpleMappings() {
        mapping.add("../.#", "##./#../...");
        mapping.add(".#./..#/###", "#..#/..../..../#..#");

        assertThat(mapping.map("../.#")).isEqualTo("##./#../...");
        assertThat(mapping.map(".#./..#/###")).isEqualTo("#..#/..../..../#..#");
    }

    @Test
    public void mappingWithFlip() {
        mapping.add(".#./..#/###", ".#./..#/###");

        assertThat(mapping.map(".#./#../###")).isEqualTo(".#./..#/###");
    }

    @Test
    public void mappingWithRotation() {
        mapping.add(".#./..#/###", ".#./..#/###");

        assertThat(mapping.map("#../#.#/##.")).isEqualTo(".#./..#/###");
        assertThat(mapping.map("###/..#/.#.")).isEqualTo(".#./..#/###");
    }

    @Test
    public void rotate() {
        char[][] grid = {
                {'1', '2', '3'},
                {'4', '5', '6'},
                {'7', '8', '9'}
        };

        char[][] expectedRotated = {
                {'7', '4', '1'},
                {'8', '5', '2'},
                {'9', '6', '3'}
        };

        char[][] rotated = RulesMapping.rotate(grid);

        assertThat(rotated[0]).contains(expectedRotated[0]);
        assertThat(rotated[1]).contains(expectedRotated[1]);
        assertThat(rotated[2]).contains(expectedRotated[2]);
    }

    static String gridToString(char[][] grid) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                builder.append(grid[i][j]).append(' ');
            }
            builder.deleteCharAt(builder.length() - 1);
            builder.append('\n');
        }

        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }
}
