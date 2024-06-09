package search;

import model.Edge;
import model.Node;
import model.Graph;

import java.util.*;

public class BFS extends Search {

    private Set<Node> visitedNodes;
    private Queue<Node> traversalNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Integer> distances;

    public BFS(Graph graph, Node start, Node end) {
        super(graph, start, end);
        this.visitedNodes = new HashSet<>();
        this.traversalNodes = new LinkedList<>();
        this.predecessors = new HashMap<>();
        this.distances = new HashMap<>();
    }

    @Override
    public void search() {
        // Initialize distances
        for (Node node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        traversalNodes.add(start);
        visitedNodes.add(start);

        while (!traversalNodes.isEmpty()) {
            Node current = traversalNodes.poll();

            // Traverse all adjacent nodes
            HashSet<Edge> adjEdges = graph.getAdjEdges(current);
            for (Edge edge : adjEdges) {
                Node neighbor = edge.getLinkedNode(current);
                int newDist = distances.get(current) + edge.getWeight();

                if (!visitedNodes.contains(neighbor)) {
                    visitedNodes.add(neighbor);
                    traversalNodes.add(neighbor);
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, current);
                } else if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, current);
                }
            }
        }


        System.out.println("End node not reachable from start node");
    }
}
