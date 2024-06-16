package search;

import model.Edge;
import model.Graph;
import model.Node;
import screen.TraversalController;

import java.util.*;

public class Dijkstra extends Search {
    private Set<Node> visitedNodes;
    private List<Node> traversedNodes = new ArrayList<>();
    private PriorityQueue<Node> traversalNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Integer> distances;
    private List<Map<String, String>> steps; // To store each step's state

    public Dijkstra(Graph graph, Node start, Node end) {
        super(graph, start, end);
        this.visitedNodes = new HashSet<>();
        this.predecessors = new HashMap<>();
        this.distances = new HashMap<>();
        this.steps = new ArrayList<>();
        this.traversalNodes = new PriorityQueue<>(Comparator.comparingInt(node -> distances.getOrDefault(node, Integer.MAX_VALUE)));
    }

    @Override
    public void search() {
        // Initialize distances
        for (Node node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        traversalNodes.add(start);

        while (!traversalNodes.isEmpty()) {
            Node current = traversalNodes.poll();
            traversedNodes.add(current);

            // Stop if we reach the end node
            if (current.equals(end)) {
                updatePath();
                recordStep(); // Record final step
                return;
            }

            if (!visitedNodes.add(current)) {
                continue;
            }

            // Traverse all adjacent nodes
            HashSet<Edge> adjEdges = graph.getAdjEdges(current);
            for (Edge edge : adjEdges) {
                Node neighbor = edge.getLinkedNode(current);
                int newDist = distances.get(current) + edge.getWeight();

                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    predecessors.put(neighbor, current);
                    traversalNodes.add(neighbor);
                }
            }
            recordStep(); // Record step after processing current node
        }

        System.out.println("End node not reachable from start node");
    }

    private void recordStep() {
        Map<String, String> step = new HashMap<>();
        for (Node node : graph.getNodes()) {
            step.put(String.valueOf(node.getValue()), String.format("Distance: %d, Previous: %s, Visited: %s",
                    distances.get(node),
                    predecessors.get(node) != null ? predecessors.get(node).getValue() : "None",
                    visitedNodes.contains(node)));
        }
        steps.add(step);
    }

    @Override
    protected void updatePath() {
        Node step = end;

        if (predecessors.get(step) == null) {
            System.out.println("No path found");
            return;
        }

        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        totalCost = distances.get(end);
    }

    public List<Node> getTraversedNodes() {
        return traversedNodes;
    }

    public List<Map<String, String>> getSteps() {
        return steps;
    }
}
