package model;

public class Edge {
    private int weight;
    private Node start;
    private Node end;

    public Edge(int weight, Node start, Node end) {
        this.weight = weight;
        this.start = start;
        this.end = end;
    }

    public int getWeight() {
        return weight;
    }

    public Node getStart() {
        return start;
    }

    public Node getEnd() {
        return end;
    }
}
