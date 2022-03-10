package at.ac.fhcampuswien;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.start();
        launch(args);



    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World!");
        Button btn = new Button();
        btn.setText("Hello JavaFX!");
        btn.setOnAction( (event) -> Platform.exit() );
        Pane root = new StackPane();
        root.getChildren().add(btn);
        stage.setScene(new Scene(root, 300, 150));
        stage.show();
    }
}
