package search;

import model.Edge;
import model.Node;
import graph.Graph;
import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Search {

    public BFS(Graph graph, Node start, Node end) {
        super(graph, start, end);
    }

    @Override
    public void search() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            path.add(current);
            if (current.equals(end)) {
                break;
            }
            for (Edge edge : graph.getEdges()) {
                if (edge.getStart().equals(current) && !path.contains(edge.getEnd())) {
                    queue.add(edge.getEnd());
                }
            }
        }
    }
}
