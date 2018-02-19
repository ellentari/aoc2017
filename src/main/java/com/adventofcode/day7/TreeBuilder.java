package com.adventofcode.day7;

import com.adventofcode.common.Tuple;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

class TreeBuilder {

    private final TreeParser treeParser = new TreeParser();

    Tree buildFrom(List<String> treeDefinition) {
        List<NodeWithChildren> parsed = treeParser.parse(treeDefinition);

        return buildTree(parsed);
    }

    private Tree buildTree(List<NodeWithChildren> nodesWithChildren) {
        TreeAssembler assembler = new TreeAssembler(nodesWithChildren);

        return assembler.assemble();
    }

    private static class TreeParser {

        private static final Pattern NODE_PATTERN = Pattern.compile(
                "(?<name>\\w+) \\((?<weight>\\d+)\\)( -> (?<children>(\\w+,?\\s*)+))?"
        );

        List<NodeWithChildren> parse(List<String> treeDefinition) {
            return treeDefinition.stream()
                    .map(this::toNodeWithChildren)
                    .collect(toList());
        }

        private NodeWithChildren toNodeWithChildren(String line) {
            Matcher matcher = NODE_PATTERN.matcher(line);

            if (matcher.find()) {
                String name = matcher.group("name");
                int weight = Integer.parseInt(matcher.group("weight"));

                List<String> children = ofNullable(matcher.group("children"))
                        .map(childrenString -> asList(childrenString.split(", ")))
                        .orElseGet(Collections::emptyList);

                return new NodeWithChildren(name, weight, children);
            }

            throw new IllegalArgumentException("Not a node: " + line);
        }
    }

    private static class TreeAssembler {

        private final Map<String, NodeWithChildren> nodes;
        private final Map<String, String> childToParent;

        private final String root;

        private TreeAssembler(List<NodeWithChildren> nodesWithChildren) {
            this.nodes = nodesWithChildren.stream()
                    .collect(toMap(nc -> nc.name, identity()));

            this.childToParent = nodesWithChildren.stream()
                    .flatMap(nwc ->
                            nwc.children.stream().map(child -> new Tuple<>(child, nwc.name))
                    )
                    .collect(toMap(t -> t._1, t -> t._2));

            this.root = getRootName(this.nodes, this.childToParent);
        }

        private String getRootName(Map<String, NodeWithChildren> all, Map<String, String> childToParent) {
            Set<String> allNames = new HashSet<>(all.keySet());
            allNames.removeAll(childToParent.keySet());

            if (allNames.size() != 1) {
                throw new IllegalArgumentException("No root node: " + allNames);
            }

            return allNames.iterator().next();
        }

        Tree assemble() {
            Map<String, Tree.Node> treeNodes = new HashMap<>();

            treeNodes.put(root, createNode(root));

            childToParent.forEach((child, parent) -> {
                Tree.Node parentNode = treeNodes.computeIfAbsent(parent, this::createNode);
                Tree.Node childNode = treeNodes.computeIfAbsent(child, this::createNode);

                parentNode.addChild(childNode);
            });

            return new Tree(treeNodes.get(root));
        }

        private Tree.Node createNode(String name) {
            return new Tree.Node(name, nodes.get(name).weight);
        }
    }

    private static final class NodeWithChildren {

        final String name;
        final int weight;
        final List<String> children;

        NodeWithChildren(String name, int weight, List<String> children) {
            this.name = name;
            this.weight = weight;
            this.children = children;
        }
    }

}
