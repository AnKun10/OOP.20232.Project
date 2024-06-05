package search;

import model.Edge;
import model.Node;
import graph.Graph;
import java.util.Stack;

public class DFS extends Search {

    public DFS(Graph graph, Node start, Node end) {
        super(graph, start, end);
    }

    @Override
    public void search() {
        Stack<Node> stack = new Stack<>();
        stack.add(start);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            path.add(current);
            if (current.equals(end)) {
                break;
            }
            for (Edge edge : graph.getEdges()) {
                if (edge.getStart().equals(current) && !path.contains(edge.getEnd())) {
                    stack.add(edge.getEnd());
                }
            }
        }
    }
}
