package graph;

import model.Node;
import model.Edge;
import java.util.ArrayList;

public abstract class Graph {
    protected ArrayList<Node> nodes;
    protected ArrayList<Edge> edges;

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
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
