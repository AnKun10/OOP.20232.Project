package screen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import model.Edge;
import model.Graph;
import model.Node;
import search.Dijkstra;
import search.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainApp extends Application {

    private TextField numberOfNodesTextField;
    private TextField maxWeightTextField;
    private Button generateButton;
    private Button dijkstraButton;
    private Button nextButton;
    private ListView<String> waitingListView;
    private ListView<String> visitedListView;
    private Canvas graphCanvas;

    private Graph graph;
    private List<Node> currentNodeList;

    @Override
    public void start(Stage primaryStage) {
        BorderPane rootLayout = new BorderPane();

        // Top section for graph generation
        HBox topSection = new HBox(10);
        numberOfNodesTextField = new TextField();
        maxWeightTextField = new TextField();
        generateButton = new Button("Generate");
        dijkstraButton = new Button("Dijkstra");
        topSection.getChildren().addAll(new Label("Number of Nodes:"), numberOfNodesTextField,
                new Label("Max Weight:"), maxWeightTextField, generateButton, dijkstraButton);

        // Center section for graph canvas
        graphCanvas = new Canvas(800, 600);

        // Bottom section for traversal controls
        HBox bottomSection = new HBox(10);
        nextButton = new Button("Next");
        waitingListView = new ListView<>();
        visitedListView = new ListView<>();
        bottomSection.getChildren().addAll(new Label("Waiting:"), waitingListView,
                new Label("Visited:"), visitedListView, nextButton);

        rootLayout.setTop(topSection);
        rootLayout.setCenter(graphCanvas);
        rootLayout.setBottom(bottomSection);

        // Set up event handlers
        generateButton.setOnAction(event -> handleGenerateAction());
        dijkstraButton.setOnAction(event -> handleDijkstraAction());
        nextButton.setOnAction(event -> handleNextButtonAction());

        Scene scene = new Scene(rootLayout, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Graph Generator");
        primaryStage.show();
    }

    private void handleGenerateAction() {
        int numNodes = Integer.parseInt(numberOfNodesTextField.getText());
        int maxWeight = Integer.parseInt(maxWeightTextField.getText());
        graph = new Graph(numNodes, maxWeight);
        drawGraph(graph);
    }

    private void handleDijkstraAction() {
        if (graph != null) {
            Search search = new Dijkstra(graph, graph.getNodes().get(0), graph.getNodes().get(4));
            Dijkstra dijkstraSearch = (Dijkstra) search;
            currentNodeList = dijkstraSearch.getTraversedNodes();
            updateListView(waitingListView, currentNodeList);
        }
    }

    private void handleNextButtonAction() {
        if (currentNodeList != null && !currentNodeList.isEmpty()) {
            Node node = currentNodeList.remove(0);
            //drawColoredNode(String.valueOf(node.getValue()), 8.0, Color.BLUE);
            updateListView(waitingListView, currentNodeList);
            visitedListView.getItems().add(String.valueOf(node.getValue()));
        }
    }

    private void drawGraph(Graph graph) {
        GraphicsContext gc = graphCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, graphCanvas.getWidth(), graphCanvas.getHeight());

        double width = graphCanvas.getWidth();
        double height = graphCanvas.getHeight();

        ArrayList<Node> nodeList = graph.getNodes();
        int n = nodeList.size();
        double radius = 8;
        double centerX = width / 2;
        double centerY = height / 2;
        double angleStep = 137.5;
        double spiralFactor = 70;

        double[] x = new double[n];
        double[] y = new double[n];
        Node[] nodeArray = nodeList.toArray(new Node[0]);

        for (int i = 0; i < n; i++) {
            double angle = Math.toRadians(i * angleStep);
            double distance = spiralFactor * Math.sqrt(i);
            x[i] = centerX + distance * Math.cos(angle);
            y[i] = centerY + distance * Math.sin(angle);
        }

        gc.setStroke(Color.BLACK);
        gc.setFill(Color.BLACK);
        for (Edge edge : graph.getEdges()) {
            Set<Node> nodesSet = edge.getNodes();
            List<Node> nodesList = new ArrayList<>(nodesSet);
            Node node1 = nodesList.get(0);
            Node node2 = nodesList.get(1);

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
                gc.strokeLine(x[index1], y[index1], x[index2],y[index2]);

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
        }
    }



    private void updateListView(ListView<String> listView, List<Node> nodeList) {
        listView.getItems().clear();
        for (Node node : nodeList) {
            listView.getItems().add(String.valueOf(node.getValue()));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
