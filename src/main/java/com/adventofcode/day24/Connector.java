package com.adventofcode.day24;

class Connector {

    private final String id;

    private final int port1;
    private final int port2;

    Connector(String id, int port1, int port2) {
        this.id = id;
        this.port1 = port1;
        this.port2 = port2;
    }

    String getId() {
        return id;
    }

    int getPort1() {
        return port1;
    }

    int getPort2() {
        return port2;
    }

    int getStrength() {
        return port1 + port2;
    }

    boolean canBeConnectedWith(Connector connector) {
        return port2 == connector.port1 || port2 == connector.port2;
    }

    Connector connectWith(Connector next) {
        if (!canBeConnectedWith(next)) {
            throw new IllegalArgumentException(this + " can not be connected to " + next);
        }

        return doConnectWith(next);
    }

    private Connector doConnectWith(Connector next) {
        if (port2 == next.port1) {
            return next;
        } else {
            return new Connector(next.id, next.port2, next.port1);
        }
    }

    @Override
    public String toString() {
        return port1 + "/" + port2;
    }
}
