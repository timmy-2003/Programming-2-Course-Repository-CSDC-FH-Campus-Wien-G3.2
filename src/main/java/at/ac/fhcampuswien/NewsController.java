package at.ac.fhcampuswien;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NewsController {

    @FXML
    private Label label2;

    public void initialize() {
        // TODO
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        label2.setText("Hello World!");
    }

}