package com.adventofcode.day24;

import static java.util.stream.Collectors.joining;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Bridge {

    private final Set<String> connectorsIds = new HashSet<>();
    private final Deque<Connector> connectors = new LinkedList<>();

    boolean hasElements() {
        return getSize() > 0;
    }

    int getSize() {
        return connectors.size();
    }

    int getStrength() {
        return connectors.stream().mapToInt(Connector::getStrength).sum();
    }

    boolean contains(Connector connector) {
        return connectorsIds.contains(connector.getId());
    }

    boolean canBeConnectedWith(Connector connector) {
        return getLast().canBeConnectedWith(connector);
    }

    void connectNext(Connector connector) {
        addNext(connectors.isEmpty() ? connector : getLast().connectWith(connector));
    }

    private void addNext(Connector connector) {
        connectors.addLast(connector);
        connectorsIds.add(connector.getId());
    }

    private Connector getLast() {
        return connectors.getLast();
    }

    int getLastPort() {
        return getLast().getPort2();
    }

    void removeLast() {
        Connector removed = connectors.removeLast();
        connectorsIds.remove(removed.getId());
    }

    String getSnapshot() {
        return connectors.stream().map(Connector::toString).collect(joining("--"));
    }
}
