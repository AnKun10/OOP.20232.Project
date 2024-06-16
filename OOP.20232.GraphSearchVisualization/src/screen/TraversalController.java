package screen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class TraversalController {

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Button nextButton;

    private String selectedOption;

    @FXML
    public void initialize() {
        ObservableList<String> options = FXCollections.observableArrayList("Dijkstra", "BellmanFord");
        choiceBox.setItems(options);
        choiceBox.setValue("Search Algorithm"); // Set default value
    }

    public Button getNextButton() {
        return nextButton;
    }

    public ChoiceBox<String> getChoiceBox() {
        return choiceBox;
    }


    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
