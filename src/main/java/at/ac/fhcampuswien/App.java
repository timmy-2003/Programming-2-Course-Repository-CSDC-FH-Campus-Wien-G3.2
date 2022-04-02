package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
        Menu menu = new Menu();
        menu.start();

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/newsappsplashscreen.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 600, 354);
        scene.setFill(Color.TRANSPARENT);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        //stage.setMinWidth(1400);
        //stage.setMinHeight(800);
        //stage.setMaximized(true);
        Image icon = new Image(String.valueOf(getClass().getResource("/images/world-news.png")));
        stage.getIcons().add(icon);
        stage.show();

        //center the splash screen into the mid
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}