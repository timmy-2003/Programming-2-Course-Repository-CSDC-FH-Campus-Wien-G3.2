package at.ac.fhcampuswien.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class NewsControllerSplashScreen {

    @FXML
    private AnchorPane anchorpane;


    /***
     * threading starts so that the other GUI can load during splash screen is showing
     * call load method
     */
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

    /***
     * load the main GUI with a delay of 5 ms
     */
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

            //create the scene
            Stage stage = new Stage();
            Scene scene = new Scene(root, 1400, 800);
            stage.setScene(scene);

            //borderless UI
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setMinWidth(1400);
            stage.setMinHeight(800);
            stage.setMaximized(true);

            // setting the icon image
            Image icon = new Image(String.valueOf(getClass().getResource("/images/world-news.png")));
            stage.getIcons().add(icon);
            stage.show();

            //close the splash screen
            Stage stagesplashscreen = (Stage) anchorpane.getScene().getWindow();
            stagesplashscreen.close();
        });
    }
}
