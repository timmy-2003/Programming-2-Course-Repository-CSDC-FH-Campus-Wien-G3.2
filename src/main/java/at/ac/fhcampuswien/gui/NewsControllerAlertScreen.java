package at.ac.fhcampuswien.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewsControllerAlertScreen {

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private ImageView imgViewExit;

    @FXML
    private ImageView imgviewAlert;

    @FXML
    private Label lblAlert;

    @FXML
    private Label lblAlertMessage;

    /***
     * set label for alert message
     * @param text
     */
    protected void setAlertMessage(String text){
        lblAlertMessage.setText(text);
    }

    /***
     * calls function exitWindow() when clicking on exit button
     * @param event
     */
    @FXML
    void exitWindow(MouseEvent event) {
        exitWindow();
    }

    /***
     * exit window
     */
    private void exitWindow() {
        Stage stage = (Stage) imgViewExit.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
