package at.ac.fhcampuswien;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;



public class NewsController {
    Timer timer = new Timer();

    @FXML
    private Button exitButton;

    @FXML
    private Button btnGetToplinesAustria;

    @FXML
    private TableView<?> tvNews;



    //needed: 4 BUTTONS
    // ELEMENT for "get top headlines austria || ELEMENT for "get all news btc" || LABEL or smth for display Count Articles || QUIT Program - maybe also button and Alert -> "quitting text"

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

    /***
     * test list for testing
     * @param event
     */
    @FXML
    void GetTopLinesAustria(ActionEvent event) {
        List<Article> testlist = new ArrayList<Article>();
        Article emp1 = new Article("Liam","20000");
        testlist.add(emp1);
        Article emp2 = new Article("Noah","2002300");
        testlist.add(emp2);
        Article emp3 = new Article("William","2011000");
        testlist.add(emp3);
        Article emp4 = new Article("James","31000");
        testlist.add(emp4);
        Article emp5 = new Article("Benjamin","2X0");
        testlist.add(emp5);


        /*tbv.getColumns().add(cl1);
        tbv.getColumns().add(cl2);
        for (Employee emp : employeeList){
            tbv.getItems().add(emp);
        }*/

    }

}