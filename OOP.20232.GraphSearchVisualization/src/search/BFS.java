package search;

import model.Edge;
import model.Node;
import model.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Search {

    private ArrayList<Node> visitedNodes;

    private Queue<Node> travesalNodes;

    public BFS(Graph graph, Node start, Node end) {
        super(graph, start, end);
        this.visitedNodes = new ArrayList<>();
    }

    @Override
    public void search() {
    }
}
