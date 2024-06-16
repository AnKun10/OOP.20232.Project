package screen;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Graph;
import model.Node;
import model.Edge;

import java.util.*;

public class GraphGenController {

    @FXML
    private Canvas graphCanvas;

    @FXML
    private Button generateButton;

    @FXML
    private TextField numberOfNodesTextField;

    @FXML
    private TextField maxWeightTextField;

    @FXML
    private Label generatedLabel;

    public Graph graph;
    public int NumNode;
    public int MaxWeight;

    private Map<String, double[]> nodeInfoMap = new HashMap<>();

    public Button getGenerateButton() {
        return generateButton;
    }

    public TextField getNumberOfNodesTextField() {
        return numberOfNodesTextField;
    }

    public TextField getMaxWeightTextField() {
        return maxWeightTextField;
    }

    public Canvas getGraphCanvas() {
        return graphCanvas;
    }

    public void drawGraph(Graph graph) {
        GraphicsContext gc = graphCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, graphCanvas.getWidth(), graphCanvas.getHeight());

        double width = graphCanvas.getWidth();
        double height = graphCanvas.getHeight();

        ArrayList<Node> nodeList = graph.getNodes();
        Set<Node> nodeSet = new HashSet<>(nodeList);
        Set<Edge> edgeList = graph.getEdges();

        int n = nodeSet.size();
        double radius = 8;
        double centerX = width / 2;
        double centerY = height / 2;
        double angleStep = 137.5;
        double spiralFactor = 70;

        double[] x = new double[n];
        double[] y = new double[n];
        Node[] nodeArray = nodeSet.toArray(new Node[0]);

        for (int i = 0; i < n; i++) {
            double angle = Math.toRadians(i * angleStep);
            double distance = spiralFactor * Math.sqrt(i);
            x[i] = centerX + distance * Math.cos(angle);
            y[i] = centerY + distance * Math.sin(angle);
        }

        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        for (Edge edge : edgeList) {
            Iterator<Node> it = edge.getNodes().iterator();
            Node node1 = it.next();
            Node node2 = it.next();

            int index1 = -1, index2 = -1;
            for (int i = 0; i < n; i++) {
                if (nodeArray[i].equals(node1)) {
                    index1 = i;
                }
                if (nodeArray[i].equals(node2)) {
                    index2 = i;
                }
                if (index1 != -1 && index2 != -1) break;
            }
            if (index1 != -1 && index2 != -1) {
                gc.strokeLine(x[index1], y[index1], x[index2], y[index2]);

                double weightX = (x[index1] + x[index2]) / 2;
                double weightY = (y[index1] + y[index2]) / 2;

                gc.setFill(Color.BLUE);
                gc.setFont(new Font("Arial", 12));
                gc.fillText(String.valueOf(edge.getWeight()), weightX, weightY);
                gc.setFill(Color.BLACK);
                gc.setFont(Font.getDefault());
            }
        }

        gc.setFill(Color.RED);
        for (int i = 0; i < n; i++) {
            gc.fillOval(x[i] - radius, y[i] - radius, radius * 2, radius * 2);
            gc.setFill(Color.BLACK);
            gc.setFont(new Font("Arial", 10));

            double labelX = x[i] - radius + 2;
            double labelY = y[i] + 4;
            gc.fillText(Character.toString(nodeArray[i].getValue()), labelX, labelY);
            gc.setFill(Color.RED);
            nodeInfoMap.put(String.valueOf(nodeArray[i].getValue()), new double[]{x[i], y[i], radius});
        }
    }

    public void drawColoredNode(String nodeName, double radius, Color color) {
        GraphicsContext gc = graphCanvas.getGraphicsContext2D();
        double[] coordinates = nodeInfoMap.get(nodeName);
        if (coordinates != null) {
            double x = coordinates[0];
            double y = coordinates[1];
            gc.setFill(color);
            gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
            gc.setFill(Color.BLACK);
            gc.setFont(new Font("Arial", 10));

            double labelX = x - radius + 2;
            double labelY = y + 4;
            gc.fillText(nodeName, labelX, labelY);
        } else {
            System.out.println("Node không tồn tại trong nodeInfoMap");
        }
    }

    public void handleGenerate() {
        this.NumNode = Integer.parseInt(this.getNumberOfNodesTextField().getText());
        this.MaxWeight = Integer.parseInt(this.getMaxWeightTextField().getText());
        this.graph = new Graph(NumNode, MaxWeight);
    }
}
