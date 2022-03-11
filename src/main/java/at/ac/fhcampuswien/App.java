package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
         launch(args);
        Menu menu = new Menu();
        menu.start();

    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/newsapp.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 1148, 725);
        stage.setScene(scene);
        stage.show();
    }
}
