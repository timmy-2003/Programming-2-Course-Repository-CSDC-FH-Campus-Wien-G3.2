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

import java.io.IOException;

public class App extends Application {
    /***
     * main function launches application
     * @param args
     */
    public static void main(String[] args) throws IOException {
        //launch(args);
        Menu menu = new Menu();
        menu.start();
    }

    /***
     * starting the splash screen
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/newsappsplashscreen.fxml"));
        Parent root = loader.load();

        //create the scene
        Scene scene = new Scene(root, 600, 354);
        scene.setFill(Color.TRANSPARENT);
        stage.centerOnScreen();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);

        // setting the icon image
        Image icon = new Image(String.valueOf(getClass().getResource("/images/world-news.png")));
        stage.getIcons().add(icon);
        stage.show();

        //center the splash screen into the mid
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}