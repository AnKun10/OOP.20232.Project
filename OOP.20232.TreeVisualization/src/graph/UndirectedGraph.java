package graph;

import model.Edge;
import model.Node;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UndirectedGraph extends Graph{
    public UndirectedGraph(int numsNode) {
        super(numsNode);
    }

    @Override
    public void generateGraph(int numberOfNodes) {
        // Custom implementation if needed
    }

    @Override
    public void generateEdges() {
        Random random = new Random();
        Set<String> edgeSet = new HashSet<>();
        // Generate a Hamiltonian path
        for (int i = 0; i < numsNode - 1; i++) {
            edges.add(new Edge(random.nextInt(10) + 1, nodes.get(i), nodes.get(i + 1)));
            edgeSet.add(nodes.get(i).getValue() + "-" + nodes.get(i + 1).getValue());
            edges.add(new Edge(random.nextInt(10) + 1, nodes.get(i+1), nodes.get(i)));
            edgeSet.add(nodes.get(i + 1).getValue() + "-" + nodes.get(i).getValue());
        }
        // Add random edges
        int additionalEdges = random.nextInt(numsNode * (numsNode - 1) / 2 - (numsNode - 1) + 1);
        while (edgeSet.size() < numsNode - 1 + additionalEdges) {
            Node start = nodes.get(random.nextInt(numsNode));
            Node end = nodes.get(random.nextInt(numsNode));
            String edgeKey = start.getValue() + "-" + end.getValue();
            if (!start.equals(end) && !edgeSet.contains(edgeKey)) {
                edges.add(new Edge(random.nextInt(10) + 1, start, end));
                edgeSet.add(edgeKey);
                edges.add(new Edge(0, end, start));
                edgeKey = end.getValue() + "-" + start.getValue();
                edgeSet.add(edgeKey);
            }
        }
    }
}
