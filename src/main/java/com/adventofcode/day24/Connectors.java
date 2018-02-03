package com.adventofcode.day24;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

class Connectors {

    private final Map<Integer, Set<Connector>> connectorsByPorts;

    private Connectors(Map<Integer, Set<Connector>> connectorsByPorts) {
        this.connectorsByPorts = connectorsByPorts;
    }

    Set<Connector> getByPort(int port) {
        return Collections.unmodifiableSet(connectorsByPorts.getOrDefault(port, Collections.emptySet()));
    }

    static Connectors from(Collection<Connector> connectors) {
        return new Connectors(indexByPorts(connectors));
    }

    private static Map<Integer, Set<Connector>> indexByPorts(Collection<Connector> connectors) {
        Map<Integer, Set<Connector>> byPorts = new HashMap<>();

        for (Connector connector : connectors) {
            if (!byPorts.containsKey(connector.getPort1())) {
                byPorts.put(connector.getPort1(), new LinkedHashSet<>());
            }
            if (!byPorts.containsKey(connector.getPort2())) {
                byPorts.put(connector.getPort2(), new LinkedHashSet<>());
            }
            byPorts.get(connector.getPort1()).add(connector);
            byPorts.get(connector.getPort2()).add(connector);
        }

        return byPorts;
    }
}
