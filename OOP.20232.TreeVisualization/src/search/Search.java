package search;

import graph.Graph;
import model.Node;
import java.util.ArrayList;

public abstract class Search {
    protected Graph graph;
    protected Node start;
    protected Node end;
    protected ArrayList<Node> path;

    public Search(Graph graph, Node start, Node end) {
        this.graph = graph;
        this.start = start;
        this.end = end;
        this.path = new ArrayList<>();
    }

    public abstract void search();

    public ArrayList<Node> getPath() {
        return path;
    }
}
