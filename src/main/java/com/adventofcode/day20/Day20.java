package com.adventofcode.day20;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

import com.adventofcode.AdventOfCodePuzzle;
import com.adventofcode.common.Parser;
import com.adventofcode.common.Parsers;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;

public class Day20 implements AdventOfCodePuzzle<Integer, Integer> {

    private static final int LONG_TERM_COUNT = 500;

    private Parser<Particle> parser = Parsers.parserFor(
            "p=<(-?\\d+),(-?\\d+),(-?\\d+)>, v=<(-?\\d+),(-?\\d+),(-?\\d+)>, a=<(-?\\d+),(-?\\d+),(-?\\d+)>",
            this::particle
    );

    private final List<String> input;

    Day20(List<String> input) {
        this.input = input;
    }

    @Override
    public Integer solvePartOne() {
        List<Particle> particles = parse(input);

        ResultRecord<Integer> closestParticleNRecord = new ResultRecord<>();

        while (closestParticleNRecord.getRecordsCount() < LONG_TERM_COUNT) {
            tick(particles);
            Particle closest = getClosesParticle(particles);
            closestParticleNRecord.record(closest.n);
        }

        return closestParticleNRecord.get();
    }

    private Particle getClosesParticle(List<Particle> particles) {
        return particles.stream()
                .min(Comparator.comparing(p -> p.position.distanceFrom(XYZ.ZERO)))
                .orElseThrow(IllegalStateException::new);
    }

    private List<Particle> parse(List<String> input) {
        return range(0, input.size())
                .mapToObj(i -> parseParticle(i, input.get(i)))
                .collect(toList());
    }

    private Particle parseParticle(int i, String string) {
        return parser.from(string)
                .map(particle1 -> {
                    particle1.n = i;
                    return particle1;
                }).orElseThrow(IllegalArgumentException::new);
    }

    // p=<-1724,-1700,5620>, v=<44,-10,-107>, a=<2,6,-9>
    private Particle particle(Matcher matcher) {
        int px = intFromGroup(matcher, 1);
        int py = intFromGroup(matcher, 2);
        int pz = intFromGroup(matcher, 3);

        int vx = intFromGroup(matcher, 4);
        int vy = intFromGroup(matcher, 5);
        int vz = intFromGroup(matcher, 6);

        int ax = intFromGroup(matcher, 7);
        int ay = intFromGroup(matcher, 8);
        int az = intFromGroup(matcher, 9);

        return new Particle(XYZ.of(px, py, pz), XYZ.of(vx, vy, vz), XYZ.of(ax, ay, az));
    }

    private int intFromGroup(Matcher matcher, int n) {
        return Integer.parseInt(matcher.group(n));
    }

    @Override
    public Integer solvePartTwo() {
        List<Particle> particles = parse(input);

        ResultRecord<Integer> afterColisionsEliminationsCountRecord = new ResultRecord<>();

        while (afterColisionsEliminationsCountRecord.getRecordsCount() < LONG_TERM_COUNT) {
            tick(particles);
            eliminateCollisions(particles);
            afterColisionsEliminationsCountRecord.record(particles.size());
        }

        return afterColisionsEliminationsCountRecord.get();
    }

    private void tick(List<Particle> particles) {
        particles.forEach(Particle::tick);
    }

    private void eliminateCollisions(List<Particle> particles) {
        Map<XYZ, List<Particle>> positions = particles.stream().collect(groupingBy(Particle::getPosition));

        positions.entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach(e -> particles.removeAll(e.getValue()));
    }

    private static class Particle {

        int n;

        XYZ position;
        XYZ velocity;

        final XYZ acceleration;

        Particle(XYZ initialPosition, XYZ velocity, XYZ acceleration) {
            this.n = n;
            this.position = initialPosition;
            this.velocity = velocity;
            this.acceleration = acceleration;
        }

        void tick() {
            velocity = velocity.increaseBy(acceleration);
            position = position.increaseBy(velocity);
        }

        XYZ getPosition() {
            return position;
        }
    }

    private static class XYZ {

        static final XYZ ZERO = new XYZ(0, 0, 0);

        final long x;
        final long y;
        final long z;

        XYZ(long x, long y, long z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        XYZ increaseBy(XYZ xyz) {
            return new XYZ(x + xyz.x, y + xyz.y, z + xyz.z);
        }

        static XYZ of(long x, long y, long z) {
            return new XYZ(x, y, z);
        }

        long distanceFrom(XYZ xyz) {
            return Math.abs(x - xyz.x) + Math.abs(y - xyz.y) + Math.abs(z - xyz.z);
        }

        @Override
        public boolean equals(Object o) {

            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            XYZ xyz = (XYZ) o;
            return x == xyz.x &&
                    y == xyz.y &&
                    z == xyz.z;
        }

        @Override
        public int hashCode() {

            return Objects.hash(x, y, z);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("XYZ{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append(", z=").append(z);
            sb.append('}');
            return sb.toString();
        }
    }

    private static class ResultRecord<T> {

        private T t;
        private int countRecorded;

        int record(T result) {
            if (result.equals(t)) {
                countRecorded++;
            } else {
                t = result;
                countRecorded = 0;
            }

            return countRecorded;
        }

        public int getRecordsCount() {
            return countRecorded;
        }

        public T get() {
            return t;
        }
    }
}
