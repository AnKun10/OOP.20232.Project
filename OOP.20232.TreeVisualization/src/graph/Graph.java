package graph;

import model.Node;
import model.Edge;

import java.util.ArrayList;
import java.util.Random;

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

    private void generateNodes() {
        for (int i = 0; i < numsNode; i++) {
            nodes.add(new Node((char) ('a' + i)));
        }
    }

    private void generateEdges() {
        Random random = new Random();
        // Generate a Hamiltonian path
        for (int i = 0; i < numsNode - 1; i++) {
            edges.add(new Edge(random.nextInt(10) + 1, nodes.get(i), nodes.get(i + 1)));
        }
        // Add random edges
        int additionalEdges = random.nextInt(numsNode * (numsNode - 1) / 2 - (numsNode - 1) + 1);
        for (int i = 0; i < additionalEdges; i++) {
            Node start = nodes.get(random.nextInt(numsNode));
            Node end = nodes.get(random.nextInt(numsNode));
            if (!start.equals(end) && !edgeExists(start, end)) {
                edges.add(new Edge(random.nextInt(10) + 1, start, end));
            }
        }
    }

    private boolean edgeExists(Node start, Node end) {
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

    public abstract void generateGraph(int numberOfNodes);
}
