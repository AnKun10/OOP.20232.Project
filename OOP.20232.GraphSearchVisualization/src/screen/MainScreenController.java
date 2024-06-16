package screen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import model.Graph;
import model.Node;
import search.Dijkstra;
import search.Search;

import java.io.IOException;
import java.util.List;

public class MainScreenController {

    @FXML
    private BorderPane rootLayout;
    private Graph graph;
    private GraphGenController graphGenController;
    private TraversalController traversalController;
    private List<Node> CurrentNode;

    @FXML
    private void initialize() {
        try {
            // Load GraphGen.fxml
            FXMLLoader graphLoader = new FXMLLoader(getClass().getResource("/screen/GraphGen.fxml"));
            AnchorPane graphPanel = graphLoader.load();
            graphGenController = graphLoader.getController();

            // Load Traversal.fxml
            FXMLLoader traversalLoader = new FXMLLoader(getClass().getResource("/screen/Traversal.fxml"));
            AnchorPane traversalPanel = traversalLoader.load();
            traversalController = traversalLoader.getController();

            // Set GraphGen.fxml to the top of rootLayout
            rootLayout.setTop(graphPanel);

            // Set Traversal.fxml to the bottom of rootLayout
            rootLayout.setBottom(traversalPanel);

            // Set up event handlers
            graphGenController.getGenerateButton().setOnAction(this::handleGenerateAction);
            traversalController.getNextButton().setOnAction(this::handleNextButtonAction);
            traversalController.getChoiceBox().setOnAction(event -> handleChoiceBoxAction());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading FXML files.");
        }
    }

    // Event handler for generate button
    private void handleGenerateAction(ActionEvent actionEvent) {
        graphGenController.handleGenerate();
        this.graph = graphGenController.graph;
        graphGenController.drawGraph(graph);
    }

    // Event handler for next button
    private void handleNextButtonAction(ActionEvent actionEvent) {
        if (CurrentNode != null) {
            for (Node node : CurrentNode) {
                graphGenController.drawColoredNode(String.valueOf(node.getValue()), 8.0, Color.BLUE);
            }
        }
    }

    // Event handler for choice box
    private void handleChoiceBoxAction() {
        String selectedOption = traversalController.getChoiceBox().getValue();
        if ("Dijkstra".equals(selectedOption)) {
            Search search = new Dijkstra(graph, graph.getNodes().get(0), graph.getNodes().get(4));
            Dijkstra dijkstraSearch = (Dijkstra) search; // Cast to Dijkstra
            this.CurrentNode = dijkstraSearch.getTraversedNodes(); // Get traversed nodes from Dijkstra
        }
        traversalController.setSelectedOption(selectedOption);
    }
}
