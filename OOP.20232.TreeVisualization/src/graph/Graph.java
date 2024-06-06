package graph;

import model.Node;
import model.Edge;

import java.util.ArrayList;

public abstract class Graph {
    protected ArrayList<Node> nodes;
    protected ArrayList<Edge> edges;
    protected int numsNode;

    public Graph(int numsNode) {
        this.numsNode = numsNode;
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        generateNodes();
        generateEdges();
    }

    protected void generateNodes() {
        for (int i = 0; i < numsNode; i++) {
            nodes.add(new Node((char) ('a' + i)));
        }
    }


    protected boolean edgeExists(Node start, Node end) {
        for (Edge edge : edges) {
            if (edge.getStart().equals(start) && edge.getEnd().equals(end)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    protected abstract void generateEdges();
    protected abstract void generateGraph(int numberOfNodes);
}
