package model;

import java.util.HashSet;
import java.util.Set;

public class Edge {
    private int weight;
    private Set<Node> nodes;

    public Edge(int weight, Node n1, Node n2) {
        this.weight = weight;
        this.nodes = new HashSet<>();
        this.nodes.add(n1);
        this.nodes.add(n2);
    }

    public int getWeight() {
        return weight;
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public boolean contains(Node node) {
        return nodes.contains(node);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return nodes.equals(edge.nodes);
    }

    @Override
    public String toString() {
        return this.nodes + " - " + this.weight;
    }
}
