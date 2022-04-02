package at.ac.fhcampuswien;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NewsControllerSplashScreen {

    @FXML
    private AnchorPane anchorpane;


    @FXML
    void initialize() {
        try {
            new Thread(() -> {
                load();
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/newsapp.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage stage = new Stage();
            Scene scene = new Scene(root, 1400, 800);
            stage.setScene(scene);
            stage.setMinWidth(1400);
            stage.setMinHeight(800);
            stage.setMaximized(true);
            Image icon = new Image(String.valueOf(getClass().getResource("/images/world-news.png")));
            stage.getIcons().add(icon);
            stage.show();
            anchorpane.getScene().getWindow().hide();
        });
    }
}
