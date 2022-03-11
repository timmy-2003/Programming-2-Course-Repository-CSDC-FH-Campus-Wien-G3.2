package at.ac.fhcampuswien;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;



public class NewsController {
    Timer timer = new Timer();

    @FXML
    private Button exitButton;



    //needed: 4 BUTTONS
    // ELEMENT for "get top headlines austria || ELEMENT for "get all news btc" || LABEL or smth for display Count Articles || QUIT Program - maybe also button and Alert -> "quitting text"

    @FXML
    private Label label2;

    public void initialize() {
        // TODO
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        label2.setText("Hello World!");
    }


    @FXML
    public void exitApplication(){
        exitButton.setText("See you soon!");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    System.exit(0);
                });
            }
        }, 1000l);
    }

}